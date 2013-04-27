package presentation.web.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.AvisoForm;

import common.business.bo.AvisosBO;
import common.dto.AvisosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class LeeAvisoAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

		AvisoForm lform = (AvisoForm) form; 
    	
    	AvisosBO avisosBO = (AvisosBO) SpringUtil.getInstance().getBean("AvisosBO");
    	AvisosDTO aviso = avisosBO.getByPrimaryKey(lform.getAviPk(), user.getPk());
    	aviso.setAviFLeido(new Date().getTime());
    	//avisosBO.delete(user.getPk(), lform.getTipAviPk());
    	avisosBO.update(aviso);
    	
    	request.removeAttribute("listAvisosUsuarios");

    	AvisosDTO[] avisos;
    	avisos = avisosBO.getByUser(user.getPk());

    	request.setAttribute("listAvisosUsuarios", avisos);
    	

		return mapping.findForward("success");
	}
}
