package presentation.web.actions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.beans.MyRowShared;
import presentation.beans.RowFile;
import presentation.web.forms.CompartidosForm;
import presentation.web.forms.ContenidosForm;

import common.business.BusinessException;
import common.business.bo.ExtraQueriesBO;
import common.business.bo.FicherosBO;
import common.dto.CarpetasDTO;
import common.dto.FicherosDTO;
import common.presentation.beans.HtmlEvents;
import common.presentation.security.beans.UserInfo;
import common.presentation.util.grid.handler.Handler;
import common.presentation.util.grid.parser.ParseGrid;
import common.presentation.util.xml.ParserXML;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class OpenFilesAction extends SecurityAction {
	private static final String gridFileXML = "/gridFiles.xml";
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");
    	
    	if (form instanceof ContenidosForm)
    	{
        	ContenidosForm lform = (ContenidosForm) form;
        	openFiles(user.getPk(), lform.getCarPk(), request);
    	}
    	else if (form instanceof CompartidosForm)
    	{
        	CompartidosForm lform = (CompartidosForm) form;
    		lform.setCarFk(foldersShared(user.getPk(), lform.getUsuPk(), lform.getCarFk(), request));
        	filesShared(user.getPk(), lform.getUsuPk(), lform.getCarFk(), request);   		
    	}
		
    	
    	return mapping.findForward("success");
	}
	
	private void openFiles(long user, long carpeta, HttpServletRequest request) throws BusinessException, FileNotFoundException, IOException
	{
    	FicherosBO ficherosBO = (FicherosBO) SpringUtil.getInstance().getBean("FicherosBO");
    	FicherosDTO[] ficherosDTO = ficherosBO.getFicheros(user, carpeta);
    	
    	List<HtmlEvents> rowFile = new ArrayList<HtmlEvents>();
    	for (int ind = 0; ind < ficherosDTO.length; ind++)
    	{
    		RowFile row = new RowFile(ficherosDTO[ind]);
    		if (ficherosDTO[ind].Preview())
    		{
        		row.setOnClick("javascript:ajaxPopup(\"preview.do\", null, " + "\"assPk=" + ficherosDTO[ind].getAssPk() + "\");");
    		}
    		row.setOnEdit("javascript:ajaxPopup(\"fileproperties.do\", null, " + "\"assPk=" + ficherosDTO[ind].getAssPk() + "\");");
			row.setOnReference("javascript:ajaxPopup(\"adminlinks.do\", null, " + "\"assPk=" + ficherosDTO[ind].getAssPk() + "\");");
			row.setOnMouseOver("javascript:lastToolTip = " + ficherosDTO[ind].getAssPk() + ";ajax_tooltip(this, \"fileinfo.do\", \"infopopup\", null, \"assPk=" + ficherosDTO[ind].getAssPk() + "\");");
			row.setOnDelete("javascript:deleteAsset(2, " + ficherosDTO[ind].getAssPk() + ", \"Vas a borrar el fichero.\\n¿Desea continuar?\");");
			row.setOnOperation("javascript:addOperation(" + ficherosDTO[ind].getAssPk() + ");");
    		rowFile.add(row);
    	}
    	
		//if ( rowFile.size() > 0 ) {
	    	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/xml");	    	
	    	ParseGrid xmlFicheros = new ParseGrid();
	    	ParserXML.getInstance().Parse(path + gridFileXML, xmlFicheros);
	    	xmlFicheros.getGrid().setData(rowFile);
	    	
	    	Handler hgFicheros = new Handler(xmlFicheros.getGrid());
	    	request.setAttribute("gridFicheros", hgFicheros);			
		//}		
	}

	private static final String gridSharedFileXML = "/gridFilesCompartidos.xml";	
	private static final String gridMySharedFileXML = "/gridMyFilesCompartidos.xml";
	private ExtraQueriesBO queriesBO = (ExtraQueriesBO) SpringUtil.getInstance().getBean("ExtraQueriesBO");

	private long foldersShared(long user, long propietario, long carpeta, HttpServletRequest request) throws BusinessException
	{
		long carpetaOk = 0;
		CarpetasDTO[] carpetas;
		request.removeAttribute("listCarpetas");
		if (user == propietario)
		{
			carpetas = queriesBO.getMyCarpetasCompartidas(user);			
		}
		else
		{
			carpetas = queriesBO.getCarpetasConCompartidos(user, propietario);			
		}

		if (carpetas.length > 0)
		{
			for (int ind=0; ind<carpetas.length; ind++)
			{
				if (carpetas[ind].getAssDescripcion() == null || carpetas[ind].getAssDescripcion().trim().length() == 0)
					carpetas[ind].setAssDescripcion(carpetas[ind].getAssNombre());
				if (carpetas[ind].getCarPk().equals(carpeta)) carpetaOk = carpeta;
			}
	    	request.setAttribute("listCarpetas", carpetas);			
		}
    	return carpetaOk;
	}

	private void filesShared(long user, Long propietario, long carpeta, HttpServletRequest request) throws BusinessException, FileNotFoundException, IOException
	{
		request.removeAttribute("gridFicheros");
    	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/xml");
    	String xmlDef = null;
    	
    	FicherosDTO[] ficherosDTO = null;
    	if ( user > 0 && !propietario.equals(user))
    	{
    		ficherosDTO = queriesBO.getFicherosCompartidos(user, propietario, carpeta);
    		xmlDef = gridSharedFileXML;
    	}
    	else if(user > 0 && propietario.equals(user))
    	{
        	ficherosDTO = queriesBO.getMyFicherosCompartidos(user, carpeta);
    		xmlDef = gridMySharedFileXML;
    	}
    	
    	if (ficherosDTO != null)
    	{
        	List<HtmlEvents> rowFile = new ArrayList<HtmlEvents>();
        	for (int ind = 0; ind < ficherosDTO.length; ind++)
        	{
        		MyRowShared row = new MyRowShared(ficherosDTO[ind]);
        		
        		if (ficherosDTO[ind].Preview())
        		{
            		row.setOnClick("javascript:ajaxPopup(\"preview.do\", null, " + "\"assPk=" + ficherosDTO[ind].getAssPk() + "\");");    			
        		}
    			row.setOnReference("javascript:ajaxPopup(\"adminlinks.do\", null, " + "\"assPk=" + ficherosDTO[ind].getAssPk() + "\");");
        		row.setOnUpdate("javascript:ajaxPopup(\"sharedfileproperties.do\", null, " + "\"assPk=" + ficherosDTO[ind].getAssPk() + "\");");
        		row.setOnMouseOver("javascript:lastToolTip = " + ficherosDTO[ind].getAssPk() + ";ajax_tooltip(this, \"fileinfo.do\", \"infopopup\", null, \"assPk=" + ficherosDTO[ind].getAssPk() + "\");");
    			row.setOnDelete("javascript:deleteShared(" + ficherosDTO[ind].getAssPk() + ", " + (propietario.equals(user)?0:ficherosDTO[ind].getUsuFk()) + ", \"Vas a borrar el fichero.\\n¿Deseas continuar?\");");

        		rowFile.add(row);
        	}
        	
        	//if ( rowFile.size() > 0 )	{
    	    	ParseGrid xmlAssets = new ParseGrid();
    	    	ParserXML.getInstance().Parse(path + xmlDef, xmlAssets);
    	    	xmlAssets.getGrid().setData(rowFile);
    	    	
    	    	Handler hgAssets = new Handler(xmlAssets.getGrid()); 
    	    	request.setAttribute("gridFicheros", hgAssets);			
    		//}    		        		
    	}
	}
}
