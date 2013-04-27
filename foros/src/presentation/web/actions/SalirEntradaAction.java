package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.MensajeForm;

import common.business.bo.DestinosBO;
import common.dto.DestinosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class SalirEntradaAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

    	MensajeForm lform = (MensajeForm)session.getAttribute("MensajeForm");

		if (lform!=null && lform.getEntPk() >0)
		{
			DestinosBO destinosBO = (DestinosBO) SpringUtil.getInstance().getBean("DestinosBO");
			DestinosDTO dto =  new DestinosDTO();
			dto.setEntFk(lform.getEntPk());
			dto.setUsuFk(user.getPk());
			destinosBO.delete(dto);
			session.removeAttribute("MensajeForm");
		}
		
    	return null;
	}

}
