package presentation.web.tld.Miniaturas.Beans;

import common.presentation.beans.Action;

public class Miniatura extends Action implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6093572929296390563L;
	private String id;
	private String imageId;
	private String href;

	public void setHref(String href) {
		this.href = href;
	}

	public String getHref() {
		return href;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImageId() {
		return imageId;
	}
}
