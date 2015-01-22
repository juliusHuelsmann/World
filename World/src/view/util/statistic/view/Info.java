package view.util.statistic.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Util;
import view.util.statistic.control.StatisticControl;

public class Info extends JPanel{

	private JLabel jlbl_color, jlbl_title, jlbl_background;
	
	public Info() {
		super();
		super.setBackground(StatisticControl.CLR_BACKGROUND);
		super.setLayout(null);
		super.setSize(100, 20);
		super.setVisible(true);
		super.setOpaque(true);
		
		final int colorWidth = 2 * 18, colorHeight = 18;
		
		jlbl_color = new JLabel();
		jlbl_color.setOpaque(true);
		jlbl_color.setBounds(1, 1, colorWidth, colorHeight);
		jlbl_color.setBorder(BorderFactory.createEtchedBorder(Color.gray, Color.black));
		super.add(jlbl_color);
		
		jlbl_title = new JLabel();
		jlbl_title.setOpaque(false);
		jlbl_title.setBorder(null);
		jlbl_title.setFont(new Font("Yu Mincho Light", Font.PLAIN, 10));
		jlbl_title.setBounds(
				jlbl_color.getWidth() +jlbl_color.getX() +1,  1,
				getWidth() / 2 - 2, getHeight() - 2);
		jlbl_title.setFocusable(false);
		super.add(jlbl_title);

		jlbl_background = new JLabel();
		jlbl_background.setFocusable(false);
		jlbl_background.setBorder(null);
		jlbl_background.setOpaque(true);
		jlbl_background.setSize(getSize());
		super.add(jlbl_background);
				
	}
	

	public void applyStroke() {

		Util.getStroke(jlbl_background, 0, 0);
	}
	/**
	 * Set the color
	 * @param _clr
	 */
	public void setColor(final Color _clr){
		jlbl_color.setBackground(_clr);
	}
	
	/**
	 * set title.
	 * @param _newTitle
	 */
	public void setTitle(final String _newTitle){
		jlbl_title.setText(_newTitle);
	}
}

