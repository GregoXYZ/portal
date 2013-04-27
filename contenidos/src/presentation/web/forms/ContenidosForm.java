package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class ContenidosForm extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1080109673703003004L;
	private long user;
	private long assPk;
	private long carFk;
	private long carPk;
	private Object tag;

	public void setUser(long user) {
		this.user = user;
	}

	public long getUser() {
		return user;
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

	public void setCarPk(long carPk) {
		this.carPk = carPk;
	}

	public long getCarPk() {
		return carPk;
	}

	public void setTag(Object tag) {
		this.tag = tag;
	}

	public Object getTag() {
		return tag;
	}

	@Override
	public void limpia() {
		user = 0;
		assPk = 0;
		carPk = 0;
		carFk = 0;
		tag = null;
	}
	
}
