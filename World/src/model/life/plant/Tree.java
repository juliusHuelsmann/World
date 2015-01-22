package model.life.plant;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import model.Statistic;
import model.life.Plant;

public class Tree extends Plant {

	final int reproducionTime = 60 + new Random().nextInt(45);
	final int lifeTime = 90 + new Random().nextInt(25);

	public Tree(final int _positionLine, final int _positionCol) {
		super(
				//emission scope
				new Point(6, 6),
				new Point (_positionLine, _positionCol),
				0.1, 10 );

		if (!( _positionCol == -1 && _positionLine == -1)) {
			Statistic.increaseAmountTrees();
		}
	}
	public Tree getPlant(final int _positionLine, final int _positionCol){
		return new Tree(_positionLine, _positionCol);
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
		return new Color(15, 100, 16);
	}

	@Override
	public String getPath() {
		return "";
	}

}
