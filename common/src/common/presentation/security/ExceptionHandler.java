package common.presentation.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ExceptionConfig;

import common.presentation.NoBrowserException;
import common.presentation.NoLoginException;
import common.presentation.SystemException;
import common.presentation.WebException;
import common.presentation.ZoneException;
import common.presentation.util.WebInfoHelper;

public class ExceptionHandler extends org.apache.struts.action.ExceptionHandler    
{   

	private static String lastUri;
	
	public ActionForward execute(Exception exception, ExceptionConfig config,                      
           ActionMapping mapping, ActionForm form, HttpServletRequest request,    
           HttpServletResponse response) throws ServletException    
	{
	    if(exception.getClass().equals(WebException.class))   
	    {
			try {
				lastUri = getLastUri(request);
				if (request.getHeader("X-Requested-With")!=null && request.getHeader("X-Requested-With").equals("XMLHttpRequest"))
				{
					response.setStatus(999);
					response.sendError(999, exception.getMessage());
				}
				response.sendRedirect(lastUri);
			} catch (IOException e) {
				WebInfoHelper.getInstance().setSystemError(request, e);
				return mapping.findForward("errorNoControlado");   
			}
			return null;   
	    }
	    else if ( exception.getClass().equals(NoLoginException.class))
	    {
        	try {
				lastUri = getLastUri(request);
        		response.sendRedirect("/portal/sessiontimeout.do?uri=" + lastUri);
			} catch (IOException e) {
				WebInfoHelper.getInstance().setSystemError(request, e);
				return mapping.findForward("errorNoControlado");
			}
			return null;
			//return mapping.findForward("noLoginException");   	    	
	    }
	    else if ( exception.getClass().equals(NoBrowserException.class))
	    {
			return mapping.findForward("noBrowser");   	    	
	    }
	    else if ( exception.getClass().equals(ZoneException.class))
	    {
			try {
				if (request.getHeader("X-Requested-With")!=null && request.getHeader("X-Requested-With").equals("XMLHttpRequest"))
				{
					response.setStatus(999);
					response.sendError(999, exception.getMessage());
				}
				response.sendRedirect("/portal/homePortal.do");
			} catch (IOException e) {
				WebInfoHelper.getInstance().setSystemError(request, e);
				return mapping.findForward("errorNoControlado");
			}
			return null;   	    	
	    }
	    if(exception.getClass().equals(SystemException.class))
	    {
			try {
				lastUri = getLastUri(request);
				if (request.getHeader("X-Requested-With")!=null && request.getHeader("X-Requested-With").equals("XMLHttpRequest"))
				{
					response.setStatus(999);
					response.sendError(999, exception.getMessage());
				}
				response.sendRedirect(lastUri);
			} catch (IOException e) {
				WebInfoHelper.getInstance().setSystemError(request, e);
				return mapping.findForward("errorNoControlado");   
			}
			return null;   	    	
	    }
	    else   
	    {
			WebInfoHelper.getInstance().setSystemError(request, exception);
			if (request.getHeader("X-Requested-With")!=null && request.getHeader("X-Requested-With").equals("XMLHttpRequest"))
			{
				try {
					response.setStatus(999);
					response.sendError(999, exception.getMessage());
				} catch (IOException e) {
					WebInfoHelper.getInstance().setSystemError(request, e);
				}
			}
			return mapping.findForward("errorNoControlado");
	    }
	}
	
	private String getLastUri(HttpServletRequest request) throws IOException
	{
		String uri;

		if (request.getHeader("X-Requested-With")!=null && request.getHeader("X-Requested-With").equals("XMLHttpRequest"))
		{
			uri = request.getContextPath();
		}
		else
		{
			uri = request.getRequestURI();        			
		}
		
		if (lastUri!=null && lastUri.equals(uri))
		{
			uri = request.getContextPath();
			if (lastUri.equals(uri))
				uri = "/portal";
		}
		
		return uri;
	}
}
