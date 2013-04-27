package common.dto;

import common.presentation.beans.HtmlEvents;

public class GruposDTO extends HtmlEvents implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2257488237683132812L;
	
	private Long gruPk;
	private String gruUkCodigo;
	private String gruDescripcion;
	private int gruPrioridad;

	public GruposDTO() {
	}

	public GruposDTO(String gruUkCodigo, String gruDescripcion, int gruPrioridad) {
		this.gruUkCodigo = gruUkCodigo;
		this.gruDescripcion = gruDescripcion;
		this.gruPrioridad = gruPrioridad;
	}

	public Long getGruPk() {
		return this.gruPk;
	}

	public void setGruPk(Long gruPk) {
		this.gruPk = gruPk;
	}

	public String getGruUkCodigo() {
		return this.gruUkCodigo;
	}

	public void setGruUkCodigo(String gruUkCodigo) {
		this.gruUkCodigo = gruUkCodigo;
	}

	public String getGruDescripcion() {
		return this.gruDescripcion;
	}

	public void setGruDescripcion(String gruDescripcion) {
		this.gruDescripcion = gruDescripcion;
	}

	public int getGruPrioridad() {
		return this.gruPrioridad;
	}

	public void setGruPrioridad(int gruPrioridad) {
		this.gruPrioridad = gruPrioridad;
	}
}
