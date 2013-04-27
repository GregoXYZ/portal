package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.util.Convert;
import presentation.web.forms.UserForm;

import common.business.bo.UsuariosBO;
import common.dto.UsuariosDTO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.Encrypt;
import common.util.spring.SpringUtil;


public class SaveUserAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	UserForm rform = (UserForm)form;
    	UsuariosBO bo = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
    	Convert.getInstance().setForm(rform);
    	UsuariosDTO dto = Convert.getInstance().frm2dto(rform);
    	if ( dto.getUsuContrasena() != null && dto.getUsuContrasena().length() > 0 )
    		dto.setUsuContrasena(Encrypt.encriptar(dto.getUsuContrasena()));
    	else
    	{
    		UsuariosDTO dtoAux =bo.getByPrimaryKey(dto.getUsuPk());
    		dto.setUsuContrasena(dtoAux.getUsuContrasena());
    	}
    	
    	if ( rform.getUsuPk() == null || rform.getUsuPk() == 0 )
    	{
    		dto.setUsuPk(null);
    		bo.add(dto);
    	} else
    	{
        	UsuariosDTO dtoAux = bo.getByPrimaryKey(rform.getUsuPk());
        	dto.setUsuAvatar(dtoAux.getUsuAvatar());
    		bo.update(dto);
    	}
    		
       	rform.limpia();

		ActionForward forward;
	    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
	    forward =  redirect;

		return forward;
    }
}
