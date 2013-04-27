package business.beans;

public class MimeTypes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8245922778324999211L;
	private Long mimTypPk;
	private String mimTypCode;

	public MimeTypes() {
	}

	public MimeTypes(String mimTypCode) {
		this.setMimTypCode(mimTypCode);
	}

	public void setMimTypPk(Long mimTypPk) {
		this.mimTypPk = mimTypPk;
	}

	public Long getMimTypPk() {
		return mimTypPk;
	}

	public void setMimTypCode(String mimTypCode) {
		this.mimTypCode = mimTypCode;
	}

	public String getMimTypCode() {
		return mimTypCode;
	}
}
