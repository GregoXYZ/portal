package common.dto;

import common.presentation.beans.HtmlEvents;

public class MimeTypesDTO extends HtmlEvents implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3272872900179447409L;
	private Long mimTypPk;
	private String mimTypCode;

	public MimeTypesDTO() {
	}

	public MimeTypesDTO(String mimTypCode) {
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
