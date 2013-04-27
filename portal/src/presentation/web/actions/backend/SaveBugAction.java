package presentation.web.actions.backend;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.util.Convert;
import presentation.web.forms.BugForm;

import common.business.bo.BugsBO;
import common.dto.BugsDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class SaveBugAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	BugForm rform = (BugForm)form;
    	Convert.getInstance().setForm(rform);
    	BugsDTO dto = Convert.getInstance().frm2dto(rform);
    	
    	BugsBO bo = (BugsBO) SpringUtil.getInstance().getBean("BugsBO");    	
    	if ( rform.getBugPk() == null || rform.getBugPk() == 0 )
    	{
        	HttpSession session = request.getSession();
        	UserInfo user = (UserInfo) session.getAttribute("user");
    		dto.setBugPk(null);
    		dto.setUsuFk(user.getPk());
    		dto.setBugFechaReport(new Date().getTime());
    		bo.add(dto);
    	} else
    	{
    		bo.update(dto);
    	}
    		
       	rform.limpia();

		ActionForward forward;
	    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
	    forward =  redirect;

		return forward;
    }
}
