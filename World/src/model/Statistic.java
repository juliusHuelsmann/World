package model;

import model.life.Life;
import model.life.creature.Monkey;
import model.life.creature.Sheep;
import model.life.creature.Wolf;
import model.life.plant.Berries;
import model.life.plant.Grass;
import model.life.plant.Tree;

public class Statistic {

	/**
	 * The current amount of life.
	 */
	private static int amountSheeps, amountWolfs, amountMonkeys, amountTrees, 
	amountGrass, amountBerries;
	
	/**
	 * The maximum amount of life in this run.
	 */
	private static int maxAmountSheeps, maxAmountWolfs, maxAmountMonkeys, maxAmountTrees, 
	maxAmountGrass, maxAmountBerries;

	/**
	 * The minimum amount of life in this run.
	 */
	private static int minAmountSheeps, minAmountWolfs, minAmountMonkeys, minAmountTrees, 
	minAmountGrass, minAmountBerries;

	private static int ageSheeps, ageWolfs, ageMonkeys, ageTrees, 
	ageGrass, ageBerries;

	private static int hungerSheeps, hungerWolfs, hungerMonkeys;

	private static int pregnantSheeps, pregnantWolfs, pregnantMonkeys;


	private static int eatenSheeps, eatenMonkeys, eatenGrass, eatenBerries;
	private static int bornSheeps, bornMonkeys, bornWolfs;
	

	public static void increaseEatenSheeps() {
		eatenSheeps++;
	}public static void increaseEatenMonkeys() {
		eatenMonkeys++;
	}

	public static void increaseEatenGrass() {
		eatenGrass++;
	}
	public static void increaseEatenBerries() {
		eatenBerries++;
	}
	public static void increaseBornSheeps() {
		bornSheeps++;
	}public static void increaseBornMonkeys() {
		bornMonkeys++;
	}
	
	
	
	public static void resetPercentages() {
		
		ageSheeps = 0;
		ageWolfs = 0;
		ageMonkeys = 0;
		ageTrees = 0;
		ageGrass = 0;
		hungerSheeps = 0;
		hungerWolfs = 0;
		hungerMonkeys = 0;
		pregnantMonkeys = 0;
		pregnantWolfs = 0;
		pregnantSheeps = 0;
	}
	
	
	public static void removeLife(Life _life) {


		if (_life instanceof Monkey) {
			decreaseAmountMonkeys();
		} else if (_life instanceof Sheep) {
			decreaseAmountSheeps();

		} else if (_life instanceof Wolf) {
			decreaseAmountWolfs();
			
		} else if (_life instanceof Grass) {
			decreaseAmountGrass();

		} else if (_life instanceof Tree) {
			decreaseAmountTrees();

		} else if (_life instanceof Berries) {
			decreaseAmountBerries();

		}
	
	}
	
	public static void add(Life _life) {

		if (_life instanceof Monkey) {
			addAgeMonkeys(((Monkey) _life).getPercentageAge());
			addHungerMonkeys(((Monkey) _life).getPercentageAge());
			if (((Monkey) _life).isPregnant()) {

				addPregnantMonkeys(((Monkey) _life).getPercentagePregancy());
			}
		} else if (_life instanceof Sheep) {
			addAgeSheeps(((Sheep) _life).getPercentageAge());
			addHungerSheeps(((Sheep) _life).getPercentageAge());
			if (((Sheep) _life).isPregnant()) {

				addPregnantSheeps(((Sheep) _life).getPercentagePregancy());
			}
		} else if (_life instanceof Wolf) {
			addAgeWolfs(((Wolf) _life).getPercentageAge());
			addHungerWolfs(((Wolf) _life).getPercentageAge());
			if (((Wolf) _life).isPregnant()) {

				addPregnantWolfs(((Wolf) _life).getPercentagePregancy());
			}
		} else if (_life instanceof Grass) {
} else if (_life instanceof Tree) {
} else if (_life instanceof Berries) {
}
	}
	

	public static void addHungerSheeps(int _add) {
		hungerSheeps += _add;
	}
	public static void addHungerWolfs(int _add) {
		hungerWolfs += _add;
	}
	public static void addHungerMonkeys(int _add) {
		hungerMonkeys += _add;
	}
	
	
	

	public static void addPregnantSheeps(int _add) {
		pregnantSheeps += _add;
	}
	public static void addPregnantWolfs(int _add) {
		pregnantWolfs += _add;
	}
	public static void addPregnantMonkeys(int _add) {
		pregnantMonkeys += _add;
	}
	
	
	

	public static void addAgeSheeps(int _add) {
		ageSheeps += _add;
	}
	public static void addAgeWolfs(int _add) {
		ageWolfs += _add;
	}
	public static void addAgeMonkeys(int _add) {
		ageMonkeys += _add;
	}
	public static void addAgeBerries(int _add) {
		ageBerries += _add;
	}
	public static void addAgeGrass(int _add) {
		ageGrass += _add;
	}
	public static void addAgeTrees(int _add) {
		ageTrees += _add;
	}
	


	public static void increaseAmountSheeps() {
		
		amountSheeps++;
		if (amountSheeps > maxAmountSheeps) {
			maxAmountSheeps = amountSheeps;
		} 
		if (amountSheeps < minAmountSheeps) {
			minAmountSheeps = amountSheeps;
		}
	}
	public static void increaseAmountWolfs() {
		amountWolfs++;
		if (amountWolfs > maxAmountWolfs) {
			maxAmountWolfs = amountWolfs;
		} 
		if (amountWolfs < minAmountWolfs) {
			minAmountWolfs = amountWolfs;
		}
	}
	public static void increaseAmountMonkeys() {
		amountMonkeys++;
		if (amountMonkeys > maxAmountMonkeys) {
			maxAmountMonkeys = amountMonkeys;
		} 
		if (amountMonkeys < minAmountMonkeys) {
			minAmountMonkeys = amountMonkeys;
		}
	}
	public static void increaseAmountTrees() {
		amountTrees++;
		if (amountTrees > maxAmountTrees) {
			maxAmountTrees = amountTrees;
		} 
		if (amountTrees < minAmountTrees) {
			minAmountTrees = amountTrees;
		}
	}
	public static void increaseAmountGrass() {
		amountGrass++;
		if (amountGrass > maxAmountGrass) {
			maxAmountGrass = amountGrass;
		} 
		if (amountGrass < minAmountGrass) {
			minAmountGrass = amountGrass;
		}
	}
	public static void increaseAmountBerries() {
		amountBerries++;
		if (amountBerries > maxAmountBerries) {
			maxAmountBerries = amountBerries;
		} 
		if (amountBerries < minAmountBerries) {
			minAmountBerries = amountBerries;
		}
	}

	public static void decreaseAmountSheeps() {
		amountSheeps--;
		if (amountSheeps > maxAmountSheeps) {
			maxAmountSheeps = amountSheeps;
		} 
		if (amountSheeps < minAmountSheeps) {
			minAmountSheeps = amountSheeps;
		}
	}
	public static void decreaseAmountWolfs() {
		amountWolfs--;
		if (amountWolfs > maxAmountWolfs) {
			maxAmountWolfs = amountWolfs;
		} 
		if (amountWolfs < minAmountWolfs) {
			minAmountWolfs = amountWolfs;
		}
	}
	public static void decreaseAmountMonkeys() {
		amountMonkeys--;
		if (amountMonkeys > maxAmountMonkeys) {
			maxAmountMonkeys = amountMonkeys;
		} 
		if (amountMonkeys < minAmountMonkeys) {
			minAmountMonkeys = amountMonkeys;
		}
	}
	public static void decreaseAmountTrees() {
		amountTrees--;
		if (amountTrees > maxAmountTrees) {
			maxAmountTrees = amountTrees;
		} 
		if (amountTrees < minAmountTrees) {
			minAmountTrees = amountTrees;
		}
	}
	public static void decreaseAmountGrass() {
		amountGrass--;
		if (amountGrass > maxAmountGrass) {
			maxAmountGrass = amountGrass;
		} 
		if (amountGrass < minAmountGrass) {
			minAmountGrass = amountGrass;
		}
	}
	public static void decreaseAmountBerries() {
		amountBerries--;
		if (amountBerries > maxAmountBerries) {
			maxAmountBerries = amountBerries;
		} 
		if (amountBerries < minAmountBerries) {
			minAmountBerries = amountBerries;
		}
	}
	/**
	 * @return the amountsheeps
	 */
	public static int getAmountsheeps() {
		return amountSheeps;
	}
	/**
	 * @return the amountwolfs
	 */
	public static int getAmountwolfs() {
		return amountWolfs;
	}
	/**
	 * @return the amountmonkeys
	 */
	public static int getAmountmonkeys() {
		return amountMonkeys;
	}
	/**
	 * @return the amountTrees
	 */
	public static int getAmountTrees() {
		return amountTrees;
	}
	/**
	 * @return the amountGrass
	 */
	public static int getAmountGrass() {
		return amountGrass;
	}
	/**
	 * @return the amountBerries
	 */
	public static int getAmountBerries() {
		return amountBerries;
	}


	/**
	 * @return the agesheeps
	 */
	public static int getAgesheeps() {
		return ageSheeps;
	}


	/**
	 * @return the agewolfs
	 */
	public static int getAgewolfs() {
		return ageWolfs;
	}


	/**
	 * @return the agemonkeys
	 */
	public static int getAgemonkeys() {
		return ageMonkeys;
	}


	/**
	 * @return the agetrees
	 */
	public static int getAgetrees() {
		return ageTrees;
	}


	/**
	 * @return the agegrass
	 */
	public static int getAgegrass() {
		return ageGrass;
	}


	/**
	 * @return the ageberries
	 */
	public static int getAgeberries() {
		return ageBerries;
	}


	/**
	 * @return the hungersheeps
	 */
	public static int getHungersheeps() {
		return hungerSheeps;
	}


	/**
	 * @return the hungerwolfs
	 */
	public static int getHungerwolfs() {
		return hungerWolfs;
	}


	/**
	 * @return the hungermonkeys
	 */
	public static int getHungermonkeys() {
		return hungerMonkeys;
	}


	/**
	 * @return the pregnantsheeps
	 */
	public static int getPregnantsheeps() {
		return pregnantSheeps;
	}


	/**
	 * @return the pregnantwolfs
	 */
	public static int getPregnantwolfs() {
		return pregnantWolfs;
	}


	/**
	 * @return the pregnantmonkeys
	 */
	public static int getPregnantmonkeys() {
		return pregnantMonkeys;
	}


	/**
	 * @return the maxAmountSheeps
	 */
	public static int getMaxAmountSheeps() {
		return maxAmountSheeps;
	}




	/**
	 * @return the maxAmountWolfs
	 */
	public static int getMaxAmountWolfs() {
		return maxAmountWolfs;
	}



	/**
	 * @return the maxAmountMonkeys
	 */
	public static int getMaxAmountMonkeys() {
		return maxAmountMonkeys;
	}




	/**
	 * @return the maxAmountTrees
	 */
	public static int getMaxAmountTrees() {
		return maxAmountTrees;
	}




	/**
	 * @return the maxAmountBerries
	 */
	public static int getMaxAmountBerries() {
		return maxAmountBerries;
	}


	/**
	 * @return the maxAmountGrass
	 */
	public static int getMaxAmountGrass() {
		return maxAmountGrass;
	}



	/**
	 * @return the minAmountSheeps
	 */
	public static int getMinAmountSheeps() {
		return minAmountSheeps;
	}


	/**
	 * @return the minAmountWolfs
	 */
	public static int getMinAmountWolfs() {
		return minAmountWolfs;
	}


	/**
	 * @return the minAmountMonkeys
	 */
	public static int getMinAmountMonkeys() {
		return minAmountMonkeys;
	}


	/**
	 * @return the minAmountTrees
	 */
	public static int getMinAmountTrees() {
		return minAmountTrees;
	}


	/**
	 * @return the minAmountGrass
	 */
	public static int getMinAmountGrass() {
		return minAmountGrass;
	}




	/**
	 * @return the minAmountBerries
	 */
	public static int getMinAmountBerries() {
		return minAmountBerries;
	}
	/**
	 * @return the eatenSheeps
	 */
	public static int getEatenSheeps() {
		return eatenSheeps;
	}
	/**
	 * @param eatenSheeps the eatenSheeps to set
	 */
	public static void setEatenSheeps(int eatenSheeps) {
		Statistic.eatenSheeps = eatenSheeps;
	}
	/**
	 * @return the eatenMonkeys
	 */
	public static int getEatenMonkeys() {
		return eatenMonkeys;
	}
	/**
	 * @param eatenMonkeys the eatenMonkeys to set
	 */
	public static void setEatenMonkeys(int eatenMonkeys) {
		Statistic.eatenMonkeys = eatenMonkeys;
	}
	/**
	 * @return the eatenGrass
	 */
	public static int getEatenGrass() {
		return eatenGrass;
	}
	/**
	 * @param eatenGrass the eatenGrass to set
	 */
	public static void setEatenGrass(int eatenGrass) {
		Statistic.eatenGrass = eatenGrass;
	}
	/**
	 * @return the eatenBerries
	 */
	public static int getEatenBerries() {
		return eatenBerries;
	}
	/**
	 * @param eatenBerries the eatenBerries to set
	 */
	public static void setEatenBerries(int eatenBerries) {
		Statistic.eatenBerries = eatenBerries;
	}
	/**
	 * @return the bornsheeps
	 */
	public static int getBornsheeps() {
		return bornSheeps;
	}
	/**
	 * @return the bornmonkeys
	 */
	public static int getBornmonkeys() {
		return bornMonkeys;
	}
	/**
	 * @return the bornwolfs
	 */
	public static int getBornwolfs() {
		return bornWolfs;
	}



}
