package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class GroupForm extends BaseForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4359681746638803746L;	
	private Long gruPk;
	private String gruUkCodigo;
	private String gruDescripcion;
	private int gruPrioridad;

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

	@Override
	public void limpia() {
		gruPk = null;
		gruUkCodigo = null;
		gruDescripcion = null;
		gruPrioridad = 0;
	}
}
