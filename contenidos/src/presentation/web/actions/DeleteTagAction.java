package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.TagForm;

import common.business.bo.TagsBO;
import common.dto.TagsDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class DeleteTagAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");
    			
		TagForm lform = (TagForm) form;
		
    	TagsBO tagBO = (TagsBO) SpringUtil.getInstance().getBean("TagsBO");
    	TagsDTO dto = new TagsDTO();
    	dto.setAssPk(lform.getAssPk());
    	dto.setTagPk(lform.getTagPk());
    	dto.setUsuPk(user.getPk());
    	tagBO.deleteTagAsset(dto);

    	return mapping.findForward("success");
	}

}
