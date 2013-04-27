package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.web.forms.UserForm;

import common.business.bo.UsuariosBO;
import common.dto.UsuariosDTO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class DeleteUserAction extends SecurityAction {
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserForm lform = (UserForm) form;
		
    	UsuariosBO bo = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
    	UsuariosDTO dto = bo.getByPrimaryKey(lform.getUsuPk());
    	
    	bo.delete(dto, request);

		ActionForward forward;
	    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
	    forward =  redirect;

		return forward;
	}
}
