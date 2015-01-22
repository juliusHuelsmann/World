package util;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.IllegalComponentStateException;
import java.awt.Image;
import java.awt.Point;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import view.View;
import model.util.adt.list.List;


/**
 * Very unspecific utility methods.
 * @author Julius Huelsmann
 * @version %I%, %U%
 */
public final class Util {

	public static String EXECUTION_SUCCESS = "Success",
			EXECUTION_FAILED = "Failure";
	
    
    /**
     * Empty utility class constructor.
     */
    private Util() { }
    

    public static BufferedImage resize(
    		BufferedImage _bi, int _width, int _height) { 
	   
    	
    	Image img_scaled = _bi.getScaledInstance(
    			_width, 
    			_height, 
    			Image.SCALE_SMOOTH);
    	
	    BufferedImage bi = new BufferedImage(
	    		_width, 
	    		_height, 
	    		BufferedImage.TYPE_INT_ARGB);

	    Graphics2D
	    g2d = bi.createGraphics();
	    g2d.drawImage(img_scaled, 0, 0, null);
	    g2d.dispose();

	    return bi;
	}  

    public static BufferedImage resize(
    		String _bi, int _width, int _height) { 
	   
    	BufferedImage img_scaled;
		try {
			img_scaled = ImageIO.read(new File(_bi));
		    return resize(img_scaled, _width, _height);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	

	}  
    
    
    /**
     * Execute linux command in terminal and return the result.
     * 
     * @param _command the command that will be executed.
     * @return the answer of the linux command
     */
    public static String executeCommandLinux(final String _command) {
    	
    	
        Process p;
        try {
        	
        	//execute command
            p = Runtime.getRuntime().exec(_command);
            BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            
            //fetch answer
            String answer = "", s;
            while ((s = br.readLine()) != null) {
            	answer += s;
            }
            p.waitFor();
            
            //if normal termination
            if (p.exitValue() == 0) {
            	answer = EXECUTION_SUCCESS  + ": " + answer;
            } else {
            	answer = EXECUTION_FAILED + ": " + answer;
            }
            
            //destroy execution process and return the result
            p.destroy();
        	return answer;
        	
        } catch (Exception e) {
        	
        	//print stack trace and return the failure message.
        	e.printStackTrace();
        	return EXECUTION_FAILED;
        }
    }
    
    
    
    /**
     * return list of strings from a text file.
     * 
     * @param _path the path of the text file
     * @return list of lines in text file
     */
    public static List<String> loadTextFile(String _path) {

    	List<String> ls_strings = new List<String>();
        try {
	        BufferedReader br = new BufferedReader(new FileReader(_path));
	        String line = br.readLine();
	
	            while (line != null) {
	            	ls_strings.insertBehind(line);
	                line = br.readLine();
	            }
				br.close();
				return ls_strings;
		} catch (IOException e) {
			e.printStackTrace();
	        return null;
		}
    }
    
    
    



    /**
     * Apply stroke on background.
     * @param jlbl_stroke the background carrying item.
     */
	public static void getStrokeGraphics(JLabel jlbl_stroke) {
		
		
		final int strokeDistance = 10;
		if (jlbl_stroke.getWidth() > 0 
				&& jlbl_stroke.getHeight() > 0
//				&& jlbl_stroke.isShowing()
				) {
			BufferedImage bi_stroke = new BufferedImage(jlbl_stroke.getWidth(), 
	        		jlbl_stroke.getHeight(), BufferedImage.TYPE_INT_ARGB);
	        
	        for (int x = 0; x < bi_stroke.getWidth(); x ++) {

	            for (int y = 0; y < bi_stroke.getHeight(); y ++) {

//	            	if ( (_addX + x + y +  _addY) % 20 == 0) {
	            	
	            	try{
	            		if ( (x + jlbl_stroke.getLocationOnScreen().x
		            			+ y + jlbl_stroke.getLocationOnScreen().y) 
		            			% strokeDistance == 0) {

		                	bi_stroke.setRGB(x, y, new Color(10,10,10, 20).getRGB());
		            	} else {

		                	bi_stroke.setRGB(x, y, new Color(250, 255, 252).getRGB());
		            	}
	            	} catch (IllegalComponentStateException e) {
	            		x = bi_stroke.getWidth();
	            		y = bi_stroke.getHeight();
	            	}
	            
	            }	
	        }
	        jlbl_stroke.setIcon(new ImageIcon(bi_stroke));
		} else {
			System.out.println("error");
		}
		
		
	} 
    /**
     * Apply stroke on background.
     * @param jlbl_stroke the background carrying item.
     */
	public static void getStroke(JLabel jlbl_stroke, int _addX, int _addY) {
		
		
		final int strokeDistance = 10;
		if (jlbl_stroke.getWidth() > 0 
				&& jlbl_stroke.getHeight() > 0
//				&& jlbl_stroke.isShowing()
				) {
			BufferedImage bi_stroke = new BufferedImage(jlbl_stroke.getWidth(), 
	        		jlbl_stroke.getHeight(), BufferedImage.TYPE_INT_ARGB);
	        
	        for (int x = 0; x < bi_stroke.getWidth(); x ++) {

	            for (int y = 0; y < bi_stroke.getHeight(); y ++) {

//	            	if ( (_addX + x + y +  _addY) % 20 == 0) {
	            	
	            	try{
	            		if ( (x + jlbl_stroke.getLocationOnScreen().x
		            			+ y + jlbl_stroke.getLocationOnScreen().y) 
		            			% strokeDistance == 0) {

		                	bi_stroke.setRGB(x, y, new Color(10,10,10, 20).getRGB());
		            	} else {

		                	bi_stroke.setRGB(x, y, new Color(238, 235, 229).getRGB());
		                	bi_stroke.setRGB(x, y, new Color(229, 227, 226).getRGB());
		            	}
	            	} catch (IllegalComponentStateException e) {
	            		x = bi_stroke.getWidth();
	            		y = bi_stroke.getHeight();
	            	}
	            
	            }	
	        }
	        jlbl_stroke.setIcon(new ImageIcon(bi_stroke));
		} else {
			System.out.println("error");
		}
		
		
	}   /**
     * Apply stroke on background.
     * @param jlbl_stroke the background carrying item.
     */
	public static void getStrokeHeadline(JLabel jlbl_stroke) {
		
		
		final int strokeDistance = 10;
		if (jlbl_stroke.getWidth() > 0 
				&& jlbl_stroke.getHeight() > 0
//				&& jlbl_stroke.isShowing()
				) {
			BufferedImage bi_stroke = new BufferedImage(jlbl_stroke.getWidth(), 
	        		jlbl_stroke.getHeight(), BufferedImage.TYPE_INT_ARGB);
	        
	        for (int x = 0; x < bi_stroke.getWidth(); x ++) {

	            for (int y = 0; y < bi_stroke.getHeight(); y ++) {

//	            	if ( (_addX + x + y +  _addY) % 20 == 0) {
	            	
	            	try{
	            		if (
	            				( 
	            				(x + jlbl_stroke.getLocationOnScreen().x
		            			+ y + jlbl_stroke.getLocationOnScreen().y) 
		            			% strokeDistance == 0)
		            			||
		            			(x + jlbl_stroke.getLocationOnScreen().x
				            			- y - jlbl_stroke.getLocationOnScreen().y) 
				            			% strokeDistance == 0) {

		                	bi_stroke.setRGB(x, y, new Color(10,10,10, 10).getRGB());
		            	} else {

		                	bi_stroke.setRGB(x, y, new Color(0, 0, 0, 0).getRGB());
		            	}
	            	} catch (IllegalComponentStateException e) {
	            		x = bi_stroke.getWidth();
	            		y = bi_stroke.getHeight();
	            	}
	            
	            }	
	        }
	        jlbl_stroke.setIcon(new ImageIcon(bi_stroke));
		} else {
			System.out.println("error");
		}
		
		
	}   /**
     * Apply stroke on background.
     * @param jlbl_stroke the background carrying item.
     */
	public static void getStrokeRec(JLabel jlbl_stroke, int _addX, int _addY) {
		
		
		final int strokeDistance = 10;
		if (jlbl_stroke.getWidth() > 0 
				&& jlbl_stroke.getHeight() > 0
//				&& jlbl_stroke.isShowing()
				) {
			BufferedImage bi_stroke = new BufferedImage(jlbl_stroke.getWidth(), 
	        		jlbl_stroke.getHeight(), BufferedImage.TYPE_INT_ARGB);
	        
	        for (int x = 0; x < bi_stroke.getWidth(); x ++) {

	            for (int y = 0; y < bi_stroke.getHeight(); y ++) {
	            	

//	            	if ( (_addX + x + y +  _addY) % 20 == 0) {
	            	
	            	try{
	            		if ( (x + jlbl_stroke.getLocationOnScreen().x
		            			- y - jlbl_stroke.getLocationOnScreen().y) 
		            			% strokeDistance == 0
		            			||  (x + jlbl_stroke.getLocationOnScreen().x
				            			+ y + jlbl_stroke.getLocationOnScreen().y) 
				            			% strokeDistance == 0) {

		                	bi_stroke.setRGB(x, y, new Color(10,10,10, 10).getRGB());
		            	} else {

		                	bi_stroke.setRGB(x, y, new Color(0, 0, 0, 0).getRGB());
		            	}
	            	} catch (IllegalComponentStateException e) {
	            		x = bi_stroke.getWidth();
	            		y = bi_stroke.getHeight();
	            	}
	            
	            }	
	        }
	        jlbl_stroke.setIcon(new ImageIcon(bi_stroke));
		}
		
	}

	
	/**
     * Apply stroke on background.
     * @param jlbl_stroke the background carrying item.
     */
	public static void getScrollStroke(JButton jlbl_stroke) {
		
		
		final int strokeDistance = 10;
		if (jlbl_stroke.getWidth() > 0 
				&& jlbl_stroke.getHeight() > 0
				) {
			BufferedImage bi_stroke = new BufferedImage(jlbl_stroke.getWidth(), 
	        		jlbl_stroke.getHeight(), BufferedImage.TYPE_INT_ARGB);
	        
	        for (int x = 0; x < bi_stroke.getWidth(); x ++) {

	            for (int y = 0; y < bi_stroke.getHeight(); y ++) {
	            	

	            	
	            	try{
	            		if ( 
	            				(x + jlbl_stroke.getLocationOnScreen().x
		            			- y - jlbl_stroke.getLocationOnScreen().y) 
		            			% strokeDistance == 0
//		            			||  (x + jlbl_stroke.getLocationOnScreen().x
//				            			+ y + jlbl_stroke.getLocationOnScreen().y) 
//				            			% strokeDistance == 0
				            			) {

		                	bi_stroke.setRGB(x, y, Color.lightGray.getRGB());
		            	} else {

		                	bi_stroke.setRGB(x, y, new Color(255, 250, 255).getRGB());
		            	}
	            	} catch (IllegalComponentStateException e) {
	            		x = bi_stroke.getWidth();
	            		y = bi_stroke.getHeight();
	            	}
	            
	            }	
	        }
	        jlbl_stroke.setIcon(new ImageIcon(bi_stroke));
		}
		
	}

    /**
     * Apply stroke on background.
     * @param jlbl_stroke the background carrying item.
     */
	public static void getRoughStroke(JLabel jlbl_stroke) {
		
		
		final int strokeDistance = 20;
		if (jlbl_stroke.getWidth() > 0 
				&& jlbl_stroke.getHeight() > 0
//				&& jlbl_stroke.isShowing()
				) {
			BufferedImage bi_stroke = new BufferedImage(jlbl_stroke.getWidth(), 
	        		jlbl_stroke.getHeight(), BufferedImage.TYPE_INT_ARGB);
	        
	        for (int x = 0; x < bi_stroke.getWidth(); x ++) {

	            for (int y = 0; y < bi_stroke.getHeight(); y ++) {
	            	

//	            	if ( (_addX + x + y +  _addY) % 20 == 0) {
	            	if ( (x + y) 
	            			% strokeDistance == 0) {

	                	bi_stroke.setRGB(x, y, new Color(40,50,50, 120).getRGB());
	            	} else {

	                	bi_stroke.setRGB(x, y, new Color(0, 0, 0, 50).getRGB());
	            	}
	            }	
	        }
	        jlbl_stroke.setIcon(new ImageIcon(bi_stroke));
		}
		
	}
}
