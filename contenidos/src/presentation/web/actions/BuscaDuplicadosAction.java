package presentation.web.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.beans.RowFile;

import common.business.bo.FicherosBO;
import common.dto.FicherosDTO;
import common.presentation.beans.HtmlEvents;
import common.presentation.security.beans.UserInfo;
import common.presentation.util.grid.handler.Handler;
import common.presentation.util.grid.parser.ParseGrid;
import common.presentation.util.xml.ParserXML;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class BuscaDuplicadosAction extends SecurityAction {
	private static final String gridFileXML = "/gridAssetsSearch.xml";

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");

    	request.removeAttribute("listAssetsEncontrados");

    	FicherosBO ficherosBO = (FicherosBO) SpringUtil.getInstance().getBean("FicherosBO");
    	
    	FicherosDTO[] ficherosDTO = ficherosBO.getDuplicados(user.getPk());
    	
    	List<HtmlEvents> rowFile = new ArrayList<HtmlEvents>();
    	for (FicherosDTO fichero: ficherosDTO)
    	{
    		RowFile row = new RowFile(fichero);
    		if (fichero.Preview())
    		{
        		row.setOnClick("javascript:ajaxPopup(\"preview.do?assPk=" + fichero.getAssPk() + "\", null);");
    		}
    		
			row.setOnMouseOver("javascript:lastToolTip = " + fichero.getAssPk() + ";ajax_tooltip(this, \"fileinfo.do?assPk=" + fichero.getAssPk() + "\", \"infopopup\");");
			if (user.getPk().compareTo(fichero.getUsuFk()) == 0)
			{
	    		row.setOnEdit("javascript:ajaxPopup(\"fileproperties.do?assPk=" + fichero.getAssPk() + "\", null);");
				row.setOnDelete("javascript:deleteAsset(2, " + fichero.getAssPk() + ", \"Vas a borrar el fichero.\\n¿Desea continuar?\");");
				row.setOnOperation("javascript:addOperation(" + fichero.getAssPk() + ");");					
				row.setOnReference("javascript:goUrl(\"home.do?carPk=" + fichero.getCarFk() + "\");");
			}
			else
				row.setOnReference("javascript:goUrl(\"compartidos.do?carPk=" + fichero.getCarFk() + "\");");
    		rowFile.add(row);
    	}
    	
		if ( rowFile.size() > 0 )
		{
	    	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/xml");	    	
	    	ParseGrid xmlFicheros = new ParseGrid();
	    	ParserXML.getInstance().Parse(path + gridFileXML, xmlFicheros);
	    	xmlFicheros.getGrid().setData(rowFile);
	    	
	    	Handler hgFicheros = new Handler(xmlFicheros.getGrid());
	    	request.setAttribute("gridFicheros", hgFicheros);			
		}
    	
		return mapping.findForward("success");
	}
}
