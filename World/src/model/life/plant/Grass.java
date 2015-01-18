package model.life.plant;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import model.life.Plant;

public class Grass extends Plant {
	
	final int reproducionTime = new Random(30).nextInt(99);
	final int lifeTime = new Random(90).nextInt(105);

	public Grass(final int _positionLine, final int _positionCol) {
		super(
				//emission scope
				new Point(9, 9),
				new Point (_positionLine, _positionCol),
				0.3);
	}
	public Grass getPlant(final int _positionLine, final int _positionCol){
		return new Grass(_positionLine, _positionCol);
	}
	@Override
	public void planckTime() {

		increaseLifetime();
		if ( getLifetime() % reproducionTime == 0) {
			reproduce();
		} else if (getLifetime() % lifeTime == 0) {
			die();
		}
	}

	@Override
	public Color getClrBackground() {
		return new Color(220, 255, 225);
	}

	@Override
	public String getPath() {
		return "";
	}

}
