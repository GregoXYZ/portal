package common.presentation.util.grid;

import common.presentation.beans.Action;

public class Column {
	private String id;
	private String header;
	private String reference;
	private Action action;
	private String icon;
	private String style;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public Action getAction() {
		return action;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIcon() {
		return icon;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getStyle() {
		return style;
	}
}
