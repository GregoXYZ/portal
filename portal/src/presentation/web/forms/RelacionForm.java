package presentation.web.forms;

import common.Constants;
import common.presentation.web.security.forms.BaseForm;

public class RelacionForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7508738116595705002L;

	private Long usuPk;
	private int evento;

	public Long getUsuPk() {
		return usuPk;
	}

	public void setUsuPk(Long usuPk) {
		this.usuPk = usuPk;
	}

	public void setEvento(int evento) {
		this.evento = evento;
	}

	public int getEvento() {
		return evento;
	}

	public void limpia()
	{
		usuPk = null;
		evento = Constants.SOLICITAR_RELACION;
	}
}
