package common.dto;

public class TagsDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9146881408798370318L;
	private Long tagPk;
	private Long assPk;
	private Long usuPk;
	private String tagUkCodigo;
	private Long tagCount;

	public Long getTagPk() {
		return this.tagPk;
	}

	public void setTagPk(Long tagPk) {
		this.tagPk = tagPk;
	}

	public void setAssPk(Long assPk) {
		this.assPk = assPk;
	}

	public Long getAssPk() {
		return assPk;
	}

	public void setUsuPk(Long usuPk) {
		this.usuPk = usuPk;
	}

	public Long getUsuPk() {
		return usuPk;
	}

	public String getTagUkCodigo() {
		return this.tagUkCodigo;
	}

	public void setTagUkCodigo(String tagUkCodigo) {
		this.tagUkCodigo = tagUkCodigo;
	}

	public void setTagCount(Long tagCount) {
		this.tagCount = tagCount;
	}

	public Long getTagCount() {
		return tagCount;
	}

}
