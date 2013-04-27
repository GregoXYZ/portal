package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.util.Convert;
import presentation.web.forms.ZonaForm;

import common.business.bo.ZonasBO;
import common.dto.ZonasDTO;
import common.presentation.util.WebInfoHelper;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class EditZonaAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	ZonaForm rform = (ZonaForm)form;
    	
    	if ( rform.getZonPk() == null || rform.getZonPk() == 0 )
    	{
           	rform.limpia();
    	} else
    	{
        	ZonasBO bo = (ZonasBO) SpringUtil.getInstance().getBean("ZonasBO");
        	ZonasDTO dto = bo.getByPrimaryKey(rform.getZonPk());
        	Convert.getInstance().setForm(rform);
        	Convert.getInstance().dto2form(dto);
    	}
    	
    	WebInfoHelper.getInstance().setWebInfo(request, "Los campos marcados con (*) son obligatorios");
    	
    	return mapping.findForward("success");    		
    }
}
