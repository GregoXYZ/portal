package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;

public class HomeAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	HttpSession session = request.getSession();
    	
    	UserInfo user = (UserInfo) session.getAttribute("user");

    	if (user == null)
    	{
        	return mapping.findForward("noLogin");    		
    	}
    	else
    	{
        	return mapping.findForward("Login");    		    		
    	}
    }
}
