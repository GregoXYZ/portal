package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.LoginForm;

import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;


public class InitAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	LoginForm rform = (LoginForm)form;
    	rform.limpia();

    	String uri = request.getParameter("uri");    	
    	if ( uri != null ) rform.setRedirectContext(uri);

    	return mapping.findForward("success");
    		
    }
}
