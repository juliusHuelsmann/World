package model.life.plant;

import java.awt.Color;
import java.awt.Point;

import model.life.Plant;

public class Grass extends Plant {

	public Grass(final int _positionLine, final int _positionCol) {
		super(
				//emission scope
				new Point(3, 3),
				new Point (_positionLine, _positionCol),
				0);
	}
	public Grass getPlant(final int _positionLine, final int _positionCol){
		return new Grass(_positionLine, _positionCol);
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
		return new Color(210, 255, 215);
	}

	@Override
	public String getPath() {
		return "";
	}

}
