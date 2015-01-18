package model.life.plant;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import model.Statistic;
import model.life.Plant;

public class Grass extends Plant {
	
	final int maxreproducionTime = 10 + new Random().nextInt(30);
	final int maxlifeTime = 90 + new Random().nextInt(20);

	public Grass(final int _positionLine, final int _positionCol) {
		super(
				//emission scope
				new Point(9, 9),
				new Point (_positionLine, _positionCol),
				0.3);
		
		if (!( _positionCol == -1 && _positionLine == -1)) {
			Statistic.increaseAmountGrass();
		}
	}
	public Grass getPlant(final int _positionLine, final int _positionCol){
		return new Grass(_positionLine, _positionCol);
	}
	@Override
	public void planckTime() {

		increaseLifetime();
		if ( getLifetime() % maxreproducionTime == 0) {
			reproduce();
		} 
		
		if (getLifetime() > maxlifeTime) {
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
