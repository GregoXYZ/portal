package presentation.web.forms;

import org.apache.struts.upload.FormFile;

import common.presentation.web.security.forms.BaseForm;

public class UserForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7508738116595705002L;

	private Long usuPk;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String user;
	private String password;
	private String mail;
	private String fechaBaja;
	private boolean activo;
	private FormFile avatar;
	private Long[] perfil;
	private boolean publicable;
	private boolean recibeAbisos;

	public Long getUsuPk() {
		return usuPk;
	}

	public void setUsuPk(Long usuPk) {
		this.usuPk = usuPk;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getFechaBaja() {
		return fechaBaja;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setAvatar(FormFile avatar) {
		this.avatar = avatar;
	}

	public FormFile getAvatar() {
		return avatar;
	}

	public void setPerfil(Long[] perfil) {
		this.perfil = perfil;
	}

	public Long[] getPerfil() {
		return perfil;
	}

	public void setPublicable(boolean publicable) {
		this.publicable = publicable;
	}

	public boolean isPublicable() {
		return publicable;
	}

	public void setRecibeAbisos(boolean recibeAbisos) {
		this.recibeAbisos = recibeAbisos;
	}

	public boolean isRecibeAbisos() {
		return recibeAbisos;
	}

	public void limpia()
	{
		usuPk = null;
		nombre = null;
		apellido1 = null;
		apellido2 = null;
		user = null;
		password = null;
		mail = null;
		fechaBaja = null;
		activo = false;
		publicable = true;
		recibeAbisos = false;
	}
}
