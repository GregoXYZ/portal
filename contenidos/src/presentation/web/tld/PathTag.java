package presentation.web.tld;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.bo.AssetsBO;
import common.business.bo.CarpetasBO;
import common.dto.AssetsDTO;
import common.dto.CarpetasDTO;
import common.presentation.security.beans.UserInfo;
import common.util.StringUtils;
import common.util.spring.SpringUtil;

public class PathTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2361273797110187775L;
	private static Log logger = LogFactory.getLog(PathTag.class);
	private Long carpeta;

	/**
	 * Over-ride del metodo doEndTag
	 */
	public int doEndTag() throws JspException {
		String html = "";

		HttpSession session = this.pageContext.getSession();	
		UserInfo user = (UserInfo) session.getAttribute("user");

		try {
			CarpetasBO carpetasBO = (CarpetasBO) SpringUtil.getInstance().getBean("CarpetasBO");
			AssetsBO assetsBO = (AssetsBO) SpringUtil.getInstance().getBean("AssetsBO");
			CarpetasDTO dto = null;
			do {
				dto = carpetasBO.getByPrimaryKey(carpeta);
				if ( dto != null )
				{
					AssetsDTO asset = assetsBO.getByPrimaryKey(dto.getAssPk(), user.getPk());
					//html = "<a href='javascript:void(0);' onclick=\"sendData('ContenidosForm','" + dto.getCarPk() + "','carPk','none','')\">" + 
					html = "<a href='javascript:void(0);' onclick=\"javascript:cargaAjax(" + dto.getCarPk() + ")\">" +
						StringUtils.escapeXML(asset.getAssNombre()) + 
						"</a>/" + html;
					carpeta = dto.getCarFk();
							}
			} while ( dto != null && dto.getCarFk() != null);
			
			if (html.trim().length() > 0)
				html = "<a href='javascript:void(0);' onclick=\"javascript:cargaAjax(0)\">Raiz</a>/" + html;
				//html = "<a href='javascript:void(0);' onclick=\"sendData('ContenidosForm','','carPk','none','')\">raiz</a>/" + html;
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

	public void setCarpeta(Long carpeta) {
		this.carpeta = carpeta;
	}

	public Long getCarpeta() {
		return carpeta;
	}
}
