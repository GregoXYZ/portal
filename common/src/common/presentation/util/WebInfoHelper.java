package common.presentation.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.presentation.beans.WebInfo;

public class WebInfoHelper {

	private static Log logger = LogFactory.getLog(WebInfoHelper.class);

	// Instancia del Singleton
	private static WebInfoHelper _instance = null;
	
	// Miembros de la clase
	// Inicializador de clase
	static {
		_instance = new WebInfoHelper();
	}

	/**
	 * Acceso al Singleton
	 * @return ConfigProperties
	 */
	public static WebInfoHelper getInstance () {
		/*if (_instance == null) {
			_instance = new WebInfo();
		}*/
		return _instance;
	}

    public void setSystemError(HttpServletRequest request, Throwable e)
    {
    	
    	HttpSession session = request.getSession();
    	WebInfo info = (WebInfo) session.getAttribute("info");
    	
    	if (info == null)
    	{
    		info = new WebInfo();
    	}
    	
    	info.setTipo(WebInfo.TypeInfo.SYSTEM);
    	if (e.getCause() != null)
    		info.append(e.getCause().toString());
    	info.append(e.getMessage());
    	if (e.getStackTrace() != null)
    		info.append(e.getStackTrace().toString());

    	logger.error("Exception: " + e);
    	if (e.getMessage() != null)
    		logger.error("Message: " + e.getMessage());
    	if (e.getCause() != null)
    		logger.error("Cause: " + e.getCause());
    	if (e.getStackTrace() != null)
    		logger.error("StackTrace: " + e.getStackTrace());
    	
    	session.setAttribute("info", info);
    }

    public void setSystemError(HttpServletRequest request, String msg)
    {
    	HttpSession session = request.getSession();
    	WebInfo info = (WebInfo) session.getAttribute("info");
    	
    	if (info == null)
    	{
    		info = new WebInfo();
    	}
    	
    	info.setTipo(WebInfo.TypeInfo.SYSTEM);    		
    	info.append(msg);
		logger.error(msg);
		
	   	session.setAttribute("info", info);
    }

    public void setWebError(HttpServletRequest request, Throwable e)
    {
    	
    	HttpSession session = request.getSession();
    	WebInfo info = (WebInfo) session.getAttribute("info");
    	
    	if (info == null)
    	{
    		info = new WebInfo();
    	}
    	
    	if (info.getTipo() != WebInfo.TypeInfo.SYSTEM)
    		info.setTipo(WebInfo.TypeInfo.ERROR);
    	if (e.getCause() != null)
    		info.append(e.getCause().toString());
    	info.append(e.getMessage());
    	if (e.getStackTrace() != null)
    		info.append(e.getStackTrace().toString());

    	logger.error("Exception: " + e);
    	if (e.getMessage() != null)
    		logger.error("Message: " + e.getMessage());
    	if (e.getCause() != null)
    		logger.error("Cause: " + e.getCause());
    	if (e.getStackTrace() != null)
    		logger.error("StackTrace: " + e.getStackTrace());
    	
    	session.setAttribute("info", info);
    }

    public void setWebError(HttpServletRequest request, String msg)
    {
    	HttpSession session = request.getSession();
    	WebInfo info = (WebInfo) session.getAttribute("info");
    	
    	if (info == null)
    	{
    		info = new WebInfo();
    	}
    	
    	if (info.getTipo() != WebInfo.TypeInfo.SYSTEM)
    		info.setTipo(WebInfo.TypeInfo.ERROR);    		
    	info.append(msg);
		logger.error(msg);
		
	   	session.setAttribute("info", info);
    }

    public void setWebWarning(HttpServletRequest request, String msg)
    {
    	HttpSession session = request.getSession();
    	WebInfo info = (WebInfo) session.getAttribute("info");
    	
    	if (info == null)
    	{
    		info = new WebInfo();
        	info.setTipo(WebInfo.TypeInfo.WARNING);    		
    	}
    	else
    	{
    		if (info.getTipo() == WebInfo.TypeInfo.MESSAGE)
    	    	info.setTipo(WebInfo.TypeInfo.WARNING);    		
    	}
    	
    	info.append(msg);
		logger.warn(msg);

	   	session.setAttribute("info", info);
    }

    public void setWebInfo(HttpServletRequest request, String msg)
    {
    	HttpSession session = request.getSession();
    	WebInfo info = (WebInfo) session.getAttribute("info");
    	
    	if (info == null)
    	{
    		info = new WebInfo();
        	info.setTipo(WebInfo.TypeInfo.MESSAGE);    		
    	}
    	
    	info.append(msg);
		logger.info(msg);
		
	   	session.setAttribute("info", info);
    }

}
