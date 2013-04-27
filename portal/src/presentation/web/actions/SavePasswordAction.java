package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import common.business.bo.UsuariosBO;
import common.dto.UsuariosDTO;
import common.presentation.beans.WebInfo;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.Encrypt;
import common.util.spring.SpringUtil;

import presentation.web.forms.ChangePasswordForm;

public class SavePasswordAction extends SecurityAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(SavePasswordAction.class);
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	ChangePasswordForm rform = (ChangePasswordForm)form;
    	    	
    	if ( rform.getNewPassword().equals(rform.getRepeatPassword()) )
    	{
        	HttpSession session = request.getSession();
        	UserInfo userInfo = (UserInfo) session.getAttribute("user");
        	
        	UsuariosBO boUsuarios = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
    		UsuariosDTO dto = boUsuarios.getLogin(userInfo.getUser(), Encrypt.encriptar(rform.getOldPassword()));
    		if ( dto == null )
    		{
        		logger.error("Usuario/Contraseña incorrectos.");
        		
            	WebInfo info = new WebInfo("Usuario/Contraseña incorrectos.", WebInfo.TypeInfo.ERROR);
            	request.setAttribute("info", info);   			
    		} else {
    			dto.setUsuContrasena(Encrypt.encriptar(rform.getNewPassword()));
    			boUsuarios.updateUserData(dto, request);
    		}
    	} else {
    		logger.error("Las contraseñas no coinciden.");
    		
        	WebInfo info = new WebInfo("Las contraseñas no coinciden.", WebInfo.TypeInfo.ERROR);
        	request.setAttribute("info", info);    		
    	}

    	rform.limpia();


	    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
	    ActionForward forward =  redirect;		
    	return forward;
    }
}
