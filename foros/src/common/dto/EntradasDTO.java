package common.dto;


public class EntradasDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2368182909713513691L;
	private Long entPk;
	private Long entFechaAlta;
	private String entSubject;
	private Boolean entRestringida;
	private Long usuFk;
	private Long[] usuarios;

	public EntradasDTO() {
	}

	public EntradasDTO(long entFechaAlta, String entSubject, Long usuFk) {
		this.entFechaAlta = entFechaAlta;
		this.entSubject = entSubject;
		this.usuFk = usuFk;
	}

	public Long getEntPk() {
		return this.entPk;
	}

	public void setEntPk(Long entPk) {
		this.entPk = entPk;
	}

	public Long getEntFechaAlta() {
		return this.entFechaAlta;
	}

	public void setEntFechaAlta(Long entFechaAlta) {
		this.entFechaAlta = entFechaAlta;
	}

	public String getEntSubject() {
		return this.entSubject;
	}

	public void setEntSubject(String entSubject) {
		this.entSubject = entSubject;
	}

	public void setEntRestringida(Boolean entRestringida) {
		this.entRestringida = entRestringida;
	}

	public Boolean getEntRestringida() {
		return entRestringida;
	}

	public Long getUsuFk() {
		return this.usuFk;
	}

	public void setUsuFk(Long usuFk) {
		this.usuFk = usuFk;
	}

	public void setUsuarios(Long[] usuarios) {
		this.usuarios = usuarios;
	}

	public Long[] getUsuarios() {
		return usuarios;
	}
}
