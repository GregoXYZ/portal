package common.dto;

import common.presentation.beans.HtmlEvents;

public class MimeFilesDTO extends HtmlEvents implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -35555453390476245L;
	private Long mimFilPk;
	private String mimFilExtension;
	private String mimFilMime;
	private String mimFilIcon;

	public MimeFilesDTO() {
	}

	public MimeFilesDTO(String mimFilExtension, String mimFilMime) {
		this.mimFilExtension = mimFilExtension;
		this.mimFilMime = mimFilMime;
	}

	public Long getMimFilPk() {
		return this.mimFilPk;
	}

	public void setMimFilPk(Long mimFilPk) {
		this.mimFilPk = mimFilPk;
	}

	public String getMimFilExtension() {
		return this.mimFilExtension;
	}

	public void setMimFilExtension(String mimFilExtension) {
		this.mimFilExtension = mimFilExtension;
	}

	public String getMimFilMime() {
		return this.mimFilMime;
	}

	public void setMimFilMime(String mimFilMime) {
		this.mimFilMime = mimFilMime;
	}

	public void setMimFilIcon(String mimFilIcon) {
		this.mimFilIcon = mimFilIcon;
	}

	public String getMimFilIcon() {
		return mimFilIcon;
	}
}
