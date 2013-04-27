package presentation.web.tld;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.IConstantes;
import common.business.BusinessException;
import common.business.bo.FicherosBO;
import common.business.bo.MimeFilesBO;
import common.dto.AssetsDTO;
import common.dto.FicherosDTO;
import common.dto.MimeFilesDTO;
import common.util.GlobalFunctions;
import common.util.spring.SpringUtil;

public class AssetsTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5982979691927501678L;
	private static Log logger = LogFactory.getLog(AssetsTag.class);
	private String elements;

	MimeFilesBO mimeFilesBO = (MimeFilesBO) SpringUtil.getInstance().getBean("MimeFilesBO");
	FicherosBO ficherosBO = (FicherosBO) SpringUtil.getInstance().getBean("FicherosBO");

	/**
	 * Over-ride del metodo doEndTag
	 */
	public int doEndTag() throws JspException {
		StringBuffer html = new StringBuffer();
		HttpSession session = this.pageContext.getSession();
		
		ServletRequest request = this.pageContext.getRequest();	
		AssetsDTO[] assets = (AssetsDTO[]) request.getAttribute(elements);

		try {
			for (AssetsDTO asset:assets)
			{
				html.append("<div id='b").append(asset.getAssPk()).append("' class='data'>");
				html.append("<table>");
				html.append("<tbody>");
				
				html.append("<tr>");
				html.append("<td class='avatar' rowspan='5'>");
				html.append("<img src='").append(GlobalFunctions.pathAbatar(asset.getUsuFk(), session)).append("'/>");
				html.append("</td>");
				html.append("</tr>");
				
				html.append("<tr class='rowfind'>");
				html.append("<td>");
				html.append("<span><img src='").append(getIcon(asset)).append("' style='margin-right: 5px;'/></span>");
				html.append("<span><a href='javascript:void(0);' >").append(asset.getAssNombre()).append("</a></span>");
				html.append("</td>");
				html.append("</tr>");
				html.append("<tr class='rowfind'>");
				html.append("<td>");
				html.append("<span>").append(asset.getAssDescripcion()).append("</span>");
				html.append("</td>");
				html.append("</tr>");
				
				html.append("<tr class='rowfind'>");
				html.append("<td>");
				html.append("Entradas:");
				html.append("</td>");
				html.append("</tr>");
				
				html.append("<tr class='rowfind'>");
				html.append("<td>");
				html.append("Links:");
				html.append("</td>");
				html.append("</tr>");
				
				html.append("</tbody>");
				html.append("</table>");
				html.append("</div>");
			}
			
			// Escribimos codigo html en la pagina
			this.pageContext.getOut().print(html.toString());
			if ( logger.isDebugEnabled() )
				logger.debug("Done!");
			
			//JspWriter salida = this.pageContext.getOut();
			//salida.println(getMenu());
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Acabamos tag y continuamos pagina
		return EVAL_PAGE;
	}

	public String getElements() {
		return elements;
	}

	public void setElements(String elements) {
		this.elements = elements;
	}
	
	private String getIcon(AssetsDTO asset) throws BusinessException
	{
		String icon = null;
		if (asset.getTipAssFk().compareTo(IConstantes.TIPO_ASSET_FICHERO) == 0)
		{
			FicherosDTO fichero = ficherosBO.getByAsset(asset.getAssPk());
			MimeFilesDTO mime = mimeFilesBO.getByPrimaryKey(fichero.getMimFilFk());
			icon = mime.getMimFilIcon();
			if ( icon == null || icon.length() == 0 )
				icon = IConstantes.DEFAUL_ICON_DIR + IConstantes.DEFAUL_ICON_FILE;
			else
				icon = IConstantes.DEFAUL_MIME_ICON_DIR + icon;			
		}
		else if (asset.getTipAssFk().compareTo(IConstantes.TIPO_ASSET_CARPETA) == 0)
		{
			icon = IConstantes.DEFAUL_ICON_DIR + IConstantes.DEFAUL_ICON_FOLDER;
		}
		else
		{
			icon = IConstantes.DEFAUL_ICON_DIR + IConstantes.DEFAUL_ICON_FILE;
		}
		return icon;
	}
}
