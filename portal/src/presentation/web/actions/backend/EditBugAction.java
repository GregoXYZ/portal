package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.util.Convert;
import presentation.web.forms.BugForm;

import common.business.bo.BugsBO;
import common.business.bo.UsuariosBO;
import common.business.bo.ZonasBO;
import common.dto.BugsDTO;
import common.dto.UsuariosDTO;
import common.dto.ZonasDTO;
import common.presentation.util.WebInfoHelper;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class EditBugAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	BugForm rform = (BugForm)form;
    	
    	if ( rform.getBugPk() == null || rform.getBugPk() == 0 )
    	{
           	rform.limpia();
    	} else
    	{
        	BugsBO bo = (BugsBO) SpringUtil.getInstance().getBean("BugsBO");
        	BugsDTO dto = bo.getByPrimaryKey(rform.getBugPk());
        	Convert.getInstance().setForm(rform);
        	Convert.getInstance().dto2form(dto);
        	
    		UsuariosBO boUsuarios = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
    		UsuariosDTO user = boUsuarios.getByPrimaryKey(rform.getUsuFk());
        	rform.setUsuName(user.getUsuNombre() + " " + user.getUsuApellido1() + " " + user.getUsuApellido2());
    	}
    	
    	ZonasBO boZonas = (ZonasBO) SpringUtil.getInstance().getBean("ZonasBO");
    	ZonasDTO[] zonas = boZonas.getZonas();        	
    	request.removeAttribute("listZonas");
    	request.setAttribute("listZonas", zonas);    	
    	
    	WebInfoHelper.getInstance().setWebInfo(request, "Los campos marcados con (*) son obligatorios");
    	
    	return mapping.findForward("success");    		
    }
}
