package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import common.Constants;
import common.business.bo.UsuariosBO;
import common.dto.UsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.presentation.web.tld.relaciones.beans.UsuarioRelacionado;
import common.util.spring.SpringUtil;

public class RelacionesUsuarioAction extends SecurityAction {
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");
    	
    	request.removeAttribute("listRelacionUsuarios");
    	
		UsuariosDTO[] usuarios;
		UsuariosBO queriesBO = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
    	usuarios = queriesBO.getUsuarios(user.getPk());
    	
    	UsuarioRelacionado[] relaciones = new UsuarioRelacionado[usuarios.length]; 
    	for (int i=0; i < usuarios.length && usuarios[i] != null; i++ )
    	{
    		UsuarioRelacionado relacion = new UsuarioRelacionado();
    		relacion.setId(":" + usuarios[i].getUsuPk());
    		relacion.setUsuPk(usuarios[i].getUsuPk());
    		String nombre = usuarios[i].getUsuNombre() + " " + 
    			usuarios[i].getUsuApellido1() + " " +
    			usuarios[i].getUsuApellido2();
    		relacion.setNombre(nombre);
    		relacion.setCodigo(usuarios[i].getUsuUkUsuario());
    		relacion.setSolicitarRelacion("updateRelacion(" + usuarios[i].getUsuPk()+ ", " + Constants.SOLICITAR_RELACION + ");");
    		relacion.setCancelarSolicitud("updateRelacion(" + usuarios[i].getUsuPk()+ ", " + Constants.CANCELAR_SOLICITUD + ");");
    		relacion.setAceptarSolicitud("updateRelacion(" + usuarios[i].getUsuPk()+ ", " + Constants.ACEPTAR_SOLICITUD + ");");
    		relacion.setRechazarSolicitud("updateRelacion(" + usuarios[i].getUsuPk()+ ", " + Constants.RECHAZAR_SOLICITUD + ");");
    		relacion.setCancelarRelacion("updateRelacion(" + usuarios[i].getUsuPk()+ ", " + Constants.CANCELAR_RELACION + ");");
    		relaciones[i] = relacion;
    	}
    	request.setAttribute("listRelacionUsuarios", relaciones);
    	
		return mapping.findForward("success");    	
	}

}
