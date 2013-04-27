package presentation.web.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.web.forms.BugForm;

import common.business.bo.BugsBO;
import common.dto.BugsDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class SaveBugAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");

		BugForm lform = (BugForm) form;
		
		BugsDTO bug = new BugsDTO();
		bug.setBugSite(lform.getBugSitePk());
		bug.setBugMessage(lform.getBugMessage());
		bug.setBugDescripcion(lform.getBugDescripcion());
		bug.setBugFechaReport(new Date().getTime());
		bug.setUsuFk(user.getPk());

		BugsBO bugBO = (BugsBO) SpringUtil.getInstance().getBean("BugsBO");
		bugBO.add(bug);

	    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
	    ActionForward forward =  redirect;
    	return forward;
	}
}
