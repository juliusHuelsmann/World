package model.map;

import java.awt.Point;

import model.life.Creature;
import model.life.Life;
import model.life.creature.Monkey;
import model.life.creature.Sheep;
import model.life.creature.Wolf;
import model.life.plant.Berries;
import model.life.plant.Grass;
import model.life.plant.Tree;

public class Scope {

	private static Scope instance;
	
	private World world;
	
	
	/**
	 * 
	 * @param _creature
	 * @return
	 * 			_rectanlge.x = dX
	 * 			_rectanlge.y = dY
	 * 			_rectanlge.width = distance between _creature and next sheep
	 * 			_rectanlge.height = the total amount of sheep visible
	 * 
	 */
//	public static Rectangle seeSheep(final Creature _creature) {
//	}
	public static Point smellSheepDirectNeighborhood(final Creature _creature){
		WorldItem[][] emissionScope = getVisibilityScope(_creature);
		
		int maxDLine = 0;
		int maxDCol = 0;
		int maxVal = 0;
		
		for (int line = -1; line <= 1; line++) {
			for (int col = -1; col <= 1; col++) {
				
				int cline = _creature.getPnt_positionInScope().x + line;
				int cCol = _creature.getPnt_positionInScope().y + col;
				int emission 
				
				= emissionScope[cline][cCol].getLifeEmission().getSheep();
				
				if (emission > maxVal) {
					maxVal = emission;
					maxDLine = line;
					maxDCol = col;
				}
			}
		}
		if (maxVal > 0) {
			return new Point(maxDLine, maxDCol);
		} else return null;
	}

	public static Point smellSheep(final Creature _creature){
		return smellCreature(_creature, new Sheep(-1, -1), null);
	}
	
public static Point smellPartner(final Creature _creature){

		
		WorldItem[][] visibilityScope = getVisibilityScope(_creature);
		
		int maxDLine = 0;
		int maxDCol = 0;
		int maxVal = 0;
		
		for (int line = 0; line < visibilityScope.length; line++) {
			for (int col = 0; col < visibilityScope[line].length; col++) {

				
				if (visibilityScope[line][col].contains(_creature)){

					Creature c2 = (Creature) visibilityScope[line][col].getContained(_creature);
					
					if (c2.isFeminin() != _creature.isFeminin()){
						
						if (c2.isFeminin()) {
							if (c2.isPregnant()) {
								continue;
							}
						} else {
							if (_creature.isPregnant())
								return null;
						}

						int emission
						= Math.min (
								Math.abs(line - _creature.getPnt_positionInScope().x), 
								Math.abs(col - _creature.getPnt_positionInScope().y));
						
						if (emission > maxVal) {
							maxVal = emission;
							maxDLine = line;
							maxDCol = col;
						}
					}
					
				}
			}
		}
		
		if (maxVal > 0) {

			maxDLine = maxDLine - _creature.getPnt_positionInScope().x;
			maxDCol = maxDCol - _creature.getPnt_positionInScope().y;

			
			if (maxDLine < 0) {
				maxDLine = -1;
			} else if (maxDLine > 0) {
				maxDLine = 1;
			}if (maxDCol< 0) {
				maxDCol = -1;
			} else if (maxDCol > 0) {
				maxDCol = 1;
			}
			
			if (maxDCol == 0 && maxDLine == 0) {
				return null;
			}
			return new Point(maxDLine, maxDCol);
		} else return null;
	}


public static Point smellWolfFriend(final Wolf _creature){

	
	WorldItem[][] visibilityScope = getVisibilityScope(_creature);
	
	int maxDLine = 0;
	int maxDCol = 0;
	int maxVal = 0;
	
	for (int line = 0; line < visibilityScope.length; line++) {
		for (int col = 0; col < visibilityScope[line].length; col++) {

			
			if (visibilityScope[line][col].contains(_creature)){

				Wolf c2 = (Wolf) visibilityScope[line][col].getContained(_creature);
				
				if (c2.getLeaderIndex() > _creature.getLeaderIndex()
						&& c2.isFeminin() == _creature.isFeminin()){

					int emission
					= Math.min (
							Math.abs(line - _creature.getPnt_positionInScope().x), 
							Math.abs(col - _creature.getPnt_positionInScope().y));
					
					if (emission > maxVal) {
						maxVal = emission;
						maxDLine = line;
						maxDCol = col;
					}
				}
				
			}
		}
	}
	
	if (maxVal > 0) {

		maxDLine = maxDLine - _creature.getPnt_positionInScope().x;
		maxDCol = maxDCol - _creature.getPnt_positionInScope().y;

		
		if (maxDLine < 0) {
			maxDLine = -1;
		} else if (maxDLine > 0) {
			maxDLine = 1;
		}if (maxDCol< 0) {
			maxDCol = -1;
		} else if (maxDCol > 0) {
			maxDCol = 1;
		}
		
		if (maxDCol == 0 && maxDLine == 0) {
			return null;
		}
		return new Point(maxDLine, maxDCol);
	} else return null;
}

public static Point smellFriend(final Creature _creature){

	
	WorldItem[][] visibilityScope = getVisibilityScope(_creature);
	
	int maxDLine = 0;
	int maxDCol = 0;
	int maxVal = 0;
	
	for (int line = 0; line < visibilityScope.length; line++) {
		for (int col = 0; col < visibilityScope[line].length; col++) {

			
			if (visibilityScope[line][col].contains(_creature)){

				Creature c2 = (Creature) visibilityScope[line][col].getContained(_creature);
				
				if (c2.isFeminin() == _creature.isFeminin()){

					int emission
					= Math.min (
							Math.abs(line - _creature.getPnt_positionInScope().x), 
							Math.abs(col - _creature.getPnt_positionInScope().y));
					
					if (emission > maxVal) {
						maxVal = emission;
						maxDLine = line;
						maxDCol = col;
					}
				}
				
			}
		}
	}
	
	if (maxVal > 0) {

		maxDLine = maxDLine - _creature.getPnt_positionInScope().x;
		maxDCol = maxDCol - _creature.getPnt_positionInScope().y;

		
		if (maxDLine < 0) {
			maxDLine = -1;
		} else if (maxDLine > 0) {
			maxDLine = 1;
		}if (maxDCol< 0) {
			maxDCol = -1;
		} else if (maxDCol > 0) {
			maxDCol = 1;
		}
		
		if (maxDCol == 0 && maxDLine == 0) {
			return null;
		}
		return new Point(maxDLine, maxDCol);
	} else return null;
}
	
	public static Point smellWolf(final Creature _creature){
		return smellCreature(_creature, new Wolf(-1, -1), null);
	}public static Point smellMonkey(final Creature _creature){
		return smellCreature(_creature, new Monkey(-1, -1), null);
	}
	
	public static Point smellNuritureForWolf(final Creature _creature) {

		return smellCreature(_creature, new Monkey(-1, -1), new Sheep(-1, -1));	}
	

	public static Point smellNuritureForMonkey(Monkey monkey) {

		return smellCreature(monkey, new Berries(-1, -1), new Tree(-1, -1));
	}
	
	public static Point smellNuritureForSheep(final Creature _creature) {

		return smellCreature(_creature, new Grass(-1, -1), null);	}
	
	
	public static Point smellCreature(final Creature _smellingCreature,
			final Life _smelledCreature1, final Life _smelledCreature2) {

		WorldItem[][] visibilityScope = getVisibilityScope(_smellingCreature);
		
		int maxDLine = 0;
		int maxDCol = 0;
		int maxVal = 0;
		
		for (int line = 0; line < visibilityScope.length; line++) {
			for (int col = 0; col < visibilityScope[line].length; col++) {

				int emission1 = 0, emission2 = 0;
				if (_smelledCreature1 != null)
					emission1 = visibilityScope[line][col].getLifeEmission().get(_smelledCreature1);
				if (_smelledCreature2 != null)
					emission2 = visibilityScope[line][col].getLifeEmission().get(_smelledCreature2);
				
				int emission = Math.max (emission1, emission2);
				
				if (emission > maxVal) {
					maxVal = emission;
					maxDLine = line;
					maxDCol = col;
				}
			}
		}
		
		if (maxVal > 0) {

			maxDLine = maxDLine - _smellingCreature.getPnt_positionInScope().x;
			maxDCol = maxDCol - _smellingCreature.getPnt_positionInScope().y;

			
			if (maxDLine < 0) {
				maxDLine = -1;
			} else if (maxDLine > 0) {
				maxDLine = 1;
			}if (maxDCol< 0) {
				maxDCol = -1;
			} else if (maxDCol > 0) {
				maxDCol = 1;
			}
			
			if (maxDCol == 0 && maxDLine == 0) {
				return null;
			}
			return new Point(maxDLine, maxDCol);
		} else return null;
	
		
	}
	
	
	public static Point smellWolfDirectNeighborhood(final Creature _creature){
		WorldItem[][] emissionScope = getVisibilityScope(_creature);
		
		int maxDLine = 0;
		int maxDCol = 0;
		int maxVal = 0;
		
		for (int line = -1; line <= 1; line++) {
			for (int col = -1; col <= 1; col++) {
				
				int cline = _creature.getPnt_positionInScope().x + line;
				int cCol = _creature.getPnt_positionInScope().y + col;
				int emission 
				
				= emissionScope[cline][cCol].getLifeEmission().getWolf();
				
				if (emission > maxVal) {
					maxVal = emission;
					maxDLine = line;
					maxDCol = col;
				}
			}
		}
		if (maxVal > 0) {
			return new Point(maxDLine, maxDCol);
		} else return null;
	}
	
	private Scope(World _world) {
		this.world = _world;
	}
	public static WorldItem[][] getVisibilityScope(final Creature _creature) {
		
		return getVisibilityScope(_creature.getPositionLine(), 
				_creature.getPositionCol(),
				_creature);
	}
	

	public static synchronized WorldItem[][] getVisibilityScope(
			final int _line, final int _cols,
			final Creature _creature) {
		
		if (getInstance() == null) {
			return null;
		} 
		final int totalMapRows = getInstance().world.getWi_world().length;
		WorldItem[][] toReturn = new WorldItem
				[_creature.getVisibleLines()]
				[_creature.getVisibleCols()];
		
		for (int line = 0; line < toReturn.length;line++) {
			final int cline = line 
					+ _line - _creature.getPnt_positionInScope().x;

			final int totalMapCols =
					getInstance().world.getWi_world()[line].length;
			
			for (int col = 0; col < toReturn[line].length; col++) {
				final int cCol = col + _cols 
						- _creature.getPnt_positionInScope().y;
				
				toReturn[line][col] = getInstance().world.getWi_world()
								[modulo(cline, totalMapRows)]
										[modulo(cCol, totalMapCols)];
			}
		}
		return toReturn;
	}
	
	

	public static WorldItem[][] getEmissionScope(final Life _creature) {
		
		return getEmissionScope(
				_creature.getPositionLine(), 
				_creature.getPositionCol(),
				_creature);
	}
	

	private static synchronized WorldItem[][] getEmissionScope(
			final int _line, final int _cols,
			final Life _creature) {
		
		if (getInstance() == null) {
			return null;
		} 
		final int totalMapRows = getInstance().world.getWi_world().length;
		WorldItem[][] toReturn = new WorldItem
				[_creature.getEmissionScope().x]
				[_creature.getEmissionScope().y];
		final int positionInSmellScopeX = _creature.getEmissionScope().x / 2;
		final int positionInSmellScopeY = _creature.getEmissionScope().y / 2;
		
		for (int line = 0; line < toReturn.length;line++) {
			final int cline = line + _line - positionInSmellScopeX;

			final int totalMapCols =
					getInstance().world.getWi_world()[line].length;
			
			for (int col = 0; col < toReturn[line].length; col++) {
				final int cCol = col + _cols - positionInSmellScopeY;
				
				toReturn[line][col] = getInstance().world.getWi_world()
								[modulo(cline, totalMapRows)]
										[modulo(cCol, totalMapCols)];
			}
		}
		return toReturn;
	}
	
	
	private static int modulo(int _x, int _y) {
		int m = _x % _y;
		if (m < 0 ){
			return modulo(m + _y, _y);
		}
		return m;
	}
	
	private static Scope getInstance() {
		return instance;
	}
	
	public static Scope initializeInstance(World _world) {

		instance = new Scope(_world);
		return instance;
	}


}
