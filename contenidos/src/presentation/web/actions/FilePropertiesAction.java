package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.UpdateFileForm;

import common.Constants;
import common.business.bo.AssetsBO;
import common.business.bo.CompartidosBO;
import common.business.bo.FicherosBO;
import common.business.bo.MimeFilesBO;
import common.business.bo.ParametrosBO;
import common.business.bo.UsuariosBO;
import common.dto.AssetsDTO;
import common.dto.CompartidosDTO;
import common.dto.FicherosDTO;
import common.dto.MimeFilesDTO;
import common.dto.UsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class FilePropertiesAction extends SecurityAction {
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

		UpdateFileForm lform = (UpdateFileForm) form; 
    	
    	FicherosBO ficheroBO = (FicherosBO) SpringUtil.getInstance().getBean("FicherosBO");
    	FicherosDTO fichero = ficheroBO.getByAsset(lform.getAssPk());

    	AssetsBO assetBO = (AssetsBO) SpringUtil.getInstance().getBean("AssetsBO");
    	AssetsDTO asset = assetBO.getByPrimaryKey(fichero.getAssPk(), user.getPk());

    	lform.setAssPk(fichero.getAssPk());
    	lform.setDescripcion(asset.getAssDescripcion());
    	lform.setFile(asset.getAssNombre());

    	CompartidosBO compartirBO = (CompartidosBO) SpringUtil.getInstance().getBean("CompartidosBO");
    	CompartidosDTO[] compartidos = compartirBO.getByAsset(fichero.getAssPk());
    	
    	Long[] usuComparten = new Long[compartidos.length];
    	for (int ind=0; ind < compartidos.length; ind++)
    	{
    		usuComparten[ind] = compartidos[ind].getUsuFk();
    	}
    	lform.setUsers(usuComparten);

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

       	MimeFilesBO mimeBO = (MimeFilesBO) SpringUtil.getInstance().getBean("MimeFilesBO");
       	MimeFilesDTO mime = mimeBO.getByPrimaryKey(fichero.getMimFilFk());
    	ParametrosBO parametrosBO = (ParametrosBO) SpringUtil.getInstance().getBean("ParametrosBO");
		if ( parametrosBO.getValor("IMAGE_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) )
		{
			request.setAttribute("assetpreviewtype", "image");
		}


		return mapping.findForward("success");    	
	}

}
