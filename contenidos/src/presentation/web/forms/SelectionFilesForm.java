package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class SelectionFilesForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8247481968985932935L;
	private Long carPk;
	private Long[] allFiles;
	private Long[] files;
	private Long[] users;
	private boolean reemplaza;
	private String tags;

	public void setCarPk(Long carPk) {
		this.carPk = carPk;
	}

	public Long getCarPk() {
		return carPk;
	}

	public void setAllFiles(Long[] allFiles) {
		this.allFiles = allFiles;
	}

	public Long[] getAllFiles() {
		return allFiles;
	}

	public void setFiles(Long[] files) {
		this.files = files;
	}

	public Long[] getFiles() {
		return files;
	}

	public void setUsers(Long[] users) {
		this.users = users;
	}

	public Long[] getUsers() {
		return users;
	}

	public void setReemplaza(boolean reemplaza) {
		this.reemplaza = reemplaza;
	}

	public boolean isReemplaza() {
		return reemplaza;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getTags() {
		return tags;
	}

	@Override
	public void limpia() {
		carPk = null;
		allFiles = null;
		files = null;
		users = null;
		reemplaza = false;
		tags = null;
	}
	
}
