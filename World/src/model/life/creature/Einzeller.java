package model.life.creature;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import model.Statistic;
import model.life.Creature;
import model.map.Scope;

public class Einzeller extends Creature{

	public Einzeller(final int _positionLine, final int _positionCol) {
		super(
				//visibility scope
				new Point(4, 4), 
				
				//position in scope of life form
				new Point(2, 1),
				
				//current position
				new Point(_positionLine, _positionCol),
				
				//delay
				4,
				
				//max time without food
				122,
				
				//max lifetime
				35, 
				
				//min age pregnancy
				4,
				
				//time of pregnancy
				10, 
				
				//emission scope
				new Point(6, 6),
				
				0.2
				);

//		if (!( _positionCol == -1 && _positionLine == -1)) {
//
//		}
	}

	@Override
	public void planckTime() {
		
		if (isAlive()) {

			increaseLifetime();
			
			if (getLifetime() % reactionTime == 0) {

				
				if (getLifetime() > getMinAgePregnancy()) {

					setPregnant(true);

					int row = new Random().nextInt(3) - 1;
					int col = new Random().nextInt(3) - 1;
					if (row == col && col == 0) {
						row = 1;
					}
					move(row, col);
				}
			}
		} else {
			die();
		}
	}
	

	public Einzeller getCreature(int _positionLine, int _positionCol){
		return new Einzeller(_positionLine, _positionCol);
	}

	@Override
	public Color getClrBackground() {
		return Color.YELLOW;
	}

	@Override
	public String getPath() {
		return "";
	}
}
