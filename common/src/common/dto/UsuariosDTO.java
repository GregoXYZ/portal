package common.dto;

import common.presentation.beans.HtmlEvents;

public class UsuariosDTO extends HtmlEvents implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5551398634929466698L;

	private Long usuPk;
	private String usuUkUsuario;
	private String usuNombre;
	private String usuApellido1;
	private String usuApellido2;
	private String usuContrasena;
	private Long usuFechaBaja;
	private String usuMail;
	private boolean usuActivo;
	private byte[] usuAvatar;
	private boolean usuPublicable;
	private boolean usuRecibeAviso;
	private Long[] perfil;

	public UsuariosDTO() {
	}

	public UsuariosDTO(String usuUkUsuario, String usuNombre,
			String usuContrasena) {
		this.usuUkUsuario = usuUkUsuario;
		this.usuNombre = usuNombre;
		this.usuContrasena = usuContrasena;
	}

	public UsuariosDTO(String usuUkUsuario, String usuNombre,
			String usuApellido1, String usuApellido2, String usuContrasena,
			Long usuFechaBaja, String usuMail) {
		this.usuUkUsuario = usuUkUsuario;
		this.usuNombre = usuNombre;
		this.usuApellido1 = usuApellido1;
		this.usuApellido2 = usuApellido2;
		this.usuContrasena = usuContrasena;
		this.usuFechaBaja = usuFechaBaja;
		this.usuMail = usuMail;
	}

	public Long getUsuPk() {
		return this.usuPk;
	}

	public void setUsuPk(Long usuPk) {
		this.usuPk = usuPk;
	}

	public String getUsuUkUsuario() {
		return this.usuUkUsuario;
	}

	public void setUsuUkUsuario(String usuUkUsuario) {
		this.usuUkUsuario = usuUkUsuario;
	}

	public String getUsuNombre() {
		return this.usuNombre;
	}

	public void setUsuNombre(String usuNombre) {
		this.usuNombre = usuNombre;
	}

	public String getUsuApellido1() {
		return this.usuApellido1;
	}

	public void setUsuApellido1(String usuApellido1) {
		this.usuApellido1 = usuApellido1;
	}

	public String getUsuApellido2() {
		return this.usuApellido2;
	}

	public void setUsuApellido2(String usuApellido2) {
		this.usuApellido2 = usuApellido2;
	}

	public String getUsuContrasena() {
		return this.usuContrasena;
	}

	public void setUsuContrasena(String usuContrasena) {
		this.usuContrasena = usuContrasena;
	}

	public Long getUsuFechaBaja() {
		return this.usuFechaBaja;
	}

	public void setUsuFechaBaja(Long usuFechaBaja) {
		this.usuFechaBaja = usuFechaBaja;
	}

	public String getUsuMail() {
		return this.usuMail;
	}

	public void setUsuMail(String usuMail) {
		this.usuMail = usuMail;
	}

	public void setUsuActivo(boolean usuActivo) {
		this.usuActivo = usuActivo;
	}

	public boolean isUsuActivo() {
		return usuActivo;
	}

	public void setUsuAvatar(byte[] usuAvatar) {
		this.usuAvatar = usuAvatar;
	}

	public byte[] getUsuAvatar() {
		return usuAvatar;
	}

	public void setUsuPublicable(boolean usuPublicable) {
		this.usuPublicable = usuPublicable;
	}

	public boolean isUsuPublicable() {
		return usuPublicable;
	}

	public void setUsuRecibeAviso(boolean usuRecibeAviso) {
		this.usuRecibeAviso = usuRecibeAviso;
	}

	public boolean isUsuRecibeAviso() {
		return usuRecibeAviso;
	}

	public void setPerfil(Long[] perfil) {
		this.perfil = perfil;
	}

	public Long[] getPerfil() {
		return perfil;
	}
}
