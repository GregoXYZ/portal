package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.MensajeForm;

import common.business.bo.QueriesForosBO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class SaveListAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

    	String[] users = request.getParameterValues("to");
    	//String[] users = request.getParameter("selectedusers").split(",");
    	Long[] lUsers;
    	if (users==null || users[0].length()==0)
    	{
    		lUsers = new Long[1];
    		lUsers[0] = user.getPk();    		
    	}
    	else
    	{
        	lUsers = new Long[users.length + 1];
        	for (int ind=0; ind<users.length; ind++)
        		lUsers[ind] = Long.valueOf(users[ind]);
        	lUsers[lUsers.length - 1] = user.getPk();
        }
    	
		MensajeForm lform = (MensajeForm)form;		
		QueriesForosBO queriesBO = (QueriesForosBO) SpringUtil.getInstance().getBean("QueriesForosBO");

		queriesBO.addEntrada(user.getPk(), lform.getEntrada(), lform.getContenido(), lform.getRestringida(),lUsers); 
    	return null;
	}

}
