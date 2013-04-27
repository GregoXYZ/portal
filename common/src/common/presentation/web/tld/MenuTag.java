package common.presentation.web.tld;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.Constants;
import common.business.BusinessException;
import common.business.bo.MenusBO;
import common.business.bo.UrlsBO;
import common.business.bo.UsuariosBO;
import common.business.bo.ZonasBO;
import common.dto.MenusDTO;
import common.dto.UrlsDTO;
import common.dto.UsuariosDTO;
import common.dto.ZonasDTO;
import common.presentation.security.beans.UserInfo;
import common.util.StringUtils;
import common.util.spring.SpringUtil;

public class MenuTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3212714553945162589L;
	
	private static Log logger = LogFactory.getLog(MenuTag.class);
	private static UsuariosBO boUsuario = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
	private static MenusBO boMenu = (MenusBO) SpringUtil.getInstance().getBean("MenusBO");
	private static UrlsBO boUrl = (UrlsBO) SpringUtil.getInstance().getBean("UrlsBO");
	private static ZonasBO boZona = (ZonasBO) SpringUtil.getInstance().getBean("ZonasBO");
	private static StringBuffer html = new StringBuffer();

	/**
	 * Over-ride del metodo doEndTag
	 */
	public int doEndTag() throws JspException {
		HttpSession session = this.pageContext.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		try {
	    	if ( user == null )
			{
				this.pageContext.getOut().print("");
				if ( logger.isDebugEnabled() )
					logger.debug("Done!");			
			}
			else
			{
				//if ( MenuTag.user == null || user.getPk().compareTo(getUser()) != 0 )
				//{
					html = null;
					html = new StringBuffer();
					createMenu(user.getPk());
				//}

				// Escribimos codigo html en la pagina
				this.pageContext.getOut().print(getMenu());
				if ( logger.isDebugEnabled() )
					logger.debug("Done!");
				
				//JspWriter salida = this.pageContext.getOut();
				//salida.println(getMenu());
			}
			
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
		
		// Acabamos tag y continuamos pagina
		return EVAL_PAGE;
	}

	private static String getMenu() {
		if (html.toString().length() == 0 )
		{
			html.append("<ul class='nav'>\n" + 
					"<li><a class='cap' href='javascript:void(0);'>\n" + 
					"No tienes ninguna opción asociada" + 
					"</a></li>\n" + 
					"</ul>\n");
		}
		return html.toString();
	}
	
	private void createMenu(Long user) throws BusinessException
	{
		try {
			String sZona = this.pageContext.getSession().getServletContext().getContextPath().replaceFirst("/", "");
	    	ZonasDTO zona = boZona.getByCode(sZona);
			MenusDTO[] menu = boMenu.getCaps(user);
			
			if (menu.length > 0)
			{
				UsuariosDTO usuarioDTO = boUsuario.getByPrimaryKey(user);
				String nombre = usuarioDTO.getUsuNombre() + " " + usuarioDTO.getUsuApellido1() + " " + usuarioDTO.getUsuApellido2();
				html.append("<ul class='nav'>\n");
				for (int i = 0;i < menu.length; i++)
				{
					if ( menu[i].getUrlFk() == null ) 
					{
						menu[i].setMenTitulo((String) StringUtils.escapeXML(menu[i].getMenTitulo().replace("@user", nombre)));
						html.append("<li><a class='cap' href='javascript:void(0);'>");
						html.append(getIcon(menu[i]));
						html.append(StringUtils.escapeXML(menu[i].getMenTitulo()));
						html.append("</a>\n");												
					}
					else
					{
						UrlsDTO url = boUrl.getByPrimaryKey(menu[i].getUrlFk());
						if (menu[i].getMenRestringido() == 'N' || zona.getZonPk().equals(url.getZonFk()))
						{
							String path = "/" + boZona.getByPrimaryKey(url.getZonFk()).getZonCodigo();
							if (menu[i].getMenPopup() == 'S')
							{
								html.append("<li><a class='cap' href='javascript:void(0);' onClick='");
								html.append("javascript:ajaxPopup(\"");
								html.append((url.getUrlDireccion() == null?"":path + "/" + url.getUrlDireccion()));
								html.append("\", null);'>");
								html.append(getIcon(menu[i]));
								html.append(StringUtils.escapeXML(menu[i].getMenTitulo()));
								html.append("</a>\n");														
							}
							else
							{
								html.append("<li><a class='cap' href='");
								html.append((url.getUrlDireccion() == null?"":path + "/" + url.getUrlDireccion()));
								html.append("'>");
								html.append(getIcon(menu[i]));
								html.append(StringUtils.escapeXML(menu[i].getMenTitulo()));
								html.append("</a>\n");							
							}							
						}
					}
					StringBuffer subMenu = creaSubmenu(user, menu[i].getMenPk());
					if (subMenu != null) html.append(subMenu);
					html.append("</li>\n");
				}
				html.append("</ul>\n");
			}
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	private StringBuffer creaSubmenu(Long user, Long parent) throws BusinessException
	{
		StringBuffer subMenu = null;
		String sZona = this.pageContext.getSession().getServletContext().getContextPath().replaceFirst("/", "");
    	ZonasDTO zona = boZona.getByCode(sZona);
		MenusDTO[] subMenuDTO = boMenu.getMenu(user, parent);
		
		if (subMenuDTO.length > 0)
		{
			subMenu = new StringBuffer();
			subMenu.append("<ul>");
			for (int i = 0;i < subMenuDTO.length; i++)
			{
				StringBuffer subMenuAux = creaSubmenu(user, subMenuDTO[i].getMenPk());
				if ( subMenuDTO[i].getUrlFk() == null ) 
				{
					String child = subMenuAux == null?"":"class='childs' ";
					subMenu.append("<li><a ");
					subMenu.append(child);
					subMenu.append(" href='javascript:void(0);'>");
					subMenu.append(getIcon(subMenuDTO[i]));
					subMenu.append(StringUtils.escapeXML(subMenuDTO[i].getMenTitulo()));
					subMenu.append("</a>\n");					
				}
				else
				{
					UrlsDTO url = boUrl.getByPrimaryKey(subMenuDTO[i].getUrlFk());
					if (subMenuDTO[i].getMenRestringido() == 'N' || zona.getZonPk().equals(url.getZonFk()))
					{
						String path =  "/" + boZona.getByPrimaryKey(url.getZonFk()).getZonCodigo();
						String child = subMenuAux == null?"":"class='childs' ";
						if (subMenuDTO[i].getMenPopup() == 'S')
						{
							subMenu.append("<li><a ");
							subMenu.append(child);
							subMenu.append("href='javascript:void(0);' onClick='");
							subMenu.append("javascript:ajaxPopup(\"");
							subMenu.append((url.getUrlDireccion() == null?"":path + "/" + url.getUrlDireccion()));
							subMenu.append("\", null);'>");
							subMenu.append(getIcon(subMenuDTO[i]));
							subMenu.append(StringUtils.escapeXML(subMenuDTO[i].getMenTitulo()));
							subMenu.append("</a>\n");												
						}
						else
						{
							subMenu.append("<li><a ");
							subMenu.append(child);
							subMenu.append("href='");
							subMenu.append((url.getUrlDireccion() == null?"":path + "/" + url.getUrlDireccion()));
							subMenu.append("'>"); 
							subMenu.append(getIcon(subMenuDTO[i]));
							subMenu.append(StringUtils.escapeXML(subMenuDTO[i].getMenTitulo()));
							subMenu.append("</a>\n");						
						}						
					}
				}
				if (subMenuAux != null) subMenu.append(subMenuAux);
				subMenu.append("</li>\n");
			}
			subMenu.append("</ul>");
		}
		return subMenu;
	}
	
	private String getIcon(MenusDTO dto)
	{
		String img;
		//String path = this.pageContext.getServletContext().getContextPath();
		String path = "/portal";

		if ( dto.getMenIcon() == null )
		{
			img = "<img src='" + path + Constants.MENU_ICON_FOLDER + Constants.DEFAULT_MENU_ICON + "' />";
		}
		else
		{			
			img = "<img src='" + path + Constants.MENU_ICON_FOLDER + dto.getMenIcon() + "' />";
		}
		
		return img;
	}
}
