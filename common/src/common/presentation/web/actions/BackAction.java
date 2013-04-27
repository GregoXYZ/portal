package common.presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.ModuleUtils;

import common.Constants;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;

public class BackAction extends SecurityAction {

	// Log
	private static final Log logger = LogFactory.getLog(BackAction.class);

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// FIXME Programar el correcto retorno
		HttpSession session = request.getSession();
    	String page = (String)session.getAttribute(Constants.PAGE_LAST_VISITED);
    	if(page==null || page.equals("")) {
    		page = (String)session.getAttribute(Constants.PAGE_BEFORE_LAST_VISITED);
    	}
    	if(page==null || page.equals("")) {
    		//Vamos a la home de la web
    		response.sendRedirect("/portal/home.do");
    		return null;
    	} else {
        	//hay que eliminar http://host:puerto/contexto
        	String context = request.getContextPath();
        	page = page.substring(page.indexOf(context)+context.length());

        	if(!page.startsWith("/")) {
        		page = "/" + page;
        	}
    	}
		if ( logger.isDebugEnabled() )
			logger.debug("Go to " + page);
    	
    	ModuleUtils moduleUtils = ModuleUtils.getInstance();
    	String module = moduleUtils.getModuleName(page, this.getServlet().getServletContext());
    	moduleUtils.selectModule(module, request, session.getServletContext());
    	
    	String urlPage = page.substring(page.indexOf(module)+module.length());

    	return new ActionForward(urlPage);
	}

}
