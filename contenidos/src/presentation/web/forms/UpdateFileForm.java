package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class UpdateFileForm extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -169643051173012751L;
	private Long assPk;
	private String file;
	private String descripcion;
	private Long[] users;
	private String tags;

	public void setAssPk(Long assPk) {
		this.assPk = assPk;
	}

	public Long getAssPk() {
		return assPk;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFile() {
		return file;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setUsers(Long[] users) {
		this.users = users;
	}

	public Long[] getUsers() {
		return users;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getTags() {
		return tags;
	}

	@Override
	public void limpia() {
		assPk = null;
		file = null;
		users = null;
		descripcion = null;
		tags = null;
	}
	
}
