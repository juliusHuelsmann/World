package view.util.statistic.view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;





import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Util;
import view.util.statistic.control.StatisticControl;
import view.util.statistic.model.Line;
import model.util.Status;
import model.util.adt.list.SecureList;

@SuppressWarnings("serial")
public class Statistic extends JPanel implements Observer {

	private JLabel jlbl_background;
	private JLabel jlbl_stuff, jlbl_stuffBG;
	private SecureList<Info> ls_jlbl_title;
	
	public void setVisible(boolean _b) {
		
		
		
		if (_b){

			super.setVisible(_b);
			
			try{

				getLocationOnScreen();	
				this.applyStroke();
				
			}catch(Exception e) {
				setVisible(false);
				Status.getLogger().warning("only possible to set visible if added to visible window / pane");
			}
		} else {

			super.setVisible(_b);
		}
	}
	public Statistic () {
		super();
		super.setLayout(null);
		super.setVisible(false);
		super.setBackground(StatisticControl.CLR_BACKGROUND);

		jlbl_stuff = new JLabel();
		jlbl_stuff.setOpaque(false);
		jlbl_stuff.setBorder(null);
		super.add(jlbl_stuff);

		
		jlbl_stuffBG = new JLabel();
		jlbl_stuffBG.setOpaque(true);
		jlbl_stuffBG.setBorder(null);
		super.add(jlbl_stuffBG);
		
		ls_jlbl_title = new SecureList<Info>();

		jlbl_background = new JLabel();
		jlbl_background.setFocusable(false);
		jlbl_background.setBorder(null);
		jlbl_background.setOpaque(true);
		super.add(jlbl_background);
				
	}
	
	
	final int heightTitle = 50;
	final int positionX = 5, positionY = 5, additionalWidth = 10;
	
	
	public void setSize(
			final int _width, 
			final int _height) {

		//
		super.setSize(_width + additionalWidth, _height + heightTitle);
		if (jlbl_stuff != null) {
			jlbl_stuff.setSize(_width, _height);
			jlbl_stuffBG.setSize(jlbl_stuff.getSize());
			jlbl_stuff.setLocation(positionX, positionY);
			jlbl_stuffBG.setLocation(positionX, positionY);

			jlbl_background.setSize(getSize());
		}
	}

	public void applyStroke() {

		Util.getStroke(jlbl_background, 0, 0);
		Util.getStrokeGraphics(jlbl_stuffBG);

		ls_jlbl_title.toFirst(SecureList.ID_NO_PREDECESSOR, SecureList.ID_NO_PREDECESSOR);
		while (!ls_jlbl_title.isEmpty() && !ls_jlbl_title.isBehind()) {
			ls_jlbl_title.getItem().applyStroke();
			ls_jlbl_title.next(SecureList.ID_NO_PREDECESSOR, SecureList.ID_NO_PREDECESSOR);
		}
		
	}
	int amountOfINfos = 0;
	
	/**
	 * {@inheritDoc}
	 */
	@Override public void update(
			final Observable _observable, 
			final Object _object) {
		
		if (_object instanceof BufferedImage) {
			jlbl_stuff.setIcon(new ImageIcon((BufferedImage) _object));
		} else if (_object instanceof Line) {
			
			Info info = new Info();
			info.setTitle(((Line)_object).getIdentifier());
			info.setColor(((Line)_object).getClr_line());
			super.add(info);
			setComponentZOrder(info, 1);
			
			ls_jlbl_title.toFirst(SecureList.ID_NO_PREDECESSOR, SecureList.ID_NO_PREDECESSOR);
			ls_jlbl_title.insertBehind(info, 
					SecureList.ID_NO_PREDECESSOR);

			
			info.setLocation(5 + 120 * amountOfINfos, 
					jlbl_stuff.getHeight() 
					+ jlbl_stuff.getY() + 5);
			amountOfINfos ++;

			ls_jlbl_title.toFirst(SecureList.ID_NO_PREDECESSOR, SecureList.ID_NO_PREDECESSOR);
			while (!ls_jlbl_title.isEmpty() && !ls_jlbl_title.isBehind()) {
				ls_jlbl_title.getItem().applyStroke();
				ls_jlbl_title.next(SecureList.ID_NO_PREDECESSOR, SecureList.ID_NO_PREDECESSOR);
			}			
			setComponentZOrder(jlbl_background, getComponentCount()-1);
			repaint();

		} else {
			System.out.println(getClass() + ".." +_object);
		}
	}
}
