package common.presentation.beans;

public abstract class HtmlEvents {
	private String onMouseOver;
	private String onClick;
	private String href;

	public void setOnMouseOver(String onMouseOver) {
		this.onMouseOver = onMouseOver;
	}

	public String getOnMouseOver() {
		return onMouseOver;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getHref() {
		return href;
	}

}
