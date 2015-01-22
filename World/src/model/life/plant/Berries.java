package model.life.plant;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import model.Statistic;
import model.life.Plant;

public class Berries extends Plant {

	final int reproducionTime = 15 + new Random().nextInt(30);
	final int timeOfLife = 30 + new Random().nextInt(10);
	
	public Berries(final int _positionLine, final int _positionCol) {
		super(
				//emission scope
				new Point(9, 9),
				new Point (_positionLine, _positionCol),
				0.2, 5);

		if (!( _positionCol == -1 && _positionLine == -1)) {
			Statistic.increaseAmountBerries();
		}
	}
	public Berries getPlant(final int _positionLine, final int _positionCol){
		return new Berries(_positionLine, _positionCol);
	}
	
	@Override
	public Color getClrBackground() {

		return new Color(100, 12, 16);	
		}

	@Override
	public String getPath() {
		return "";
	}


	@Override
	public void planckTime() {

		increaseLifetime();
		if ( getLifetime() % reproducionTime == 0) {
			
			int i = new Random().nextInt(4);
			for (int j = 0; j < i; j++) {

				reproduce();
			}
		} else if (getLifetime() >= timeOfLife ) {
			die();
		}
	}
}
