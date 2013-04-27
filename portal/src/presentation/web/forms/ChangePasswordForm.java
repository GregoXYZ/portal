package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class ChangePasswordForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7214652796744582675L;
	private String oldPassword;
	private String newPassword;
	private String repeatPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	public void limpia()
	{
		oldPassword = null;
		newPassword = null;
		repeatPassword = null;
	}
}
