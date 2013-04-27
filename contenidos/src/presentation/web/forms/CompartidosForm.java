package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class CompartidosForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8942781876300096126L;
	private long usuPk;
	private long assPk;
	private long carFk;
	private Object tag;

	public void setUsuPk(long usuPk) {
		this.usuPk = usuPk;
	}

	public long getUsuPk() {
		return usuPk;
	}

	public void setAssPk(long assPk) {
		this.assPk = assPk;
	}

	public long getAssPk() {
		return assPk;
	}

	public void setCarFk(long carFk) {
		this.carFk = carFk;
	}

	public long getCarFk() {
		return carFk;
	}

	public void setTag(Object tag) {
		this.tag = tag;
	}

	public Object getTag() {
		return tag;
	}

	@Override
	public void limpia() {
		usuPk = 0;
		assPk = 0;
		carFk = 0;
		tag = null;
	}
	
}
