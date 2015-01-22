package view.util.statistic.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.lang.management.ManagementFactory;
import java.util.Observable;

import model.util.Status;
import model.util.adt.list.SecureList;
import model.util.adt.list.SecureListSort;

public class Diagram extends Observable {

	

	/**
	 * The next position in field indicates where to write the next-added value.
	 * 
	 * If the array is entirely filled, the 'next-position-in-field' contains
	 * the value from where to start drawing the line.
	 */
	private int nextPositionInArray = 0;
	
	/**
	 * Increase the amount of values that are currently saved inside the 
	 * line's values array.
	 * At the beginning of the displaying of a line, the array is not
	 * completely filled. Therefore this argument indicates the final 
	 * position of the values array which is printed to the line
	 * and one can calculate the current position of the points painted
	 * to BufferedImage.
	 */
	private int amountValues = 0;

	
	/**
	 * The length of the Line's history array that contains all the statistic
	 * values that are painted to the line.
	 */
	private int historyLength;
	
	/**
	 * List of lines handled by statistic model and sorted by id.
	 */
	private SecureListSort<Line> sls_lines;
	
	
	/**
	 * The current id of the next line. Is increased if a new line is 
	 * added to the statistics.
	 */
	private int currentIdLine;
	
	/**
	 * The BufferedImage to which the line classes paint their statistics.
	 */
	private BufferedImage bi_diagram;
	
	/**
	 * The id which is returned if an error occurs while trying to add a
	 * new diagram.
	 */
	public static final int ID_ERROR = -1;
	
	
	public Diagram(final Dimension _d_sizeDiagram,
			final int _historyLength) {
		
		//initialize list of diagrams
		this.sls_lines = new SecureListSort<Line>();

		//initialize the current position in array of the contained diagrams
		//and the amount of saved values with 0.
		this.nextPositionInArray = 0;
		this.amountValues = 0;
		this.historyLength = _historyLength;
		
		//initialize the BufferedImage with specified size.
		setImageSize(_d_sizeDiagram);
	}
	
	
	/**
	 * Add new line to the list of lines. 
	 * 
	 * If a fatal error occurs and it is not possible anymore to add a new
	 * line, the method logs a severe error and the ID_ERROR is returned 
	 * because no new line is added.
	 * 
	 * If an error occurs that can be solved, the method logs a severe error,
	 * but continues its work and returns a natural number (inside the integer
	 * scope) which is used as a unique identifier for the new added line.
	 * 
	 * In case a new line can be added, the color is applied to it and the
	 * observer is set for being able to display the data.
	 * 
	 * @param _clr 
	 * 			the color in which the line is printed.
	 * 
	 * @return
	 * 			the unique identifier of the newly-added in case of success,
	 * 			otherwise the ID_ERROR.
	 */
	public synchronized final int addLine(
			final Color _clr, final String _identifier) {
		
		
		//if the list of the lines are not already initialized print error 
		//and re- initialize it for being able to proceed without further error.
		if (sls_lines == null) {
			
			//print error
			Status.getLogger().severe("Fatal error: The list of lines in"
					+ " statistic has been removed.");
			
			//re - initialize the list of lines.
			this.sls_lines = new SecureListSort<Line>();
		}
		
		//if the maximum amount of handled lines is reached (2 147 483 647)
		//no action is performed for avoiding integer-out-of-bounds-exception.
		//This error indicates an error in the external program which uses
		//the statistics class (because it is unusual to handle billions of 
		//lines graphically).
		if (currentIdLine == Integer.MAX_VALUE) {

			//print error
			Status.getLogger().severe("Fatal error: There are too many "
					+ "lines added to the statistic."
					+ " No action is performed for avoiding "
					+ "integer out of bounds exception.");
		
			//return error id because no line has been created.
			return ID_ERROR;
		}
		
		//start a new transaction without predecessor because the action
		//of adding a line is not handled by another process.
		final int transactionID = sls_lines.startTransaction("Add line", 
				SecureList.ID_NO_PREDECESSOR);
		
		//insert the new line to the list of lines and increase the 
		//id for being able to pass a unique id to the next created 
		//line.
		//the id is saved for maintaining integrity even if the current id is
		//saved by another process while the current line is added.
		final int id_newLine = currentIdLine;
		increaseCurrentLineID();
		
		//initialize line and set settings
		Line d_toAdd = new Line(_clr, historyLength, _identifier, id_newLine);
		
		sls_lines.insertSorted(
				d_toAdd, 
				id_newLine, 
				transactionID);
		
		//finish the transaction because list operations for adding a new 
		//line are finished
		sls_lines.finishTransaction(transactionID);
		
		setChanged();
		notifyObservers(d_toAdd);
		
		//return the id of the new - created line
		return id_newLine;
	}
	
	
	
	/**
	 * Increase the id which is passed to the next line.
	 */
	private synchronized void increaseCurrentLineID() {
		currentIdLine++;
	}
	
	
	/**
	 * Set color of specified line. Returns whether the operation finished
	 * successfully.
	 * 
	 * Otherwise an error is logged.
	 * 
	 * @param _id_linem
	 * 			the (unique) identifier of the line.
	 * 
	 * @param _clr_line
	 * 			the color which is to be set to the line with above 
	 * 			specified identifier.
	 * 
	 * @return 
	 *			whether the color has been set successfully.
	 */
	public boolean setColor(
			final int _id_linem, 
			final Color _clr_line) {

		
		//if the list of the lines are not already initialized print error 
		//and re- initialize it for being able to proceed without further error.
		if (sls_lines == null) {
			
			//print error
			Status.getLogger().severe("Fatal error: The list of lines in"
					+ " statistic has been removed. Therefor the line"
					+ " with the specified id is not containded by it."
					+ " The color can not be changed.");
			
			//re - initialize the list of lines.
			this.sls_lines = new SecureListSort<Line>();
			
			//return false because it is impossible that the demanded line
			//can be found in blank list
			return false;
		}

		
		//if the given id is the error id, something has gone wrong with adding
		//the line and the error has not been caught by the external
		//use of the statistics classes.
		if (_id_linem == ID_ERROR) {

			//print error
			Status.getLogger().severe("Fatal error: The passed id for changing "
					+ "line is an error id. Something has gone wrong with"
					+ "the creation of the line that is to be altered now."
					+ " The color can not be changed.");
		
			//return error id because no line has been created.
			return false;
		}
		
		
		
		//start a new transaction and closedAction without predecessor because 
		//the action of changing the color of a line is neither handled 
		//by an external process nor changing the state of the list.
		final int transactionID = sls_lines.startTransaction("set Color", 
				SecureList.ID_NO_PREDECESSOR);
		final int closedActionID = sls_lines.startClosedAction("set Color", 
				SecureList.ID_NO_PREDECESSOR);
		
		//fetch the demanded line out of sortedList
		Line found_line = sls_lines.searchSorted(
				_id_linem, transactionID, closedActionID);
		
		
		//perform action
		if (found_line != null) {
			found_line.setClr_line(_clr_line);
		}
		
		//finish the transaction and closedAction because list operations 
		//are finished
		sls_lines.finishTransaction(transactionID);
		sls_lines.finishClosedAction(closedActionID);
		
		//return the id of the new - created line
		return (found_line != null);
	}

	
	

	
	public synchronized boolean addingComplete() {

		//if the list of the lines are not already initialized print error 
		//and re- initialize it for being able to proceed without further error.
		if (sls_lines == null) {
			
			//print error
			Status.getLogger().severe("Fatal error: The list of lines in"
					+ " statistic has been removed. Therefor the line"
					+ " with the specified id is not containded by it."
					+ " The new point can not be added.");
			
			//re - initialize the list of lines.
			this.sls_lines = new SecureListSort<Line>();
			
			//return false because it is impossible that the demanded line
			//can be found in blank list
			return false;
		}


		setImageSize(new Dimension(bi_diagram.getWidth(),
				bi_diagram.getHeight()));

		//start a new transaction and closedAction without predecessor because 
		//the action of painting to BufferedImage is neither handled 
		//by an external process nor changing the state of the list.
		final int transactionID = sls_lines.startTransaction("notify", 
				SecureList.ID_NO_PREDECESSOR);
		final int closedActionID = sls_lines.startClosedAction("notify", 
				SecureList.ID_NO_PREDECESSOR);

		//pass the list
		sls_lines.toFirst(transactionID, closedActionID);
		while (!sls_lines.isEmpty() && !sls_lines.isBehind()) {
			
			if (sls_lines.getItem() != null) {
				
				
				//calculate the start position in array.
				final int startPositionInArray;
				if (amountValues == historyLength) {
					startPositionInArray = nextPositionInArray;
				} else {
					startPositionInArray = 0;
				}
				
				//paint line to the BufferedImage.
				sls_lines.getItem().paintEntirely(
						startPositionInArray, 
						amountValues, 
						bi_diagram);
			} else {
				Status.getLogger().warning("Empty line");
			}
			sls_lines.next(transactionID, closedActionID);
		}
		
		
		//finish the transaction and closedAction because list operations 
		//are finished
		sls_lines.finishTransaction(transactionID);
		sls_lines.finishClosedAction(closedActionID);

		paintRastar(false);
		
		
		
		increaseAmountValues();
		increasePositionInField();
		
		
		//set changed
		setChanged();
		notifyObservers(bi_diagram);
		
		
		return true;
	}


	/**
	 * Add new point to specified line. 
	 * 
	 * Returns whether the operation finished successfully.
	 * 
	 * Otherwise an error is logged.
	 * 
	 * @param _id_linem 
	 * 				the identifier of the specified line.
	 * 
	 * @param _newValue 
	 * 				the new value added to the specified line.
	 */
	public synchronized boolean addValue(
			final int _id_linem, 
			final int _newValue){
		
		//if the list of the lines are not already initialized print error 
		//and re- initialize it for being able to proceed without further error.
		if (sls_lines == null) {
			
			//print error
			Status.getLogger().severe("Fatal error: The list of lines in"
					+ " statistic has been removed. Therefor the line"
					+ " with the specified id is not containded by it."
					+ " The new point can not be added.");
			
			//re - initialize the list of lines.
			this.sls_lines = new SecureListSort<Line>();
			
			//return false because it is impossible that the demanded line
			//can be found in blank list
			return false;
		}

		
		//if the given id is the error id, something has gone wrong with adding
		//the line and the error has not been caught by the external
		//use of the statistics classes.
		if (_id_linem == ID_ERROR) {

			//print error
			Status.getLogger().severe("Fatal error: The passed id for changing "
					+ "line is an error id. Something has gone wrong with"
					+ "the creation of the line that is to be altered now."
					+ " The new point can not be added.");
		
			//return error id because no line has been created.
			return false;
		}
		
		
		
		//start a new transaction and closedAction without predecessor because 
		//the action of changing the color of a line is neither handled 
		//by an external process nor changing the state of the list.
		final int transactionID = sls_lines.startTransaction("add point", 
				SecureList.ID_NO_PREDECESSOR);
		final int closedActionID = sls_lines.startClosedAction("add point", 
				SecureList.ID_NO_PREDECESSOR);
		
		//fetch the demanded line out of sortedList
		Line found_line = sls_lines.searchSorted(
				_id_linem, transactionID, closedActionID);
		
		
		//perform action
		if (found_line != null) {
			found_line.addValue(nextPositionInArray, _newValue);
		}
		
		//finish the transaction and closedAction because list operations 
		//are finished
		sls_lines.finishTransaction(transactionID);
		sls_lines.finishClosedAction(closedActionID);
		
		//return the id of the new - created line
		return (found_line != null);
	}


	

	public synchronized void setImageSize(final Dimension _d_sizeDiagram) {

		
		
		
		//initialize the BufferedImage with specified size.
		this.bi_diagram = new BufferedImage(
				_d_sizeDiagram.width, _d_sizeDiagram.height, 
				BufferedImage.TYPE_INT_ARGB);
		paintRastar(true);
	
		
	}
	
	public void paintRastar(boolean _paintWhite) {
		

		final int 
		amount_separations_horizontal = 4 - 1,
		amount_separations_vertical = 10 - 1;
		
		//initialize the array with 
		final int rgbWiht = new Color(255, 255, 255, 0).getRGB();
		final int rgbFilled = new Color(130, 120, 110).getRGB();
		for (int x = 0; x < bi_diagram.getWidth(); x++) {
			for (int y = 0; y < bi_diagram.getHeight(); y++) {

				final int height = bi_diagram.getHeight() - 1;
				final int width = bi_diagram.getWidth() - 1;

				
				//line at the right and left border
				if (x == 0 || x == width) {
					if (y  % 9 == 0 || (y + 1) % 9 == 0) {

						if(_paintWhite)
						bi_diagram.setRGB(x, y, rgbWiht);
					} else {

						bi_diagram.setRGB(x, y, rgbFilled);
					}
				} else if (y == 0 || y == bi_diagram.getHeight() - 1) {
					if (x % 9 == 0 || (x + 1) % 9 == 0) {

						if(_paintWhite)
						bi_diagram.setRGB(x, y, rgbWiht);
					} else {

						bi_diagram.setRGB(x, y, rgbFilled);
					}
				} else {
					
					// y separation lines (not border)
					if ((y) % (int) 
							(height / amount_separations_horizontal) == 0) {
						

						//if paint it (otherwise near to border line due to 
						//computation inaccuracy
						if (height - y > amount_separations_horizontal) {

							if (x % 5 == 0 || (x + 1) % 5 == 0) {

								if(_paintWhite)
								bi_diagram.setRGB(x, y, rgbWiht);
							} else {

								bi_diagram.setRGB(x, y, rgbFilled);
							}
						} else {

							if(_paintWhite)
							bi_diagram.setRGB(x, y, rgbWiht);
						}
						
					} else if ((x 
							//move the lines, too
							+ nextPositionInArray
							
							*bi_diagram.getWidth() / historyLength
							
							) % (int) 
							(width / amount_separations_vertical) == 0) {
						

						//if paint it (otherwise near to border line due to 
						//computation inaccuracy
						if (width - x > amount_separations_vertical) {

							if (y % 5 == 0 || (y + 1) % 5 == 0) {

								if(_paintWhite)
								bi_diagram.setRGB(x, y, rgbWiht);
							} else {

								bi_diagram.setRGB(x, y, rgbFilled);
							}
						} else {

							if(_paintWhite)
							bi_diagram.setRGB(x, y, rgbWiht);
						}
						
					} else {

						if(_paintWhite)
						bi_diagram.setRGB(x, y, rgbWiht);
					}
				}
			}	
		}
	}
	
	/**
	 * Increase the amount of values that are currently saved inside the 
	 * values array.
	 * At the beginning of the displaying of a line, the array is not
	 * completely filled. Therefore this argument indicates the final 
	 * position of the values array which is printed to the line
	 * and one can calculate the current position of the points painted
	 * to BufferedImage.
	 */
	private synchronized void increaseAmountValues() {
		amountValues = Math.min(amountValues + 1, historyLength);
	}

	
	/**
	 * The next position in field indicates where to write the next-added value.
	 * 
	 * If the array is entirely filled, the 'next-position-in-field' contains
	 * the value from where to start drawing the line.
	 */
	private synchronized void increasePositionInField() {
		nextPositionInArray = (nextPositionInArray + 1) % historyLength;
	}
}
