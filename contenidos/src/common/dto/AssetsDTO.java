package common.dto;

public class AssetsDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5381040130787986567L;
	private Long assPk;
	private String assNombre;
	private String assDescripcion;
	private Long tipAssFk;
	private Long usuFk;
	private Long assFechaAlta;

	public Long getAssPk() {
		return this.assPk;
	}

	public void setAssPk(Long assPk) {
		this.assPk = assPk;
	}

	public void setAssNombre(String assNombre) {
		this.assNombre = assNombre;
	}

	public String getAssNombre() {
		return assNombre;
	}

	public void setAssDescripcion(String assDescripcion) {
		this.assDescripcion = assDescripcion;
	}

	public String getAssDescripcion() {
		return assDescripcion;
	}

	public void setTipAssFk(Long tipAssFk) {
		this.tipAssFk = tipAssFk;
	}

	public Long getTipAssFk() {
		return tipAssFk;
	}

	public void setUsuFk(Long usuFk) {
		this.usuFk = usuFk;
	}

	public Long getUsuFk() {
		return usuFk;
	}

	public void setAssFechaAlta(Long assFechaAlta) {
		this.assFechaAlta = assFechaAlta;
	}

	public Long getAssFechaAlta() {
		return assFechaAlta;
	}

}
