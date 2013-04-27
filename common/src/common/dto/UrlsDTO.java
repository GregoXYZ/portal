package common.dto;

import common.presentation.beans.HtmlEvents;

public class UrlsDTO extends HtmlEvents implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6832860047326480830L;
	private Long urlPk;
	private String urlCodigo;
	private String urlDescripcion;
	private String urlDireccion;
	private Long zonFk;
	private Long[] grupos;
	
	public UrlsDTO() {
	}

	public UrlsDTO(Long zona, String urlCodigo, String urlDescripcion,
			String urlDireccion) {
		this.zonFk = zona;
		this.urlCodigo = urlCodigo;
		this.urlDescripcion = urlDescripcion;
		this.urlDireccion = urlDireccion;
	}

	public Long getUrlPk() {
		return urlPk;
	}

	public void setUrlPk(Long urlPk) {
		this.urlPk = urlPk;
	}

	public String getUrlCodigo() {
		return urlCodigo;
	}

	public void setUrlCodigo(String urlCodigo) {
		this.urlCodigo = urlCodigo;
	}

	public String getUrlDescripcion() {
		return urlDescripcion;
	}

	public void setUrlDescripcion(String urlDescripcion) {
		this.urlDescripcion = urlDescripcion;
	}

	public String getUrlDireccion() {
		return urlDireccion;
	}

	public void setUrlDireccion(String urlDireccion) {
		this.urlDireccion = urlDireccion;
	}

	public Long getZonFk() {
		return zonFk;
	}

	public void setZonFk(Long zonFk) {
		this.zonFk = zonFk;
	}

	public void setGrupos(Long[] grupos) {
		this.grupos = grupos;
	}

	public Long[] getGrupos() {
		return grupos;
	}

}
