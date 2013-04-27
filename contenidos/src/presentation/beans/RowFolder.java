package presentation.beans;

import java.util.Date;

import common.dto.CarpetasDTO;
import common.presentation.beans.HtmlEvents;

public class RowFolder extends HtmlEvents implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6536882472966212208L;

	private Long assPk;
	private Long carPk;
	private String assNombre;
	private String assDescripcion;
	private Date assFechaAlta;
	private String icon;
	
	private String onEdit;
	private String onDelete;
	private String onReference;
	private String onOperation;

	public RowFolder(CarpetasDTO folder) {
		this.setAssPk(folder.getAssPk());
		this.setCarPk(folder.getCarPk());
		this.setAssNombre(folder.getAssNombre());
		this.setAssDescripcion(folder.getAssDescripcion());
		this.setAssFechaAlta(new Date(folder.getAssFechaAlta()));
		this.setIcon(folder.getIcon());
	}
	
	public RowFolder(Long assPk, Long carPk, String assNombre, 
			String assDescripcion, String icon) {
		super();
		this.setAssPk(assPk);
		this.setCarPk(carPk);
		this.setAssNombre(assNombre);
		this.setAssDescripcion(assDescripcion);
		this.setIcon(icon);
	}

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

	public void setAssFechaAlta(Date assFechaAlta) {
		this.assFechaAlta = assFechaAlta;
	}

	public Date getAssFechaAlta() {
		return assFechaAlta;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}

	public void setOnEdit(String onEdit) {
		this.onEdit = onEdit;
	}

	public String getOnEdit() {
		return onEdit;
	}

	public void setOnDelete(String onDelete) {
		this.onDelete = onDelete;
	}

	public String getOnDelete() {
		return onDelete;
	}

	public void setOnReference(String onReference) {
		this.onReference = onReference;
	}

	public String getOnReference() {
		return onReference;
	}

	public void setOnOperation(String onOperation) {
		this.onOperation = onOperation;
	}

	public String getOnOperation() {
		return onOperation;
	}

}
