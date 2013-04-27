package presentation.web.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.MensajeForm;

import common.Constants;
import common.business.bo.DestinosBO;
import common.business.bo.EntradasBO;
import common.business.bo.UsuariosBO;
import common.dto.DestinosDTO;
import common.dto.EntradasDTO;
import common.dto.UsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class InvitacionAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");

    	request.removeAttribute("listUsers");
    	request.removeAttribute("restringida");

    	MensajeForm sform = (MensajeForm)session.getAttribute("MensajeForm");
		EntradasBO entradasBO = (EntradasBO) SpringUtil.getInstance().getBean("EntradasBO");
		EntradasDTO entrada = entradasBO.getByPrimaryKey(sform.getEntPk());

		if (entrada != null && !entrada.getEntRestringida())
		{
	    	MensajeForm lform = (MensajeForm) form;
	    	lform.setEntrada(sform.getEntrada());
	    	lform.setEntPk(sform.getEntPk());
	    	
			UsuariosBO usuariosBO = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
			DestinosBO destinosBO = (DestinosBO) SpringUtil.getInstance().getBean("DestinosBO");
			UsuariosDTO[] usuarios = usuariosBO.getUsuarios(user.getPk(), Constants.RELACION_ACEPTADA);
			List<UsuariosDTO> usersDisponibles = new ArrayList<UsuariosDTO>();
	    	for (int i=0; i < usuarios.length; i++ )
	    	{
	    		DestinosDTO destino = destinosBO.getByPrimaryKey(usuarios[i].getUsuPk(), sform.getEntPk());
	    		if (destino == null)
	    		{
		    		String nombre = "(" + usuarios[i].getUsuUkUsuario() + ") " + 
	    			usuarios[i].getUsuNombre() + " " + 
	    			usuarios[i].getUsuApellido1() + " " +
	    			usuarios[i].getUsuApellido2(); 
		    		usuarios[i].setUsuUkUsuario(nombre);
	    			usersDisponibles.add(usuarios[i]);
	    		}
	    	}
	    	request.setAttribute("listUsers", usersDisponibles);
		}
		else if (entrada!=null && entrada.getEntRestringida())
		{
	    	request.setAttribute("restringida", true);
		}
			
    	return mapping.findForward("success");
	}

}
