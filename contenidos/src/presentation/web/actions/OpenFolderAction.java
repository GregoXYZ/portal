package presentation.web.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.beans.RowFolder;
import presentation.web.forms.ContenidosForm;

import common.business.bo.AssetsBO;
import common.business.bo.CarpetasBO;
import common.dto.AssetsDTO;
import common.dto.CarpetasDTO;
import common.presentation.beans.HtmlEvents;
import common.presentation.security.beans.UserInfo;
import common.presentation.util.grid.handler.Handler;
import common.presentation.util.grid.parser.ParseGrid;
import common.presentation.util.xml.ParserXML;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class OpenFolderAction extends SecurityAction {
	
	private static final String gridFolderXML = "/gridFolders.xml";
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");
    	
    	ContenidosForm lform = (ContenidosForm) form;
    	
		CarpetasBO carpetasBO = (CarpetasBO) SpringUtil.getInstance().getBean("CarpetasBO");
		CarpetasDTO[] carpetasDTO = carpetasBO.getCarpetas(user.getPk(), lform.getCarPk());

		List<HtmlEvents> rowFloder = new ArrayList<HtmlEvents>();
    	for (int ind = 0; ind < carpetasDTO.length; ind++)
    	{
    		RowFolder row = new RowFolder(carpetasDTO[ind]);
    		row.setOnEdit("javascript:ajaxPopup(\"folderproperties.do?assPk=" + carpetasDTO[ind].getAssPk() + "\", null);");
    		row.setOnClick("javascript:cargaAjax(" + carpetasDTO[ind].getCarPk() + ");");
			row.setOnDelete("javascript:deleteAsset(1, " + carpetasDTO[ind].getAssPk() + ", \"Va a eliminar la carpeta.\\n¿Desea continuar?\");");
			row.setOnReference("javascript:ajaxPopup(\"adminlinks.do?assPk=" + carpetasDTO[ind].getAssPk() + "\", null);");
			row.setOnOperation("javascript:addOperation(" + carpetasDTO[ind].getAssPk() + ");");
			rowFloder.add(row);
    	}

		//if (rowFloder.size() > 0)	{
	    	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/xml");
	    	ParseGrid xmlCarpetas = new ParseGrid();
	    	ParserXML.getInstance().Parse(path + gridFolderXML, xmlCarpetas);
	    	xmlCarpetas.getGrid().setData(rowFloder);
	    	
	    	Handler hgCarpetas = new Handler(xmlCarpetas.getGrid()); 
	    	request.setAttribute("gridCarpetas", hgCarpetas);			
		//}
    	
    	CarpetasDTO carpetaDTO = carpetasBO.getByPrimaryKey(lform.getCarPk());
    	
    	if (carpetaDTO != null)
    	{
    		AssetsBO assetBO = (AssetsBO) SpringUtil.getInstance().getBean("AssetsBO");
    		AssetsDTO asset = assetBO.getByPrimaryKey(carpetaDTO.getAssPk(), user.getPk());
    		if (asset == null )
    			lform.limpia();
    		else
    		{
    			lform.setCarFk(carpetaDTO.getCarFk()==null?0:carpetaDTO.getCarFk());    			
    		}
    	}
    	
    	return mapping.findForward("success");
	}
}
