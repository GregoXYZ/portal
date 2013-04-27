package presentation.web.forms;

import common.presentation.web.forms.SearchForm;

public class BuscarForm extends SearchForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2255180074287825419L;
	
	private String multipletags;

	public void setMultipletags(String multipletags) {
		this.multipletags = multipletags;
	}

	public String getMultipletags() {
		return multipletags;
	}

	public void limpia()
	{
		super.limpia();
		multipletags = null;
	}	
}
