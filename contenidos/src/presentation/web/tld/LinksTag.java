package presentation.web.tld;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.bo.ExtraQueriesBO;
import common.dto.AssetsDTO;
import common.presentation.security.beans.UserInfo;
import common.util.spring.SpringUtil;

public class LinksTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1728085898349975934L;
	private static Log logger = LogFactory.getLog(LinksTag.class);
	private Long asset;

	/**
	 * Over-ride del metodo doEndTag
	 */
	public int doEndTag() throws JspException {
		StringBuffer html = new StringBuffer();
		HttpSession session = this.pageContext.getSession();	
		UserInfo user = (UserInfo) session.getAttribute("user");

		try {
			ExtraQueriesBO queriesBO = (ExtraQueriesBO) SpringUtil.getInstance().getBean("ExtraQueriesBO");
			AssetsDTO[] dto = queriesBO.getLinks(user.getPk(), asset);
			
			for (AssetsDTO assetDTO:dto)
			{
				html.append("<div class='link'>");
				html.append("<span class='url'>");
				html.append("<a href='");
				html.append(assetDTO.getAssNombre());
				html.append("' title='");
				html.append(assetDTO.getAssNombre());
				html.append("' target='_blank'>");
				html.append(assetDTO.getAssDescripcion());
				html.append("</a>");
				html.append("</span>");

				html.append("<span class='deletelink'>");
				html.append("<a href='javascript:void(0);' onclick='javascript:borraLink(");
				html.append(assetDTO.getAssPk());
				html.append(",");
				html.append(asset);
				html.append(")'>");
				html.append("<img title='Elimina el link ");
				html.append(assetDTO.getAssDescripcion());
				html.append("' src='images/delete.png' />");
				html.append("</a>");
				html.append("</span>");
				html.append("</div>");
			}
			
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		// Escribimos codigo html en la pagina
		try {
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

	public void setAsset(Long asset) {
		this.asset = asset;
	}

	public Long getAsset() {
		return asset;
	}
}
