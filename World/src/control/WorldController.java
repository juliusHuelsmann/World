package control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.StatisticsImplementation;
import view.View;
import model.Statistic;
import model.map.World;
import model.util.Status;

public class WorldController implements Runnable, ActionListener {

	private World world;
	private Thread thread_life;
	private View view;
	private StatisticsImplementation si;
	
	
	private final int id_stats_grass, id_stats_sheep, id_stats_wolf, id_stats_trees, id_stats_monkeys;
	
	public WorldController(int _width, int _height) {
		
		view = new View(this);
		world = new World(_width, _height, view.getvWorld());
		view.repaint();
		
		si = new StatisticsImplementation();
		id_stats_grass = si.getSC().addDiagram(Color.green, "Grass");
		id_stats_wolf = si.getSC().addDiagram(Color.orange, "Wolf");
		id_stats_sheep = si.getSC().addDiagram(Color.gray, "Sheep");
		id_stats_trees = si.getSC().addDiagram(new Color(200, 200, 100), "Trees");
		id_stats_monkeys = si.getSC().addDiagram(Color.pink, "Monkey");
	}
	
	
	
	public static void main(String[]args){
		new WorldController(200, 200);
	}
	
	public static final int INDEX_AD_LIBITIUM = -1, INDEX_UNDEFINED = -2;
	private int maxStep = -1;
	@Override
	public void run() {


		while (
				(world.getCurrentStep() < maxStep
				|| maxStep == INDEX_AD_LIBITIUM)
				&& (maxStep != INDEX_UNDEFINED)) {

			world.planckTime();

			si.getSC().addData(id_stats_grass, Statistic.getAmountGrass() / 150 / 5);
			si.getSC().addData(id_stats_sheep, Statistic.getAmountsheeps()  / 150);
			si.getSC().addData(id_stats_monkeys, Statistic.getAmountmonkeys() / 150);
			si.getSC().addData(id_stats_trees, Statistic.getAmountTrees()  / 150);
			si.getSC().addData(id_stats_wolf, Statistic.getAmountTrees()  / 150);
			si.getSC().addingComplete();
			world.paintBI();
			world.increaseCurrentStep();
			
			si.getJlbl_anzahl().setText("Anzahl:" + world.getCurrentStep());
			
			try {

				Thread.sleep(Status.getInstance().getDelayForPlanckTime());				
			} catch(Exception e) {
				if (thread_life != null) {

					thread_life.interrupt();
				} else {
					Status.getLogger().severe("thread falsch gestarted.");
				}
				world.resetCurrentStep();
				break;
			}
			
		}
	}



	@Override
	public void actionPerformed(final ActionEvent _event) {
		if (_event.getSource().equals(view.getJbtn_init())) {

			if (world != null) {

				world.initialize();
					
			}
		} else if (_event.getSource().equals(view.getJbtn_initGrass())) {

			if (world != null) {

				world.initializeGrass();
					
			}
		} else if (_event.getSource().equals(view.getJbtn_initWolf())) {

			if (world != null) {

				world.initializeWolf();
					
			}
		} else if (_event.getSource().equals(view.getJbtn_initTrees())) {

			if (world != null) {

				world.initializeTrees();
					
			}
		} else if (_event.getSource().equals(view.getJbtn_initBerries())) {

			if (world != null) {

				world.initializeBerries();
					
			}
		} else if (_event.getSource().equals(view.getJbtn_initSheep())) {

			if (world != null) {

				world.initializeSheep();
					
			}
		} else if (_event.getSource().equals(view.getJbtn_initMonkey())) {

			if (world != null) {

				world.initializeMonkey();
					
			}
		} else 	if (_event.getSource().equals(view.getJbtn_run())) {

			if (world != null) {

				if (thread_life == null || !thread_life.isAlive() 
						|| thread_life.isInterrupted()) {

					thread_life = new Thread(this);
					thread_life.start();
				} else {
					thread_life.interrupt();
				}
			}
		} else 	if (_event.getSource().equals(view.getJbtn_oneStep())) {

			if (world != null) {

				if (thread_life == null || !thread_life.isAlive() 
						|| thread_life.isInterrupted()) {

					world.planckTime();
				} 
			}
		}
	}
	
}
