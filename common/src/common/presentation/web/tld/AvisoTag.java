package common.presentation.web.tld;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.presentation.beans.WebInfo;
import common.presentation.beans.WebInfo.TypeInfo;
import common.presentation.security.beans.UserInfo;
import common.util.StringUtils;


public class AvisoTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1481012441320900962L;
	private static Log logger = LogFactory.getLog(AvisoTag.class);
	private StringBuffer html;
	private WebInfo info;
	
	/**
	 * Over-ride del metodo doEndTag
	 */
	public int doEndTag() throws JspException {
		html = new StringBuffer();
		
		try {
			HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
	    	HttpSession session = request.getSession();

			UserInfo user = (UserInfo) session.getAttribute("user");
			if (info == null)
			{
		    	info = (WebInfo) session.getAttribute("info");
			}
			
			if (info != null && info.getValue().toString().trim().length() > 0)
			{
				html.append("<script type='text/javascript'>"); 
				html.append("$(document).ready(function() {	");
				if ( info.getTipo() == TypeInfo.SYSTEM && user != null)
				{
					html.append("$('#erroricon').fadeIn(3000);");
				}
				else
				{
					html.append("$('#erroricon').fadeOut(3000);");					
				}
				html.append("});");
				html.append("</script>");

				html.append("<div class='webinfo'>");
				html.append("<div class='web" + info.getTipo().name().toLowerCase() + "'>");
				if ( (info.getTipo() == TypeInfo.SYSTEM) && user != null)
				{
					html.append("<a id='awebinfo' onclick=\"javascript:ajaxPopup('/portal/reportBug.do?bugMessage=' + $(this).html()");
					html.append(", null);\" href=\"javascript:void(0); \" title='Reporta error' >");
				}
				html.append(StringUtils.escapeXML(info.getValue().toString()));
				if ( (info.getTipo() == TypeInfo.SYSTEM) && user != null)
				{
					html.append("</a>");
				}
				html.append("</div>");
				html.append("<div style='padding: 0 20px 3px 0; width: 100%; text-align: center; color: #662B27; font-size: 0.8em;'>");
				html.append("<a class='boton' href='javascript:void(0);' onclick='javascript:clearInfoWeb();'>Pulsa AQUê para cerrar (Se cerrar‡ automaticamente en 30 segs).</a></div>");
				html.append("</div>");
				session.removeAttribute("info");
				this.pageContext.getOut().print(html.toString()); 
			}
			
			if ( logger.isDebugEnabled() )
				logger.debug("Done!");
			
			//JspWriter salida = this.pageContext.getOut();
			//salida.println(getMenu());
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
		}

		return EVAL_PAGE;
	}

	public void setInfo(WebInfo info) {
		this.info = info;
	}

	public WebInfo getInfo() {
		return info;
	}
}
