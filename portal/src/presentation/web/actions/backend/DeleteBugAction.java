package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.web.forms.BugForm;

import common.business.bo.BugsBO;
import common.dto.BugsDTO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class DeleteBugAction extends SecurityAction {
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		BugForm lform = (BugForm) form;
		
		BugsBO bo = (BugsBO) SpringUtil.getInstance().getBean("BugsBO");
		BugsDTO dto = bo.getByPrimaryKey(lform.getBugPk());
    	
    	bo.delete(dto);

		ActionForward forward;
	    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
	    forward =  redirect;

		return forward;
	}
}
