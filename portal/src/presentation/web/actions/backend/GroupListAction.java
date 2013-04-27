package presentation.web.actions.backend;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import common.business.bo.GruposBO;
import common.dto.GruposDTO;
import common.presentation.beans.HtmlEvents;
import common.presentation.util.grid.handler.Handler;
import common.presentation.util.grid.parser.ParseGrid;
import common.presentation.util.xml.ParserXML;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class GroupListAction extends SecurityAction {
	
	private static final String gridXML = "/gridBackEndGroups.xml";
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	GruposBO bo = (GruposBO) SpringUtil.getInstance().getBean("GruposBO");
    	HtmlEvents[] groupsDTO = bo.getGrupos();
    	
    	List<HtmlEvents> groups = Arrays.asList(groupsDTO);
    	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/xml");
    	
    	for (HtmlEvents grupo : groupsDTO)
    	{
    		GruposDTO row = (GruposDTO) grupo;
    		row.setOnClick("javascript:ajaxPopup(\"/portal/backend/editgroup.do\", null, " + "\"gruPk=" + row.getGruPk() + "\");");
    	}
    	
    	ParseGrid xml = new ParseGrid();
    	ParserXML.getInstance().Parse(path + gridXML, xml);
    	xml.getGrid().setData(groups);
    	
    	Handler hg = new Handler(xml.getGrid()); 
    	
    	request.setAttribute(xml.getGrid().getName(), hg);
    	
		return mapping.findForward("success");
	}

}
