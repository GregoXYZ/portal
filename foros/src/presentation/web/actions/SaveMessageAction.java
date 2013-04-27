package presentation.web.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.MensajeForm;

import common.business.bo.ContenidosBO;
import common.business.bo.DestinosBO;
import common.dto.ContenidosDTO;
import common.dto.DestinosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class SaveMessageAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");
		MensajeForm lform = (MensajeForm)form;

		DestinosBO destinosBO = (DestinosBO) SpringUtil.getInstance().getBean("DestinosBO");
		DestinosDTO destino = destinosBO.getByPrimaryKey(user.getPk(), lform.getEntPk());
    	
		if (destino != null )
		{
			ContenidosBO contenidosBO = (ContenidosBO) SpringUtil.getInstance().getBean("ContenidosBO");
			ContenidosDTO dto = new ContenidosDTO(lform.getContenido(), new Date().getTime(), lform.getEntPk(), user.getPk(), lform.getConFk());			
			contenidosBO.add(dto); 
		}

    	return null;
	}

}
