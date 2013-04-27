package presentation.beans;

import java.util.Date;

import common.dto.FicherosDTO;
import common.presentation.beans.HtmlEvents;

public class RowFile extends HtmlEvents implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 224004873302525285L;
	private Long assPk;
	private String assNombre;
	private String assDescripcion;
	private Date assFechaAlta;
	private Long ficSize;
	private String mimFilExtension;
	private String icon;
	
	private String onDelete;
	private String onEdit;
	private String onReference;
	private String onOperation;

	public RowFile(FicherosDTO file) {
		this.assPk = file.getAssPk();
		this.assNombre = file.getAssNombre();
		this.assDescripcion = file.getAssDescripcion();
		this.assFechaAlta = new Date(file.getAssFechaAlta());
		this.ficSize = file.getFicSize();
		this.mimFilExtension = file.getMimFilExtension();
		this.icon = file.getIcon();
	}
	
	public RowFile(Long assPk, String assNombre, String assDescripcion,
			Long ficSize, String mimFilExtension, String icon) {
		super();
		this.assPk = assPk;
		this.assNombre = assNombre;
		this.assDescripcion = assDescripcion;
		this.ficSize = ficSize;
		this.mimFilExtension = mimFilExtension;
		this.icon = icon;
	}

	public void setAssPk(Long assPk) {
		this.assPk = assPk;
	}
	public Long getAssPk() {
		return assPk;
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

	public void setFicSize(Long ficSize) {
		this.ficSize = ficSize;
	}
	public Long getFicSize() {
		return ficSize;
	}
	public void setMimFilExtension(String mimFilExtension) {
		this.mimFilExtension = mimFilExtension;
	}
	public String getMimFilExtension() {
		return mimFilExtension;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIcon() {
		return icon;
	}

	public void setOnDelete(String onDelete) {
		this.onDelete = onDelete;
	}

	public String getOnDelete() {
		return onDelete;
	}

	public void setOnEdit(String onEdit) {
		this.onEdit = onEdit;
	}

	public String getOnEdit() {
		return onEdit;
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
