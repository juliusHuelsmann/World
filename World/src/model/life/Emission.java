package model.life;

import java.awt.Color;

import model.life.creature.Monkey;
import model.life.creature.Sheep;
import model.life.creature.Wolf;
import model.life.plant.Berries;
import model.life.plant.Grass;
import model.life.plant.Tree;
import model.util.Status;

public class Emission {

	private double monkey = 0, sheep = 0, wolf = 0, berries = 0, tree = 0, grass = 0;
	private Color clr_monkey, clr_sheep, clr_wolf, clr_berries, clr_tree, clr_grass;
	private final double reduction = 0.5;
	
	
	public Emission() {
		clr_monkey = new Color(0,0,0);
		clr_sheep = new Color(0,0,0);
		clr_wolf = new Color(0,0,0);
		clr_berries= new Color(0,0,0);
		clr_tree = new Color(0,0,0);
		clr_grass = new Color(0,0,0);
	}
	public void planckTime() {
		final double perc = 1.0 - reduction;
		monkey = 1.0 * monkey * perc;
		sheep = 1.0 * sheep * perc;
		wolf = 1.0 * wolf * perc;
		berries = 1.0 * berries * perc;
		tree = 1.0 * tree * perc;
		grass = 1.0 * grass * perc;
	}
	
	
	public Color getColor() {
		final double divisor;
		if (getMonkey() + getSheep() + getWolf() + getBerries() + getTree() + getGrass() == 0) {
			return Color.black;
		} else {
			divisor = monkey + sheep + wolf + berries + tree + grass;
		}

		int red = (int) ((clr_monkey.getRed() * monkey + clr_sheep.getRed() * sheep
				+ clr_wolf.getRed() * wolf + clr_berries.getRed() * berries
				+ clr_tree.getRed() * tree + clr_grass.getRed() * grass) 
				/ divisor) ;
		int green = (int) ((clr_monkey.getGreen() * monkey + clr_sheep.getGreen() * sheep
				+ clr_wolf.getGreen() * wolf + clr_berries.getGreen() * berries
				+ clr_tree.getGreen() * tree+ clr_grass.getGreen() * grass ) 
				/ divisor) ;


		int blue = (int) ((clr_monkey.getBlue() * monkey + clr_sheep.getBlue() * sheep
				+ clr_wolf.getBlue() * wolf + clr_berries.getBlue() * berries
				+ clr_tree.getBlue() * tree + clr_grass.getBlue() * grass) 
				/ divisor) ;
		
		
		
		return new Color(red, green, blue);
	}
	public void increase(final Life _life, final double _add) {
		if (_life instanceof Monkey) {
			increaseMoney(_add);
			clr_monkey = _life.getClrBackground();
		} else if (_life instanceof Sheep) {
			increaseSheep(_add);
			clr_sheep = _life.getClrBackground();
		} else if (_life instanceof Wolf) {
			increaseWolf(_add);
			clr_wolf = _life.getClrBackground();
		} else if (_life instanceof Berries) {
			increaseBerries(_add);
			clr_berries = _life.getClrBackground();
		} else if (_life instanceof Tree) {
			increaseTree(_add);
			clr_tree = _life.getClrBackground();
		} else if (_life instanceof Grass) {
			increaseGrass(_add);
			clr_grass = _life.getClrBackground();
		} else {
			Status.getLogger().severe("implementation error." + _life);
		}
	}
	
	
	public int get(Life _smelledCreature) {
		if (_smelledCreature instanceof Wolf) {
			return getWolf();
		} else if (_smelledCreature instanceof Monkey) {
			return getMonkey();
		} else if (_smelledCreature instanceof Sheep) {
			return getSheep();
		} else if (_smelledCreature instanceof Berries) {
			return getBerries();
		} else if (_smelledCreature instanceof Tree) {
			return getTree();
		}else if (_smelledCreature instanceof Grass) {
			return getGrass();
		} else {
			Status.getLogger().severe("implementation error");
			return 0;
		}
	}

	public void increaseMoney(final double _add) {
		monkey += _add;
	}

	public void increaseGrass(final double _add) {
		grass += _add;
	}

	public void increaseSheep(final double _add) {
		sheep += _add;
	}

	public void increaseWolf(final double _add) {
		wolf += _add;
	}

	public void increaseBerries(final double _add) {
		berries += _add;
	}

	public void increaseTree(final double _add) {
		tree += _add;
	}

	/**
	 * @return the monkey
	 */
	public int getMonkey() {
		return (int) (monkey);
	}
	/**
	 * @return the monkey
	 */
	public int getGrass() {
		return (int) (grass);
	}

	/**
	 * @return the sheep
	 */
	public int getSheep() {
		return (int) (sheep);
	}

	/**
	 * @return the wolf
	 */
	public int getWolf() {
		return (int) (wolf);
	}

	/**
	 * @return the berries
	 */
	public int getBerries() {
		return (int) (berries);
	}

	/**
	 * @return the tree
	 */
	public int getTree() {
		return (int) (tree);
	}
}
