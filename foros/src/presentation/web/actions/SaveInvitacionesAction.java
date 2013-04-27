package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.MensajeForm;

import common.business.bo.EntradasBO;
import common.business.bo.QueriesForosBO;
import common.dto.EntradasDTO;
import common.presentation.WebException;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class SaveInvitacionesAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

    	MensajeForm lform = (MensajeForm)session.getAttribute("MensajeForm");
    	String[] users = request.getParameterValues("to");
    	
    	Long[] lUsers;
		EntradasBO entradasBO = (EntradasBO) SpringUtil.getInstance().getBean("EntradasBO");
		EntradasDTO entrada = entradasBO.getByPrimaryKey(lform.getEntPk());
    	if (users!=null && users[0].length() > 0 && !entrada.getEntRestringida())
    	{
        	lUsers = new Long[users.length + 1];
        	for (int ind=0; ind<users.length; ind++)
        		lUsers[ind] = Long.valueOf(users[ind]);
        	lUsers[lUsers.length - 1] = user.getPk();
        	
    		QueriesForosBO queriesBO = (QueriesForosBO) SpringUtil.getInstance().getBean("QueriesForosBO");
    		queriesBO.addInvitaciones(lform.getEntPk(), user.getPk(), lUsers);
        }
    	else if (entrada.getEntRestringida())
    	{
	    	throw new WebException("Esta entrada esta declarada como restringida y no permite invitaciones.", request);
    	}
		
    	return null;
	}

}
