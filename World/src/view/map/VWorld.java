package view.map;

import java.awt.Color;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.util.Status;

@SuppressWarnings("serial")
public class VWorld extends JPanel implements Observer  {

	private VWorldItem[][] vwi;

	
	public VWorld() {
		
		super.setLayout(null);
		
	}
	
	
	public void setSize(int _width, int _height) {

		super.setSize(_width, _height);
		for (int line = 0; vwi != null && line < vwi.length; line++) {
			for (int column = 0; column < vwi[line].length; column++) {
				
				if (vwi[line][column] != null) {

					vwi[line][column].setLocation(
							getWidth() * line / (vwi.length + 1), 
							getHeight() * column / (vwi[line].length + 1));
					vwi[line][column].setSize(
							getWidth() / (vwi.length + 1), 
							getHeight() / (vwi[line].length + 1));
				}
			}
		}
	}
	@Override
	public void update(Observable o, Object arg) {
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
					vwi[line][column].setLocation(
							getWidth() * line / (vwi.length + 1), 
							getHeight() * column / (vwi[line].length + 1));
					vwi[line][column].setSize(
							getWidth() / (vwi.length + 1), 
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

		} else {
			
			Status.getLogger().severe("observer error" + arg );
		}
	}
}
