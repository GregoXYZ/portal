package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.util.Convert;
import presentation.web.forms.MimeFileForm;

import common.business.bo.MimeFilesBO;
import common.dto.MimeFilesDTO;
import common.presentation.util.WebInfoHelper;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class EditMimeFileAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	MimeFileForm rform = (MimeFileForm)form;
    	
    	if ( rform.getMimFilPk() == null || rform.getMimFilPk() == 0 )
    	{
           	rform.limpia();
    	} else
    	{
        	MimeFilesBO bo = (MimeFilesBO) SpringUtil.getInstance().getBean("MimeFilesBO");
        	MimeFilesDTO dto = bo.getByPrimaryKey(rform.getMimFilPk());
        	Convert.getInstance().setForm(rform);
        	Convert.getInstance().dto2form(dto);
    	}
    	
		WebInfoHelper.getInstance().setWebInfo(request, "Los campos marcados con (*) son obligatorios");
    	
    	return mapping.findForward("success");    		
    }
}
