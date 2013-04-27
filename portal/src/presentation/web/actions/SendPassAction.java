package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.UserForm;

import common.business.bo.UsuariosBO;
import common.dto.UsuariosDTO;
import common.presentation.beans.WebInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class SendPassAction extends SecurityAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(SendPassAction.class);

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserForm lform = (UserForm) form;
		UsuariosBO boUsuarios = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
		UsuariosDTO user = boUsuarios.getByCode(lform.getUser());

    	WebInfo info = (WebInfo) request.getAttribute("info");

    	if ( user != null && user.getUsuMail().toLowerCase().equals(lform.getMail().toLowerCase()) )
		{
        	if (info == null)
        	{
        		info = new WebInfo();
        		info.setTipo(WebInfo.TypeInfo.MESSAGE);
        	}
    		
        	// genera y envia mail
    		info.append("Proximamente recibirá un mail con su nueva contraseña.", WebInfo.TypeInfo.MESSAGE);
    		logger.info("Se ha solicitado una nueva contraseña para el usuario " + lform.getUser() + " y el mail " + lform.getMail());
		}
    	else
    	{
        	if (info == null)
        	{
        		info = new WebInfo();
        	}
    		
    		info.append("Los datos incorrectos. No existe nigún usuario registrado con ese mail.", WebInfo.TypeInfo.ERROR);
    		logger.error("Se ha solicitado una nueva contraseña para el usuario " + lform.getUser() + " y el mail " + lform.getMail());
    		//return mapping.findForward("error");
    	}
    	
		request.setAttribute("info", info);

		return mapping.findForward("success");
	}

}
