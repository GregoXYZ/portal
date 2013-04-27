package common.dto;

public class UrlsGruposDTO implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2713031779257705943L;
	private Long urlFk;
	private Long gruFk;

	public void setUrlFk(Long urlFk) {
		this.urlFk = urlFk;
	}
	public Long getUrlFk() {
		return urlFk;
	}
	public UrlsGruposDTO() {
	}
	public Long getGruFk() {
		return gruFk;
	}
	public void setGruFk(Long gruFk) {
		this.gruFk = gruFk;
	}
}
