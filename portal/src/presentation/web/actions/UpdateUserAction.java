package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionRedirect;

import presentation.web.forms.UserForm;

import common.Constants;
import common.business.bo.UsuariosBO;
import common.business.bo.UsuariosGruposBO;
import common.dto.UsuariosDTO;
import common.presentation.WebException;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.FileUtils;
import common.util.spring.SpringUtil;

public class UpdateUserAction extends SecurityAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(UpdateUserAction.class);
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	UserForm rform = (UserForm)form;
    	
    	validaUser(rform, request);

    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");
    	
    	if ( rform.getAvatar() != null && rform.getAvatar().getFileSize() > Constants.MAX_SIZE_AVATAR )
    	{
    		// Error por tamaño del avatar demasiado grande
    		logger.error("El usuario " + user.getUser() + 
    				" ha intentado subir un avatar que superaba el máximo de tamaño permitido " + 
    				rform.getAvatar().getFileSize() + "/" + Constants.MAX_SIZE_AVATAR );
    		throw new WebException("Se ha intentado subir un avatar que superaba el máximo de tamaño permitido " + 
    				rform.getAvatar().getFileSize() + "/" + Constants.MAX_SIZE_AVATAR, request);
    	}
    	
    	if ( user != null )
    	{
        	UsuariosBO bo = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
        	UsuariosDTO dto = bo.getByPrimaryKey(user.getPk());
        	
        	dto.setUsuNombre(rform.getNombre());
        	dto.setUsuApellido1(rform.getApellido1());
        	dto.setUsuApellido2(rform.getApellido2());
        	dto.setUsuMail(rform.getMail());
        	dto.setUsuPublicable(rform.isPublicable());
        	dto.setUsuRecibeAviso(rform.isRecibeAbisos());
        	if ( rform.getAvatar() != null && rform.getAvatar().getFileSize() > 0 )
        	{
        		dto.setUsuAvatar(rform.getAvatar().getFileData());
				String path = request.getSession().getServletContext().getRealPath(Constants.TEMP_USER_DIR + Constants.DEFAUL_AVATAR_DIR) + "/";
    			FileUtils.carga(path + dto.getUsuUkUsuario(), dto.getUsuAvatar());        		
        	}
        	

        	UsuariosGruposBO boPerfil = (UsuariosGruposBO) SpringUtil.getInstance().getBean("UsuariosGruposBO");
        	dto.setPerfil(boPerfil.getByUser(user.getPk()));

       		bo.updateUserData(dto, request);
        	
        	// Modificao los datos de sesion
        	user.setNombre(rform.getNombre());
        	user.setApellido1(rform.getApellido1());
        	user.setApellido2(rform.getApellido2());
        	user.setMail(rform.getMail());
        	user.setPublicable(rform.isPublicable());
        	user.setRecibeAbisos(rform.isRecibeAbisos());
        	session.setAttribute("user", user);
    	}
    	rform.limpia();

	    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
	    ActionForward forward =  redirect;		
    	return forward;
    		
    }
    
    private void validaUser(UserForm form, HttpServletRequest request) throws WebException
    {
    	if ( form.getNombre() == null || form.getNombre().trim().length() == 0)
    	{
    		throw new WebException("El nombre es obligatorio.", request);
    	}
    	
    	if ( form.getMail() == null || form.getMail().trim().length() == 0 )
    	{
    		throw new WebException("El campo mail es obligatorio.", request);
    	}
    }
}
