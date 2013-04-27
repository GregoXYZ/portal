package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.SelectionFilesForm;

import common.business.BusinessException;
import common.business.bo.AssetsBO;
import common.business.bo.ExtraQueriesBO;
import common.dto.AssetsDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class ShareFilesAction extends SecurityAction {
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");
		
    	String[] files = request.getParameterValues("files");
    	if (files[0].length()==0) return null;

    	SelectionFilesForm lform = (SelectionFilesForm) form;
    	
    	String[] users = request.getParameterValues("users");
    	if (users == null || users[0].length()==0) users = new String[0];;

    	Long[] lUsers = new Long[users.length];
    	Long[] assets = new Long[files.length];
    	
    	for (int ind=0; ind<users.length; ind++)
    		lUsers[ind] = Long.valueOf(users[ind]);
    	
    	for (int ind=0; ind<files.length; ind++)
    		assets[ind] = Long.valueOf(files[ind]);
    	
    	AssetsBO assetBO = (AssetsBO) SpringUtil.getInstance().getBean("AssetsBO");
		AssetsDTO[] assetsDTO = new AssetsDTO[assets.length];
    	for (int ind=0; ind < assets.length; ind ++)
    	{
    		assetsDTO[ind] = assetBO.getByPrimaryKey(assets[ind], user.getPk());
    		if (assetsDTO[ind] == null) {
    			throw new BusinessException("No puedes realizar la operaciónn ya que hay ficheros que no te pertenecen.");    			
    		}
    	}
		
    	ExtraQueriesBO queriesBO = (ExtraQueriesBO) SpringUtil.getInstance().getBean("ExtraQueriesBO");
    	queriesBO.comparteFicheros(assetsDTO, lUsers, lform.isReemplaza(), lform.getTags());
    			
    	lform.limpia();

		return null;
	}

}
