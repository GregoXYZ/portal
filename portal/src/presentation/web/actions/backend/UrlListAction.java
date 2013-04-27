package presentation.web.actions.backend;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import common.business.bo.UrlsBO;
import common.dto.UrlsDTO;
import common.presentation.beans.HtmlEvents;
import common.presentation.util.grid.handler.Handler;
import common.presentation.util.grid.parser.ParseGrid;
import common.presentation.util.xml.ParserXML;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class UrlListAction extends SecurityAction {
	
	private static final String gridXML = "/gridBackEndUrls.xml";
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	UrlsBO bo = (UrlsBO) SpringUtil.getInstance().getBean("UrlsBO");
    	HtmlEvents[] urlsDTO = bo.getUrls();
    	
    	for (HtmlEvents url : urlsDTO)
    	{
    		UrlsDTO row = (UrlsDTO) url;
    		row.setOnClick("javascript:ajaxPopup(\"/portal/backend/editurl.do\", null, " + "\"urlPk=" + row.getUrlPk() + "\");");
    	}
    	
    	List<HtmlEvents> urls = Arrays.asList(urlsDTO);
    	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/xml");
    	
    	ParseGrid xml = new ParseGrid();
    	ParserXML.getInstance().Parse(path + gridXML, xml);
    	xml.getGrid().setData(urls);
    	
    	Handler hg = new Handler(xml.getGrid()); 
    	
    	request.setAttribute(xml.getGrid().getName(), hg);
    	
		return mapping.findForward("success");
	}

}
