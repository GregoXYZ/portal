package common.presentation.web.tld;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.Constants;
import common.business.BusinessException;
import common.business.bo.UsuariosBO;
import common.dto.UsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.util.FileUtils;
import common.util.spring.SpringUtil;


public class AvatarTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6099748660230496744L;
	private static Log logger = LogFactory.getLog(AvatarTag.class);
	private Long userId;
	private String userCode;
	private Integer width;
	private Integer height;
	
	private static StringBuffer html;

	/**
	 * Over-ride del metodo doEndTag
	 */
	public int doEndTag() throws JspException {

		String userCode = null;
		html = new StringBuffer();

       	try {
			if ( userId != null )
			{
	        	UsuariosBO bo = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
	 			UsuariosDTO dto = bo.getByPrimaryKey(userId);
	 			userCode = dto.getUsuUkUsuario();
			}
			else if ( this.userCode != null )
			{
	 			userCode = this.userCode;				
			}
			else
			{
				HttpSession session = this.pageContext.getSession();	
				UserInfo user = (UserInfo) session.getAttribute("user");
				userCode = user.getUser();
			}
		} catch (BusinessException e) {
			logger.error(e);
			e.printStackTrace();
		}
		
		if ( userCode != null )
		{
			//String path = "/portal" + Constants.TEMP_USER_DIR + Constants.DEFAUL_AVATAR_DIR + userCode;
			String path = this.pageContext.getSession().getServletContext().getRealPath("/");
			String contextPath = this.pageContext.getSession().getServletContext().getContextPath();
			int ind = path.indexOf(contextPath);
			path = path.substring(0, ind) + "/portal" + Constants.TEMP_USER_DIR + Constants.DEFAUL_AVATAR_DIR + userCode;
			
			if ( FileUtils.fileExists(path) )
			{
				path = "/portal" + Constants.TEMP_USER_DIR + Constants.DEFAUL_AVATAR_DIR + userCode;
			}
			else
			{
	    		path = "/portal/images/" + Constants.DEFAUL_AVATAR;    			    						
			}

			html.append("<img class='avatar' src='");
			html.append(path);
			if (this.width != null && this.width>0)
			{
				html.append("' width='");
				html.append(this.width);
			}
			if (this.height != null && this.height>0)
			{
				html.append("' height='");
				html.append(this.height);
			}
			html.append("'/>");
		}
		
		// Escribimos codigo html en la pagina
		try {
			this.pageContext.getOut().print(html.toString());
			if ( logger.isDebugEnabled() )
				logger.debug("Done!");			
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
		}
		
		// Acabamos tag y continuamos pagina
		return EVAL_PAGE;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getWidth() {
		return width;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getHeight() {
		return height;
	}
}
