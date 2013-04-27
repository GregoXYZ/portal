package presentation.web.actions.backend;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import common.business.bo.MenusBO;
import common.dto.MenusDTO;
import common.presentation.beans.HtmlEvents;
import common.presentation.util.grid.handler.Handler;
import common.presentation.util.grid.parser.ParseGrid;
import common.presentation.util.xml.ParserXML;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class MenuListAction extends SecurityAction {
	
	private static final String gridXML = "/gridBackEndMenus.xml";
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	MenusBO bo = (MenusBO) SpringUtil.getInstance().getBean("MenusBO");
    	HtmlEvents[] menusDTO = bo.getMenus();
    	
    	List<HtmlEvents> menus = Arrays.asList(menusDTO);
    	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/xml");
    	
    	for (HtmlEvents menu : menusDTO)
    	{
    		MenusDTO row = (MenusDTO) menu;
    		row.setOnClick("javascript:ajaxPopup(\"/portal/backend/editmenu.do\", null, " + "\"menPk=" + row.getMenPk() + "\");");
    	}
    	
    	ParseGrid xml = new ParseGrid();
    	ParserXML.getInstance().Parse(path + gridXML, xml);
    	xml.getGrid().setData(menus);
    	
    	Handler hg = new Handler(xml.getGrid()); 
    	
    	request.setAttribute(xml.getGrid().getName(), hg);
    	
		return mapping.findForward("success");
	}

}
