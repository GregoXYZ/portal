package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.web.forms.GroupForm;

import common.business.bo.GruposBO;
import common.dto.GruposDTO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class DeleteGroupAction extends SecurityAction {
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		GroupForm lform = (GroupForm) form;
		
    	GruposBO bo = (GruposBO) SpringUtil.getInstance().getBean("GruposBO");
    	GruposDTO dto = bo.getByPrimaryKey(lform.getGruPk());
    	
    	bo.delete(dto);

		ActionForward forward;
	    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
	    forward =  redirect;

		return forward;
	}
}
