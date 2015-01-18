package view.util;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class FetchButon extends JButton {

	private Object obj;
	public FetchButon(Object _obj) {
		this.obj = _obj;
	}
	/**
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}
	/**
	 * @param obj the obj to set
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}
