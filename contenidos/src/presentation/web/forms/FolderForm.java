package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class FolderForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2878717933148677788L;	
	private Long assPk;
	private Long carPk;
	private String nombre;
	private String descripcion;
	
	public void setAssPk(Long assPk) {
		this.assPk = assPk;
	}
	public Long getAssPk() {
		return assPk;
	}
	public void setCarPk(Long carPk) {
		this.carPk = carPk;
	}
	public Long getCarPk() {
		return carPk;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public void limpia() {
		carPk = null;
		nombre = null;
		setDescripcion(null);
	}
	
}
