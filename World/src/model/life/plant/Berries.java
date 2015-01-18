package model.life.plant;

import java.awt.Color;
import java.awt.Point;

import model.life.Plant;

public class Berries extends Plant {

	public Berries(final int _positionLine, final int _positionCol) {
		super(
				//emission scope
				new Point(9, 9),
				new Point (_positionLine, _positionCol),
				0.2);
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
	}
}
