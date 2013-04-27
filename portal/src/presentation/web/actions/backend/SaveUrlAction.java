package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.util.Convert;
import presentation.web.forms.UrlForm;

import common.business.bo.UrlsBO;
import common.dto.UrlsDTO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class SaveUrlAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	UrlForm rform = (UrlForm)form;
    	UrlsBO bo = (UrlsBO) SpringUtil.getInstance().getBean("UrlsBO");    	
    	Convert.getInstance().setForm(rform);
    	UrlsDTO dto = Convert.getInstance().frm2dto(rform);
    	
    	if ( rform.getUrlPk() == null || rform.getUrlPk() == 0 )
    	{
    		dto.setUrlPk(null);
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
