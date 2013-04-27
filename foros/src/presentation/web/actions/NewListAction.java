package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.MensajeForm;

import common.Constants;
import common.business.bo.UsuariosBO;
import common.dto.UsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class NewListAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

		MensajeForm lform = (MensajeForm)form;
		
		lform.limpia();
		
		lform.setFrom(user.getNombre() + 
				(user.getApellido1()==null?"":" " + user.getApellido1()) +
				(user.getApellido2()==null?"":" " + user.getApellido2())
				);
		
		UsuariosDTO[] usuarios  = (UsuariosDTO[]) request.getAttribute("listUsers");
    	if ( usuarios == null )
    	{
    		UsuariosBO queriesBO = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
        	usuarios = queriesBO.getUsuarios(user.getPk(), Constants.RELACION_ACEPTADA);
	    	for (int i=0; i < usuarios.length; i++ )
	    	{
	    		String nombre = "(" + usuarios[i].getUsuUkUsuario() + ") " + 
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
