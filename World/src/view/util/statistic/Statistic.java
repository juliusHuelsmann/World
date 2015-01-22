package view.util.statistic;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Statistic extends JPanel implements Observer {

	
	
	
	public Statistic () {
		super();
		
		super.setVisible(true);
	}
	
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override public void update(
			final Observable _observable, 
			final Object _object) {
		
		
		
	}

	
	
}
