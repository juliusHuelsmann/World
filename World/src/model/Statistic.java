package model;

import model.life.Life;
import model.life.creature.Monkey;
import model.life.creature.Sheep;
import model.life.creature.Wolf;
import model.life.plant.Berries;
import model.life.plant.Grass;
import model.life.plant.Tree;

public class Statistic {

	private static int amountSheeps, amountWolfs, amountMonkeys, amountTrees, 
	amountGrass, amountBerries;

	private static int ageSheeps, ageWolfs, ageMonkeys, ageTrees, 
	ageGrass, ageBerries;

	private static int hungerSheeps, hungerWolfs, hungerMonkeys;

	private static int pregnantSheeps, pregnantWolfs, pregnantMonkeys;

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
	}
	public static void increaseAmountWolfs() {
		amountWolfs++;
	}
	public static void increaseAmountMonkeys() {
		amountMonkeys++;
	}
	public static void increaseAmountTrees() {
		amountTrees++;
	}
	public static void increaseAmountGrass() {
		amountGrass++;
	}
	public static void increaseAmountBerries() {
		amountBerries++;
	}

	public static void decreaseAmountSheeps() {
		amountSheeps--;
	}
	public static void decreaseAmountWolfs() {
		amountWolfs--;
	}
	public static void decreaseAmountMonkeys() {
		amountMonkeys--;
	}
	public static void decreaseAmountTrees() {
		amountTrees--;
	}
	public static void decreaseAmountGrass() {
		amountGrass--;
	}
	public static void decreaseAmountBerries() {
		amountBerries--;
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

}
