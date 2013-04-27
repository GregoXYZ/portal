package common.dto;

import common.presentation.beans.HtmlEvents;

public class ZonasDTO extends HtmlEvents implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3557226408877118552L;
	private Long zonPk;
	private String zonCodigo;
	private String zonDesc;

	public ZonasDTO() {
	}

	public ZonasDTO(String zonCodigo, String zonDesc) {
		this.zonCodigo = zonCodigo;
		this.zonDesc = zonDesc;
	}

	public Long getZonPk() {
		return this.zonPk;
	}

	public void setZonPk(Long zonPk) {
		this.zonPk = zonPk;
	}

	public String getZonCodigo() {
		return this.zonCodigo;
	}

	public void setZonUkCodigo(String zonCodigo) {
		this.zonCodigo = zonCodigo;
	}

	public String getZonDesc() {
		return this.zonDesc;
	}

	public void setZonDesc(String zonDesc) {
		this.zonDesc = zonDesc;
	}
}
