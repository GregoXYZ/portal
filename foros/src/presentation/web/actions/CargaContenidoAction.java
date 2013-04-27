package presentation.web.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.MensajeForm;

import common.business.bo.QueriesForosBO;
import common.business.bo.UsuariosBO;
import common.dto.MensajesDTO;
import common.dto.UsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.GlobalFunctions;
import common.util.spring.SpringUtil;


public class CargaContenidoAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

		MensajeForm lform = (MensajeForm)form;

		if (lform.getConPk() == 0)
			lform.limpia();
		else
		{
			QueriesForosBO queriesBO = (QueriesForosBO) SpringUtil.getInstance().getBean("QueriesForosBO");
			MensajesDTO dto = queriesBO.getContenido(user.getPk(), lform.getConPk());
			
			if (dto == null)
				lform.limpia();
			else
			{
				lform.setContenido(dto.getContenido().getConData());
				lform.setFechaAlta(new Date(dto.getContenido().getConFechaAlta()));
				lform.setEntrada(dto.getEntrada().getEntSubject());
				
				UsuariosBO usuariosBO = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
				UsuariosDTO userDTO = usuariosBO.getByPrimaryKey(dto.getContenido().getUsuFk());
				lform.setFrom(userDTO.getUsuNombre() + " " + 
								(userDTO.getUsuApellido1()==null?"":userDTO.getUsuApellido1() + " ") + 
								(userDTO.getUsuApellido2()==null?"":userDTO.getUsuApellido2()));
				
				request.setAttribute("pathabatar", GlobalFunctions.pathAbatar(userDTO.getUsuPk(), request.getSession()));				
			}
		}
    	return mapping.findForward("success");			
	}
}
