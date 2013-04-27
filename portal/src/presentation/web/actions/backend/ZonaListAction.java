package presentation.web.actions.backend;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import common.business.bo.ZonasBO;
import common.dto.ZonasDTO;
import common.presentation.beans.HtmlEvents;
import common.presentation.util.grid.handler.Handler;
import common.presentation.util.grid.parser.ParseGrid;
import common.presentation.util.xml.ParserXML;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class ZonaListAction extends SecurityAction {
	
	private static final String gridXML = "/gridBackEndZonas.xml";
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	ZonasBO bo = (ZonasBO) SpringUtil.getInstance().getBean("ZonasBO");
    	HtmlEvents[] zonasDTO = bo.getZonas();
    	
    	for (HtmlEvents zona : zonasDTO)
    	{
    		ZonasDTO row = (ZonasDTO) zona;
    		row.setOnClick("javascript:ajaxPopup(\"/portal/backend/editzona.do\", null, " + "\"zonPk=" + row.getZonPk() + "\");");
    	}
    	
    	List<HtmlEvents> zonas = Arrays.asList(zonasDTO);
    	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/xml");
    	
    	ParseGrid xml = new ParseGrid();
    	ParserXML.getInstance().Parse(path + gridXML, xml);
    	xml.getGrid().setData(zonas);
    	
    	Handler hg = new Handler(xml.getGrid()); 
    	
    	request.setAttribute(xml.getGrid().getName(), hg);
    	
		return mapping.findForward("success");
	}

}
