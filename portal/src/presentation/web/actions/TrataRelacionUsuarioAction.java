package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.RelacionForm;

import common.Constants;
import common.business.bo.RelacionesUsuariosBO;
import common.dto.RelacionesUsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class TrataRelacionUsuarioAction extends SecurityAction {
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

    	RelacionForm lform = (RelacionForm) form;
    	RelacionesUsuariosBO relacionBO = (RelacionesUsuariosBO) SpringUtil.getInstance().getBean("RelacionesUsuariosBO");
    	RelacionesUsuariosDTO dto = relacionBO.getRelacionUsuarios(user.getPk(), lform.getUsuPk());
    	if (dto == null && lform.getEvento() == Constants.SOLICITAR_RELACION)
    	{
    		dto = new RelacionesUsuariosDTO();
    		dto.setUsu1Fk(user.getPk());
    		dto.setUsu2Fk(lform.getUsuPk());
    		dto.setEstRelUsuFk(Constants.RELACION_SOLICITADA);
        	relacionBO.add(dto);
    	}
    	else if (dto.getEstRelUsuFk().equals(Constants.RELACION_SOLICITADA))
    	{
    		if (lform.getEvento() == Constants.RECHAZAR_SOLICITUD && user.getPk().equals(dto.getUsu2Fk()))
        		relacionBO.delete(dto);
    		else if (lform.getEvento() == Constants.ACEPTAR_SOLICITUD  && user.getPk().equals(dto.getUsu2Fk()))
    		{
        		dto.setEstRelUsuFk(Constants.RELACION_ACEPTADA);
            	relacionBO.update(dto);
    		}
    		else if (lform.getEvento() == Constants.CANCELAR_SOLICITUD  && user.getPk().equals(dto.getUsu1Fk()))
        		relacionBO.delete(dto);
    	}
    	else if (lform.getEvento() == Constants.CANCELAR_RELACION && 
    			(user.getPk().equals(dto.getUsu1Fk()) || user.getPk().equals(dto.getUsu2Fk())))
    	{
    		relacionBO.delete(dto);
    	}
    	
		return mapping.findForward("success");    	
	}

}
