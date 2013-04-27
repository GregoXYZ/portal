package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.web.forms.MimeFileForm;

import common.business.bo.MimeFilesBO;
import common.dto.MimeFilesDTO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class DeleteMimeFileAction extends SecurityAction {
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		MimeFileForm lform = (MimeFileForm) form;
		
    	MimeFilesBO bo = (MimeFilesBO) SpringUtil.getInstance().getBean("MimeFilesBO");
    	MimeFilesDTO dto = bo.getByPrimaryKey(lform.getMimFilPk());
    	
    	bo.delete(dto);

		ActionForward forward;
	    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
	    forward =  redirect;

		return forward;
	}
}
