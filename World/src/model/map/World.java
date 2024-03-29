package model.map;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import model.Statistic;
import model.life.creature.Einzeller;
import model.life.creature.Monkey;
import model.life.creature.Sheep;
import model.life.creature.Wolf;
import model.life.plant.Berries;
import model.life.plant.Grass;
import model.life.plant.Tree;
import model.util.Status;
import control.WorldController;

public class World extends Observable {

	
	private int currentStep = 0;
	
	
	private WorldItem[][] wi_world;
	
	public World(int _width, int _height, Observer _o) {
		
		addObserver(_o);
		setChanged();
		notifyObservers(new Point(_width, _height));
		
		
		this.wi_world = new WorldItem[_width][_height];
		Scope.initializeInstance(this);
		
		for (int line = 0; line < wi_world.length; line++) {
			for (int column = 0; column < wi_world[line].length; column++) {
				wi_world[line][column] = new WorldItem();
			}
		}
		
		setChanged();
		notifyObservers(wi_world);
	}
	
	
	public void planckTime() {
		
		
		Statistic.resetPercentages();
		

		for (int line = 0; line < wi_world.length; line++) {
			for (int column = 0; column < wi_world[line].length; column++) {
				wi_world[line][column].getLifeEmission().planckTime();
				if (wi_world[line][column].getLife() != null) {
					
					wi_world[line][column].startLifePlanckTime();
				}
			}
		}
		
		//has to be inside different for loops because otherwise the creatures
		//that move in direction of for loop are allowed to move more than once
		for (int line = 0; line < wi_world.length; line++) {
			for (int column = 0; column < wi_world[line].length; column++) {
				if (wi_world[line][column].getLife() != null) {
					wi_world[line][column].endLifePlanckTime();
				} 
				
				wi_world[line][column].updateEmission();
			}
		}
		
		String s = "";
		
		if (Statistic.getAmountwolfs() == 0) {

			s += 
					"Wolf: \t " + Statistic.getAmountwolfs();

		} else {

			s += 
					"Wolf: \t " + Statistic.getAmountwolfs()
					+ "\n\tage:\t" + (Statistic.getAgewolfs() / Statistic.getAmountwolfs())
					+ "\n\thunger:\t" + (Statistic.getHungerwolfs() / Statistic.getAmountwolfs())
					+ "\n\tpregnancy:\t" + (Statistic.getPregnantwolfs() / Statistic.getAmountwolfs());

		}
		
		if (Statistic.getAmountsheeps() == 0) {
			s +=
					"\n\nSheep: \t " + Statistic.getAmountsheeps();
			
		} else {
			s +=
					"\n\nSheep: \t " + Statistic.getAmountsheeps()
					+ "\n\tage:\t" + (Statistic.getAgesheeps() / Statistic.getAmountsheeps())
					+ "\n\thunger:\t" + (Statistic.getHungersheeps() / Statistic.getAmountsheeps())
					+ "\n\tpregnancy:\t" + (Statistic.getPregnantsheeps() / Statistic.getAmountsheeps());
			
		}
				
		
		if (Statistic.getAmountmonkeys() == 0) {
			s +=
					"\n\nSheep: \t " + Statistic.getAmountmonkeys();
				
		} else {
			s +=
					"\n\nSheep: \t " + Statistic.getAmountmonkeys()
					+ "\n\tage:\t" + (Statistic.getAgemonkeys() / Statistic.getAmountmonkeys())
					+ "\n\thunger:\t" + (Statistic.getHungermonkeys() / Statistic.getAmountmonkeys())
					+ "\n\tpregnancy:\t" + (Statistic.getPregnantmonkeys() / Statistic.getAmountmonkeys());
			
		}
		
		s +=
				"\n\n"
				+ "\nGrass: \t " + Statistic.getAmountGrass()
				+ "\nTrees: \t " + Statistic.getAmountTrees()
				+ "\nBerries: \t " + Statistic.getAmountBerries();
		
		setChanged();
		notifyObservers(s);
	}
	
	
	
	
	


	/**
	 * @return the currentStep
	 */
	public int getCurrentStep() {
		return currentStep;
	}




	/**
	 */
	public synchronized void increaseCurrentStep() {
		this.currentStep = currentStep + 1;
	}
	


	/**
	 */
	public void resetCurrentStep() {
		this.currentStep = 0;
	}	


	/**
	 */
	public void disinitializeCurrentStep() {
		this.currentStep = WorldController.INDEX_UNDEFINED;
	}


	public void initialize() {

		final double percentageWolfs = 0.1, percentageSheep = 5, percentageMonkey = 5,
				percentageBerries = 15, percentageTrees = 25, percentageGrass = 55;
//				percentageEinzeller = 5;
	
		final int 
		amountWolfs = (int) (percentageWolfs * wi_world.length * wi_world[0].length / 100),
		amountSheep	= (int) (percentageSheep * wi_world.length * wi_world[0].length / 100),
		amountMonkey = 	(int) (percentageMonkey * wi_world.length * wi_world[0].length / 100),
		amountGrass = (int) (percentageGrass * wi_world.length * wi_world[0].length / 100),
		amountTrees = (int) (percentageTrees * wi_world.length * wi_world[0].length / 100),
		amountBerries = (int) (percentageBerries * wi_world.length * wi_world[0].length / 100),
//		amountEinzeller = percentageEinzeller * wi_world.length * wi_world[0].length / 100;
		amountEinzeller = 0;

		Random rand = new Random();
		for (int currentEntity = 0; 
				currentEntity < amountWolfs; 
				currentEntity++) {
			int line = rand.nextInt(wi_world.length);
			int column = new Random().nextInt(wi_world[0].length);
			
			if (wi_world[line][column].addLife(new Wolf(line, column))){
				wi_world[line][column].emitLifeSmell();
			} else {
				currentEntity --;
			}
			
		}
		for (int currentEntity = 0; 
				currentEntity < amountEinzeller; 
				currentEntity++) {
			int line = rand.nextInt(wi_world.length);
			int column = new Random().nextInt(wi_world[0].length);
			
			if (wi_world[line][column].addLife(new Einzeller(line, column))){
				wi_world[line][column].emitLifeSmell();
			} else {
				currentEntity --;
			}
			
		}
		for (int currentEntity = 0; 
				currentEntity < amountGrass; 
				currentEntity++) {
			int line = rand.nextInt(wi_world.length);
			int column = new Random().nextInt(wi_world[0].length);

			if (wi_world[line][column].addLife(new Grass(line, column))){
				wi_world[line][column].emitLifeSmell();
			}
		}
		for (int currentEntity = 0; 
				currentEntity < amountSheep; 
				currentEntity++) {

			int line = new Random().nextInt(wi_world.length);
			int column = new Random().nextInt(wi_world[0].length);

			if (wi_world[line][column].addLife(new Sheep(line, column))){

				
				wi_world[line][column].emitLifeSmell();
			} else {
				currentEntity --;
			}
			
		}
		for (int currentEntity = 0; 
				currentEntity < amountMonkey; 
				currentEntity++) {

			int line = new Random().nextInt(wi_world.length);
			int column = new Random().nextInt(wi_world[0].length);
			if (wi_world[line][column].addLife(new Monkey(line, column))){
			wi_world[line][column].emitLifeSmell();
			} else {
				currentEntity --;
			}
			
		}
		for (int currentEntity = 0; 
				currentEntity < amountBerries; 
				currentEntity++) {

			int line = new Random().nextInt(wi_world.length);
			int column = new Random().nextInt(wi_world[0].length);

			if (wi_world[line][column].addLife(new Berries(line, column))){

				wi_world[line][column].emitLifeSmell();
			} else {
				currentEntity --;
			}
			
		}
		for (int currentEntity = 0; 
				currentEntity < amountTrees; 
				currentEntity++) {
			int line = new Random().nextInt(wi_world.length);
			int column = new Random().nextInt(wi_world[0].length);

			if (wi_world[line][column].addLife(new Tree(line, column))) {
				wi_world[line][column].emitLifeSmell();
			} else {
				currentEntity --;
			}
		}
		
		if (wi_world != null) {

			for (int line = 0; line < wi_world.length; line++) {
				for (int column = 0; column < wi_world[line].length; column++) {
					wi_world[line][column].updateEmission();
				}
			}			
		} else {
			Status.getLogger().severe("failed initializing");
		}
		
		Status.getLogger().info("initialization process completed.");
	}
	
	
	
	public void initializeSheep() {

		final int amountSheep = 25;
		
		for (int currentEntity = 0; 
				currentEntity < amountSheep; 
				currentEntity++) {

			int line = new Random().nextInt(wi_world.length);
			int column = new Random().nextInt(wi_world[0].length);

			if (wi_world[line][column].addLife(new Sheep(line, column))){

				
				wi_world[line][column].emitLifeSmell();
			} else {
				currentEntity --;
			}
			
		}
		
		if (wi_world != null) {

			for (int line = 0; line < wi_world.length; line++) {
				for (int column = 0; column < wi_world[line].length; column++) {
					wi_world[line][column].updateEmission();
				}
			}			
		} else {
			Status.getLogger().severe("failed initializing");
		}
		
	}public void initializeWolf() {

		final int amountWolfs = 5;


		Random rand = new Random();
		for (int currentEntity = 0; 
				currentEntity < amountWolfs; 
				currentEntity++) {
			int line = rand.nextInt(wi_world.length);
			int column = new Random().nextInt(wi_world[0].length);
			
			if (wi_world[line][column].addLife(new Wolf(line, column))){
				wi_world[line][column].emitLifeSmell();
			} else {
				currentEntity --;
			}
			
		}
		
		if (wi_world != null) {

			for (int line = 0; line < wi_world.length; line++) {
				for (int column = 0; column < wi_world[line].length; column++) {
					wi_world[line][column].updateEmission();
				}
			}			
		} else {
			Status.getLogger().severe("failed initializing");
		}
	}
	
	
	public void paintBI() {
		
		final int stretchX = 3, stretchY = 3;
		BufferedImage bi = new BufferedImage(
				stretchX * wi_world.length, 
				stretchY * wi_world[0].length,
				BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < wi_world.length; i++) {
			for (int j = 0; j < wi_world[i].length; j++) {
				
				
				Color clr = wi_world[i][j].getLifeEmission().getColor();
				if (clr == Color.black) 
					clr = new Color(0, 0, 0, 0);
				for (int dx = 0; dx < stretchX; dx++) {
					for (int dy = 0; dy < stretchX; dy++) {
						bi.setRGB(stretchX * i + dx, 
								j * stretchY + dy,
								clr.getRGB()
								);
						
					}					
				}
//				().getItem().getClrBackground().getRGB());
			}
		}
		
		setChanged();
		notifyObservers(bi);
	}
	
	public void initializeGrass() {

		final int amountGrass = 25;
		for (int currentEntity = 0; 
				currentEntity < amountGrass; 
				currentEntity++) {
			int line = new Random().nextInt(wi_world.length);
			int column = new Random().nextInt(wi_world[0].length);

			if (wi_world[line][column].addLife(new Grass(line, column))){
				wi_world[line][column].emitLifeSmell();
			}
		}
		
		if (wi_world != null) {

			for (int line = 0; line < wi_world.length; line++) {
				for (int column = 0; column < wi_world[line].length; column++) {
					wi_world[line][column].updateEmission();
				}
			}			
		} else {
			Status.getLogger().severe("failed initializing");
		}
		
	}
	
	public void initializeMonkeyOld() {

		int amountMonkey = 5;

		for (int currentEntity = 0; 
				currentEntity < amountMonkey; 
				currentEntity++) {

			int line = new Random().nextInt(wi_world.length);
			int column = new Random().nextInt(wi_world[0].length);
			if (wi_world[line][column].addLife(new Monkey(line, column))){
			wi_world[line][column].emitLifeSmell();
			} else {
				currentEntity --;
			}
		}
			

			if (wi_world != null) {

				for (int line = 0; line < wi_world.length; line++) {
					for (int column = 0; column < wi_world[line].length; column++) {
						wi_world[line][column].updateEmission();
					}
				}			
			} else {
				Status.getLogger().severe("failed initializing");
			}
		}
	
	public void initializeMonkey() {

		int amountMonkey = 15;

		int randline = new Random().nextInt(wi_world.length);
		int randcolumn = new Random().nextInt(wi_world[0].length);
		for (int currentEntity = 0; 
				currentEntity < amountMonkey; 
				currentEntity++) {

			if (wi_world[randline + currentEntity][randcolumn].addLife(new Monkey(randline, randcolumn))){
				wi_world[randline][randcolumn].emitLifeSmell();
			} else {
				currentEntity --;
				randcolumn ++;
			}
		}
			

			if (wi_world != null) {

				for (int line = 0; line < wi_world.length; line++) {
					for (int column = 0; column < wi_world[line].length; column++) {
						wi_world[line][column].updateEmission();
					}
				}			
			} else {
				Status.getLogger().severe("failed initializing");
			}
		}


	/**
	 * @return the wi_world
	 */
	public WorldItem[][] getWi_world() {
		return wi_world;
	}


	public void initializeTrees() {

		int amountTrees = 5;

		for (int currentEntity = 0; 
				currentEntity < amountTrees; 
				currentEntity++) {

			int line = new Random().nextInt(wi_world.length);
			int column = new Random().nextInt(wi_world[0].length);
			if (wi_world[line][column].addLife(new Tree(line, column))){
			wi_world[line][column].emitLifeSmell();
			} else {
				currentEntity --;
			}
		}
			

			if (wi_world != null) {

				for (int line = 0; line < wi_world.length; line++) {
					for (int column = 0; column < wi_world[line].length; column++) {
						wi_world[line][column].updateEmission();
					}
				}			
			} else {
				Status.getLogger().severe("failed initializing");
			}
		}
	
	public void initializeBerries() {

		int amountBerries = 5;

		for (int currentEntity = 0; 
				currentEntity < amountBerries; 
				currentEntity++) {

			int line = new Random().nextInt(wi_world.length);
			int column = new Random().nextInt(wi_world[0].length);
			if (wi_world[line][column].addLife(new Berries(line, column))){
			wi_world[line][column].emitLifeSmell();
			} else {
				currentEntity --;
			}
		}
			

			if (wi_world != null) {

				for (int line = 0; line < wi_world.length; line++) {
					for (int column = 0; column < wi_world[line].length; column++) {
						wi_world[line][column].updateEmission();
					}
				}			
			} else {
				Status.getLogger().severe("failed initializing");
			}
		}

}
