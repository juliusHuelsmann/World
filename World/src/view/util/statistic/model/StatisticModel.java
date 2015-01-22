package view.util.statistic.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Observable;
import model.util.Status;
import model.util.adt.list.SecureList;
import model.util.adt.list.SecureListSort;

public class StatisticModel extends Observable {

	

	/**
	 * The next position in field indicates where to write the next-added value.
	 * 
	 * If the array is entirely filled, the 'next-position-in-field' contains
	 * the value from where to start drawing the diagram.
	 */
	private int nextPositionInArray = 0;
	
	/**
	 * Increase the amount of values that are currently saved inside the 
	 * diagram's values array.
	 * At the beginning of the displaying of a diagram, the array is not
	 * completely filled. Therefore this argument indicates the final 
	 * position of the values array which is printed to the diagram
	 * and one can calculate the current position of the points painted
	 * to BufferedImage.
	 */
	private int amountValues = 0;

	
	/**
	 * The length of the Diagram's history array that contains all the statistic
	 * values that are painted to the diagram.
	 */
	private int historyLength;
	
	/**
	 * List of diagrams handled by statistic model and sorted by id.
	 */
	private SecureListSort<Diagram> sls_diagrams;
	
	
	/**
	 * The current id of the next diagram. Is increased if a new diagram is 
	 * added to the statistics.
	 */
	private int currentIdDiagram;
	
	/**
	 * The BufferedImage to which the diagram classes paint their statistics.
	 */
	private BufferedImage bi_diagram;
	
	/**
	 * The id which is returned if an error occurs while trying to add a
	 * new diagram.
	 */
	public static final int ID_ERROR = -1;
	
	
	public StatisticModel(final Dimension _d_sizeDiagram,
			final int _historyLength) {
		
		//initialize list of diagrams
		this.sls_diagrams = new SecureListSort<Diagram>();

		//initialize the current position in array of the contained diagrams
		//and the amount of saved values with 0.
		this.nextPositionInArray = 0;
		this.amountValues = 0;
		this.historyLength = _historyLength;
		
		//initialize the BufferedImage with specified size.
		setImageSize(_d_sizeDiagram);
	}
	
	
	/**
	 * Add new diagram to the list of diagrams. 
	 * 
	 * If a fatal error occurs and it is not possible anymore to add a new
	 * diagram, the method logs a severe error and the ID_ERROR is returned 
	 * because no new diagram is added.
	 * 
	 * If an error occurs that can be solved, the method logs a severe error,
	 * but continues its work and returns a natural number (inside the integer
	 * scope) which is used as a unique identifier for the new added diagram.
	 * 
	 * In case a new diagram can be added, the color is applied to it and the
	 * observer is set for being able to display the data.
	 * 
	 * @param _clr 
	 * 			the color in which the diagram is printed.
	 * 
	 * @return
	 * 			the unique identifier of the newly-added in case of success,
	 * 			otherwise the ID_ERROR.
	 */
	public synchronized final int addDiagram(
			final Color _clr) {
		
		
		//if the list of the diagrams are not already initialized print error 
		//and re- initialize it for being able to proceed without further error.
		if (sls_diagrams == null) {
			
			//print error
			Status.getLogger().severe("Fatal error: The list of diagrams in"
					+ " statistic has been removed.");
			
			//re - initialize the list of diagrams.
			this.sls_diagrams = new SecureListSort<Diagram>();
		}
		
		//if the maximum amount of handled diagrams is reached (2 147 483 647)
		//no action is performed for avoiding integer-out-of-bounds-exception.
		//This error indicates an error in the external program which uses
		//the statistics class (because it is unusual to handle billions of 
		//diagrams graphically).
		if (currentIdDiagram == Integer.MAX_VALUE) {

			//print error
			Status.getLogger().severe("Fatal error: There are too many "
					+ "diagrams added to the statistic."
					+ " No action is performed for avoiding "
					+ "integer out of bounds exception.");
		
			//return error id because no diagram has been created.
			return ID_ERROR;
		}
		
		//start a new transaction without predecessor because the action
		//of adding a diagram is not handled by another process.
		final int transactionID = sls_diagrams.startTransaction("Add diagram", 
				SecureList.ID_NO_PREDECESSOR);
		
		//insert the new diagram to the list of diagrams and increase the 
		//id for being able to pass a unique id to the next created 
		//diagram.
		//the id is saved for maintaining integrity even if the current id is
		//saved by another process while the current diagram is added.
		final int id_newDiagram = currentIdDiagram;
		increaseCurrentDiagramID();
		
		//initialize diagram and set settings
		Diagram d_toAdd = new Diagram(_clr, historyLength);
		
		sls_diagrams.insertSorted(
				d_toAdd, 
				id_newDiagram, 
				transactionID);
		
		//finish the transaction because list operations for adding a new 
		//diagram are finished
		sls_diagrams.finishTransaction(transactionID);
		
		//return the id of the new - created diagram
		return id_newDiagram;
	}
	
	
	
	/**
	 * Increase the id which is passed to the next diagram.
	 */
	private synchronized void increaseCurrentDiagramID() {
		currentIdDiagram++;
	}
	
	
	/**
	 * Set color of specified diagram. Returns whether the operation finished
	 * successfully.
	 * 
	 * Otherwise an error is logged.
	 * 
	 * @param _id_diagramm
	 * 			the (unique) identifier of the diagram.
	 * 
	 * @param _clr_diagram
	 * 			the color which is to be set to the diagram with above 
	 * 			specified identifier.
	 * 
	 * @return 
	 *			whether the color has been set successfully.
	 */
	public boolean setColor(
			final int _id_diagramm, 
			final Color _clr_diagram) {

		
		//if the list of the diagrams are not already initialized print error 
		//and re- initialize it for being able to proceed without further error.
		if (sls_diagrams == null) {
			
			//print error
			Status.getLogger().severe("Fatal error: The list of diagrams in"
					+ " statistic has been removed. Therefor the diagram"
					+ " with the specified id is not containded by it."
					+ " The color can not be changed.");
			
			//re - initialize the list of diagrams.
			this.sls_diagrams = new SecureListSort<Diagram>();
			
			//return false because it is impossible that the demanded diagram
			//can be found in blank list
			return false;
		}

		
		//if the given id is the error id, something has gone wrong with adding
		//the diagram and the error has not been caught by the external
		//use of the statistics classes.
		if (_id_diagramm == ID_ERROR) {

			//print error
			Status.getLogger().severe("Fatal error: The passed id for changing "
					+ "diagram is an error id. Something has gone wrong with"
					+ "the creation of the diagram that is to be altered now."
					+ " The color can not be changed.");
		
			//return error id because no diagram has been created.
			return false;
		}
		
		
		
		//start a new transaction and closedAction without predecessor because 
		//the action of changing the color of a diagram is neither handled 
		//by an external process nor changing the state of the list.
		final int transactionID = sls_diagrams.startTransaction("set Color", 
				SecureList.ID_NO_PREDECESSOR);
		final int closedActionID = sls_diagrams.startClosedAction("set Color", 
				SecureList.ID_NO_PREDECESSOR);
		
		//fetch the demanded diagram out of sortedList
		Diagram found_diagram = sls_diagrams.searchSorted(
				_id_diagramm, transactionID, closedActionID);
		
		
		//perform action
		if (found_diagram != null) {
			found_diagram.setClr_line(_clr_diagram);
		}
		
		//finish the transaction and closedAction because list operations 
		//are finished
		sls_diagrams.finishTransaction(transactionID);
		sls_diagrams.finishClosedAction(closedActionID);
		
		//return the id of the new - created diagram
		return (found_diagram != null);
	}

	
	

	
	public synchronized boolean addingComplete() {

		//if the list of the diagrams are not already initialized print error 
		//and re- initialize it for being able to proceed without further error.
		if (sls_diagrams == null) {
			
			//print error
			Status.getLogger().severe("Fatal error: The list of diagrams in"
					+ " statistic has been removed. Therefor the diagram"
					+ " with the specified id is not containded by it."
					+ " The new point can not be added.");
			
			//re - initialize the list of diagrams.
			this.sls_diagrams = new SecureListSort<Diagram>();
			
			//return false because it is impossible that the demanded diagram
			//can be found in blank list
			return false;
		}


		setImageSize(new Dimension(bi_diagram.getWidth(),
				bi_diagram.getHeight()));

		//start a new transaction and closedAction without predecessor because 
		//the action of painting to BufferedImage is neither handled 
		//by an external process nor changing the state of the list.
		final int transactionID = sls_diagrams.startTransaction("notify", 
				SecureList.ID_NO_PREDECESSOR);
		final int closedActionID = sls_diagrams.startClosedAction("notify", 
				SecureList.ID_NO_PREDECESSOR);

		//pass the list
		sls_diagrams.toFirst(transactionID, closedActionID);
		while (!sls_diagrams.isEmpty() && !sls_diagrams.isBehind()) {
			
			if (sls_diagrams.getItem() != null) {
				
				
				//calculate the start position in array.
				final int startPositionInArray;
				if (amountValues == historyLength) {
					startPositionInArray = nextPositionInArray;
				} else {
					startPositionInArray = 0;
				}
				
				//paint diagram to the BufferedImage.
				sls_diagrams.getItem().paintEntirely(
						startPositionInArray, 
						amountValues, 
						bi_diagram);
			} else {
				Status.getLogger().warning("Empty diagram");
			}
			sls_diagrams.next(transactionID, closedActionID);
		}
		
		
		//finish the transaction and closedAction because list operations 
		//are finished
		sls_diagrams.finishTransaction(transactionID);
		sls_diagrams.finishClosedAction(closedActionID);
		
		
		
		
		increaseAmountValues();
		increasePositionInField();
		
		
		//set changed
		setChanged();
		notifyObservers(bi_diagram);
		
		
		return true;
	}


	/**
	 * Add new point to specified diagram. 
	 * 
	 * Returns whether the operation finished successfully.
	 * 
	 * Otherwise an error is logged.
	 * 
	 * @param _id_diagramm 
	 * 				the identifier of the specified diagram.
	 * 
	 * @param _newValue 
	 * 				the new value added to the specified diagram.
	 */
	public synchronized boolean addValue(
			final int _id_diagramm, 
			final int _newValue){
		
		//if the list of the diagrams are not already initialized print error 
		//and re- initialize it for being able to proceed without further error.
		if (sls_diagrams == null) {
			
			//print error
			Status.getLogger().severe("Fatal error: The list of diagrams in"
					+ " statistic has been removed. Therefor the diagram"
					+ " with the specified id is not containded by it."
					+ " The new point can not be added.");
			
			//re - initialize the list of diagrams.
			this.sls_diagrams = new SecureListSort<Diagram>();
			
			//return false because it is impossible that the demanded diagram
			//can be found in blank list
			return false;
		}

		
		//if the given id is the error id, something has gone wrong with adding
		//the diagram and the error has not been caught by the external
		//use of the statistics classes.
		if (_id_diagramm == ID_ERROR) {

			//print error
			Status.getLogger().severe("Fatal error: The passed id for changing "
					+ "diagram is an error id. Something has gone wrong with"
					+ "the creation of the diagram that is to be altered now."
					+ " The new point can not be added.");
		
			//return error id because no diagram has been created.
			return false;
		}
		
		
		
		//start a new transaction and closedAction without predecessor because 
		//the action of changing the color of a diagram is neither handled 
		//by an external process nor changing the state of the list.
		final int transactionID = sls_diagrams.startTransaction("add point", 
				SecureList.ID_NO_PREDECESSOR);
		final int closedActionID = sls_diagrams.startClosedAction("add point", 
				SecureList.ID_NO_PREDECESSOR);
		
		//fetch the demanded diagram out of sortedList
		Diagram found_diagram = sls_diagrams.searchSorted(
				_id_diagramm, transactionID, closedActionID);
		
		
		//perform action
		if (found_diagram != null) {
			found_diagram.addValue(nextPositionInArray, _newValue);
		}
		
		//finish the transaction and closedAction because list operations 
		//are finished
		sls_diagrams.finishTransaction(transactionID);
		sls_diagrams.finishClosedAction(closedActionID);
		
		//return the id of the new - created diagram
		return (found_diagram != null);
	}


	

	public synchronized void setImageSize(final Dimension _d_sizeDiagram) {

		//initialize the BufferedImage with specified size.
		this.bi_diagram = new BufferedImage(
				_d_sizeDiagram.width, _d_sizeDiagram.height, 
				BufferedImage.TYPE_INT_RGB);
		
		//initialize the array with 
		final int rgbWiht = new Color(255, 255, 255).getRGB();
		for (int x = 0; x < bi_diagram.getWidth(); x++) {
			for (int y = 0; y < bi_diagram.getHeight(); y++) {
				bi_diagram.setRGB(x, y, rgbWiht);
			}	
		}
	}
	
	/**
	 * Increase the amount of values that are currently saved inside the 
	 * values array.
	 * At the beginning of the displaying of a diagram, the array is not
	 * completely filled. Therefore this argument indicates the final 
	 * position of the values array which is printed to the diagram
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
	 * the value from where to start drawing the diagram.
	 */
	private synchronized void increasePositionInField() {
		nextPositionInArray = (nextPositionInArray + 1) % historyLength;
	}
}
