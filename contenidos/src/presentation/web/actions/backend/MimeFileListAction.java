package presentation.web.actions.backend;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import common.business.bo.MimeFilesBO;
import common.presentation.beans.HtmlEvents;
import common.presentation.util.grid.handler.Handler;
import common.presentation.util.grid.parser.ParseGrid;
import common.presentation.util.xml.ParserXML;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class MimeFileListAction extends SecurityAction {
	
	private static final String gridXML = "/gridBackEndMimeFiles.xml";
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	MimeFilesBO bo = (MimeFilesBO) SpringUtil.getInstance().getBean("MimeFilesBO");
    	HtmlEvents[] mimefilesDTO = bo.getMimeFiles();
    	
    	List<HtmlEvents> urls = Arrays.asList(mimefilesDTO);
    	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/xml");
    	
    	ParseGrid xml = new ParseGrid();
    	ParserXML.getInstance().Parse(path + gridXML, xml);
    	xml.getGrid().setData(urls);
    	
    	Handler hg = new Handler(xml.getGrid()); 
    	
    	request.setAttribute(xml.getGrid().getName(), hg);
    	
		return mapping.findForward("success");
	}

}
