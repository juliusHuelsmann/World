package model.life.creature;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import model.life.Creature;

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
