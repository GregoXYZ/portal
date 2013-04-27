package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import common.business.bo.CuotasBO;
import common.dto.CuotasDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class UserInfoAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
		{

		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		
		CuotasBO cuotaBO = (CuotasBO) SpringUtil.getInstance().getBean("CuotasBO");
    	CuotasDTO cuota = cuotaBO.getCuotaRestante(user.getPk());
    	
    	request.setAttribute("diskcuota", cuota == null?0:(float) cuota.getCuoCuotaDisk() / 1024);
    	request.setAttribute("filecuota", cuota == null?0:(float) cuota.getCuoCuotaFile() / 1024);
    	
		return mapping.findForward("success");
	}
}
