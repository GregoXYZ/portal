package common.dto;

import common.presentation.beans.HtmlEvents;

public class ExtensionFilesDTO extends HtmlEvents implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1678955714271210976L;
	private Long extFilPk;
	private String extFilCode;
	private Long mimTypFk;
	private String extFilIcon;

	public ExtensionFilesDTO() {
	}

	public ExtensionFilesDTO(String extFilCode, Long mimTypFk) {
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
