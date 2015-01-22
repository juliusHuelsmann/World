package model.util;

import java.util.logging.Logger;

public final class Status {

	
	/**
	 * 
	 */
	private static Status instance;
	
	public static final int DISPLAY_BUTTON_ARRAY = 0,
			DISPLAY_IMAGE = 1,
			DISPLAY_NOTHING = 2;
	
	private static int displayMode = DISPLAY_BUTTON_ARRAY;
	
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




	/**
	 * @return the displayMode
	 */
	public static int getDisplayMode() {
		return displayMode;
	}




	/**
	 * @param displayMode the displayMode to set
	 */
	public static void setDisplayMode(int displayMode) {
		Status.displayMode = displayMode;
	}
}
