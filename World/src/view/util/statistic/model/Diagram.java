package view.util.statistic.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Diagram {


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
	}
	
	public synchronized void addValue(
			final int _id_in_array,
			final int _newValue) {
		
		//save value
		values[_id_in_array] = _newValue;
	}
	
	public synchronized void paintEntirely(
			
			final int _startLocationInArray,
			final int _amountOfValuesFilled,
			BufferedImage _bi) {
		
		
		//the size of one displayed value.
		//the size of the image is divided by the length of the array and
		//not by the amount of values filled because if the array has 
		//not been entirely filled, the painting has to start at the
		//right corner of the BufferedImage.
		final int displayWidthValue = _bi.getWidth() / values.length;
		final double displayHeightValue = 1;
		
		
		//the amount of pixel by which the drawing is shifted in x direction.
		final int shiftX = 
				
				//the amount of values which is not initialized yet
				(values.length - _amountOfValuesFilled)
				
				//multiplied by the size one value has got in BufferedImage
				* displayWidthValue;
		
		for (int i = 0; i < _amountOfValuesFilled; i++) {
			
			final int positionInArray = (i + _startLocationInArray) % values.length;
			
			int xInBufferedImage = shiftX + i;
			int yInBufferedImage = (int) (values[positionInArray]
					* displayHeightValue);
			
			if (xInBufferedImage < 0 || xInBufferedImage > _bi.getWidth()) {

				System.err.println("xcorrdinate err " + xInBufferedImage);
				if (xInBufferedImage < 0) {
					xInBufferedImage = 0;
				}
				if (xInBufferedImage >= _bi.getWidth()) {
					xInBufferedImage = _bi.getWidth() - 1;
				}
			}
			
			if (yInBufferedImage < 0 || yInBufferedImage >= _bi.getHeight()) {

				System.err.println("ycorrdinate err " + yInBufferedImage);				
				if (yInBufferedImage < 0) {
					yInBufferedImage = 0;
				}
				if (yInBufferedImage >= _bi.getHeight()) {
					yInBufferedImage = _bi.getHeight() - 1;
				}
			}

			_bi.setRGB(xInBufferedImage, yInBufferedImage, clr_line.getRGB());
			
		}
		
	}
	
	
	
}

