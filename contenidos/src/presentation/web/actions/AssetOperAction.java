package presentation.web.actions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.OperacionesForm;

import common.business.bo.AssetsBO;
import common.dto.AssetsDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class AssetOperAction extends SecurityAction {

	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

		OperacionesForm lform = (OperacionesForm) form;
		
		if (lform.getUser() == null)
			lform.setUser(user.getPk());
		
		AssetsBO assetsBO = (AssetsBO) SpringUtil.getInstance().getBean("AssetsBO");

		Map<Long, String> assets = new HashMap<Long, String>();
		assets.putAll(lform.getAssets());
		
		Iterator<?> it = assets.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
								
			AssetsDTO asset = assetsBO.getByPrimaryKey((Long) e.getKey(), user.getPk());
			if (asset == null)
				lform.getAssets().remove(e.getKey());
		}

		request.removeAttribute("operationAssets");
		if (lform.getUser().compareTo(user.getPk()) != 0)
			lform.setAssets(null);
		request.setAttribute("operationAssets", lform.getAssets());

    	return mapping.findForward("success");
	}

}
