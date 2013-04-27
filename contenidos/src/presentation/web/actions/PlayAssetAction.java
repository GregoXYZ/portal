package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import common.business.bo.AssetsBO;
import common.business.bo.FicherosBO;
import common.business.bo.MimeFilesBO;
import common.dto.AssetsDTO;
import common.dto.FicherosDTO;
import common.dto.MimeFilesDTO;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class PlayAssetAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.removeAttribute("asset");

		String asset = request.getParameter("asset");

		AssetsBO assetBO = (AssetsBO)SpringUtil.getInstance().getBean("AssetsBO");
		Long id = Long.valueOf(asset);
		AssetsDTO assetDTO = assetBO.getByPrimaryKey(id);

		FicherosBO ficherosBO = (FicherosBO)SpringUtil.getInstance().getBean("FicherosBO");
		FicherosDTO ficherosDTO = ficherosBO.getByAsset(assetDTO.getAssPk());

		MimeFilesBO mimeBO = (MimeFilesBO)SpringUtil.getInstance().getBean("MimeFilesBO");
		MimeFilesDTO mimeDTO = mimeBO.getByPrimaryKey(ficherosDTO.getMimFilFk());

		request.setAttribute("asset", asset);
		request.setAttribute("assetformat", mimeDTO.getMimFilExtension());

    	return mapping.findForward("success");
	}

}
