package common.presentation.beans;

public class Action extends HtmlEvents {
	private String name;
	private String image;
	private String formname;
	private String param;
	private String text;
	private String title;
	private String alt;
	private String style;
	private String message;
	private String confirm;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setFormname(String formname) {
		this.formname = formname;
	}
	public String getFormname() {
		return formname;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getParam() {
		return param;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public String getAlt() {
		return alt;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getStyle() {
		return style==null?"":style;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getConfirm() {
		return confirm;
	}
}
