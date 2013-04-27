package presentation.web.tld;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.IConstantes;
import common.business.BusinessException;
import common.business.bo.AssetsBO;
import common.business.bo.FicherosBO;
import common.business.bo.MimeFilesBO;
import common.business.bo.ParametrosBO;
import common.dto.AssetsDTO;
import common.dto.FicherosDTO;
import common.dto.MimeFilesDTO;
import common.presentation.security.beans.UserInfo;
import common.util.spring.SpringUtil;

public class OperacionesTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5982979691927501678L;
	private static Log logger = LogFactory.getLog(OperacionesTag.class);
	private String elements;

	AssetsBO assetsBO = (AssetsBO) SpringUtil.getInstance().getBean("AssetsBO");
	FicherosBO ficherosBO = (FicherosBO) SpringUtil.getInstance().getBean("FicherosBO");
	MimeFilesBO mimeFilesBO = (MimeFilesBO) SpringUtil.getInstance().getBean("MimeFilesBO");

	/**
	 * Over-ride del metodo doEndTag
	 */
	@SuppressWarnings("unchecked")
	public int doEndTag() throws JspException {
		StringBuffer html = new StringBuffer();
		HttpSession session = this.pageContext.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");
    	String icon = IConstantes.DEFAUL_ICON_DIR + IConstantes.DEFAUL_ICON_FILE;
		
		ServletRequest request = this.pageContext.getRequest();	
		Map<Long, String> assets = (Map<Long, String>) request.getAttribute(elements);

		if (assets != null && assets.size() > 0)
		{
			try {
				Iterator<?> it = assets.entrySet().iterator();
				while (it.hasNext()) {
					icon = IConstantes.DEFAUL_ICON_DIR + IConstantes.DEFAUL_ICON_FILE;
					Map.Entry e = (Map.Entry)it.next();
										
					AssetsDTO asset = assetsBO.getByPrimaryKey((Long) e.getKey(), user.getPk());
					if (asset != null)
					{
						FicherosDTO fichero = null;
						MimeFilesDTO mime = null;
						if (asset.getTipAssFk().compareTo(IConstantes.TIPO_ASSET_FICHERO) == 0)
						{
							fichero = ficherosBO.getByAsset(asset.getAssPk());
							mime = mimeFilesBO.getByPrimaryKey(fichero.getMimFilFk());
							if ( mime.getMimFilIcon() != null && mime.getMimFilIcon().length() > 0 )
								icon = IConstantes.DEFAUL_MIME_ICON_DIR + mime.getMimFilIcon();		
						}
						else if (asset.getTipAssFk().compareTo(IConstantes.TIPO_ASSET_CARPETA) == 0)
						{
							icon = IConstantes.DEFAUL_ICON_DIR + IConstantes.DEFAUL_ICON_FOLDER;
						}
						html.append("<div id='").append(e.getKey()).append("' class='operasset' >");
						html.append("<div style='width: 100%; ' >");
						html.append("<a style=\"float: right; \" onclick=\"javascript:$('#opercontent #");
						html.append(e.getKey()).append("').remove(); cargaDiv(null, 'remomveoper.do', null, null, null, 'assPk=");
						html.append(e.getKey());
						html.append("');\" href='javascript:void(0);' title='Cerrar operación'>");
						html.append("<img src='images/module-close.gif' />");
						html.append("</a>");
						html.append("<img src='").append(icon).append("' style='margin: 0 3px 0 0;' />");
						html.append(e.getValue());
						html.append("</div>");

						if (fichero != null && mime != null && asset.getTipAssFk().compareTo(IConstantes.TIPO_ASSET_FICHERO) == 0)
						{
							ParametrosBO parametrosBO = (ParametrosBO) SpringUtil.getInstance().getBean("ParametrosBO");
							if ( parametrosBO.getValor("IMAGE_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) )
							{
								html.append("<img src=\"resizer?asset=").append(fichero.getAssPk()).append("&width=50&height=50\"/>");
							}
							else if ( parametrosBO.getValor("VIDEO_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) )
							{
								html.append("<video style=\"max-width:50px; max-height:50px;\" src=\"play/sound.").append(mime.getMimFilExtension()).append("?asset=").append(fichero.getAssPk()).append("\" controls autobuffer>");
								/*
								html.append("<div class=\"hmtl5-fallback\">");
								html.append("<br/>");
								html.append("You must have an HTML5 capable browser.");
								html.append("<br/>");
								html.append("</div>");
								*/
								html.append("</video>");
							}
							else if ( parametrosBO.getValor("SOUND_FORMATS").contains(mime.getMimFilExtension().toLowerCase()) )
							{
								html.append("<audio style=\"max-width:50px; max-height:50px;\" src=\"play/sound.").append(mime.getMimFilExtension()).append("?asset=").append(fichero.getAssPk()).append("\" controls autobuffer>");
								/*
								html.append("<div class=\"hmtl5-fallback\">");
								html.append("<br/>");
								html.append("You must have an HTML5 capable browser.");
								html.append("<br/>");
								html.append("</div>");
								*/
								html.append("</audio>");
							}
						}

						html.append("<div class='operoptions' style='display: block;' >");
						html.append("<a class='boton' onclick='javascript:moverAsset(").append(asset.getAssPk()).append(", \"");
						html.append(asset.getTipAssFk().compareTo(IConstantes.TIPO_ASSET_FICHERO) == 0?"ficheros":(asset.getTipAssFk().compareTo(IConstantes.TIPO_ASSET_CARPETA) == 0?"carpetas":""));
						html.append("\")' style='margin: 0 1px 0 1px; padding: 0 5px 0 5px;' href='javascript:void(0);' title='Mover a la carpeta actual'>");
						html.append("<img src='images/edit-cut.png' style='margin: 0 3px 0 0;'/>");
						html.append("</a>");
						/*
						html.append("<a class='boton' onclick='javascript:copiarAsset(").append(asset.getAssPk()).append(", \"");
						html.append(asset.getTipAssFk().compareTo(IConstantes.TIPO_ASSET_FICHERO) == 0?"ficheros":(asset.getTipAssFk().compareTo(IConstantes.TIPO_ASSET_CARPETA) == 0?"carpetas":""));
						html.append("\")' style='margin: 0 1px 0 1px; padding: 0 5px 0 5px;' href='javascript:void(0);' title='Copia a la carpeta actual'>");
						html.append("<img src='images/edit-copy.png' style='margin: 0 3px 0 0;'/>");
						html.append("</a>");
						*/
						html.append("</div>");
						html.append("</div>");						
					}
				}							
			} catch (BusinessException e1) {
				logger.error(e1.getMessage());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}
		try {			
			// Escribimos codigo html en la pagina
			this.pageContext.getOut().print(html.toString());
			if ( logger.isDebugEnabled() )
				logger.debug("Done!");
			
			//JspWriter salida = this.pageContext.getOut();
			//salida.println(getMenu());
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
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
}
