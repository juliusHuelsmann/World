package view.util.statistic.control;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.util.statistic.model.StatisticModel;
import view.util.statistic.view.Statistic;


public class StatisticControl {

	
	private Statistic vw_statistic;
	private StatisticModel mdl_statistic;
	
	public StatisticControl(final Dimension _d) {
		
		this.vw_statistic = new Statistic();
		this.vw_statistic.setSize(_d.width, _d.height);
		
		this.mdl_statistic = new StatisticModel(_d, _d.width / 3);
		this.mdl_statistic.addObserver(vw_statistic);
		
	}

	
	public void addData(final int _id, final int _newValue) {
		mdl_statistic.addValue(_id, _newValue);
	}
	
	public int addDiagram(final Color _clr) {
		return mdl_statistic.addDiagram(_clr);
	}
	
	public void addingComplete() {
		mdl_statistic.addingComplete();
	}
	
	
	public JPanel getVW_Statistic() {
		return vw_statistic;
	}
	
	
	public static void main(String[]args){
		
		Dimension d = new Dimension (300, 300);
		final StatisticControl sc = new StatisticControl(d);
		final int addY = 40;
		final int addX = 18;
		
		JFrame jf = new JFrame("diagramm sample");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
		jf.setSize(d.width + addX, d.height + addY);
		jf.setLocationRelativeTo(null);
		
		jf.add(sc.getVW_Statistic());
		
		
		jf.setVisible(true);
		jf.setResizable(true);

		new Thread() {
			public void run() {

				final int diag_1 = sc.addDiagram(Color.red);
				final int diag_2 = sc.addDiagram(Color.green);
				final int diag_3 = sc.addDiagram(Color.blue);

				for (int i = 0; i < 1500; i ++) {

					final int r1 =  new Random().nextInt(20);
					final int r2 =  10 + new Random().nextInt(30);
					final int r3 =  30 + new Random().nextInt(50);
					
					sc.addData(diag_1, r1);
					sc.addData(diag_2, r2);
					sc.addData(diag_3, r3);
					
					sc.addingComplete();
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} .start();
		
	}
}
