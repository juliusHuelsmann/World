package model.life.creature;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import model.life.Creature;
import model.map.Scope;

public class Wolf extends Creature{

	public Wolf(final int _positionLine, final int _positionCol) {
		super(
				//visibility scope
				new Point(5, 5), 

				//position in scope of life form
				new Point(1, 1),
				
				//current position
				new Point(_positionLine, _positionCol),
				
				//delay
				1,
				
				//max time without food
				20,
				
				//max lifetime
				120,
				
				//min age pregnancy
				25,
				
				//time of pregnancy
				10,
				//emission scope
				new Point(9, 9),
				
				1);
		// TODO Auto-generated constructor stub
	}

	
	
	
	@Override
	public void planckTime() {
		
		if (isAlive()) {

			increaseLifetime();
			
			if (getLifetime() % reactionTime == 0) {
				
				
				
				//different behavior if is pregnant or not
//				if (false && isPregnant()) {
//					
//				} else {


					Point p_partner = Scope.smellPartner(this);
					Point p_nurriture = Scope.smellNuritureForWolf(this);

					
					if (getLifetime() < getMinAgePregnancy()) {
						p_partner = null;
					}
					
					if (p_partner == null && p_nurriture == null) {
						
						//random movement
						
						
						int row = new Random().nextInt(3) - 1;
						int col = new Random().nextInt(3) - 1;
						if (row == col && col == 0) {
							row = 1;
						}
						move(row, col);
						
					} else if (p_nurriture != null && p_partner == null) {
						move(p_nurriture.x, p_nurriture.y);
						
					} else if (p_nurriture == null && p_partner != null) {

						move(p_partner.x, p_partner.y);
					} else {
						//smelled both wolf and sheep.
						
						if (getPercentageAge() > 50) {

							move(p_partner.x, p_partner.y);
						} else if (getPercentageHunger() > 25) {

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
}