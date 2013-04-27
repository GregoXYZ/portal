package common.dto;

public class EstadosRelacionUsuariosDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5694229290794639389L;
	private Long estRelUsuPk;
	private String estRelUsuUkCodigo;

	public EstadosRelacionUsuariosDTO() {
	}

	public EstadosRelacionUsuariosDTO(String estRelUsuUkCodigo) {
		this.estRelUsuUkCodigo = estRelUsuUkCodigo;
	}

	public Long getEstRelUsuPk() {
		return this.estRelUsuPk;
	}

	public void setEstRelUsuPk(Long estRelUsuPk) {
		this.estRelUsuPk = estRelUsuPk;
	}

	public String getEstRelUsuUkCodigo() {
		return this.estRelUsuUkCodigo;
	}

	public void setEstRelUsuUkCodigo(String estRelUsuUkCodigo) {
		this.estRelUsuUkCodigo = estRelUsuUkCodigo;
	}

}
