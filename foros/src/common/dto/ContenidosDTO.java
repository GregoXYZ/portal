package common.dto;

public class ContenidosDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7891043362370571189L;
	private Long conPk;
	private String conData;
	private Long conFechaAlta;
	private Long entFk;
	private Long usuFk;
	private Long conFk;

	public ContenidosDTO() {
	}

	public ContenidosDTO(String conData, Long conFechaAlta, Long entFk, Long usuFk) {
		this.conData = conData;
		this.conFechaAlta = conFechaAlta;
		this.entFk = entFk;
		this.usuFk = usuFk;
	}

	public ContenidosDTO(String conData, Long conFechaAlta, Long entFk, Long usuFk, Long conFk) {
		this.conData = conData;
		this.conFechaAlta = conFechaAlta;
		this.entFk = entFk;
		this.usuFk = usuFk;
		this.conFk = conFk;
	}

	public Long getConPk() {
		return this.conPk;
	}

	public void setConPk(Long conPk) {
		this.conPk = conPk;
	}

	public String getConData() {
		return this.conData;
	}

	public void setConData(String conData) {
		this.conData = conData;
	}

	public long getConFechaAlta() {
		return this.conFechaAlta;
	}

	public void setConFechaAlta(Long conFechaAlta) {
		this.conFechaAlta = conFechaAlta;
	}

	public long getEntFk() {
		return this.entFk;
	}

	public void setEntFk(Long entFk) {
		this.entFk = entFk;
	}

	public Long getUsuFk() {
		return this.usuFk;
	}

	public void setUsuFk(Long usuFk) {
		this.usuFk = usuFk;
	}

	public void setConFk(Long conFk) {
		this.conFk = conFk;
	}

	public Long getConFk() {
		return conFk;
	}

}
