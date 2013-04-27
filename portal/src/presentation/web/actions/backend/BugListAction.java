package presentation.web.actions.backend;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import common.business.bo.BugsBO;
import common.dto.BugsDTO;
import common.presentation.beans.HtmlEvents;
import common.presentation.util.grid.handler.Handler;
import common.presentation.util.grid.parser.ParseGrid;
import common.presentation.util.xml.ParserXML;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class BugListAction extends SecurityAction {
	
	private static final String gridXML = "/gridBackEndBugs.xml";
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	BugsBO bo = (BugsBO) SpringUtil.getInstance().getBean("BugsBO");
    	HtmlEvents[] bugsDTO = bo.getBugsExtended();
    	
    	for (HtmlEvents bug: bugsDTO)
    	{
    		BugsDTO dto = (BugsDTO) bug;
    		if (dto.getBugMessage().length() > 300)
    		{
    			dto.setBugMessage(dto.getBugMessage().substring(0, 297) + "...");
    		}
    		if (dto.getBugDescripcion().length() > 300)
    		{
    			dto.setBugDescripcion(dto.getBugDescripcion().substring(0, 297) + "...");
    		}

    		if (dto.getBugPriFk().equals(1L))
    		{
    			dto.setBugIcon("/portal/images/bugger/bug-critical.png");
    			dto.setStyle("bug-critical");
    		}
    		else if (dto.getBugPriFk().equals(2L))
    		{
    			dto.setBugIcon("/portal/images/bugger/bug-high.png");
    			dto.setStyle("bug-high");
    		}
    		else if (dto.getBugPriFk().equals(3L))
    		{
    			dto.setBugIcon("/portal/images/bugger/bug-medium.png");    			
    			dto.setStyle("bug-medium");
    		}
    		else if (dto.getBugPriFk().equals(4L))
    		{
    			dto.setBugIcon("/portal/images/bugger/bug-low.png");
    			dto.setStyle("bug-low");
    		}
    		else if (dto.getBugPriFk().equals(5L))
    		{
    			dto.setBugIcon("/portal/images/bugger/bug-undecided.png");
    			dto.setStyle("bug-undecided");
    		}
    		else if (dto.getBugPriFk().equals(6L))
    		{
    			dto.setBugIcon("/portal/images/bugger/bug-wishlist.png");
    			dto.setStyle("bug-wishlist");
    		}
    	}
    	
    	List<HtmlEvents> bugs = Arrays.asList(bugsDTO);
    	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/xml");
    	
    	for (HtmlEvents bug : bugsDTO)
    	{
    		BugsDTO row = (BugsDTO) bug;
    		row.setOnClick("javascript:ajaxPopup(\"/portal/backend/editbug.do\", null, " + "\"bugPk=" + row.getBugPk() + "\");");
    	}
    	
    	ParseGrid xml = new ParseGrid();
    	ParserXML.getInstance().Parse(path + gridXML, xml);
    	xml.getGrid().setData(bugs);
    	
    	Handler hg = new Handler(xml.getGrid()); 
    	
    	request.setAttribute(xml.getGrid().getName(), hg);
    	
		return mapping.findForward("success");
	}

}
