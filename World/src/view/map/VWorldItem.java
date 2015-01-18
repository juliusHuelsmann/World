package view.map;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import control.CWorldItem;
import view.util.FetchButon;
import model.life.Emission;
import model.map.WorldItem;
import model.util.Status;

@SuppressWarnings("serial")
public class VWorldItem extends JPanel implements Observer{

	
	private FetchButon jbtn_text;
	private WorldItem worldItem;
	
	public VWorldItem() {
		super();
		super.setLayout(null);
		super.setBackground(Color.lightGray);
		super.setOpaque(true);
		
		jbtn_text = new FetchButon(this);
		jbtn_text.setFont(new Font("", Font.BOLD, 10));
		jbtn_text.setBorder(null);
		jbtn_text.setContentAreaFilled(false);
		jbtn_text.setOpaque(false);
		jbtn_text.addActionListener(CWorldItem.getInstance());
		super.add(jbtn_text);
	}

	public void setSize(int _width, int _height) {
		super.setSize(_width, _height);
		jbtn_text.setSize(_width, _height);
	}
	@Override
	public void update(Observable arg0, Object arg1) {

		if (arg1 instanceof Color) {
			super.setBackground((Color) arg1);
			super.setOpaque(true);
		} else if (arg1 instanceof WorldItem) {
			
			worldItem = (WorldItem) arg1;
			Emission emission = ((WorldItem) arg1).getLifeEmission();

			int sum =  emission.getBerries() + emission.getMonkey() 
					+ emission.getSheep() + emission.getTree()
					+ emission.getWolf();
			
			int divisor = 0;
			if ( emission.getBerries() > 0) {
				divisor++;
			}if ( emission.getMonkey() > 0) {
				divisor++;
			}if ( emission.getSheep() > 0) {
				divisor++;
			}if ( emission.getTree() > 0) {
				divisor++;
			}if ( emission.getWolf() > 0) {
				divisor++;
			}
			divisor = Math.max (divisor, 1);

			sum /= divisor;
			if (sum != 0) {
				jbtn_text.setText("" + sum);
						
			} else {
				jbtn_text.setText("");
			}
			jbtn_text.setForeground(emission.getColor());
			
			
			
		} else {
			Status.getLogger().severe("error");
		}
		
		repaint();
		
	}

	/**
	 * @return the jbtn_text
	 */
	public JButton getJbtn_text() {
		return jbtn_text;
	}

	/**
	 * @return the emission
	 */
	public WorldItem getWorldItem() {
		return worldItem;
	}
}
