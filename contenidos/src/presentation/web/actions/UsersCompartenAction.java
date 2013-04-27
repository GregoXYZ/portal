package presentation.web.actions;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.tld.Miniaturas.Beans.Miniatura;

import common.business.BusinessException;
import common.business.bo.ExtraQueriesBO;
import common.dto.UsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.GlobalFunctions;
import common.util.spring.SpringUtil;

public class UsersCompartenAction extends SecurityAction {
	
	private ExtraQueriesBO queriesBO = (ExtraQueriesBO) SpringUtil.getInstance().getBean("ExtraQueriesBO");

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");
    	
    	userShared(user.getPk(), request);
    	
		return mapping.findForward("success");
	}

	private void userShared(long user, HttpServletRequest request) throws BusinessException, IOException {

    	UsuariosDTO[] usuarios = queriesBO.getUsersComparten(user);
    	
    	Miniatura[] comparten = new Miniatura[usuarios.length];
    	for (int ind = 0; ind < usuarios.length; ind++)
    	{
    		comparten[ind] = new Miniatura();
    		comparten[ind].setId("usu" + usuarios[ind].getUsuPk());
    		if (ind == 0)
    			comparten[ind].setStyle("user myuser");
    		else
    			comparten[ind].setStyle("user");
    		
    		String path = GlobalFunctions.pathAbatar(usuarios[ind].getUsuPk(), request.getSession());
    		comparten[ind].setImage(path);
    		
    		comparten[ind].setFormname("CompartidosForm");
    		comparten[ind].setParam("user");
    		comparten[ind].setConfirm("False");
    		comparten[ind].setOnClick("cargaUser(\"" + usuarios[ind].getUsuPk() + "\");");
    		String nombre = usuarios[ind].getUsuNombre() + " " + usuarios[ind].getUsuApellido1() + " " + usuarios[ind].getUsuApellido2();
    		if (nombre.length() > 25)
    		{
    			nombre = nombre.substring(0, 23) + "...";
    		}
    		comparten[ind].setText(nombre);
    	}
    	request.removeAttribute("listUsers");
    	request.setAttribute("listUsers", Arrays.asList(comparten));
	}
}
