package common.dto;

public class RelacionesUsuariosDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1563404256577077267L;
	private Long relUsuPk;
	private Long usu1Fk;
	private Long usu2Fk;
	private Long relUsuFechaRelacion;
	private Long estRelUsuFk;
	public RelacionesUsuariosDTO() {
	}

	public RelacionesUsuariosDTO(Long estRelUsuFk,
			Long usu1Fk, Long usu2Fk) {
		this.estRelUsuFk = estRelUsuFk;
		this.usu1Fk = usu1Fk;
		this.usu2Fk = usu2Fk;
	}

	public Long getRelUsuPk() {
		return this.relUsuPk;
	}

	public void setRelUsuPk(Long relUsuPk) {
		this.relUsuPk = relUsuPk;
	}

	public Long getUsu1Fk() {
		return this.usu1Fk;
	}

	public void setUsu1Fk(Long usu1Fk) {
		this.usu1Fk = usu1Fk;
	}

	public Long getUsu2Fk() {
		return this.usu2Fk;
	}

	public void setUsu2Fk(Long usu2Fk) {
		this.usu2Fk = usu2Fk;
	}

	public void setRelUsuFechaRelacion(Long relUsuFechaRelacion) {
		this.relUsuFechaRelacion = relUsuFechaRelacion;
	}

	public Long getRelUsuFechaRelacion() {
		return relUsuFechaRelacion;
	}

	public void setEstRelUsuFk(Long estRelUsuFk) {
		this.estRelUsuFk = estRelUsuFk;
	}

	public Long getEstRelUsuFk() {
		return estRelUsuFk;
	}

}
