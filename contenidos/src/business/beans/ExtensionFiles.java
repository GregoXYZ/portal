package business.beans;

public class ExtensionFiles implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6692933482572183148L;
	private Long extFilPk;
	private String extFilCode;
	private Long mimTypFk;
	private String extFilIcon;

	public ExtensionFiles() {
	}

	public ExtensionFiles(String extFilCode, Long mimTypFk) {
		this.setExtFilCode(extFilCode);
		this.setMimTypFk(mimTypFk);
	}

	public void setExtFilPk(Long extFilPk) {
		this.extFilPk = extFilPk;
	}

	public Long getExtFilPk() {
		return extFilPk;
	}

	public void setExtFilCode(String extFilCode) {
		this.extFilCode = extFilCode;
	}

	public String getExtFilCode() {
		return extFilCode;
	}

	public void setMimTypFk(Long mimTypFk) {
		this.mimTypFk = mimTypFk;
	}

	public Long getMimTypFk() {
		return mimTypFk;
	}

	public void setExtFilIcon(String extFilIcon) {
		this.extFilIcon = extFilIcon;
	}

	public String getExtFilIcon() {
		return extFilIcon;
	}

}
