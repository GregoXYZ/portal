package presentation.web.actions;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.util.Parametros;

import common.IConstantes;
import common.business.bo.AssetsBO;
import common.business.bo.CompartidosBO;
import common.business.bo.FicherosBO;
import common.business.bo.MimeFilesBO;
import common.business.bo.UsuariosBO;
import common.dto.AssetsDTO;
import common.dto.CompartidosDTO;
import common.dto.FicherosDTO;
import common.dto.MimeFilesDTO;
import common.dto.UsuariosDTO;
import common.presentation.SystemException;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.presentation.web.security.forms.BaseForm;
import common.util.BeanAnalyze;
import common.util.FileUtils;
import common.util.spring.SpringUtil;

public class OpenFileAction extends SecurityAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(OpenFileAction.class);
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

    	Long assPk = (Long) new BeanAnalyze(form).getProperty("assPk"); 
		AssetsBO assetsBO = (AssetsBO)SpringUtil.getInstance().getBean("AssetsBO");
		AssetsDTO assetsDTO = assetsBO.getByPrimaryKey(assPk);
		
		if (assetsDTO == null)
		{
	    	throw new SystemException("El fichero no existe.", request);
		}
		
		((BaseForm)form).limpia(); 
		
		FicherosDTO ficherosDTO = null;
		String path = null;
		if (assetsDTO.getUsuFk().equals(user.getPk()))
		{	    	
			FicherosBO ficherosBO = (FicherosBO)SpringUtil.getInstance().getBean("FicherosBO");
			ficherosDTO = ficherosBO.getByAsset(assetsDTO.getAssPk());

			path = Parametros.get_instance().getValor(IConstantes.DEFAUL_USER_DIR) + user.getUser() + "/";
		}
		else
		{
			CompartidosBO compartidosBO = (CompartidosBO)SpringUtil.getInstance().getBean("CompartidosBO");
			CompartidosDTO compartidosDTO = compartidosBO.getByUniqueKey(assetsDTO.getAssPk(), user.getPk());
			
			if (compartidosDTO !=null)
			{
				FicherosBO ficherosBO = (FicherosBO)SpringUtil.getInstance().getBean("FicherosBO");
				ficherosDTO = ficherosBO.getByAsset(assetsDTO.getAssPk());		

				UsuariosBO usuariosBO = (UsuariosBO)SpringUtil.getInstance().getBean("UsuariosBO");
				UsuariosDTO usuariosDTO = usuariosBO.getByPrimaryKey(assetsDTO.getUsuFk());

				path = Parametros.get_instance().getValor(IConstantes.DEFAUL_USER_DIR) + usuariosDTO.getUsuUkUsuario() + "/";
			}
			else
			{
		    	throw new SystemException("El fichero no existe.", request);
			}		
		}

		MimeFilesBO mimefileBO = (MimeFilesBO)SpringUtil.getInstance().getBean("MimeFilesBO");
		MimeFilesDTO mimefileDTO = mimefileBO.getByPrimaryKey(ficherosDTO.getMimFilFk());
		
		if (new File(path + ficherosDTO.getFicSysName()).exists())
		{
	        response.addHeader("Content-Disposition","attachment;filename=" + assetsDTO.getAssNombre());
	        response.setContentType(mimefileDTO.getMimFilMime());
	        FileUtils.readFromDisk(path, ficherosDTO.getFicSysName(), response);

	        logger.info("El usuario " + user.getUser() + " ha accedico al fichero " + path + ficherosDTO.getFicSysName());
			return null;
		}
		else
		{
	    	throw new SystemException("El fichero " + path + ficherosDTO.getFicSysName() + " no existe.", request);
		}
	}

}
