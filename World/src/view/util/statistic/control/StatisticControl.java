package view.util.statistic.control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import util.MousePositionTracker;
import util.Util;
import view.util.statistic.model.Diagram;
import view.util.statistic.view.Statistic;


public class StatisticControl {

	
	public static final Color CLR_BACKGROUND = new Color(238, 235, 229);
	private Statistic vw_statistic;
	private Diagram mdl_statistic;
	
	public StatisticControl(final Dimension _d) {
		
		this.vw_statistic = new Statistic();
		this.vw_statistic.setSize(_d.width, _d.height);
		
		this.mdl_statistic = new Diagram(_d, _d.width / 3);
		this.mdl_statistic.addObserver(vw_statistic);
		
	}
	
	

	
	public void addData(final int _id, final int _newValue) {
		mdl_statistic.addValue(_id, _newValue);
	}
	
	public int addDiagram(final Color _clr, final String _name) {
		return mdl_statistic.addLine(_clr, _name);
	}
	
	public void addingComplete() {
		mdl_statistic.addingComplete();
	}
	
	
	public JPanel getVW_Statistic() {
		return vw_statistic;
	}
	
	
	public static void main(String[]args){
		
		Dimension d = new Dimension (450, 80);
		final StatisticControl sc = new StatisticControl(d);
		
		final JFrame jf = new JFrame("chart sample");
		jf.setUndecorated(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
		jf.setSize(500, 300);
		jf.setLocationRelativeTo(null);
		jf.getContentPane().setBackground(CLR_BACKGROUND);
		
		jf.add(sc.getVW_Statistic());
		sc.getVW_Statistic().setLocation((int) (jf.getWidth() - d.getWidth()) / 2, 30);
		
		
		jf.setVisible(true);
		jf.setResizable(false);

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
		jbtn_exit.setBounds(jf.getWidth() - 65, 3, 60, 20);
		jf.add(jbtn_exit);

		JButton jbtn_minimize = new JButton();
		jbtn_minimize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jf.setState(JFrame.HIDE_ON_CLOSE);
			}
		});
		jbtn_minimize.setBackground(new Color(135, 140, 200));
		jbtn_minimize.setForeground(Color.white);
		jbtn_minimize.setText("-");
		jbtn_minimize.setFocusable(false);
		jbtn_minimize.setFont(new Font("Courier new", Font.PLAIN, 15));
		jbtn_minimize.setBounds(jf.getWidth() - 120, 3, 50, 20);
		jf.add(jbtn_minimize);
		
		Color clr_headline = new Color(205, 210, 208);
		clr_headline = new Color(238, 238, 238);
		JLabel jlbl_textTitle = new JLabel("chart sample");
		jlbl_textTitle.setFocusable(false);
		jlbl_textTitle.setBorder(null);
		jlbl_textTitle.setOpaque(true);
		jlbl_textTitle.setBackground(clr_headline);
		jlbl_textTitle.setBounds(jf.getWidth() / 2 - 50, 7, 100, 15);
		jlbl_textTitle.setOpaque(true);
		jf.add(jlbl_textTitle);
		
		JLabel jlbl_title = new JLabel();
		MousePositionTracker mpt = new MousePositionTracker(jf);
		jlbl_title.setFocusable(false);
		jlbl_title.setBorder(null);
		jlbl_title.addMouseListener(mpt);
		jlbl_title.addMouseMotionListener(mpt);
		jlbl_title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		jlbl_title.setBounds(2, 2, jf.getWidth() - 4, 25);
		jlbl_title.setOpaque(true);
		jlbl_title.setBackground(clr_headline);
		jf.add(jlbl_title);
//		Util.getStrokeHeadline(jlbl_title);

		
		JLabel jlbl_background = new JLabel();
		jlbl_background.setFocusable(false);
		jlbl_background.setBorder(null);
		jlbl_background.setBounds(2, 22, jf.getWidth() - 4, jf.getHeight() - 24);
		jlbl_background.setOpaque(true);
		jf.add(jlbl_background);
		Util.getStroke(jlbl_background, 0, 0);
		
		JLabel jlbl_border = new JLabel();
		jlbl_border.setSize(jf.getWidth(), jf.getHeight());
		jlbl_border.setBorder(BorderFactory.createEtchedBorder(Color.white, Color.gray));
		jf.add(jlbl_border);
		
		sc.getVW_Statistic().setVisible(true);
		
		new Thread() {
			public void run() {

				final int diag_1 = sc.addDiagram(new Color(254, 104, 0), "CPU0");
				final int diag_2 = sc.addDiagram(new Color(33, 185, 20), "CPU1");
				final int diag_3 = sc.addDiagram(new Color(68, 109, 185), "CPU2");

				jf.repaint();
				for (int i = 0; i < 1500; i ++) {

					final int r1 =  new Random().nextInt(20);
					final int r2 =  10 + new Random().nextInt(30);
					final int r3 =  30 + new Random().nextInt(50);
					sc.addData(diag_3, r1);
					sc.addData(diag_2, r2);
					sc.addData(diag_1, r3);
					
					sc.addingComplete();
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} .start();
		
	}
}
