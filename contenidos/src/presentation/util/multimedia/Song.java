package presentation.util.multimedia;

import java.io.Serializable;

public class Song implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5373712900104845048L;
	private Long asset;
	private String link;
	private String name;
	
	public void setAsset(Long asset) {
		this.asset = asset;
	}
	public Long getAsset() {
		return asset;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLink() {
		return link;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
