package common.dto;

public class UsuTagsAssetDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3136884245718709711L;
	private Long usuTagAssPk;
	private Long tagAssFk;
	private Long usuFk;

	public UsuTagsAssetDTO() {
	}

	public UsuTagsAssetDTO(Long tagAssFk, Long usuFk) {
		this.setTagAssFk(tagAssFk);
		this.setUsuFk(usuFk);
	}

	public void setUsuTagAssPk(Long usuTagAssPk) {
		this.usuTagAssPk = usuTagAssPk;
	}

	public Long getUsuTagAssPk() {
		return usuTagAssPk;
	}

	public void setTagAssFk(Long tagAssFk) {
		this.tagAssFk = tagAssFk;
	}

	public Long getTagAssFk() {
		return tagAssFk;
	}

	public void setUsuFk(Long usuFk) {
		this.usuFk = usuFk;
	}

	public Long getUsuFk() {
		return usuFk;
	}
}
