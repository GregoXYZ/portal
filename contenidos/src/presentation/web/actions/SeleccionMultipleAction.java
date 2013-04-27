package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.SelectionFilesForm;

import common.Constants;
import common.business.bo.FicherosBO;
import common.business.bo.UsuariosBO;
import common.dto.FicherosDTO;
import common.dto.UsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class SeleccionMultipleAction extends SecurityAction {
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

		SelectionFilesForm lform = (SelectionFilesForm) form; 
		
    	FicherosBO ficherosBO = (FicherosBO) SpringUtil.getInstance().getBean("FicherosBO");
    	FicherosDTO[] listFicheros = ficherosBO.getFicheros(user.getPk(), lform.getCarPk());

    	request.removeAttribute("allFiles");
    	request.setAttribute("allFiles", listFicheros);

    	request.removeAttribute("listFicherosSeleccion");
    	request.setAttribute("listFicherosSeleccion", new FicherosDTO[0]);

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

    	lform.limpia();
    	
		return mapping.findForward("success");    	
	}

}
