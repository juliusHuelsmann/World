package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.life.Creature;
import model.life.Emission;
import model.life.Life;
import model.util.adt.list.List;
import view.map.VWorldItem;
import view.util.FetchButon;

public class CWorldItem implements ActionListener {

	private static CWorldItem instance;
	
	@Override
	public void actionPerformed(final ActionEvent _arg0) {
		
		if (_arg0.getSource() instanceof FetchButon) {
			final FetchButon fb = (FetchButon) _arg0.getSource();
			if (fb.getObj() instanceof VWorldItem) {
				VWorldItem vwi = (VWorldItem) fb.getObj();
				Emission  e = vwi.getWorldItem().getLifeEmission();
				List<Life> l = vwi.getWorldItem().getLife();
				
				
				String text = 
						"Wolf:\t" + e.getWolf() + "\n"
								+ "Sheep:\t" + e.getSheep() + "\n"
								+ "Monkey:\t" + e.getMonkey() + "\n"
								+ "Tree:\t" + e.getTree() + "\n"
								+ "Berries:\t" + e.getBerries() + "\n"
								+ "Grass:\t" + e.getGrass() + "\n"
								;

				boolean killable = false;
				if (l != null) {
					System.out.println("hier");
					l.toFirst();
					while(!l.isBehind() && !l.isEmpty()) {
						if (l.getItem() instanceof Creature) {

							Creature c = (Creature) l.getItem();
							text += "\n\n" + "Creature"
							+ "\nHunger:\t" + c.getPercentageHunger() 
							+ "\nPregnant:\t" + c.isPregnant()
							+ "\npercentage:\t" + c.getPercentagePregancy()
							+ "\nfemale:\t" + c.isFeminin()
							+ "\nage:\t" + c.getPercentageAge();

						}else {

							text += "\n\n" + "Plant";
						}
						l.next();
					}
					
					killable = true;
					text += "\nDo you want to kill instance of Life?";
				}
				
				int j = JOptionPane.showConfirmDialog(null, text );
				if (killable && (j == JOptionPane.YES_OPTION)) {

					l.toFirst();
					l.getItem().die();
				}
			}
		}
	}
	
	public static CWorldItem getInstance() {
		if (instance == null) {
			instance = new CWorldItem();
		}
		return instance;
		
	}

}
