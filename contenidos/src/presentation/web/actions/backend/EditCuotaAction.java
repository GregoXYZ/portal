package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.backend.CuotaForm;

import common.business.bo.CuotasBO;
import common.business.bo.UsuariosBO;
import common.dto.CuotasDTO;
import common.dto.UsuariosDTO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class EditCuotaAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CuotaForm lform = (CuotaForm) form;
		if ( lform.getUsuFkPk() != null && lform.getUsuFkPk() > 0 )
		{
			CuotasBO cuotaBO = (CuotasBO) SpringUtil.getInstance().getBean("CuotasBO");
			CuotasDTO cuotaDTO = cuotaBO.getByPrimaryKey(lform.getUsuFkPk());
			if ( cuotaDTO != null ) {
				if ( cuotaDTO.getCuoCuotaDisk() !=null ) lform.setCuoCuotaDisk(cuotaDTO.getCuoCuotaDisk() / 1048576);
				if ( cuotaDTO.getCuoCuotaFile() !=null ) lform.setCuoCuotaFile(cuotaDTO.getCuoCuotaFile() / 1048576);
			}
			else lform.setCuoCuotaDisk(0L);
		}

    	UsuariosDTO[] usuarios = (UsuariosDTO[]) request.getAttribute("listUsers");
    	if ( usuarios == null )
    	{
	    	UsuariosBO boUsuarios = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
	    	usuarios = boUsuarios.getUsuarios();
	    	for (int i=0; i < usuarios.length; i++ )
	    	{
	    		String nombre = usuarios[i].getUsuUkUsuario() + " - " + 
	    			usuarios[i].getUsuNombre() + " " + 
	    			usuarios[i].getUsuApellido1() + " " +
	    			usuarios[i].getUsuApellido2(); 
	    		usuarios[i].setUsuUkUsuario(nombre);
	    	}
	    	request.removeAttribute("listUsers");
	    	request.setAttribute("listUsers", usuarios);
    	}
		
		return mapping.findForward("success");    	
	}
}
