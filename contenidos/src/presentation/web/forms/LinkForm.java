package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class LinkForm extends BaseForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6806580506172016852L;
	private Long linPk;
	private Long assPk;
	private Long assFkRef;
	private Long usuFk;				// Propietario del link
	private String nombre;
	private String descripcion;
	private Long[] users;

	public void setLinPk(Long linPk) {
		this.linPk = linPk;
	}

	public Long getLinPk() {
		return linPk;
	}

	public void setAssPk(Long assPk) {
		this.assPk = assPk;
	}

	public Long getAssPk() {
		return assPk;
	}

	public void setUsuFk(Long usuFk) {
		this.usuFk = usuFk;
	}

	public Long getUsuFk() {
		return usuFk;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setUsers(Long[] users) {
		this.users = users;
	}

	public Long[] getUsers() {
		return users;
	}

	public void setAssFkRef(Long assFkRef) {
		this.assFkRef = assFkRef;
	}

	public Long getAssFkRef() {
		return assFkRef;
	}

	@Override
	public void limpia() {
		linPk = null;
		assPk = null;
		assFkRef = null;
		usuFk = null;
		nombre = null;
		descripcion = null;
		users = null;
	}

}
