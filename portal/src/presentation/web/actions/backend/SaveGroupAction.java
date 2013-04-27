package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.util.Convert;
import presentation.web.forms.GroupForm;

import common.business.bo.GruposBO;
import common.dto.GruposDTO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class SaveGroupAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	GroupForm rform = (GroupForm)form;
    	GruposBO bo = (GruposBO) SpringUtil.getInstance().getBean("GruposBO");    	
    	Convert.getInstance().setForm(rform);
    	GruposDTO dto = Convert.getInstance().frm2dto(rform);
    	
    	if ( rform.getGruPk() == null || rform.getGruPk() == 0 )
    	{
    		dto.setGruPk(null);
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
