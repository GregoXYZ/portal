package presentation.web.forms;

import org.apache.struts.upload.FormFile;

import common.presentation.web.security.forms.BaseForm;

public class MimeFileForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5729769648417002951L;
	private Long mimFilPk;
	private String mimFilExtension;
	private String mimFilMime;
	private String mimFilIcon;
	private FormFile iconFile;

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

	public void setIconFile(FormFile iconFile) {
		this.iconFile = iconFile;
	}

	public FormFile getIconFile() {
		return iconFile;
	}

	public void setMimFilIcon(String mimFilIcon) {
		this.mimFilIcon = mimFilIcon;
	}

	public String getMimFilIcon() {
		return mimFilIcon;
	}

	@Override
	public void limpia() {
		mimFilPk = null;
		mimFilExtension = null;
		mimFilMime = null;
		setMimFilIcon(null);
		iconFile = null;
	}

}
