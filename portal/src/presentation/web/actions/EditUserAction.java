package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.UserForm;

import common.presentation.security.beans.UserInfo;
import common.presentation.util.WebInfoHelper;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;

public class EditUserAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	UserForm rform = (UserForm)form;

    	WebInfoHelper.getInstance().setWebInfo(request, "Los campos marcados con (*) son obligatorios");
    	
       	rform.limpia();

    	HttpSession session = request.getSession();
    	
    	UserInfo userInfo = (UserInfo) session.getAttribute("user");

    	rform.setNombre(userInfo.getNombre());
    	rform.setApellido1(userInfo.getApellido1());
    	rform.setApellido2(userInfo.getApellido2());
    	rform.setMail(userInfo.getMail());
    	rform.setUser(userInfo.getUser());
    	rform.setPublicable(userInfo.isPublicable());
    	rform.setRecibeAbisos(userInfo.isRecibeAbisos());

    	return mapping.findForward("success");
    		
    }
}
