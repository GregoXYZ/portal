package presentation.web.forms.backend;

import common.presentation.web.security.forms.BaseForm;

public class CuotaForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5888042729546083812L;
	private Long usuFkPk;
	private Long cuoCuotaDisk;
	private Long cuoCuotaFile;

	public Long getUsuFkPk() {
		return this.usuFkPk;
	}

	public void setUsuFkPk(Long usuFkPk) {
		this.usuFkPk = usuFkPk;
	}

	public void setCuoCuotaDisk(Long cuoCuotaDisk) {
		this.cuoCuotaDisk = cuoCuotaDisk;
	}

	public Long getCuoCuotaDisk() {
		return cuoCuotaDisk;
	}

	public void setCuoCuotaFile(Long cuoCuotaFile) {
		this.cuoCuotaFile = cuoCuotaFile;
	}

	public Long getCuoCuotaFile() {
		return cuoCuotaFile;
	}

	@Override
	public void limpia() {
		usuFkPk = null;
		setCuoCuotaDisk(null);
		setCuoCuotaFile(null);
	}

}
