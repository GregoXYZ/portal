package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.util.Convert;
import presentation.web.forms.MenuForm;

import common.Constants;
import common.business.bo.MenusBO;
import common.dto.MenusDTO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class SaveMenuAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	MenuForm rform = (MenuForm)form;
    	MenusBO bo = (MenusBO) SpringUtil.getInstance().getBean("MenusBO");

    	Convert.getInstance().setForm(rform);
    	MenusDTO dto = Convert.getInstance().frm2dto(rform);
    	
    	if ( rform.getMenPk() == null || rform.getMenPk() == 0 )
    	{
    		dto.setMenPk(null);
    		if ( dto.getMenIcon() == null )
    			bo.add(dto);
    		else
    		{
    			String path = request.getSession().getServletContext().getRealPath(Constants.MENU_ICON_FOLDER) + "/";
    			bo.add(path, rform.getMenIcon().getFileData(), dto);
    		}
    	} else
    	{
    		if ( dto.getMenIcon() == null )
        		bo.update(dto);
    		else
    		{
    			String path = request.getSession().getServletContext().getRealPath(Constants.MENU_ICON_FOLDER) + "/";
        		bo.update(path, rform.getMenIcon().getFileData(), dto);
    		}
    	}
    		
       	rform.limpia();

		ActionForward forward;
	    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
	    forward =  redirect;

		return forward;
    }
}
