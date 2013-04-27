package common.presentation.security.beans;

import java.util.Hashtable;

import common.business.beans.Usuarios;
import common.dto.UsuariosDTO;

public class UserInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3942313413918803127L;
	private long pk;
	private String user;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String mail;
	private boolean publicable;
	private boolean recibeAbisos;
	private Hashtable<?, ?> perfil;
	private long sessionPk;

	public UserInfo() {
		super();
	}
	
	public void setUserInfo(UsuariosDTO dto) {
    	pk = dto.getUsuPk();
    	user =dto.getUsuUkUsuario();
    	nombre = dto.getUsuNombre();
    	apellido1 = dto.getUsuApellido1();
    	apellido2 = dto.getUsuApellido2();
    	mail = dto.getUsuMail();
    	publicable = dto.isUsuPublicable();
    	recibeAbisos = dto.isUsuRecibeAviso();
	}

	public void setUserInfo(UsuariosDTO dto, Hashtable<?, ?> perfil) {
    	pk = dto.getUsuPk();
    	user =dto.getUsuUkUsuario();
    	nombre = dto.getUsuNombre();
    	apellido1 = dto.getUsuApellido1();
    	apellido2 = dto.getUsuApellido2();
    	mail = dto.getUsuMail();
    	publicable = dto.isUsuPublicable();
    	recibeAbisos = dto.isUsuRecibeAviso();
    	this.perfil = perfil;
	}

	public void setUserInfo(Usuarios dao, Hashtable<?, ?> perfil) {
    	pk = dao.getUsuPk();
    	user =dao.getUsuUkCodigo();
    	nombre = dao.getUsuNombre();
    	apellido1 = dao.getUsuApellido1();
    	apellido2 = dao.getUsuApellido2();
    	mail = dao.getUsuMail();
    	publicable = dao.isUsuPublicable();
    	recibeAbisos = dao.isUsuRecibeAviso();    	
    	this.perfil = perfil;
	}

	public Long getPk() {
		return pk;
	}
	public void setPk(Long pk) {
		this.pk = pk;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1 == null?"":apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2 == null?"":apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
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

	public void setPerfil(Hashtable<?, ?> perfil) {
		this.perfil = perfil;
	}

	public Hashtable<?, ?> getPerfil() {
		return perfil;
	}

	public void setSessionPk(Long sessionPk) {
		this.sessionPk = sessionPk;
	}

	public Long getSessionPk() {
		return sessionPk;
	}

	/*
	public void refresh() throws BusinessException
	{
    	UsuariosBO boUsuarios = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
    	UsuariosDTO dto = boUsuarios.getByCode(user);

    	UtilsBO boUtils = (UtilsBO) SpringUtil.getInstance().getBean("UtilsBO");
    	setUserInfo(dto, boUtils.getPerfil(dto.getUsuPk()));
	}
	*/
}
