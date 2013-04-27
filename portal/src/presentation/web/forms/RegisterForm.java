package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class RegisterForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2574092727829446119L;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String user;
	private String contrasena;
	private String contrasena2;
	private String mail;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getContrasena2() {
		return contrasena2;
	}
	public void setContrasena2(String contrasena2) {
		this.contrasena2 = contrasena2;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public void limpia()
	{
		nombre = null;
		apellido1 = null;
		apellido2 = null;
		user = null;
		contrasena = null;
		contrasena2 = null;
		mail = null;
	}
}
