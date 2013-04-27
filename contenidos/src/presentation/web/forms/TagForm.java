package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class TagForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -598284481577231928L;
	private Long tagPk;
	private String tag;
	private Long assPk;

	public Long getTagPk() {
		return this.tagPk;
	}

	public void setTagPk(Long tagPk) {
		this.tagPk = tagPk;
	}

	public void setAssPk(Long assPk) {
		this.assPk = assPk;
	}

	public Long getAssPk() {
		return assPk;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	@Override
	public void limpia() {
		tagPk = null;
		tag = null;
		assPk = null;
	}

}
