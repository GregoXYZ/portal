package common.dto;

public class UsuariosGruposDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -342726942294616461L;
	
	private Long usuFk;
	private Long gruFk;

	public UsuariosGruposDTO() {
	}
	
	public Long getUsuFk() {
		return usuFk;
	}
	public void setUsuFk(Long usuFk) {
		this.usuFk = usuFk;
	}
	public Long getGruFk() {
		return gruFk;
	}
	public void setGruFk(Long gruFk) {
		this.gruFk = gruFk;
	}
}
