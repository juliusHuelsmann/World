package view.util.statistic.view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Statistic extends JPanel implements Observer {

	
	
	
	private JLabel jlbl_stuff;
	
	public Statistic () {
		super();
		super.setLayout(null);
		super.setVisible(true);
		
		jlbl_stuff = new JLabel();
		jlbl_stuff.setOpaque(true);
		jlbl_stuff.setBorder(BorderFactory.createLineBorder(Color.black));
		super.add(jlbl_stuff);
	}
	
	
	public void setSize(
			final int _width, 
			final int _height) {

		//
		super.setSize(_width, _height);
		if (jlbl_stuff != null) {
			jlbl_stuff.setSize(_width, _height);
		}
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override public void update(
			final Observable _observable, 
			final Object _object) {
		
		if (_object instanceof BufferedImage) {
			jlbl_stuff.setIcon(new ImageIcon((BufferedImage) _object));
		}
	}
}
