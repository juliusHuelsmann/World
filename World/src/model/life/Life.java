package model.life;

import java.awt.Color;
import java.awt.Point;

import model.Statistic;
import model.map.Scope;
import model.map.WorldItem;


public abstract class Life {

	private Point emissionScope;
	private double emissionMulitplicator;

	
	/**
	 * 
	 */
	protected int positionLine = 0, positionCol = 0;
	public Life(final Point _emissionScope,
			final Point _position,
			final double _emissionMultiplicator) {
		
		this.emissionMulitplicator =  _emissionMultiplicator;
		this.emissionScope = _emissionScope;
		this.positionCol = _position.y;
		this.positionLine = _position.x;

	}
	private boolean moved = false;
	public void startPlanckTime(){
		
		if (allowedToMove()) {
			Statistic.add(this);
			planckTime();
			moved = true;
			emitSmell();
		}
	}
	public abstract void die();
	
	public void emitSmell() {
		
		
		WorldItem [][] wi = Scope.getEmissionScope(this);
		
		final int posXinScopeEmission = wi.length / 2;
		final int posYinScopeEmission = wi[0].length / 2;
		
		for (int line = 0; line < wi.length; line++) {
			for (int col = 0; col < wi[line].length; col++) {
				
				wi[line][col].getLifeEmission().increase(this,
						getEmissionMulitplicator() * 
						Math.pow(0.7, Math.abs(line - posXinScopeEmission)) * 
						Math.min(
								Math.abs(posXinScopeEmission - Math.abs(line - posXinScopeEmission)),
								Math.abs(posYinScopeEmission - Math.abs(col - posYinScopeEmission))));
			}
		}
		

		wi[posXinScopeEmission][posYinScopeEmission].getLifeEmission().increase(
				this, 2); 
		
	}
	
	public void endPlanckTime() {

			moved = false;
	}
	
	public boolean allowedToMove() {
		return !moved;
	}
	
	private int lifetime = 0;
	
	
	
	protected abstract void planckTime();
	public abstract Color getClrBackground();
	public abstract String getPath();
	/**
	 * @return the lifetime
	 */
	public int getLifetime() {
		return lifetime;
	}
	/**
	 */
	public void increaseLifetime() {
		lifetime ++;
	}

	/**
	 * @return the emissionScope
	 */
	public Point getEmissionScope() {
		return emissionScope;
	}

	/**
	 * @param emissionScope the emissionScope to set
	 */
	public void setEmissionScope(Point emissionScope) {
		this.emissionScope = emissionScope;
	}

	/**
	 * @return the positionLine
	 */
	public int getPositionLine() {
		return positionLine;
	}
	/**
	 * @param positionLine the positionLine to set
	 */
	public void setPositionLine(int positionLine) {
		this.positionLine = positionLine;
	}
	/**
	 * @return the positionCol
	 */
	public int getPositionCol() {
		return positionCol;
	}
	/**
	 * @param positionCol the positionCol to set
	 */
	public void setPositionCol(int positionCol) {
		this.positionCol = positionCol;
	}

	/**
	 * @return the emissionMulitplicator
	 */
	public double getEmissionMulitplicator() {
		return emissionMulitplicator;
	}

	/**
	 * @param emissionMulitplicator the emissionMulitplicator to set
	 */
	public void setEmissionMulitplicator(double emissionMulitplicator) {
		this.emissionMulitplicator = emissionMulitplicator;
	}
}
