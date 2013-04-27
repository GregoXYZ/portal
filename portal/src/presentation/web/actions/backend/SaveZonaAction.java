package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.util.Convert;
import presentation.web.forms.ZonaForm;

import common.business.bo.ZonasBO;
import common.dto.ZonasDTO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class SaveZonaAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	ZonaForm rform = (ZonaForm)form;
    	ZonasBO bo = (ZonasBO) SpringUtil.getInstance().getBean("ZonasBO");    	
    	Convert.getInstance().setForm(rform);
    	ZonasDTO dto = Convert.getInstance().frm2dto(rform);
    	
    	if ( rform.getZonPk() == null || rform.getZonPk() == 0 )
    	{
    		dto.setZonPk(null);
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
