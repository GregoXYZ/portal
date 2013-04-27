package common.dto;

public class TagsNubeDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7816817066004637941L;
	private Long tagPk;
	private String tagUkCodigo;
	private Long contador;

	public void setTagPk(Long tagPk) {
		this.tagPk = tagPk;
	}

	public Long getTagPk() {
		return tagPk;
	}

	public String getTagUkCodigo() {
		return this.tagUkCodigo;
	}

	public void setTagUkCodigo(String tagUkCodigo) {
		this.tagUkCodigo = tagUkCodigo;
	}

	public void setContador(Long contador) {
		this.contador = contador;
	}

	public Long getContador() {
		return contador;
	}
}
