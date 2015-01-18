package model.life.creature;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import model.life.Creature;
import model.map.Scope;

public class Monkey extends Creature{

	public Monkey(final int _positionLine, final int _positionCol) {
		super(
				//visibility scope
				new Point(5, 4), 
				
				//position in scope of life form
				new Point(2, 1),
				
				//current position
				new Point(_positionLine, _positionCol),
				
				//delay
				2,
				
				//max time without food
				122,
				
				//max lifetime
				100, 
				
				//min age pregnancy
				9,
				
				//time of pregnancy
				4, 
				
				//emission scope
				new Point(6, 6),
				
				0.2
				);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void planckTime() {
		
		if (isAlive()) {

			increaseLifetime();
			
			if (getLifetime() % reactionTime == 0) {
				
				
					Point p_partner = Scope.smellPartner(this);
					Point p_nurriture = Scope.smellNuritureForMonkey(this);

					
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
	

	public Monkey getCreature(int _positionLine, int _positionCol){
		return new Monkey(_positionLine, _positionCol);
	}

	@Override
	public Color getClrBackground() {
		return Color.magenta;
	}

	@Override
	public String getPath() {
		return "";
	}
}
