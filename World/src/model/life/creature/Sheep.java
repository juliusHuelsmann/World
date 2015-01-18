package model.life.creature;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import model.life.Creature;

public class Sheep extends Creature{

	public Sheep(final int _positionLine, final int _positionCol) {
		super(
				//visibility scope
				new Point(2, 2),

				//position in scope of life form
				new Point(0, 0),
				
				//current position
				new Point(_positionLine, _positionCol),
				
				//delay,
				3,
				
				//max time without food
				15,
				
				//max lifetime
				60,
				
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
		increaseLifetime();
		
		if (getLifetime() % reactionTime == 0) {
			int row = new Random().nextInt(2) - 1;
			int col = new Random().nextInt(2) - 1;
			if (row == col && col == 0) {
				row = 1;
			}
			move(row, col);
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