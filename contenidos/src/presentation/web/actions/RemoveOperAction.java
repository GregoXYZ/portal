package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.OperacionesForm;

import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;


public class RemoveOperAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		OperacionesForm lform = (OperacionesForm) form;
		Long asset = Long.valueOf(request.getParameter("assPk"));
		
		request.removeAttribute("operationAssets");
		lform.getAssets().remove(asset);
		request.setAttribute("operationAssets", lform.getAssets());

    	return mapping.findForward("success");
	}

}
