package presentation.web.actions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.web.forms.LoginForm;

import common.business.BusinessException;
import common.business.bo.SessionsBO;
import common.presentation.WebException;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class LoginAction extends SecurityAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(LoginAction.class);
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response) throws IOException, WebException {

    	LoginForm lform = (LoginForm)form;
    	
     	SessionsBO boSessions = (SessionsBO) SpringUtil.getInstance().getBean("SessionsBO");
     	String userCode = lform.getUser()==null?"":lform.getUser();
    	try {
			if ( !boSessions.login(userCode, 
					lform.getPassword()==null?"":lform.getPassword(), request, response) )
			{
				//String userCode = loginUser;
				lform.limpia();
				lform.setUser(userCode);
				throw new WebException("Usuario/Contraseña incorrectos.", request);
				//return mapping.findForward("errorlogin");    		
			}
		} catch (BusinessException e) {
			lform.limpia();
			lform.setUser(userCode);
			throw new WebException(e.getMessage(), request);
			//return mapping.findForward("errorlogin");    		
		}

    	if ( lform.getRedirectContext() != null && lform.getRedirectContext().length() > 0 )
    	{
    		if ( logger.isDebugEnabled() ) 
    		{
    			logger.debug("Redireccionando a " + lform.getRedirectContext());
    		}
    		response.sendRedirect(lform.getRedirectContext());
        	lform.limpia();
    		return null;
    	}
    	lform.limpia();
    	
	    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
		return redirect;
    }
}
