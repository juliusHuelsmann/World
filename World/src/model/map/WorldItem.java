package model.map;

import java.awt.Color;
import java.util.Observable;
import model.life.Emission;
import model.life.Life;


/**
 * 
 * @author juliu_000
 *
 */
public class WorldItem extends Observable {
	
	
	private Life life = null;
	
	//hier kommen stink sachen rein zum Beispiel und spuren von 
	//life.
	private final Emission lifeEmission;
	
	public WorldItem() {
		lifeEmission = new Emission();
	}


	/**
	 * @return the life
	 */
	public Life getLife() {
		return life;
	}

	/**
	 * @param _life the life to set
	 */
	public void addLife(Life _life) {
		
		if (life == null) {

			this.life = _life;
			setChanged();	
			notifyObservers(life.getClrBackground());
		} else {
			System.out.println("life not null");
		}
	}
	

	/**
	 * @param life the life to set
	 */
	public void remvoeLife(Life life) {
		
		this.life = null;
		
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
}
