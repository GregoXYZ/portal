package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.LoginForm;

import common.business.bo.SessionsBO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class TimeOutAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	LoginForm rform = (LoginForm)form;
    	rform.limpia();

		SessionsBO boSession = (SessionsBO) SpringUtil.getInstance().getBean("SessionsBO");
		
		boSession.logout(request, response);
		//boSession.closeSession(request, response);
		
    	String uri = request.getParameter("uri");
    	if ( uri != null ) 
    		request.setAttribute("uri", uri);

    	return mapping.findForward("success");
    }
}
