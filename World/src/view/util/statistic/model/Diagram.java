package view.util.statistic.model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;

import model.util.adt.list.SecureList;

public class Diagram extends Observable {


	private double [] values;
	
	private Color clr_line;
	
	
	/**
	 * @return the clr_line
	 */
	public Color getClr_line() {
		return clr_line;
	}

	/**
	 * @param clr_line the clr_line to set
	 */
	public synchronized void setClr_line(Color clr_line) {
		this.clr_line = clr_line;
	}
	
	public Diagram(final Color _clr_line,
			final int _historyLength) {
		
		//save values
		this.clr_line = _clr_line;
		this.values = new double[_historyLength];
		this.nextPositionInArray = 0;
		this.amountValues = 0;
	}
	
	public synchronized void addValue(int _newValue) {
		
		//save value
		values[nextPositionInArray] = _newValue;
		
		//increase position in values field and the amount of values saved in 
		//values array.
		increasePositionInField();
		increaseAmountValues();
		
		//compute BufferedImage
		
		//set changed etc.
	}
	
	
	
}

