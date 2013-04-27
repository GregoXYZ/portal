package presentation.web.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.AssetInfoForm;

import common.business.bo.AssetsBO;
import common.business.bo.CompartidosBO;
import common.business.bo.FicherosBO;
import common.business.bo.MimeFilesBO;
import common.business.bo.ParametrosBO;
import common.dto.AssetsDTO;
import common.dto.CompartidosDTO;
import common.dto.FicherosDTO;
import common.dto.MimeFilesDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class AssetInfoAction extends SecurityAction {
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

    	AssetInfoForm lform = (AssetInfoForm) form; 
    	Long id = lform.getAssPk();
		lform.limpia();

		AssetsBO assetsBO = (AssetsBO)SpringUtil.getInstance().getBean("AssetsBO");
		AssetsDTO assetsDTO = assetsBO.getByPrimaryKey(id);
    	
		if (assetsDTO == null )
	    	return mapping.findForward("error");

		assetsDTO = assetsBO.getByPrimaryKey(id, user.getPk());		
		if (assetsDTO == null )
		{
			CompartidosBO compartidoBO = (CompartidosBO)SpringUtil.getInstance().getBean("CompartidosBO");
			CompartidosDTO compartido = compartidoBO.getByUniqueKey(id, user.getPk());
			if (compartido == null )
		    	return mapping.findForward("error");
			lform.setFechaCreacion(new Date(compartido.getAssFechaAlta()).toString());
		}
		else
		{
			lform.setFechaCreacion(new Date(assetsDTO.getAssFechaAlta()).toString());
		}

		FicherosBO ficheroBO = (FicherosBO)SpringUtil.getInstance().getBean("FicherosBO");
		FicherosDTO fichero = ficheroBO.getByAsset(id);

		MimeFilesBO mimeBO = (MimeFilesBO)SpringUtil.getInstance().getBean("MimeFilesBO");
		MimeFilesDTO mime = mimeBO.getByPrimaryKey(fichero.getMimFilFk());
		
		lform.setAssPk(id);
		lform.setMimeType(mime.getMimFilExtension());
		lform.setFicSize(fichero.getFicSize());
		fichero.setMimFilExtension(mime.getMimFilExtension());
		lform.setPreview(fichero.Preview());
    	ParametrosBO parametrosBO = (ParametrosBO) SpringUtil.getInstance().getBean("ParametrosBO");
		lform.setMakeMiniature(parametrosBO.getValor("IMAGE_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) ||
				parametrosBO.getValor("VIDEO_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) ||
				parametrosBO.getValor("SOUND_FORMATS").contains(mime.getMimFilExtension().toLowerCase()));
    	
		if ( parametrosBO.getValor("TEXT_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) )
		{
			request.setAttribute("assetpreviewtype", "text");
			request.setAttribute("assetpreview", fichero.getAssPk());
			request.setAttribute("assetformat", mime.getMimFilExtension().toLowerCase());
		}
		else if ( parametrosBO.getValor("IMAGE_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) )
		{
			request.setAttribute("assetpreviewtype", "image");
			request.setAttribute("assetpreview", fichero.getAssPk());
			request.setAttribute("assetformat", mime.getMimFilExtension().toLowerCase());
		}
		else if ( parametrosBO.getValor("VIDEO_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) )
		{
			request.setAttribute("assetpreviewtype", "video");
			request.setAttribute("assetpreview", fichero.getAssPk());
			request.setAttribute("assetformat", mime.getMimFilExtension().toLowerCase());
		}
		else if ( parametrosBO.getValor("SOUND_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) )
		{
			request.setAttribute("assetpreviewtype", "sound");
			request.setAttribute("assetpreview", fichero.getAssPk());
			request.setAttribute("assetformat", mime.getMimFilExtension().toLowerCase());
		}

		return mapping.findForward("success");			
	}
}
