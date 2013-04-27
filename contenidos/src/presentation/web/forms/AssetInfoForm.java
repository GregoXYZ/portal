package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class AssetInfoForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4195906745405266806L;
	private Long assPk;
	private Long ficSize;
	private String fechaCreacion;
	private String mimeType;
	private boolean preview;
	private boolean makeMiniature;

	public void setAssPk(Long assPk) {
		this.assPk = assPk;
	}
	public Long getAssPk() {
		return assPk;
	}
	public Long getFicSize() {
		return ficSize;
	}
	public void setFicSize(Long ficSize) {
		this.ficSize = ficSize;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setPreview(boolean preview) {
		this.preview = preview;
	}
	public boolean isPreview() {
		return preview;
	}
	public void setMakeMiniature(boolean makeMiniature) {
		this.makeMiniature = makeMiniature;
	}
	public boolean isMakeMiniature() {
		return makeMiniature;
	}
	
	@Override
	public void limpia() {
		ficSize = null;
		assPk = null;
		fechaCreacion = null;
		mimeType = null;
		preview = false;
		makeMiniature = false;
	}
	
}
