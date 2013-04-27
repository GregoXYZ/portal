package presentation.web.actions.backend;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import common.business.bo.UsuariosBO;
import common.dto.UsuariosDTO;
import common.presentation.beans.HtmlEvents;
import common.presentation.util.grid.handler.Handler;
import common.presentation.util.grid.parser.ParseGrid;
import common.presentation.util.xml.ParserXML;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class UserListAction extends SecurityAction {
	
	private static final String gridXML = "/gridBackEndUsers.xml";
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	UsuariosBO bo = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
    	HtmlEvents[] usersDTO = bo.getUsuarios();
    	
    	for (HtmlEvents usuario : usersDTO)
    	{
    		UsuariosDTO row = (UsuariosDTO) usuario;
    		row.setOnClick("javascript:ajaxPopup(\"/portal/backend/edituser.do\", null, " + "\"usuPk=" + row.getUsuPk() + "\");");
    	}
    	
    	List<HtmlEvents> users = Arrays.asList(usersDTO);
    	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/xml");
    	
    	ParseGrid xml = new ParseGrid();
    	ParserXML.getInstance().Parse(path + gridXML, xml);
    	xml.getGrid().setData(users);
    	
    	Handler hg = new Handler(xml.getGrid()); 
    	
    	request.setAttribute(xml.getGrid().getName(), hg);
    	
		return mapping.findForward("success");
	}

}
