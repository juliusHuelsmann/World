package view;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import view.map.VWorld;

@SuppressWarnings("serial")
public class View extends JFrame {

	private JButton jbtn_init, jbtn_initGrass, jbtn_initSheep, jbtn_initWolf, jbtn_initTrees, jbtn_initBerries, jbtn_initMonkey, jbtn_run, jbtn_oneStep;
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
		jbtn_initGrass = new JButton("init Grass");
		jbtn_initGrass.addActionListener(actionListener);
		super.add(jbtn_initGrass);
		jbtn_initSheep = new JButton("init Sheep");
		jbtn_initSheep.addActionListener(actionListener);
		super.add(jbtn_initSheep);
		jbtn_initWolf = new JButton("init Wolf");
		jbtn_initWolf.addActionListener(actionListener);
		super.add(jbtn_initWolf);
		jbtn_initMonkey = new JButton("init Monkey");
		jbtn_initMonkey.addActionListener(actionListener);
		super.add(jbtn_initMonkey);
		jbtn_initTrees= new JButton("init trees");
		jbtn_initTrees.addActionListener(actionListener);
		super.add(jbtn_initTrees);
		jbtn_initBerries= new JButton("init berries");
		jbtn_initBerries.addActionListener(actionListener);
		super.add(jbtn_initBerries);

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
			
			jbtn_initGrass.setBounds(getWidth() - minusMap - buttonWidth, jbtn_oneStep.getY() + jbtn_oneStep.getHeight() + 5, buttonWidth, 20);
			jbtn_initMonkey.setBounds(getWidth() - minusMap - buttonWidth, jbtn_initGrass.getY() + jbtn_initGrass.getHeight() + 5, buttonWidth, 20);
			jbtn_initSheep.setBounds(getWidth() - minusMap - buttonWidth, jbtn_initMonkey.getY() + jbtn_initMonkey.getHeight() + 5, buttonWidth, 20);
			jbtn_initWolf.setBounds(getWidth() - minusMap - buttonWidth, jbtn_initSheep.getY() + jbtn_initSheep.getHeight() + 5, buttonWidth, 20);
			jbtn_initBerries.setBounds(getWidth() - minusMap - buttonWidth, jbtn_initWolf.getY() + jbtn_initWolf.getHeight() + 5, buttonWidth, 20);
			jbtn_initTrees.setBounds(getWidth() - minusMap - buttonWidth, jbtn_initBerries.getY() + jbtn_initBerries.getHeight() + 5, buttonWidth, 20);
			
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
	 * @return the jbtn_go
	 */
	public JButton getJbtn_initSheep() {
		return jbtn_initSheep;
	}

	/**
	 * @return the jbtn_go
	 */
	public JButton getJbtn_initWolf() {
		return jbtn_initWolf;
	}

	/**
	 * @return the jbtn_go
	 */
	public JButton getJbtn_initGrass() {
		return jbtn_initGrass;
	}

	/**
	 * @return the jbtn_go
	 */
	public JButton getJbtn_initMonkey() {
		return jbtn_initMonkey;
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

	/**
	 * @return the jbtn_initTrees
	 */
	public JButton getJbtn_initTrees() {
		return jbtn_initTrees;
	}

	/**
	 * @param jbtn_initTrees the jbtn_initTrees to set
	 */
	public void setJbtn_initTrees(JButton jbtn_initTrees) {
		this.jbtn_initTrees = jbtn_initTrees;
	}

	/**
	 * @return the jbtn_initBerries
	 */
	public JButton getJbtn_initBerries() {
		return jbtn_initBerries;
	}

	/**
	 * @param jbtn_initBerries the jbtn_initBerries to set
	 */
	public void setJbtn_initBerries(JButton jbtn_initBerries) {
		this.jbtn_initBerries = jbtn_initBerries;
	}

}
