package presentation.web.actions;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import common.business.bo.ExtraQueriesBO;
import common.dto.TagsNubeDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class CreaNubeAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
    	
		String sTipo = request.getParameter("tipo");
    	Integer tipo = Integer.valueOf(sTipo==null?"1":sTipo);
    	request.removeAttribute("listTags");
    	
    	TagsNubeDTO[] dto = null;
    	ExtraQueriesBO queriesBO = (ExtraQueriesBO) SpringUtil.getInstance().getBean("ExtraQueriesBO");
		dto = queriesBO.getTagsNube(user.getPk(), tipo);    		

		request.setAttribute("listTags", Arrays.asList(dto));			
    	
		return mapping.findForward("success");
	}
}
