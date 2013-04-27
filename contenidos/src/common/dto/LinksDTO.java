package common.dto;

public class LinksDTO extends AssetsDTO implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2660304342652055250L;
	private Long linPk;
	private Long assFk;
	private Long assFkRef;

	public void setLinPk(Long linPk) {
		this.linPk = linPk;
	}

	public Long getLinPk() {
		return linPk;
	}

	public void setAssFk(Long assFk) {
		this.assFk = assFk;
	}

	public Long getAssFk() {
		return assFk;
	}

	public void setAssFkRef(Long assFkRef) {
		this.assFkRef = assFkRef;
	}

	public Long getAssFkRef() {
		return assFkRef;
	}
}
