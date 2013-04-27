package common.presentation.web.tld;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.Constants;
import common.business.BusinessException;
import common.business.bo.AvisosBO;
import common.business.bo.UsuariosBO;
import common.dto.AvisosDTO;
import common.dto.UsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.util.GlobalFunctions;
import common.util.StringUtils;
import common.util.spring.SpringUtil;

public class NotificacionesTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7085317008794590102L;
	
	private static Log logger = LogFactory.getLog(NotificacionesTag.class);
	private StringBuffer html;
	private String view;

	private static AvisosBO boAvisos = (AvisosBO) SpringUtil.getInstance().getBean("AvisosBO");

	/**
	 * Over-ride del metodo doEndTag
	 */
	public int doEndTag() throws JspException {
		html = new StringBuffer();
		
		try {
			UserInfo user = (UserInfo) this.pageContext.getSession().getAttribute("user");
			AvisosDTO[] avisos = boAvisos.getByUser(user.getPk());
			
			if ("LIST".equalsIgnoreCase(view))
			{
				listHTML(avisos);
			}
			else
			{
				iconHTML(avisos);
			}
						
			this.pageContext.getOut().print(html.toString()); 
			if ( logger.isDebugEnabled() )
				logger.debug("Done!");
			
			//JspWriter salida = this.pageContext.getOut();
			//salida.println(getMenu());
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}

		return EVAL_PAGE;
	}

	private void iconHTML (AvisosDTO[] avisos)
	{
		html.append("<div>");
		if (avisos.length > 0)
		{
			Long total = 0L;

			for (AvisosDTO aviso:avisos)
			{
				total += aviso.getAviCantidad()==0?1:aviso.getAviCantidad();
			}
			
			String alt = "Tienes " + total + " notificaciones pendientes.";
			html.append("<table class='text-notification'>");
			html.append("<tr>");
			html.append("<td>");
			html.append("<img src='/portal/images/info.png'/>");
			html.append("</td>");
			html.append("<td>");
			html.append("Avisos pendientes");
			html.append("<td>");
			html.append("<tr>");
			html.append("</table>");
			html.append("<div style='padding-left: 70px;'>");
			html.append("<a href='javascript:void(0);' ")
				.append("onclick='javascript:ajaxPopup(\"/portal/avisos.do\", null);' >");
			html.append("<img src='/portal/images/comments.png' alt='");				
			html.append(alt);
			html.append("' ");
			html.append("title='");				
			html.append(alt);
			html.append("'/>");
			html.append("</a>\n");
			html.append("</div>");
		}
		else
		{
			String alt = "No tienes nuevas notificaciones.";
			html.append("<img src='/portal/images/comment.png' alt='");				
			html.append(alt);
			html.append("' ");
			html.append("title='");				
			html.append(alt);
			html.append("'/>");
		}
		html.append("</div>");
	}
	
	private void listHTML(AvisosDTO[] avisos)
	{
		try {
			if (avisos!=null && avisos.length > 0)
			{
				HttpSession session = this.pageContext.getSession();
				UserInfo user = (UserInfo) session.getAttribute("user");						
	    		
				html.append("<div class='avisosusuario'>\n");
				for (int i = 0; i < avisos.length;i++)
				{
					html.append("<div ");
					if (avisos[i].getAviPk() != null)
					{
						html.append("id='")
							.append(avisos[i].getAviPk())
							.append("' ");							
					}
					html.append("class='aviso ")
						.append(avisos[i].getTipAviFk() == null?"":avisos[i].getTipAviFk())
						.append("'>");
					creaFicha(user.getPk(), avisos[i]);
					html.append("</div>");						
				}
				html.append("</div>\n");
			}
			else
			{
				html.append("<div style='text-align: center;'>No hay avisos pendientes.</div>");						
			}		
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
	}
	
	private void creaFicha(Long user, AvisosDTO data) throws BusinessException, IOException
	{
		UsuariosBO boUsuario = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
		UsuariosDTO userDTO = boUsuario.getByPrimaryKey(data.getUsuFkOrigen());
		String nombre = userDTO.getUsuNombre() + " " + userDTO.getUsuApellido1() + " " + userDTO.getUsuApellido2();
		
		String path = GlobalFunctions.pathAbatar(userDTO.getUsuPk(), this.pageContext.getSession());

		html.append("<div class='ficha'>");
		html.append("<table width='100%'>");
		html.append("<tr>");
		html.append("<td class='abatar'>");
		html.append("<img src='");
		html.append(path);
		html.append("'/>\n");
		html.append("</td>");		
		html.append("<td class='data'>");
		//html.append("<h1><b><p>");
		//html.append(data.getTipAviUkCodigo());
		//html.append("</p></b></h1>");
		html.append("<p><h3><b>");
		html.append(StringUtils.escapeXML(getAvisoDescripcion(data.getTipAviFk())));
		html.append("</b></h3>");
		html.append("</p>");
		html.append(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date(data.getAviFEnvio())));
		html.append("<p>");		
		html.append(StringUtils.escapeXML(nombre));
		html.append("</p>");		
		html.append("<p>");		
		html.append("Cantidad: ");
		html.append(data.getAviCantidad());
		html.append("</p>");		
		html.append("</td>");
		html.append("<td class='opcion'>");
		html.append("<a class='boton' href='javascript:void(0);' onclick='javascript:updateAviso(");
		html.append(data.getAviPk());
		html.append(");");
		html.append("'>");
		html.append("<img src='/portal/images/dialog-apply.png'/> <span>Ok</span>");
		html.append("</a>");				
		html.append("</td>");		
		html.append("</tr>");
		html.append("</table>");					
		html.append("</div>");		
	}
		
	public void setView(String view) {
		this.view = view;
	}

	public String getView() {
		return view;
	}
	
	private String getAvisoDescripcion(Integer tip_avi)
	{
		if (tip_avi.intValue() == Constants.AVISO_AMISTAD)
		{
			return "Tienes solicitudes de amistad.";
		}
		else if (tip_avi.intValue() == Constants.AVISO_AMISTAD_ACEPTDA)
		{
			return "Han aceptado la solicitud de amistad.";			
		}
		else if (tip_avi.intValue() == Constants.AVISO_COMPARTIDOS)
		{
			return "Tienes nuevos archivos compartidos.";			
		}
		else if (tip_avi.intValue() == Constants.AVISO_FORO)
		{
			return "Nueva entrada en el foro.";			
		}
		else
			return "";
	}
}
