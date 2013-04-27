package presentation.web.tld;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.bo.QueriesForosBO;
import common.business.bo.UsuariosBO;
import common.dto.ContenidosDTO;
import common.dto.EntradasDTO;
import common.dto.MensajesDTO;
import common.dto.UsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.util.GlobalFunctions;
import common.util.StringUtils;
import common.util.spring.SpringUtil;

public class ForosTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2361273797110187775L;
	private static Log logger = LogFactory.getLog(ForosTag.class);

	private Long entrada;
	private StringBuffer html;

	
	/**
	 * Over-ride del metodo doEndTag
	 */
	public int doEndTag() throws JspException {
		html = new StringBuffer();
		
		HttpSession session = this.pageContext.getSession();	
		UserInfo user = (UserInfo) session.getAttribute("user");

		if (entrada != null)
		{
			threats(user.getPk(), entrada);
		}
		else
		{
			resumen(user.getPk());
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

	private void resumen(Long user)
	{
		try {
			UsuariosBO usuariosBO = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
			QueriesForosBO queriesBO = (QueriesForosBO) SpringUtil.getInstance().getBean("QueriesForosBO");
			EntradasDTO[] entradas = queriesBO.getEntradas(user);
			for (EntradasDTO dto : entradas)
			{
				ContenidosDTO contenidoDTO = queriesBO.getUltimoContenido(dto.getEntPk());
				UsuariosDTO userDTO = usuariosBO.getByPrimaryKey(contenidoDTO.getUsuFk());
				html.append("<div id='").
					append(dto.getEntPk()).
					append("_").
					append(contenidoDTO.getConPk()).
					append("' onclick='javascript:cargaContenido(").
					append(dto.getEntPk()).
					append(")' class='result'>");
				if (dto.getEntRestringida())
				{
					html.append("<img class='link' title='Entrada restringida' src='images/lock.png'/>");
				}
				html.append("<a class='connect' href='javascript:void(0);' linkindex='50'><span>");
				html.append(StringUtils.escapeXML(dto.getEntSubject()));
				html.append("</span></a>");
				html.append("<div class='blurb'><div>");
				html.append(StringUtils.escapeXML(contenidoDTO.getConData().length()>200?contenidoDTO.getConData().substring(0, 197) + "...":contenidoDTO.getConData()));
				html.append("</div></div>");
				html.append("<img class='link' alt='View Message' src='images/link_arrow.gif'/>");
				html.append("<div class='meta'>");
				html.append(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date(contenidoDTO.getConFechaAlta()))).
					append(" - ").append(StringUtils.escapeXML(userDTO.getUsuNombre() + " " + 
						(userDTO.getUsuApellido1()==null?"":userDTO.getUsuApellido1() + " ") + 
						(userDTO.getUsuApellido2()==null?"":userDTO.getUsuApellido2())));
				html.append("</div>");
				html.append("</div>");
			}
		} catch (BusinessException e) {
			logger.error(e);
		}
	}

	private void threats(Long user, Long entrada)
	{
		HttpSession session = this.pageContext.getSession();	
		try {
			UsuariosBO usuariosBO = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
			QueriesForosBO queriesBO = (QueriesForosBO) SpringUtil.getInstance().getBean("QueriesForosBO");
			
			MensajesDTO[] dto = queriesBO.getThreats(user, entrada);
			for (MensajesDTO mensaje:dto)
			{
				UsuariosDTO usuario = usuariosBO.getByPrimaryKey(mensaje.getContenido().getUsuFk());
				
				html.append("<div class='values'>");
				html.append("<div id='messageactions' class='actionsdropdown'>");
				html.append("</div>");
				html.append("<table id='headers'>");
				html.append("<tbody>");
				html.append("<tr>");
				html.append("<td class='avatar' rowspan='5'>");
				html.append("<img src='").append(GlobalFunctions.pathAbatar(usuario.getUsuPk(), session)).append("'/>");
				html.append("</td>");
				html.append("</tr>");
				html.append("<tr>");
				html.append("<th>Foro:</th>");
				html.append("<td>");
				html.append(mensaje.getEntrada().getEntSubject());
				html.append("</td>");
				html.append("<td class='actions'>");
				html.append("<span class='actions mklink' onclick='javascript:nuevoMensaje(").
					append(mensaje.getEntrada().getEntPk()).
					append(", ").
					append(mensaje.getContenido().getConPk()).
					append(")'>Responder</span>");
				html.append("</td>");
				html.append("</tr>");
				html.append("<tr>");
				html.append("<th>De:</th>");
				html.append("<td colspan='2'>");
				html.append(StringUtils.escapeXML(usuario.getUsuNombre())).
					append(" ").
					append(usuario.getUsuApellido1()==null?"":usuario.getUsuApellido1()).
					append(" ").
					append(usuario.getUsuApellido2()==null?"":usuario.getUsuApellido2());
				html.append("</td>");
				html.append("</tr>");
				html.append("<tr>");
				html.append("<th>Cuando:</th>");
				html.append("<td colspan='2'>");
				html.append(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date(mensaje.getContenido().getConFechaAlta())));
				html.append("</td>");
				html.append("</tr>");
				html.append("</tbody>");
				html.append("</table>");
				html.append("<div id='body' class='messagebody'>");
				html.append("<div class='pws'>");
				html.append(mensaje.getContenido().getConData());
				html.append("</div>");
				html.append("</div>");
				html.append("</div>");				
			}
		} catch (BusinessException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public void setEntrada(Long entrada) {
		this.entrada = entrada;
	}

	public Long getEntrada() {
		return entrada;
	}
}
