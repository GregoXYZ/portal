package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.FolderForm;

import common.IConstantes;
import common.business.bo.CarpetasBO;
import common.business.bo.CuotasBO;
import common.dto.AssetsDTO;
import common.dto.CuotasDTO;
import common.presentation.WebException;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class CreateFolderAction extends SecurityAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(CreateFolderAction.class);
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		FolderForm lform = (FolderForm) form; 
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");
    	
		CuotasBO cuotaBO = (CuotasBO) SpringUtil.getInstance().getBean("CuotasBO");
    	CuotasDTO cuota = cuotaBO.getCuotaRestante(user.getPk());
    	
		if ( cuota != null && cuota.getCuoCuotaDisk() > IConstantes.PESO_CARPETA && cuota.getCuoCuotaFile() > IConstantes.PESO_CARPETA )
		{
	    	Long carpeta = null;
	    	if (lform!=null && lform.getCarPk()!=null && lform.getCarPk() >0 ) carpeta = lform.getCarPk();
	  	
	    	AssetsDTO assDTO =  new AssetsDTO();
	    	assDTO.setAssNombre(lform.getNombre());
	    	assDTO.setAssDescripcion(lform.getDescripcion());
	    	assDTO.setUsuFk(user.getPk());
	    	assDTO.setTipAssFk(IConstantes.TIPO_ASSET_CARPETA);
	    	
			CarpetasBO carpetasBO = (CarpetasBO) SpringUtil.getInstance().getBean("CarpetasBO");
			carpetasBO.add(carpeta, assDTO);
		}    	
		else
		{
	    	StringBuffer info = new StringBuffer();
	    	
	    	Long wCuota = cuota==null?0:cuota.getCuoCuotaDisk();
	    	info.append("No tienes cuota (").append(wCuota).append(") de disco suficiente para crear carpetas(").append(IConstantes.PESO_CARPETA).append(").");
			logger.warn(user.getUser() + " ha intentado superar la cuota asignada " + wCuota + "/" + IConstantes.PESO_CARPETA);
			throw new WebException(info.toString(), request);
		}
		lform.limpia();
    	
	    /*
	    ActionRedirect redirect =  new ActionRedirect(mapping.findForward("success"));
	    ActionForward forward =  redirect;		
		return forward
		*/;
		return null;
	}

}
