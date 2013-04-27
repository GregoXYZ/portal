package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class BugForm extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1039593775446293814L;
	private Long bugPk;
	private Long bugSitePk;
	private String bugSite;
	private String bugMessage;
	private String bugDescripcion;
	private Long usuFk;
	private String usuName;
	private String bugFechaReport;
	private Long bugEstado;
	private String bugEstadoDesc;
	private Long bugPrioridad;
	private String bugPrioridadDesc;
	
	public Long getBugPk() {
		return bugPk;
	}

	public void setBugPk(Long bugPk) {
		this.bugPk = bugPk;
	}

	public void setBugSitePk(Long bugSitePk) {
		this.bugSitePk = bugSitePk;
	}

	public Long getBugSitePk() {
		return bugSitePk;
	}

	public String getBugSite() {
		return bugSite;
	}

	public void setBugSite(String bugSite) {
		this.bugSite = bugSite;
	}

	public String getBugMessage() {
		return bugMessage;
	}

	public void setBugMessage(String bugMessage) {
		this.bugMessage = bugMessage;
	}

	public String getBugDescripcion() {
		return bugDescripcion;
	}

	public void setBugDescripcion(String bugDescripcion) {
		this.bugDescripcion = bugDescripcion;
	}

	public Long getUsuFk() {
		return usuFk;
	}

	public void setUsuFk(Long usuFk) {
		this.usuFk = usuFk;
	}

	public void setUsuName(String usuName) {
		this.usuName = usuName;
	}

	public String getUsuName() {
		return usuName;
	}

	public String getBugFechaReport() {
		return bugFechaReport;
	}

	public void setBugFechaReport(String bugFechaReport) {
		this.bugFechaReport = bugFechaReport;
	}

	public void setBugEstado(Long bugEstado) {
		this.bugEstado = bugEstado;
	}

	public Long getBugEstado() {
		return bugEstado;
	}

	public void setBugEstadoDesc(String bugEstadoDesc) {
		this.bugEstadoDesc = bugEstadoDesc;
	}

	public String getBugEstadoDesc() {
		return bugEstadoDesc;
	}

	public void setBugPrioridad(Long bugPrioridad) {
		this.bugPrioridad = bugPrioridad;
	}

	public Long getBugPrioridad() {
		return bugPrioridad;
	}

	public void setBugPrioridadDesc(String bugPrioridadDesc) {
		this.bugPrioridadDesc = bugPrioridadDesc;
	}

	public String getBugPrioridadDesc() {
		return bugPrioridadDesc;
	}

	@Override
	public void limpia() {
		bugPk = null;
		bugSite = null;
		bugMessage = null;
		bugDescripcion = null;
		usuFk = null;
		bugFechaReport = null;
		bugEstado = null;
		bugPrioridad = null;
	}
	
}
