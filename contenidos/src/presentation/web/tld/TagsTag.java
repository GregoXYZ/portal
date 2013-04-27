package presentation.web.tld;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.bo.ExtraQueriesBO;
import common.dto.TagsDTO;
import common.presentation.security.beans.UserInfo;
import common.util.spring.SpringUtil;

public class TagsTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1728085898349975934L;
	private static Log logger = LogFactory.getLog(TagsTag.class);
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
			TagsDTO[] dto = queriesBO.getTags(user.getPk(), asset);
			
			for (TagsDTO tag:dto)
			{
				html.append("<span class='tag'>");
				html.append(tag.getTagUkCodigo());
				html.append("<a href='javascript:void(0);' onclick='javascript:borraTag(");
				html.append(tag.getTagPk());
				html.append(", ");
				html.append(asset);
				html.append(")'>");
				html.append("<img title='Elimina el tag ");
				html.append(tag.getTagUkCodigo());
				html.append("' src='images/delete.png' />");
				html.append("</a>");
				html.append("</span>");
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
