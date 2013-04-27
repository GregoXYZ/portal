package presentation.web.actions;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.SessionForm;

import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;

public class SessionInfoAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	//HttpSession session = request.getSession();
    	//UserInfo user = (UserInfo) session.getAttribute("user");
    	
    	SessionForm lform = (SessionForm) form;
    	
    	InetAddress addr = InetAddress.getByName("69.89.27.203");
    	String hostName = addr.getHostName();
    	lform.setHostName(hostName);
    	
    	return mapping.findForward("success");
    }
}
