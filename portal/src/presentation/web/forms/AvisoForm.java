package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class AvisoForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2692315924368401219L;
	private Long aviPk;
	private Long usuPk;
	private String usuUkCodigo;
	private String usuNombre;
	private String usuApellido1;
	private String usuApellido2;
	private Long tipAviPk;
	private String tipAviUkCodigo;
	private String tipAviDescripcion;
	private Long cantidad;

	public void setAviPk(Long aviPk) {
		this.aviPk = aviPk;
	}

	public Long getAviPk() {
		return aviPk;
	}

	public void setUsuPk(Long usuPk) {
		this.usuPk = usuPk;
	}

	public Long getUsuPk() {
		return usuPk;
	}

	public void setUsuUkCodigo(String usuUkCodigo) {
		this.usuUkCodigo = usuUkCodigo;
	}

	public String getUsuUkCodigo() {
		return usuUkCodigo;
	}

	public void setUsuNombre(String usuNombre) {
		this.usuNombre = usuNombre;
	}

	public String getUsuNombre() {
		return usuNombre;
	}

	public void setUsuApellido1(String usuApellido1) {
		this.usuApellido1 = usuApellido1;
	}

	public String getUsuApellido1() {
		return usuApellido1;
	}

	public void setUsuApellido2(String usuApellido2) {
		this.usuApellido2 = usuApellido2;
	}

	public String getUsuApellido2() {
		return usuApellido2;
	}

	public void setTipAviPk(Long tipAviPk) {
		this.tipAviPk = tipAviPk;
	}

	public Long getTipAviPk() {
		return tipAviPk;
	}

	public void setTipAviUkCodigo(String tipAviUkCodigo) {
		this.tipAviUkCodigo = tipAviUkCodigo;
	}

	public String getTipAviUkCodigo() {
		return tipAviUkCodigo;
	}

	public void setTipAviDescripcion(String tipAviDescripcion) {
		this.tipAviDescripcion = tipAviDescripcion;
	}

	public String getTipAviDescripcion() {
		return tipAviDescripcion;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Long getCantidad() {
		return cantidad;
	}

	@Override
	public void limpia() {
		aviPk = null;
		usuPk = null;
		usuUkCodigo = null;
		usuNombre = null;
		usuApellido1 = null;
		usuApellido2 = null;
		tipAviPk = null;
		tipAviUkCodigo = null;
		tipAviDescripcion = null;
		cantidad = null;
	}
}
