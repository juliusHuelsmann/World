package view;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import view.map.VWorld;

@SuppressWarnings("serial")
public class View extends JFrame {

	private JButton jbtn_init, jbtn_run, jbtn_oneStep;
	private VWorld vWorld;
	final int width = 500, height = 500, minusMap = 20, buttonWidth = 100, minusMapHeight = 30;
	
	public View(ActionListener actionListener) {
		super();
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setLocationRelativeTo(null);
		super.setLayout(null);

		jbtn_init = new JButton("inititalize");
		jbtn_init.addActionListener(actionListener);
		super.add(jbtn_init);

		jbtn_run = new JButton("run");
			jbtn_run.addActionListener(actionListener);
		super.add(jbtn_run);
		jbtn_oneStep = new JButton("run one step");
		jbtn_oneStep.addActionListener(actionListener);
		super.add(jbtn_oneStep);
		
		vWorld = new VWorld();
		vWorld.setSize(width - minusMap - buttonWidth, height - minusMapHeight);
		super.add(vWorld);
		
		setSize(width, height);
		super.setVisible(true);
		
	}
	
	public void validate() {

		super.validate();
		if (vWorld != null && jbtn_oneStep != null) {
			jbtn_init.setBounds(getWidth() - minusMap - buttonWidth, 0, buttonWidth, 20);
			jbtn_run.setBounds(getWidth() - minusMap - buttonWidth, jbtn_init.getY() + jbtn_init.getHeight() + 5, buttonWidth, 20);
			jbtn_oneStep.setBounds(getWidth() - minusMap - buttonWidth, jbtn_run.getY() + jbtn_run.getHeight() + 5, buttonWidth, 20);

			vWorld.setSize(getWidth() - minusMap - buttonWidth, getHeight() - minusMapHeight);
		}
	}
	
	public void setSize(int _width, int _height) {
		super.setSize(_width, _height);
		
	}
	
	/**
	 * @return the vWorld
	 */
	public VWorld getvWorld() {
		return vWorld;
	}



	/**
	 * @return the jbtn_go
	 */
	public JButton getJbtn_init() {
		return jbtn_init;
	}



	/**
	 * @return the jbtn_run
	 */
	public JButton getJbtn_run() {
		return jbtn_run;
	}

	/**
	 * @return the jbtn_oneStep
	 */
	public JButton getJbtn_oneStep() {
		return jbtn_oneStep;
	}

}
