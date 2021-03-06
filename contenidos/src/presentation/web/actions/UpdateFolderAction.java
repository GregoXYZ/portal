package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.FolderForm;

import common.business.bo.AssetsBO;
import common.dto.AssetsDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class UpdateFolderAction extends SecurityAction {
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

    	FolderForm lform = (FolderForm) form;
		
    	AssetsBO assetBO = (AssetsBO) SpringUtil.getInstance().getBean("AssetsBO");
		AssetsDTO asset = assetBO.getByPrimaryKey(lform.getAssPk(), user.getPk());
		asset.setAssDescripcion(lform.getDescripcion());
		asset.setAssNombre(lform.getNombre());
    	
		assetBO.update(asset);
		
    	lform.limpia();
		//response.sendRedirect("home.do");
		
		//return new ActionRedirect(mapping.findForward("success"));
    	return null;
	}

}
