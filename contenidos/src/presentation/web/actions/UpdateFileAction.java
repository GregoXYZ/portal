package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.UpdateFileForm;

import common.business.bo.AssetsBO;
import common.business.bo.ExtraQueriesBO;
import common.dto.AssetsDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class UpdateFileAction extends SecurityAction {
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

		UpdateFileForm lform = (UpdateFileForm) form;
		
    	AssetsBO assetBO = (AssetsBO) SpringUtil.getInstance().getBean("AssetsBO");
		AssetsDTO asset = assetBO.getByPrimaryKey(lform.getAssPk(), user.getPk());
		//asset.setAssPk(lform.getAssPk());
		//asset.setTipAssFk(IConstantes.TIPO_ASSET_FICHERO);
		asset.setAssDescripcion(lform.getDescripcion());
		asset.setAssNombre(lform.getFile());
		
    	ExtraQueriesBO queriesBO = (ExtraQueriesBO) SpringUtil.getInstance().getBean("ExtraQueriesBO");
    	queriesBO.updateFichero(asset, lform.getUsers(), lform.getTags());
    			
    	lform.limpia();

		//return new ActionRedirect(mapping.findForward("success"));
		return null;
	}

}
