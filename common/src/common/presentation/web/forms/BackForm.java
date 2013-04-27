package common.presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class BackForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -883287497809248375L;

	private String backContext;

	public void setBackContext(String backContext) {
		this.backContext = backContext;
	}

	public String getBackContext() {
		return backContext;
	}
	
	public void limpia()
	{
		backContext = null;
	}
}
