package model.life;

import java.awt.Point;
import java.util.Random;

import model.life.creature.Monkey;
import model.life.creature.Sheep;
import model.life.creature.Wolf;
import model.map.Scope;
import model.map.WorldItem;
import model.util.Status;


/**
 * 
 * @author Julius Huelsmann
 * @version %I%, %U%
 */
public abstract class Creature extends Life {

	/**
	 * The time a life is sleeping in planck time.
	 */
	int sleepTime = -1;

	/**
	 * 
	 */
	protected int reactionTime = 0;
	

	/**
	 * 
	 */
	protected int visibleLines = 0, visibleCols = 0;
	protected int smellEmission;
	
	private boolean feminin;
	private boolean pregnant;
	private int maxTimeWithoutFood;
	private int maxLifetime;
	
	private boolean alive;
	
	
	private Point pnt_positionInScope;

	
	/**
	 * Minimal age after which a creature is getitng pregnant and
	 * time difference between pregnancy  start and birth.
	 */
	private int minAgePregnancy, totalTimePregnancy;
	
	private int cTimeWithoutFood, cTimePregnany;
	/**
	 * Constructor of abstract class Creature.
	 * 
	 * @param _visibilityScope
	 * 			the area which is visible for the Creature.
	 * 
	 * @param _pnt_positionInScope
	 * 			the position of the creature in visibilityScope
	 * 
	 * @param _position
	 * 			the position of the creature in the map
	 * 
	 * @param _reactionTime
	 */
	public Creature(
			final Point _visibilityScope, 
			final Point _pnt_positionInScope,
			final Point _position,
			
			final int _reactionTime,
			final int _maxTimeWithoutFood,
			final int _maxLifetime,
			
			final int _minAgePregnancy,
			final int _timePregnancy,
			
			final Point _emissionScope,
			final double _emissionMult) {
		
		super(_emissionScope, _position, _emissionMult);
		
		this.reactionTime = _reactionTime;
		this.visibleCols = _visibilityScope.y;
		this.visibleLines = _visibilityScope.x;
		this.pnt_positionInScope = _pnt_positionInScope;
		this.maxLifetime = _maxLifetime;
		this.reactionTime = _reactionTime;
		this.maxTimeWithoutFood = -maxTimeWithoutFood;
		
		this.minAgePregnancy = _minAgePregnancy;
		this.totalTimePregnancy = _timePregnancy;
		this.maxTimeWithoutFood = _maxTimeWithoutFood;
		
		//random values
		this.feminin = new Random().nextBoolean();
		this.pregnant = false;
		
		//set alive by default
		this.alive = true;
	}
	

	public int getPercentageHunger() {
		return 100 * cTimeWithoutFood / maxTimeWithoutFood;
	}
	public int getPercentagePregancy() {
		return 100 * cTimePregnany / totalTimePregnancy;
	}public int getPercentageAge() {
		return 100 * getLifetime() / maxLifetime;
	}


	public void die() {

		
		WorldItem[][]  wi_scope = Scope.getVisibilityScope(this);

		if (wi_scope != null 
				&& wi_scope[pnt_positionInScope.x][pnt_positionInScope.y]
						.getLife() != null) {
			
			wi_scope[pnt_positionInScope.x][pnt_positionInScope.y]
					.remvoeLife(this);
		} else {
//			System.out.println("error");
			Status.getLogger().severe("not initialized yet");
		}

	}
	
	public void birth() {

		
		WorldItem[][]  wi_scope = Scope.getVisibilityScope(this);
		
		
		//if the current creature exists on map
		if (wi_scope != null 
				&& wi_scope[pnt_positionInScope.x][pnt_positionInScope.y]
						.getLife() == this) {
			
			wi_scope[pnt_positionInScope.x][pnt_positionInScope.y]
					.remvoeLife(this);
			
			
			boolean born = false;
			for (int dLine = -1; dLine <= 1 && !born; dLine++) {
				for (int dCol = -1; dCol <= 1 && !born; dCol++) {

					WorldItem[][] wi_newScope = Scope.getVisibilityScope(
									positionLine + dLine, 
									positionCol + dCol, this);
					
					if (wi_scope != null && wi_newScope != null
							&& wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
									.getLife() == null) {

						wi_newScope[getPnt_positionInScope().x][getPnt_positionInScope().y]
								.addLife(getCreature(positionLine + dLine, 
										positionCol + dCol));
						born = true;
					}
				}
			}


		} else {
//			System.out.println("error");
//			Status.getLogger().severe("not initialized yet" + wi_newScope);
		}
	}

	public abstract Creature getCreature(int _positionLine, int _positionCol);
	
	public void move(int _dX, int _dY) {

		WorldItem[][]  wi_scope = Scope.getVisibilityScope(this);
		WorldItem[][] wi_newScope = Scope.getVisibilityScope(
						positionLine + _dX, 
						positionCol + _dY, this);
		
		if (wi_scope != null && wi_newScope != null
				&& wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
						.getLife() == null) {
			
			wi_scope[pnt_positionInScope.x][pnt_positionInScope.y]
					.remvoeLife(this);

			positionLine += _dX;
			positionCol  += _dY;

			wi_newScope[getPnt_positionInScope().x][getPnt_positionInScope().y]
					.addLife(this);
		} else if (wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
						.getLife() != null){
			
			if (wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
						.getLife() instanceof Wolf) {
				if (this instanceof Wolf) {

					System.out.println("not implemented yet");
					System.out.println(getClass());
					
				} 
				
			} else if (wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
						.getLife() instanceof Sheep
						|| wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
								.getLife() instanceof Monkey) {
				if (this instanceof Wolf) {

					cTimeWithoutFood = 0;

					wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
							.remvoeLife(this);
					move(_dX, _dY);
					
				} else if (this instanceof Sheep){
					System.out.println("not implemented yet");
					System.out.println(getClass());
				}
			}
//			System.out.println("error");
//			Status.getLogger().severe("not initialized yet" + wi_newScope);
		}

	}
	
	/**
	 * @return the visibleLines
	 */
	public int getVisibleLines() {
		return visibleLines;
	}
	/**
	 * @return the visibleCols
	 */
	public int getVisibleCols() {
		return visibleCols;
	}
	/**
	 * @return the pnt_positionInScope
	 */
	public Point getPnt_positionInScope() {
		return pnt_positionInScope;
	}


	/**
	 * @return the feminin
	 */
	public boolean isFeminin() {
		return feminin;
	}


	/**
	 * @return the pregnant
	 */
	public boolean isPregnant() {
		return pregnant;
	}


	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}


	/**
	 * @param alive the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}


	@Override 
	public void increaseLifetime() {
		super.increaseLifetime();
		this.increaseTimePregnancy();
		this.increaseTimeWithoutFood();
		if(
				getMaxLifetime() < getLifetime()
				|| maxTimeWithoutFood < cTimeWithoutFood) {
			
			setAlive(false);
		}
		if ( cTimePregnany >= totalTimePregnancy) {
			pregnant = false;
			cTimePregnany = 0;
			birth();
		}
	}
	
	public void increaseTimeWithoutFood() {
		cTimeWithoutFood++;
	}
	public void increaseTimePregnancy() {
		if (isPregnant()) {
			cTimePregnany++;
		}
	}
	/**
	 * @return the maxLifetime
	 */
	public int getMaxLifetime() {
		return maxLifetime;
	}
	/**
	 * @return the minAgePregnancy
	 */
	public int getMinAgePregnancy() {
		return minAgePregnancy;
	}
	/**
	 * @param minAgePregnancy the minAgePregnancy to set
	 */
	public void setMinAgePregnancy(int minAgePregnancy) {
		this.minAgePregnancy = minAgePregnancy;
	}

}
