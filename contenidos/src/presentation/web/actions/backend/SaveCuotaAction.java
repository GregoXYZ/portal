package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.web.forms.backend.CuotaForm;

import common.business.bo.CuotasBO;
import common.dto.CuotasDTO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class SaveCuotaAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CuotaForm lform = (CuotaForm) form;		
		
		CuotasBO cuotaBO = (CuotasBO) SpringUtil.getInstance().getBean("CuotasBO");
    	CuotasDTO cuota = cuotaBO.getCuotaRestante(lform.getUsuFkPk());
    	
		if ( cuota == null  )
		{
			cuota = new CuotasDTO();
			cuota.setUsuFkPk(lform.getUsuFkPk());
			cuota.setCuoCuotaDisk(lform.getCuoCuotaDisk() * 1048576); // 1048576 = 1024 * 1024
			cuota.setCuoCuotaFile(lform.getCuoCuotaFile() * 1048576); // 1048576 = 1024 * 1024
			cuotaBO.add(cuota);
		}
		else
		{
			cuota.setCuoCuotaDisk(lform.getCuoCuotaDisk() * 1048576); // 1048576 = 1024 * 1024
			cuota.setCuoCuotaFile(lform.getCuoCuotaFile() * 1048576); // 1048576 = 1024 * 1024
			cuotaBO.update(cuota);		
		}
		
		lform.limpia();			

		ActionForward forward;
	    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
	    forward =  redirect;

		return forward;
	}
}
