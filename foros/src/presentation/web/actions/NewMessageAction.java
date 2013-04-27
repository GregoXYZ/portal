package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.MensajeForm;

import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;


public class NewMessageAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

		MensajeForm lform = (MensajeForm)form;
		
		lform.setFrom(user.getNombre() + 
				(user.getApellido1()==null?"":" " + user.getApellido1()) +
				(user.getApellido2()==null?"":" " + user.getApellido2())
				);
		
    	return mapping.findForward("success");
	}

}
