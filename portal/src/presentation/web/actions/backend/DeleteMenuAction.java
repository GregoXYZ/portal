package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.web.forms.MenuForm;

import common.business.bo.MenusBO;
import common.dto.MenusDTO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class DeleteMenuAction extends SecurityAction {
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		MenuForm lform = (MenuForm) form;
		
    	MenusBO bo = (MenusBO) SpringUtil.getInstance().getBean("MenusBO");
    	MenusDTO dto = bo.getByPrimaryKey(lform.getMenPk());
    	
    	bo.delete(dto);

		ActionForward forward;
	    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
	    forward =  redirect;

		return forward;
	}
}
