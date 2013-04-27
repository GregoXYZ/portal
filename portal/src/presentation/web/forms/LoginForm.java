package presentation.web.forms;

import common.presentation.web.security.forms.BaseForm;

public class LoginForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7508738116595705002L;

	private String user;
	private String password;
	private String redirectContext;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRedirectContext(String redirectContext) {
		this.redirectContext = redirectContext;
	}

	public String getRedirectContext() {
		return redirectContext;
	}
	
	public void limpia()
	{
		user = null;
		password = null;
		redirectContext = null;
	}
}
