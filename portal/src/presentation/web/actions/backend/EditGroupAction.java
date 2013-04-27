package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.util.Convert;
import presentation.web.forms.GroupForm;

import common.business.bo.GruposBO;
import common.dto.GruposDTO;
import common.presentation.util.WebInfoHelper;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class EditGroupAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	GroupForm rform = (GroupForm)form;
    	
    	if ( rform.getGruPk() == null || rform.getGruPk() == 0 )
    	{
           	rform.limpia();
    	} else
    	{
        	GruposBO bo = (GruposBO) SpringUtil.getInstance().getBean("GruposBO");
        	GruposDTO dto = bo.getByPrimaryKey(rform.getGruPk());
        	Convert.getInstance().setForm(rform);
        	Convert.getInstance().dto2form(dto);
    	}
    	
		WebInfoHelper.getInstance().setWebInfo(request, "Los campos marcados con (*) son obligatorios");
    	
    	return mapping.findForward("success");    		
    }
}
