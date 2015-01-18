package model.life.creature;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import model.life.Creature;
import model.map.Scope;

public class Sheep extends Creature{

	public Sheep(final int _positionLine, final int _positionCol) {
		super(
				//visibility scope
				new Point(4, 4),

				//position in scope of life form
				new Point(0, 0),
				
				//current position
				new Point(_positionLine, _positionCol),
				
				//delay,
				3,
				
				//max time without food
				25,
				
				//max lifetime
				160,
				
				//min age pregnancy
				9,
				
				//time of pregnancy
				3,
				//emission scope
				new Point(5, 5),
				
				0.5);
	}
	@Override
	public void planckTime() {
		
		if (isAlive()) {

			increaseLifetime();
			
			if (getLifetime() % reactionTime == 0) {
				
				
					Point p_partner = Scope.smellPartner(this);
					Point p_nurriture = Scope.smellNuritureForSheep(this);

					
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
						
						if (getPercentageHunger() > 25) {

							move(p_nurriture.x, p_nurriture.y);
							
						}  else {
							move(p_partner.x, p_partner.y);
						}
					}
			}
		} else {
			die();
		}
	}
	

	public Sheep getCreature(int _positionLine, int _positionCol){
		return new Sheep(_positionLine, _positionCol);
	}
	@Override
	public Color getClrBackground() {
		return Color.white;
	}

	@Override
	public String getPath() {
		return "";
	}

}