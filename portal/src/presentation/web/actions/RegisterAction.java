package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.RegisterForm;

import common.business.bo.UtilsBO;
import common.dto.UsuariosDTO;
import common.presentation.beans.WebInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.Encrypt;
import common.util.spring.SpringUtil;

public class RegisterAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	RegisterForm rform = (RegisterForm)form;
    	
    	WebInfo info = validaUser(rform);
    	if (info.getTipo() == WebInfo.TypeInfo.ERROR)
    	{
        	request.setAttribute("info", info);
        	return mapping.findForward("error");
    	}

       	UsuariosDTO dtoUser = new UsuariosDTO(rform.getUser(), rform.getNombre(), rform.getApellido1(), rform.getApellido2(), 
    			Encrypt.encriptar(rform.getContrasena()), null, rform.getMail());
       	
    	UtilsBO bo = (UtilsBO) SpringUtil.getInstance().getBean("UtilsBO");    	
    	bo.registerUser(dtoUser);

    	rform.limpia();

    	//info.append("Próximamente recibirás un mail confirmando la activación de tu cuenta.\n Gracias por registrarte.");
    	info.append("¡¡¡Gracias por registrarte!!!");
    	request.setAttribute("info", info);

    	return mapping.findForward("success");
    		
    }
    
    private WebInfo validaUser(RegisterForm form)
    {
    	WebInfo info = new WebInfo();
    	
    	info.setTipo(WebInfo.TypeInfo.MESSAGE);
    	if ( form.getUser() == null || form.getUser().trim().length() == 0)
    	{
    		info.setTipo(WebInfo.TypeInfo.ERROR);
    		info.append("El código de usuario es obligatorio.");
    	}
    	
    	if ( form.getNombre() == null || form.getNombre().trim().length() == 0)
    	{
    		info.setTipo(WebInfo.TypeInfo.ERROR);
    		info.append("El nombre es obligatorio.");
    	}
    	
    	if ( form.getContrasena() == null || form.getContrasena().trim().length() == 0 )
    	{
    		info.setTipo(WebInfo.TypeInfo.ERROR);
    		info.append("La contraseña es obligatoria.");    		
    	}
    	
    	if ( !form.getContrasena().equals(form.getContrasena2()) )
    	{
    		info.setTipo(WebInfo.TypeInfo.ERROR);
    		info.append("Las contraseñas no coinciden.");    		
    	}
    	
    	if ( form.getMail() == null || form.getMail().trim().length() == 0 )
    	{
    		info.setTipo(WebInfo.TypeInfo.ERROR);
    		info.append("El campo mail es obligatorio.");    		
    	}
    	return info;
    }
}
