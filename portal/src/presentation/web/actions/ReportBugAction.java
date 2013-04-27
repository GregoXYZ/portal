package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.BugForm;

import common.business.bo.ZonasBO;
import common.dto.ZonasDTO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class ReportBugAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		BugForm lform = (BugForm) form; 

		if (lform.getBugMessage() == null)
			lform.setBugMessage((String) request.getParameter("mensaje"));
    	
    	ZonasBO boZonas = (ZonasBO) SpringUtil.getInstance().getBean("ZonasBO");
    	ZonasDTO[] zonas = boZonas.getZonas();
    	/*
    	ZonasDTO zona = boZonas.getByCode(mapping.getApplicationZone());
    	if (zona != null)
    		lform.setBugSitePk(zona.getZonPk());
    	*/
    	request.removeAttribute("listZonas");
    	request.setAttribute("listZonas", zonas);    	

		return mapping.findForward("success");
	}
}
