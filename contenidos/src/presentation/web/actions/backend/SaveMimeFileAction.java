package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.util.Convert;
import presentation.web.forms.MimeFileForm;

import common.IConstantes;
import common.business.bo.MimeFilesBO;
import common.dto.MimeFilesDTO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class SaveMimeFileAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	MimeFileForm rform = (MimeFileForm)form;
    	MimeFilesBO bo = (MimeFilesBO) SpringUtil.getInstance().getBean("MimeFilesBO");    	
    	Convert.getInstance().setForm(rform);
    	MimeFilesDTO dto = Convert.getInstance().frm2dto(rform);
    	
    	String path = null;
    	byte[] file = null;
    	if ( rform.getIconFile() != null && rform.getIconFile().getFileName().length() > 0 )
    	{
			String sysPath = request.getSession().getServletContext().getRealPath("/") + IConstantes.DEFAUL_MIME_ICON_DIR;
    		path = sysPath + rform.getIconFile().getFileName();
    		file = rform.getIconFile().getFileData();

    	}
    	
    	if ( rform.getMimFilPk() == null || rform.getMimFilPk() == 0 )
    	{
    		dto.setMimFilPk(null);
    		bo.add(path, file, dto);
    	} else
    	{
    		bo.update(path, file, dto);
    	}
    		
       	rform.limpia();

		ActionForward forward;
	    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
	    forward =  redirect;

		return forward;
    }
}
