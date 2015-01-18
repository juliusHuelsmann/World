package model.life;

import java.awt.Point;
import java.util.Random;

import model.map.Scope;
import model.map.WorldItem;
import model.util.Status;
import model.util.adt.list.List;


public abstract class Plant extends Life {

	public Plant(final Point _emissionScope, final Point _position,
			final double _emissionMult) {
		super(_emissionScope, _position, _emissionMult);
	}
	List<Creature> ls_creatureEats;
	List<Creature> ls_creatureDestroys;
	List<Creature> ls_creatureHarvest;
	

	public void die() {

		
		WorldItem[][]  wi_scope = Scope.getEmissionScope(this);

		if (wi_scope != null 
				&& wi_scope[wi_scope.length / 2][wi_scope[0].length / 2]
						.getLife() != null) {

			wi_scope[wi_scope.length / 2][wi_scope[0].length / 2]
					.remvoeLife(this);
		} else {
//			System.out.println("error");
			Status.getLogger().severe("not initialized yet");
		}

	}

	public abstract Plant getPlant(int _positionLine, int _positionCol);
	public void reproduce() {

		WorldItem[][]  wi_scope = Scope.getEmissionScope(this);

		if (wi_scope != null ){

			final int posXInScope = wi_scope.length /2;
			final int posYInScope = wi_scope[0].length /2;
			int row = new Random().nextInt(wi_scope.length);
			int col = new Random().nextInt(wi_scope[0].length);

			if (wi_scope[row][col].getLife() == null) {

				Plant plant = getPlant(
						getPositionLine() + row - posXInScope, 
						getPositionCol() + col - posYInScope);
				wi_scope[row][col]
						.addLife(plant);
			}
		} 
			
		
		
	}
}
