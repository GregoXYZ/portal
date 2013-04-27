package presentation.web.forms;

import java.util.HashMap;
import java.util.Map;

import common.presentation.web.security.forms.BaseForm;

public class OperacionesForm extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 220748187556015352L;
	private Long user;
	private Map<Long, String> assets;

	public void setUser(Long user) {
		this.user = user;
	}

	public Long getUser() {
		return user;
	}

	public void setAssets(Map<Long, String> assets) {
		this.assets = assets;
	}

	public Map<Long, String> getAssets() {
		if (assets == null)
			assets = new HashMap<Long, String>();
		return assets;
	}

	@Override
	public void limpia() {
		assets = null;
	}
	
}
