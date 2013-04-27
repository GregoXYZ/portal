package common.presentation.web.security.actions;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.business.BusinessException;
import common.presentation.NoBrowserException;
import common.presentation.NoLoginException;
import common.presentation.SystemException;
import common.presentation.WebException;
import common.presentation.ZoneException;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;

public abstract class SecurityAction extends Action {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(SecurityAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // keep JSTL and Struts Locale's in synch
		/*
		HttpSession session = request.getSession();
		Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
		if (locale == null) {
			locale = request.getLocale();
		}
		if (request.getParameter("locale") != null) {
			locale = new Locale(request.getParameter("locale"));
		}
		session.setAttribute(Globals.LOCALE_KEY, locale);
		Config.set(session, Config.FMT_LOCALE, locale);
        */
    	        
        ActionForward action = null;
        doRestriccion(mapping, request, response);

        try 
        {
        	/*
    		if(request.getHeader("X-Requested-With")!=null && request.getHeader("X-Requested-With").equals("XMLHttpRequest") && form instanceof BaseForm)
    			((BaseForm)form).toUTF8();
    		*/
        	action = _execute((SecurityActionMapping) mapping, form, request, response);            
            return action;
        }
        catch (BusinessException e) {
			throw new SystemException(e, request);
		} catch (WebException e) {
			throw new WebException(e, request);
		}
        catch (Exception e) {
			throw new SystemException(e, request);
		}
        finally
        {

        }
    }

    public abstract ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response) throws Exception;   
    
    private void doRestriccion(ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) throws WebException, NoLoginException, NoBrowserException, ZoneException
    {   
    	HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		
		String navigator = request.getHeader("User-Agent");
		
		if ( navigator == null || 
				navigator.contains("MSIE") || 
				navigator.trim().length() == 0 )
		{
			throw new NoBrowserException("Navegador incorrecto.", request);
		}
		
	     /* Este metodo realiza las comprobaciones de acceso con    
	     el perfil del usuario que haya en sesión */
    	SecurityActionMapping securityMapping = (SecurityActionMapping)mapping;
    	String zona = securityMapping.getApplicationZone();
    	
    	/* Comprueba que el usuario tenga privilegios para acceder al action */
    	if ( zona == null || zona.trim().length() == 0 ) 
    	{
    		logger.info("Zona pública. (" + request.getRequestURI() + ")");
    		if ( user != null )
    			throw new ZoneException("Ya estás logado, no puedes acceder a este site.", request);
    	}
    	else 
    	{
    		if ( user == null) 
    		{
				throw new NoLoginException("Acceso no permitido. (No user)", request);
			}
    		else 
    		{
    			if ( logger.isDebugEnabled() )
    				logger.debug("Combrobar privelegios del usuario " + user.getUser() + " para la zona " + zona);
        		Hashtable<?, ?> perfil = user.getPerfil();
        		
        		if (perfil != null && perfil.get(zona) != null )
        		{
        	    	String url = securityMapping.getApplicationUrl();
        	    	if ( url == null || url.trim().length() == 0 )
        	    	{
        	    		logger.info("Url '" + request.getContextPath() + "' pública.");
        	    	}
        	    	else
        	    	{
        	    		if ( logger.isDebugEnabled() )
        	    			logger.debug("Combrobar privelegios del usuario " + user.getUser() + " para la url " + url);
            			Hashtable<?, ?> urls = (Hashtable<?, ?>) perfil.get(zona);
            			if ( urls.get(url) == null )
            			{
            				throw new WebException("Acceso no permitido. (URL)", request); 
            			}
            			else
            			{
            	    		logger.info("Se permite el acceso a " + user.getUser() + " a la url '" + url + "' de la zona '" + zona + "'");            				
            			}
        			}
    	    	}
        		else 
        		{
    				throw new WebException("Acceso no permitido. (ZONA)", request);        			
        		}
    		}    		
    	}
    }
}
