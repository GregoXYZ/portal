package business.beans;

// Generated 23-abr-2009 23:40:24 by Hibernate Tools 3.2.4.GA

/**
 * Tags generated by hbm2java
 */
public class Tags implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8815304118388392989L;
	private Long tagPk;
	private String tagUkCodigo;
	private Long tagCount;

	public Tags() {
	}

	public Tags(String tagUkCodigo) {
		this.tagUkCodigo = tagUkCodigo;
	}

	public Long getTagPk() {
		return this.tagPk;
	}

	public void setTagPk(Long tagPk) {
		this.tagPk = tagPk;
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
