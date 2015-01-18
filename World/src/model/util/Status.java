package model.util;

import java.util.logging.Logger;

public final class Status {

	
	/**
	 * 
	 */
	private static Status instance;
	
	
	/**
	 * 
	 */
	private Logger logger = Logger.getLogger("Info");
	
	
	/**
	 * The delay after one step of planch time.
	 */
	private int delayForPlanckTime = 70;
	
	
	/**
	 * 
	 */
	private Status() { }

	
	
	
	/**
	 * @return the log
	 */
	public static Logger getLogger() {
		return getInstance().logger;
	}


	/**
	 * @return the instance
	 */
	public static Status getInstance() {
		
		if (instance == null){
			instance = new Status();
		}
		return instance;
	}








	/**
	 * @return the delayForPlanckTime
	 */
	public int getDelayForPlanckTime() {
		return delayForPlanckTime;
	}




	/**
	 * @param delayForPlanckTime the delayForPlanckTime to set
	 */
	public void setDelayForPlanckTime(int delayForPlanckTime) {
		this.delayForPlanckTime = delayForPlanckTime;
	}
}
