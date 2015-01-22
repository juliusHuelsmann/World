package view.map;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.util.Status;

@SuppressWarnings("serial")
public class VWorld extends JPanel implements Observer  {

	private VWorldItem[][] vwi;
	
	private JLabel jlbl_image;
	private BufferedImage bi;

	private JTextArea jta_text;
	
	public VWorld() {
		
		super.setLayout(null);
		
		jta_text = new JTextArea();
		jta_text.setOpaque(false);
		jta_text.setEditable(false);
		jta_text.setFocusable(false);
		jta_text.setBorder(null);
		super.add(jta_text);
		
		jlbl_image = new JLabel();
		jlbl_image.setVisible(true);
		super.add(jlbl_image);
	}
	
	
	public void setSize(int _width, int _height) {

		super.setSize(_width, _height);
		
		int iconX = getWidth() * 3 / 4;
		jta_text.setBounds(iconX, 20, getWidth() - iconX, getHeight() - 30);

		jlbl_image.setBounds(0, 0, iconX, getHeight());
		
		for (int line = 0; vwi != null && line < vwi.length; line++) {
			for (int column = 0; column < vwi[line].length; column++) {
				
				if (vwi[line][column] != null) {

					vwi[line][column].setLocation(
							iconX * line / (vwi.length + 1), 
							getHeight() * column / (vwi[line].length + 1));
					vwi[line][column].setSize(
							iconX / (vwi.length + 1), 
							getHeight() / (vwi[line].length + 1));
				}
			}
		}
	}
	@Override
	public void update(Observable o, Object arg) {
		
		final boolean observeTotaly = false;
		if (observeTotaly){
			if (arg instanceof Point) {
				
				if (vwi != null) {
					for (int line = 0; line < vwi.length; line++) {
						for (int column = 0; column < vwi[line].length; column++) {
							remove(vwi[line][column]);
						}
					}
					
				}

				vwi = new VWorldItem[((Point) arg).x][((Point) arg).y];
				
				for (int line = 0; line < vwi.length; line++) {
					for (int column = 0; column < vwi[line].length; column++) {
						vwi[line][column] = new VWorldItem();

						if (line % 2 == column % 2 ) {

							vwi[line][column].
							setBorder(BorderFactory.createLineBorder(Color.gray));
						}

						int iconX = getWidth() * 4 / 5;
						vwi[line][column].setLocation(
								iconX * line / (vwi.length + 1), 
								getHeight() * column / (vwi[line].length + 1));
						vwi[line][column].setSize(
								iconX / (vwi.length + 1), 
								getHeight() / (vwi[line].length + 1));
						super.add(vwi[line][column]);
					}
				}
			} else if (arg instanceof Observable[][]){

				Observable [][] obs = (Observable [][]) arg;
				if (vwi != null &&
						obs.length == vwi.length)  {
					for (int line = 0; line < vwi.length; line++) {

						if (obs.length == vwi.length && obs[line].length == vwi[line].length) {

							for (int column = 0; column < vwi[line].length; column++) {
								 obs[line][column].addObserver(vwi[line][column]);
							}
						} else {
							Status.getLogger().warning("fatal error");
						}
					}
				} else {

					Status.getLogger().warning("fatal error");
				}

			} 
		} else if (arg instanceof BufferedImage) {
			jlbl_image.setIcon(new ImageIcon((BufferedImage)arg));
		}
		
		
		if (arg instanceof String) {
			jta_text.setText("" + arg);
		}
//		else {
//			
//			Status.getLogger().severe("observer error" + arg );
//		}
	}
}
