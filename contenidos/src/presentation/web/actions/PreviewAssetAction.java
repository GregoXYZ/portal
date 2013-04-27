package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

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
import common.presentation.util.WebInfoHelper;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.presentation.web.security.forms.BaseForm;
import common.util.BeanAnalyze;
import common.util.spring.SpringUtil;

public class PreviewAssetAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

    	Long assPk = (Long) new BeanAnalyze(form).getProperty("assPk"); 
		AssetsBO assetsBO = (AssetsBO)SpringUtil.getInstance().getBean("AssetsBO");
		AssetsDTO assetsDTO = assetsBO.getByPrimaryKey(assPk);
		
		((BaseForm)form).limpia(); 
		
		FicherosDTO ficherosDTO = null;
		if (assetsDTO.getUsuFk().equals(user.getPk()))
		{	    	
			FicherosBO ficherosBO = (FicherosBO)SpringUtil.getInstance().getBean("FicherosBO");
			ficherosDTO = ficherosBO.getByAsset(assetsDTO.getAssPk());
			
			MimeFilesBO mimeBO = (MimeFilesBO)SpringUtil.getInstance().getBean("MimeFilesBO");
			MimeFilesDTO mime = mimeBO.getByPrimaryKey(ficherosDTO.getMimFilFk());
        	ParametrosBO parametrosBO = (ParametrosBO) SpringUtil.getInstance().getBean("ParametrosBO");
    		if ( parametrosBO.getValor("TEXT_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) )
    		{
    			request.setAttribute("assetpreviewtype", "text");
    			request.setAttribute("assetpreview", ficherosDTO.getAssPk());
    			request.setAttribute("assetformat", mime.getMimFilExtension().toLowerCase());
    		}
    		else if ( parametrosBO.getValor("IMAGE_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) )
    		{
    			request.setAttribute("assetpreviewtype", "image");
    			request.setAttribute("assetpreview", ficherosDTO.getAssPk());
    			request.setAttribute("assetformat", mime.getMimFilExtension().toLowerCase());
    		}
    		else if ( parametrosBO.getValor("VIDEO_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) )
    		{
    			request.setAttribute("assetpreviewtype", "video");
    			request.setAttribute("assetpreview", ficherosDTO.getAssPk());
    			request.setAttribute("assetformat", mime.getMimFilExtension().toLowerCase());
    		}
		}
		else
		{
			CompartidosBO compartidosBO = (CompartidosBO)SpringUtil.getInstance().getBean("CompartidosBO");
			CompartidosDTO compartidosDTO = compartidosBO.getByUniqueKey(assetsDTO.getAssPk(), user.getPk());
			
			if (compartidosDTO !=null)
			{
				FicherosBO ficherosBO = (FicherosBO)SpringUtil.getInstance().getBean("FicherosBO");
				ficherosDTO = ficherosBO.getByAsset(assetsDTO.getAssPk());		

				MimeFilesBO mimeBO = (MimeFilesBO)SpringUtil.getInstance().getBean("MimeFilesBO");
				MimeFilesDTO mime = mimeBO.getByPrimaryKey(ficherosDTO.getMimFilFk());
	        	ParametrosBO parametrosBO = (ParametrosBO) SpringUtil.getInstance().getBean("ParametrosBO");
	    		if ( parametrosBO.getValor("TEXT_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) )
	    		{
	    			request.setAttribute("assetpreviewtype", "text");
	    			request.setAttribute("assetpreview", ficherosDTO.getAssPk());
	    			request.setAttribute("assetformat", mime.getMimFilExtension().toLowerCase());
	    		}
	    		else if ( parametrosBO.getValor("IMAGE_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) )
	    		{
	    			request.setAttribute("assetpreviewtype", "image");
	    			request.setAttribute("assetpreview", ficherosDTO.getAssPk());
	    			request.setAttribute("assetformat", mime.getMimFilExtension().toLowerCase());
	    		}
	    		else if ( parametrosBO.getValor("VIDEO_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) )
	    		{
	    			request.setAttribute("assetpreviewtype", "video");
	    			request.setAttribute("assetpreview", ficherosDTO.getAssPk());
	    			request.setAttribute("assetformat", mime.getMimFilExtension().toLowerCase());
	    		}
			}
			else
			{
	    		WebInfoHelper.getInstance().setWebError(request, "El fichero no existe no existe.");
		    	return mapping.findForward("error");				
			}		
		}

		return mapping.findForward("success");
	}	

}
