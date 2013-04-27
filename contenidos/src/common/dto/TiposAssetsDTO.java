package common.dto;

public class TiposAssetsDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9157176731254834076L;
	private Long tipAssPk;
	private String tipAssUkCodigo;
	private String tipAssNombre;
	private String tipAssDescripcion;

	public Long getTipAssPk() {
		return this.tipAssPk;
	}

	public void setTipAssPk(Long tipAssPk) {
		this.tipAssPk = tipAssPk;
	}

	public String getTipAssUkCodigo() {
		return this.tipAssUkCodigo;
	}

	public void setTipAssUkCodigo(String tipAssUkCodigo) {
		this.tipAssUkCodigo = tipAssUkCodigo;
	}

	public String getTipAssNombre() {
		return this.tipAssNombre;
	}

	public void setTipAssNombre(String tipAssNombre) {
		this.tipAssNombre = tipAssNombre;
	}

	public String getTipAssDescripcion() {
		return this.tipAssDescripcion;
	}

	public void setTipAssDescripcion(String tipAssDescripcion) {
		this.tipAssDescripcion = tipAssDescripcion;
	}

}
