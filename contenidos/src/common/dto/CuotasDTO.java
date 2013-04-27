package common.dto;

public class CuotasDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5384532494343564725L;
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
		return cuoCuotaFile>cuoCuotaDisk?cuoCuotaDisk:cuoCuotaFile;
	}

}
