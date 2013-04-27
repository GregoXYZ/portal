package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class UrlForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -767898350473610079L;
	private Long urlPk;
	private String urlCodigo;
	private String urlDescripcion;
	private String urlDireccion;
	private Long zonFk;
	private Long[] grupos;

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

	@Override
	public void limpia() {
		urlPk = null;
		urlCodigo = null;
		urlDescripcion = null;
		urlDireccion = null;
		zonFk = null;
	}

}
