package model.map;

import java.awt.Color;
import java.util.Observable;

import model.life.Creature;
import model.life.Emission;
import model.life.Life;
import model.life.creature.Monkey;
import model.life.creature.Sheep;
import model.life.creature.Wolf;
import model.life.plant.Grass;
import model.util.Status;
import model.util.adt.list.List;


/**
 * 
 * @author juliu_000
 *
 */
public class WorldItem extends Observable {
	
	
	private List<Life> life = null;
	
	//hier kommen stink sachen rein zum Beispiel und spuren von 
	//life.
	private final Emission lifeEmission;
	
	public WorldItem() {
		lifeEmission = new Emission();
		life = new List<Life>();
	}


	/**
	 * @return the life
	 */
	public List<Life> getLife() {
		return life;
	}

	/**
	 * @param _life the life to set
	 */
	public boolean addLife(Life _life) {
		
		if (life == null
				|| life.isEmpty()) {

			life.insertBehind(_life);
			setChanged();	
			notifyObservers(_life.getClrBackground());
			return true;
		} else if (_life instanceof Grass){

			if (!containsGrass()) {
				life.insertBehind(_life);
				return true;
			}
			return false;
		} else {
			
			boolean b = false;
			life.toFirst();
			int amount = 0;
			while(!life.isEmpty() && !life.isBehind()) {
				if (!(life.getElement().getContent() instanceof Grass)) {
					b = true;
				}
				amount ++;
				life.next();
			}
			if (!b && amount < 2) {

				life.insertBehind(_life);
				setChanged();	
				notifyObservers(_life.getClrBackground());
				return true;
			}
			
			
		}
		return false;
	}
	

	public void startLifePlanckTime() {

		life.toFirst();
		while(!life.isEmpty() && !life.isEmpty() && !life.isBehind()) {
			if (life.getElement() != null 
					&& life.getElement().getContent() != null) {

				life.getElement().getContent().startPlanckTime();
				life.next();
			}
		}
	}
	public void endLifePlanckTime() {

		life.toFirst();
		while(!life.isEmpty() && !life.isBehind()) {
			life.getElement().getContent().endPlanckTime();
			life.next();
		}
	
		
	}


	public boolean containsWolf() {

		life.toFirst();
		while(!life.isEmpty() && !life.isBehind()) {
			if (life.getElement().getContent() instanceof Wolf){
				return true;
			}
			
			life.next();
		}
		return false;
	}

	public Wolf getContainedWolf() {

		life.toFirst();
		while(!life.isEmpty() && !life.isBehind()) {
			if (life.getElement().getContent() instanceof Wolf){
				return (Wolf) life.getElement().getContent();
			}
			
			life.next();
		}
		return null;
	}
	public Grass getContainedGrass() {

		life.toFirst();
		while(!life.isEmpty() && !life.isBehind()) {
			if (life.getElement().getContent() instanceof Grass){
				return (Grass) life.getElement().getContent();
			}
			
			life.next();
		}
		return null;
	}
	public Sheep getContainedSheep() {

		life.toFirst();
		while(!life.isEmpty() && !life.isBehind()) {
			if (life.getElement().getContent() instanceof Sheep){
				return (Sheep) life.getElement().getContent();
			}
			
			life.next();
		}
		return null;
	}
	public Monkey getContainedMonkey() {

		life.toFirst();
		while(!life.isEmpty() && !life.isBehind()) {
			if (life.getElement().getContent() instanceof Monkey){
				return (Monkey) life.getElement().getContent();
			}
			
			life.next();
		}
		return null;
	}
	
	public Life getContained(Life _c) {
		if (_c instanceof Monkey) {

			return getContainedMonkey();
		} else if (_c instanceof Sheep) {

			return getContainedSheep();
		} else if (_c instanceof Wolf) {
			return getContainedWolf();
		} else if (_c instanceof Grass) {
			return getContainedGrass();
		} else {
			new Error().printStackTrace();
			Status.getLogger().severe("impl. errr." + _c);
			return null;
		}
	}
	public boolean containsSheep() {

		life.toFirst();
		while(!life.isEmpty() && !life.isBehind()) {
			if (life.getElement().getContent() instanceof Sheep){
				return true;
			}
			
			life.next();
		}
		return false;
	
		
	}
	public boolean containsGrass() {

		life.toFirst();
		while(!life.isEmpty() && !life.isBehind()) {
			if (life.getElement().getContent() instanceof Grass){
				return true;
			}
			
			life.next();
		}
		return false;
	
		
	}
	public boolean containsMonkey() {

		life.toFirst();
		while(!life.isEmpty() && !life.isBehind()) {
			if (life.getElement().getContent() instanceof Monkey){
				return true;
			}
			
			life.next();
		}
		return false;
	
		
	}
	public void emitLifeSmell() {

		life.toFirst();
		while(!life.isEmpty() && !life.isBehind()) {
			life.getElement().getContent().emitSmell();
			life.next();
		}
	
		
	}
	/**
	 * @param _life the life to set
	 */
	public void remvoeLife(Life 
			_life) {
		
		boolean b = this.life.find(_life);
		if (b) {
			life.remove();
		} else {
			Status.getLogger().severe("implementation error");
		}
		
		setChanged();
		notifyObservers(Color.LIGHT_GRAY);
	}
	/**
	 */
	public void removeCreature() {

		life.toFirst();
		while(!life.isEmpty() && !life.isBehind()){
			
			if (life.getElement().getContent() instanceof Creature){
				life.remove();
			}
			life.next();
		}
		
		
		setChanged();
		notifyObservers(Color.LIGHT_GRAY);
	}
	/**
	 */
	public void removeGrass() {

		life.toFirst();
		while(!life.isEmpty() && !life.isBehind()){
			
			if (life.getElement().getContent() instanceof Grass){
				life.remove();
			}
			life.next();
		}
		
		
		setChanged();
		notifyObservers(Color.LIGHT_GRAY);
	}


	/**
	 * @return the lifeEmission
	 */
	public Emission getLifeEmission() {
		return lifeEmission;
	}
	
	
	public void updateEmission() {
		
		setChanged();
		notifyObservers(this);
	}


	public boolean contains(Creature _creature) {
		if (_creature instanceof Monkey) {
			return containsMonkey();
		}if (_creature instanceof Wolf) {
			return containsWolf();
		}if (_creature instanceof Sheep) {
			return containsSheep();
		}else{
			Status.getLogger().severe("error");
			return false;
		}
	}


	public int getAmountOfContained() {
		int amoutn = 0;
		life.toFirst();
		while(!life.isEmpty() && !life.isBehind()) {
			life.next();amoutn++;
		}
		return amoutn;
	}
}
