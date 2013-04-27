package common.dto;

public class GruposMenusDTO implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2713031779257705943L;
	private Long menFk;
	private Long gruFk;

	public void setMenFk(Long menFk) {
		this.menFk = menFk;
	}
	public Long getMenFk() {
		return menFk;
	}
	public GruposMenusDTO() {
	}
	public Long getGruFk() {
		return gruFk;
	}
	public void setGruFk(Long gruFk) {
		this.gruFk = gruFk;
	}
}
