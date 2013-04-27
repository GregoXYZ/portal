package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.util.Convert;
import presentation.web.forms.UserForm;

import common.business.bo.GruposBO;
import common.business.bo.UsuariosBO;
import common.business.bo.UsuariosGruposBO;
import common.dto.GruposDTO;
import common.dto.UsuariosDTO;
import common.presentation.util.WebInfoHelper;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class EditUserAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	UserForm rform = (UserForm)form;
    	
    	if ( rform.getUsuPk() == null || rform.getUsuPk() == 0 )
    	{
           	rform.limpia();
    	} else
    	{
        	UsuariosBO bo = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
        	UsuariosDTO dto = bo.getByPrimaryKey(rform.getUsuPk());
        	Convert.getInstance().setForm(rform);
        	Convert.getInstance().dto2form(dto);

        	UsuariosGruposBO boPerfil = (UsuariosGruposBO) SpringUtil.getInstance().getBean("UsuariosGruposBO");
        	rform.setPerfil(boPerfil.getByUser(rform.getUsuPk()));
    	}
    	
    	GruposBO boGrupos = (GruposBO) SpringUtil.getInstance().getBean("GruposBO");
    	GruposDTO[] grupos = boGrupos.getGrupos();        	
    	request.removeAttribute("listGrupos");
    	request.setAttribute("listGrupos", grupos);    	
    	
    	WebInfoHelper.getInstance().setWebInfo(request, "Los campos marcados con (*) son obligatorios");
    	
    	return mapping.findForward("success");    		
    }
}
