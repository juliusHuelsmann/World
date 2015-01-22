package model.life.creature;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import model.Statistic;
import model.life.Creature;
import model.map.Scope;

public class Wolf extends Creature{

	private double leaderIndex = new Random().nextInt(2) * new Random().nextDouble() * 15.0
			+ new Random().nextDouble() * 1.0;
	
	
	public Wolf(final int _positionLine, final int _positionCol) {
		super(
				//visibility scope
				new Point(6, 6), 

				//position in scope of life form
				new Point(1, 1),
				
				//current position
				new Point(_positionLine, _positionCol),
				
				//delay
				2,
				
				//max time without food
				60,
				
				//max lifetime
				200,
				
				//min age pregnancy
				25,
				
				//time of pregnancy
				20,
				//emission scope
				new Point(9, 9),
				
				1);

		if (!( _positionCol == -1 && _positionLine == -1)) {
			Statistic.increaseAmountWolfs();
		}
	}

	
	
	
	@Override
	public void planckTime() {
		
		if (isAlive()) {

			increaseLifetime();
			
			if (getLifetime() % reactionTime == 0) {
				
				Point p_partner = Scope.smellPartner(this);
				Point p_friend = Scope.smellWolfFriend(this);
				Point p_nurriture = Scope.smellNuritureForWolf(this);

					
				if (getLifetime() < getMinAgePregnancy()
						&& p_nurriture == null) {
					p_partner = null;
				}
					
				if (p_partner == null && p_nurriture == null) {
				
					//random movement
					if (p_friend == null) {

						
						int row = new Random().nextInt(3) - 1;
						int col = new Random().nextInt(3) - 1;
						if (row == col && col == 0) {
							row = 1;
						}
						move(row, col);
					} else {

						move(p_friend.x, p_friend.y);
					}
						
						
				} else if (p_nurriture != null && p_partner == null) {
						move(p_nurriture.x, p_nurriture.y);
						
				} else if (p_nurriture == null && p_partner != null) {

						move(p_partner.x, p_partner.y);
				} else {
						//smelled both wolf and sheep.
						
						if (getPercentageHunger() > 25) {

							move(p_nurriture.x, p_nurriture.y);
						}  else {
							move(p_partner.x, p_partner.y);
						}
					}
//				}
			}
		} else {
			die();
		}
	}


	public Wolf getCreature(int _positionLine, int _positionCol){
		return new Wolf(_positionLine, _positionCol);
	}
	@Override
	public Color getClrBackground() {
		return Color.orange;
	}

	@Override
	public String getPath() {
		return "";
	}




	/**
	 * @return the leaderIndex
	 */
	public double getLeaderIndex() {
		return leaderIndex;
	}




	/**
	 * @param leaderIndex the leaderIndex to set
	 */
	public void setLeaderIndex(double leaderIndex) {
		this.leaderIndex = leaderIndex;
	}
}