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
import common.dto.MensajesDTO;
import common.dto.UsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.util.StringUtils;
import common.util.spring.SpringUtil;

public class MensajesTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2361273797110187775L;
	private static Log logger = LogFactory.getLog(MensajesTag.class);

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
			MensajesDTO[] mensajes = queriesBO.getResults(user);
			for (MensajesDTO dto : mensajes)
			{
				UsuariosDTO userDTO = usuariosBO.getByPrimaryKey(dto.getContenido().getUsuFk());
				html.append("<div id='").
					append(dto.getEntrada().getEntPk()).
					append("_").
					append(dto.getContenido().getConPk()).
					append("' onclick='javascript:cargaThreats(").
					append(dto.getEntrada().getEntPk()).
					append(", ").
					append(dto.getContenido().getConPk()).
					append(")' class='result'>");
				if (dto.getEntrada().getEntRestringida())
				{
					html.append("<img class='link' title='Entrada restringida' src='images/lock.png'/>");
				}
				html.append("<a class='connect' href='javascript:void(0);' linkindex='50'><span>");
				html.append(StringUtils.escapeXML(dto.getEntrada().getEntSubject()));
				html.append("</span></a>");
				html.append("<div class='blurb'><div>");
				html.append(StringUtils.escapeXML(dto.getContenido().getConData().length()>200?dto.getContenido().getConData().substring(0, 197) + "...":dto.getContenido().getConData()));
				html.append("</div></div>");
				html.append("<img class='link' alt='View Message' src='images/link_arrow.gif'/>");
				html.append("<div class='meta'>");
				html.append(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date(dto.getContenido().getConFechaAlta()))).
				append(" - ").
				append(StringUtils.escapeXML(userDTO.getUsuNombre())).
				append(" ").
				append(userDTO.getUsuApellido1()==null?"":userDTO.getUsuApellido1()).
				append(" ").
				append(userDTO.getUsuApellido2()==null?"":userDTO.getUsuApellido2());
				html.append("</div>");
				html.append("</div>");
			}
		} catch (BusinessException e) {
			logger.error(e);
		}
	}

	private void threats(Long user, Long entrada)
	{
		html.append("<tbody>");
		try {
			UsuariosBO usuariosBO = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
			
			QueriesForosBO queriesBO = (QueriesForosBO) SpringUtil.getInstance().getBean("QueriesForosBO");			
			MensajesDTO[] mensajes = queriesBO.getThreats(user, entrada);
			for (MensajesDTO dto : mensajes)
			{
				UsuariosDTO userDTO = usuariosBO.getByPrimaryKey(dto.getContenido().getUsuFk());
				html.append("<tr id='").
					append(dto.getContenido().getConPk()).
					append("' class='threadmessage othermessage' onclick='javascript:cargaContenido(").
					append(dto.getContenido().getConPk()).
					append(")'>");
				html.append("<td>");
				html.append(StringUtils.escapeXML(userDTO.getUsuNombre() + " " + 
						(userDTO.getUsuApellido1()==null?"":userDTO.getUsuApellido1() + " ") + 
						(userDTO.getUsuApellido2()==null?"":userDTO.getUsuApellido2())));
				html.append("</td>");
				html.append("<td>");
				html.append(new Date(dto.getContenido().getConFechaAlta()));
				html.append("</td>");
				html.append("<td>");
				html.append("</td>");
				html.append("</tr>");
			}
		} catch (BusinessException e) {
			logger.error(e);
		}
		html.append("</tbody>");
	}

	public void setEntrada(Long entrada) {
		this.entrada = entrada;
	}

	public Long getEntrada() {
		return entrada;
	}
}
