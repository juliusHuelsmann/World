package model.life;

import java.awt.Point;
import java.util.Random;

import model.Statistic;
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

	private void setTimeWithoutFood(int _timewithoutfood) {
		this.cTimeWithoutFood = _timewithoutfood;
	}
	
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
			Statistic.removeLife(this);
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
						.getLife().find(this)) {
			
//			wi_scope[pnt_positionInScope.x][pnt_positionInScope.y]
//					.remvoeLife(this);
			
			
			boolean born = false;
			for (int dLine = -1; dLine <= 1 && !born; dLine++) {
				for (int dCol = -1; dCol <= 1 && !born; dCol++) {

					WorldItem[][] wi_newScope = Scope.getVisibilityScope(
									positionLine + dLine, 
									positionCol + dCol, this);
					
					if (wi_scope != null && wi_newScope != null
							&& (wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
									.getAmountOfContained() == 0
									|| 
									(wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
											.getAmountOfContained() == 1 
											&&

											(wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
													.containsGrass())))) {

						Creature c = getCreature(positionLine + dLine, 
								positionCol + dCol);
						c.setTimeWithoutFood(cTimeWithoutFood / 2);
						wi_newScope[getPnt_positionInScope().x][getPnt_positionInScope().y]
								.addLife(c);
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
		
		if (wi_scope != null 
				&& wi_newScope != null) {
			
			
			//interaction
			
			if (this instanceof Sheep) {

				//sheep vs sheep
				if (wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
								.containsSheep()){

					if ( this.isFeminin() != wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
							.getContainedSheep().isFeminin()) {
						
						if (this.isFeminin()) {
							setPregnant(true);
						} else {
							wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
									.getContainedSheep().setPregnant(true);
						}
					}							
						
				} else if(wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
								.containsGrass()) {

					cTimeWithoutFood = 0;
					wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
							.removeGrass();
					move(_dX, _dY);
				}
				
				
				
				
				
				
				
				
			}
			
			
			if (wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
						.addLife(this)) {
			
				wi_scope[pnt_positionInScope.x][pnt_positionInScope.y]
						.remvoeLife(this);
				positionLine += _dX;
				positionCol  += _dY;
				
			} else {
				if (wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
						.containsWolf()) {
					if (this instanceof Wolf) {
						
						if ( this.isFeminin() != wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
								.getContainedWolf().isFeminin()) {
							
							if (this.isFeminin()) {
								setPregnant(true);
							} else {
								wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
										.getContainedWolf().setPregnant(true);
							}
						} else {
//							System.out.println("wrong.");
						}
					} 
					
				} else if (wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
						.containsSheep()
							|| wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
									.containsMonkey()) {
					if (this instanceof Wolf) {
	
						cTimeWithoutFood = 0;
	
						wi_newScope[pnt_positionInScope.x][pnt_positionInScope.y]
								.removeCreature();
						move(_dX, _dY);
						
					} 
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


	/**
	 * @param pregnant the pregnant to set
	 */
	public void setPregnant(boolean pregnant) {
		this.pregnant = pregnant;
	}

}
