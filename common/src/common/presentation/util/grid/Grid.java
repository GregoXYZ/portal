package common.presentation.util.grid;

import java.util.List;

import common.presentation.beans.Action;
import common.presentation.beans.HtmlEvents;

public class Grid {
	private String name;
	private String title;
	private String style;
	private Boolean showAlertNoData;
	private String selectKey;
	private Boolean editable;
	private Boolean showRowNums;
	private Boolean withHeader;
	private Boolean withFooter;
	private String rowId;
	private List<Column> columns;
	private List<Action> rowActions;
	private List<Action> globalActions;
	private List<?> selectedElements;
	private List<HtmlEvents> data;

	public Grid() {
		super();
		title = "";
		withHeader = true;
		withFooter = true;
		editable = false;
		showRowNums = false;
	}
	
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}
	public Boolean getEditable() {
		return editable;
	}
	public void setShowRowNums(Boolean showRowNums) {
		this.showRowNums = showRowNums;
	}

	public Boolean getShowRowNums() {
		return showRowNums;
	}

	public void setWithHeader(Boolean withHeader) {
		this.withHeader = withHeader;
	}
	public Boolean getWithHeader() {
		return withHeader;
	}
	public void setWithFooter(Boolean withFooter) {
		this.withFooter = withFooter;
	}
	public Boolean getWithFooter() {
		return withFooter;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getRowId() {
		return rowId;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setStyle(String style) {
		this.style = style;
	}

	public String getStyle() {
		return style;
	}

	public void setShowAlertNoData(Boolean showAlertNoData) {
		this.showAlertNoData = showAlertNoData;
	}
	public Boolean getShowAlertNoData() {
		return showAlertNoData;
	}
	public void setData(List<HtmlEvents> data) {
		this.data = data;
	}
	public List<HtmlEvents> getData() {
		return data;
	}
	public void setRowActions(List<Action> rowActions) {
		this.rowActions = rowActions;
	}
	public List<Action> getRowActions() {
		return rowActions;
	}
	public void setGlobalActions(List<Action> globalActions) {
		this.globalActions = globalActions;
	}
	public List<Action> getGlobalActions() {
		return globalActions;
	}
	public void setSelectKey(String selectKey) {
		this.selectKey = selectKey;
	}
	public String getSelectKey() {
		return selectKey;
	}
	public void setSelectedElements(List<?> selectedElements) {
		this.selectedElements = selectedElements;
	}
	public List<?> getSelectedElements() {
		return selectedElements;
	}
}
