package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import common.presentation.util.WebInfoHelper;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.presentation.web.security.forms.BaseForm;


public class CreateUserAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	BaseForm rform = (BaseForm)form;
    	
    	WebInfoHelper.getInstance().setWebInfo(request, "Los campos marcados con (*) son obligatorios");
    	
       	rform.limpia();
       	
       	return mapping.findForward("success");
    }
}
