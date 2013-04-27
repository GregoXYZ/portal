package common.presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class SearchForm extends BaseForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3065599378118713721L;
	
	private String campo1;

	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	public String getCampo1() {
		return campo1==null?"":campo1;
	}

	public void limpia()
	{
		campo1 = null;
	}
}
