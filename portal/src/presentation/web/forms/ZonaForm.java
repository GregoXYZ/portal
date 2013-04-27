package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class ZonaForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7562061764906770679L;
	private Long zonPk;
	private String zonCodigo;
	private String zonDesc;

	public Long getZonPk() {
		return this.zonPk;
	}

	public void setZonPk(Long zonPk) {
		this.zonPk = zonPk;
	}

	public String getZonDesc() {
		return this.zonDesc;
	}

	public void setZonDesc(String zonDesc) {
		this.zonDesc = zonDesc;
	}

	public void setZonCodigo(String zonCodigo) {
		this.zonCodigo = zonCodigo;
	}

	public String getZonCodigo() {
		return zonCodigo;
	}

	@Override
	public void limpia() {
		zonPk = null;
		zonCodigo = null;
		zonDesc = null;
	}
}
