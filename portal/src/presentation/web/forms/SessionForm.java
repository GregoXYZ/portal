package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class SessionForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7508738116595705002L;
	
	private String user;
	private String nombreUser;
	private Long fechaCreacion;
	private String hostName;

	public String getNombreUser() {
		return nombreUser;
	}

	public void setNombreUser(String nombreUser) {
		this.nombreUser = nombreUser;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setFechaCreacion(Long fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getFechaCreacion() {
		return fechaCreacion;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostName() {
		return hostName;
	}

	public void limpia()
	{
		user = null;
		nombreUser = null;
		fechaCreacion = null;
	}
}
