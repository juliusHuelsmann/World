package model.map;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

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

		final int amountWolfs = 5, amountSheep = 5, amountMonkey = 5,
				amountBerries = 5, amountTrees = 5, amountGrass = 50;
//		final int amountWolfs = 0, amountSheep = 60, amountMonkey = 0,
//		amountBerries = 0, amountTrees = 1, amountGrass = 50;
//		final int amountWolfs = 0, amountSheep = 10, amountMonkey = 0,
//				amountBerries = 0, amountTrees = 1, amountGrass = 50;
//		final int amountWolfs = 1, amountSheep = 0, amountMonkey = 0,
//				amountBerries = 0, amountTrees = 0, amountGrass = 0;


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


	/**
	 * @return the wi_world
	 */
	public WorldItem[][] getWi_world() {
		return wi_world;
	}

}
