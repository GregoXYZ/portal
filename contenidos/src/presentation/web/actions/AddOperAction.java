package presentation.web.actions;

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


public class AddOperAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");

		OperacionesForm lform = (OperacionesForm) form;
		Long asset = Long.valueOf(request.getParameter("assPk"));
		
		request.removeAttribute("operationAssets");
		if (!lform.getAssets().containsKey(asset))
		{
			AssetsBO assetsBO = (AssetsBO)SpringUtil.getInstance().getBean("AssetsBO");
			AssetsDTO assetsDTO = assetsBO.getByPrimaryKey(asset, user.getPk());
			lform.getAssets().put(assetsDTO.getAssPk(), assetsDTO.getAssNombre());
		}
		request.setAttribute("operationAssets", lform.getAssets());

    	return mapping.findForward("success");
	}

}
