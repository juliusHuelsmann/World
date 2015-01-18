package model.life.plant;

import java.awt.Color;
import java.awt.Point;

import model.life.Plant;

public class Tree extends Plant {

	public Tree(final int _positionLine, final int _positionCol) {
		super(
				//emission scope
				new Point(6, 6),
				new Point (_positionLine, _positionCol),
				0.1);
	}
	public Tree getPlant(final int _positionLine, final int _positionCol){
		return new Tree(_positionLine, _positionCol);
	}
	@Override
	public void planckTime() {

		increaseLifetime();
		if ( getLifetime() % 24 == 0) {
			reproduce();
		} else if (getLifetime() % 50 == 0) {
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
