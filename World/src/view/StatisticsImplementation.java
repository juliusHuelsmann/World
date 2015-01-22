package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import util.MousePositionTracker;
import util.Util;
import view.util.statistic.control.StatisticControl;

public class StatisticsImplementation extends JFrame {



	Dimension d = new Dimension (450, 80);
	
	private final StatisticControl sc = new StatisticControl(d);
	
	public StatisticControl getSC() {
		return sc;
	}
	public StatisticsImplementation() {

		
		super.setUndecorated(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(null);
		super.setSize(500, 300);
		super.setAlwaysOnTop(true);
		super.setLocationRelativeTo(null);
		super.getContentPane().setBackground(StatisticControl.CLR_BACKGROUND);
		
		super.add(sc.getVW_Statistic());
		sc.getVW_Statistic().setLocation((int) (super.getWidth() - d.getWidth()) / 2, 30);
		
		
		super.setVisible(true);
		super.setResizable(false);

		JButton jbtn_exit = new JButton();
		jbtn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		jbtn_exit.setBackground(new Color(200, 140, 135));
		jbtn_exit.setForeground(Color.white);
		jbtn_exit.setText("x");
		jbtn_exit.setFocusable(false);
		jbtn_exit.setFont(new Font("Courier new", Font.PLAIN, 15));
		jbtn_exit.setBounds(super.getWidth() - 65, 3, 60, 20);
		super.add(jbtn_exit);

		JButton jbtn_minimize = new JButton();
		jbtn_minimize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setState(JFrame.HIDE_ON_CLOSE);
			}
		});
		jbtn_minimize.setBackground(new Color(135, 140, 200));
		jbtn_minimize.setForeground(Color.white);
		jbtn_minimize.setText("-");
		jbtn_minimize.setFocusable(false);
		jbtn_minimize.setFont(new Font("Courier new", Font.PLAIN, 15));
		jbtn_minimize.setBounds(super.getWidth() - 120, 3, 50, 20);
		super.add(jbtn_minimize);
		
		Color clr_headline = new Color(205, 210, 208);
		clr_headline = new Color(238, 238, 238);
		JLabel jlbl_textTitle = new JLabel("chart sample");
		jlbl_textTitle.setFocusable(false);
		jlbl_textTitle.setBorder(null);
		jlbl_textTitle.setOpaque(true);
		jlbl_textTitle.setBackground(clr_headline);
		jlbl_textTitle.setBounds(super.getWidth() / 2 - 50, 7, 100, 15);
		jlbl_textTitle.setOpaque(true);
		super.add(jlbl_textTitle);
		
		JLabel jlbl_title = new JLabel();
		MousePositionTracker mpt = new MousePositionTracker(this);
		jlbl_title.setFocusable(false);
		jlbl_title.setBorder(null);
		jlbl_title.addMouseListener(mpt);
		jlbl_title.addMouseMotionListener(mpt);
		jlbl_title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		jlbl_title.setBounds(2, 2, super.getWidth() - 4, 25);
		jlbl_title.setOpaque(true);
		jlbl_title.setBackground(clr_headline);
		super.add(jlbl_title);
//		Util.getStrokeHeadline(jlbl_title);

		
		JLabel jlbl_background = new JLabel();
		jlbl_background.setFocusable(false);
		jlbl_background.setBorder(null);
		jlbl_background.setBounds(2, 22, super.getWidth() - 4, super.getHeight() - 24);
		jlbl_background.setOpaque(true);
		super.add(jlbl_background);
		Util.getStroke(jlbl_background, 0, 0);
		
		JLabel jlbl_border = new JLabel();
		jlbl_border.setSize(super.getWidth(), super.getHeight());
		jlbl_border.setBorder(BorderFactory.createEtchedBorder(Color.white, Color.gray));
		super.add(jlbl_border);
		
		sc.getVW_Statistic().setVisible(true);
		
		
	
	}

}
