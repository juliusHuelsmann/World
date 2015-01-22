package view.util.statistic.model;

import java.awt.Color;
import java.awt.image.BufferedImage;


public class Line {


	private double [] values;
	
	private Color clr_line;
	
	private final static int ID_LINE = 0, ID_INTEGRAL = 1;
	private static final int linemode = ID_LINE;
	
	
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
	
	private int int_identifier;
	
	private String string_identifier;
	public Line(final Color _clr_line,
			final int _historyLength,
			final String _string_identifier,
			final int _int_id) {
		
		//save values
		this.clr_line = _clr_line;
		this.string_identifier = _string_identifier;
		this.int_identifier = _int_id;
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
		
		
		final int max = Math.max(Math.max(clr_line.getRed(), clr_line.getGreen()), clr_line.getBlue());
		final int clr_shadowRGB = new Color(
				clr_line.getRed() * 255 / max,clr_line.getGreen() * 255 / max,
				clr_line.getBlue() * 255 / max, 100).getRGB();
		
		//the size of one displayed value.
		//the size of the image is divided by the length of the array and
		//not by the amount of values filled because if the array has 
		//not been entirely filled, the painting has to start at the
		//right corner of the BufferedImage.
		final double displayWidthValue = 1.0 *_bi.getWidth() / values.length ;
		final double displayHeightValue = 1;
		
		//the amount of pixel by which the drawing is shifted in x direction.
		final int shiftX = 
				
				//the amount of values which is not initialized yet
				(int) ((values.length - _amountOfValuesFilled)
				
				//multiplied by the size one value has got in BufferedImage
				* displayWidthValue);
		
		
		int predecessorXinImage = -1,
				predecessoryinImage = -1;
		for (int i = 0; i < _amountOfValuesFilled; i++) {
			
			final int positionInArray = (i + _startLocationInArray) % values.length;
			
			int xInBufferedImage = shiftX + (int) (i * displayWidthValue);
			int yInBufferedImage = 
					_bi.getHeight() - 1 - 
					(int) (values[positionInArray]
					* displayHeightValue);
			
			if (xInBufferedImage < 0 || xInBufferedImage > _bi.getWidth()) {

//				System.err.println("xcorrdinate err " + xInBufferedImage);
				if (xInBufferedImage < 0) {
					xInBufferedImage = 0;
				}
				if (xInBufferedImage >= _bi.getWidth()) {
					xInBufferedImage = _bi.getWidth() - 1;
				}
			}
			
			if (yInBufferedImage < 0 || yInBufferedImage >= _bi.getHeight()) {

//				System.err.println("ycorrdinate err " + yInBufferedImage);				
				if (yInBufferedImage < 0) {
					yInBufferedImage = 0;
				}
				if (yInBufferedImage >= _bi.getHeight()) {
					yInBufferedImage = _bi.getHeight() - 1;
				}
			}


			if (predecessorXinImage != -1 && predecessoryinImage != -1) {
				paintLine(_bi, predecessorXinImage, xInBufferedImage, 
						predecessoryinImage, yInBufferedImage, 
						clr_line.getRGB(), clr_shadowRGB);
			}
			predecessorXinImage = xInBufferedImage;
			predecessoryinImage = yInBufferedImage;
			
		}
		
	}
	
	private void paintLine(
			final BufferedImage _bi,
			final int _x1, final int _x2, final int _y1, final int _y2,
			final int _clr, final int _clr2) {

		//compute delta values
		int dX = (int) (_x1 - _x2);
		int dY = (int) (_y1 - _y2);

        //print the line between the two points
        for (int a = 0; a < Math.max(Math.abs(dX), Math.abs(dY)); a++) {
            int plusX = a * dX /  Math.max(Math.abs(dX), Math.abs(dY));
            int plusY = a * dY /  Math.max(Math.abs(dX), Math.abs(dY));
            
            
            
            switch (linemode) {
            case ID_INTEGRAL:
            	for (int i = _bi.getHeight() - 2; i > _y1 - plusY; i -- ) {
            		
            		final Color clr_fetched = new Color(_bi.getRGB(_x1 - plusX,  i), true);
            		
            		if (
            				clr_fetched.getRed() == 255
            				&& clr_fetched.getGreen() == 255
            				&& clr_fetched.getBlue() == 255) {

            			_bi.setRGB(_x1 - plusX, i, _clr2);

                		final Color clr_fetched2 = new Color(_bi.getRGB(_x1 - plusX,  i), true);

            			final int alphaFetched = clr_fetched2.getAlpha();
            			
            			if (alphaFetched >= 255) {
            				System.out.println("alll");
            				System.exit(1);
            			}
            			
            		} else {
            			final Color clr_toSet = new Color(_clr2, true);
            			
            			final int alphaFetched = clr_fetched.getAlpha();
            			
            			if (alphaFetched < 255) {

                			final int alphatoSet = clr_toSet.getAlpha();
                			final double percAlphaTS = 1.0 * alphatoSet / (alphatoSet + alphaFetched);
                			final double percAlphaF = 1.0 * alphaFetched / (alphatoSet + alphaFetched);
                			
                			final int red = (int) (clr_toSet.getRed() * percAlphaTS + clr_fetched.getRed() * percAlphaF);
                			final int green = (int) (clr_toSet.getGreen() * percAlphaTS + clr_fetched.getGreen() * percAlphaF);
                			final int blue = (int) (clr_toSet.getBlue() * percAlphaTS + clr_fetched.getBlue() * percAlphaF);
                			final int alpha = Math.max(alphaFetched, alphatoSet);

                			
                			System.out.println("red" +red);
                			System.out.println("green" + green);
                			System.out.println("blue" + blue);
                			final Color clr = new Color(red, green, blue, alpha);
                			_bi.setRGB(_x1 - plusX, i, clr.getRGB());
            			}
            		} 
            		
//            		if ((i + _x1 - plusX) % 6 == (2 * int_identifier)
//            				&& (new Color(_bi.getRGB(_x1 - plusX,  i), true).getRed() == 255
//            				&& new Color(_bi.getRGB(_x1 - plusX,  i), true).getGreen() == 255
//            				&& new Color(_bi.getRGB(_x1 - plusX,  i), true).getBlue() == 255))
//            			_bi.setRGB(_x1 - plusX, i, _clr2);
            	}
            	
                _bi.setRGB(_x1 - plusX, _y1 - plusY, _clr);
            	break;
            case ID_LINE:

                _bi.setRGB(_x1 - plusX, _y1 - plusY, _clr);
                break;
                default:
                	break;
            }
        }
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return string_identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.string_identifier = identifier;
	}
	
	
	
	
	
}

