package common.presentation.web.tld.relaciones;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.Constants;
import common.business.BusinessException;
import common.business.bo.RelacionesUsuariosBO;
import common.business.bo.UsuariosBO;
import common.dto.RelacionesUsuariosDTO;
import common.dto.UsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.tld.relaciones.beans.UsuarioRelacionado;
import common.util.GlobalFunctions;
import common.util.spring.SpringUtil;

public class RelacionesUsuarioTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 811909866276853137L;
	private static Log logger = LogFactory.getLog(RelacionesUsuarioTag.class);
	private String elements;
	private StringBuffer html;
	private RelacionesUsuariosBO relacionBO = (RelacionesUsuariosBO) SpringUtil.getInstance().getBean("RelacionesUsuariosBO");
	private UsuariosBO userBO = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");

	/**
	 * Over-ride del metodo doEndTag
	 */
	public int doEndTag() throws JspException {
		html = new StringBuffer();
		try {
			ServletRequest request = this.pageContext.getRequest();	
			UsuarioRelacionado[] relaciones = (UsuarioRelacionado[]) request.getAttribute(elements);

			if (relaciones!=null && relaciones.length > 0)
			{
				HttpSession session = this.pageContext.getSession();
				UserInfo user = (UserInfo) session.getAttribute("user");						
	    		
				html.append("<div class='relacionesusuario'>\n");
				for (int i = 0; i < relaciones.length && relaciones[i] != null; i++)
				{
					if (!user.getPk().equals(relaciones[i].getUsuPk()))
					{
						html.append("<div ");
						if (relaciones[i].getId() != null)
						{
							html.append("id='")
								.append(relaciones[i].getId())
								.append("' ");							
						}
						html.append("class='relacion ")
							.append(relaciones[i].getClassName() == null?"":relaciones[i].getClassName())
							.append("'>");
						creaFila(user.getPk(), relaciones[i]);
						html.append("</div>");
					}
				}
				html.append("</div>\n");
			}
			else
			{
				html.append("<div style='text-align: center;'>Aun no conoces a nadie.</div>");						
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

	public String getElements() {
		return elements;
	}

	public void setElements(String elements) {
		this.elements = elements;
	}
	
	private void creaFila(Long user, UsuarioRelacionado data) throws BusinessException, IOException
	{				
		String path = GlobalFunctions.pathAbatar(data.getUsuPk(), this.pageContext.getSession());

		UsuariosDTO userDTO = userBO.getByPrimaryKey(data.getUsuPk());		
		RelacionesUsuariosDTO relacion = relacionBO.getRelacionUsuarios(user, data.getUsuPk());

		html.append("<div class='ficha'>");
		html.append("<table width='100%'>");
		html.append("<tr>");		
		html.append("<td class='abatar' rowspan='2'>");
		html.append("<img src='");
		html.append(path);
		html.append("'/>\n");
		html.append("</td>");
		html.append("<td class='data'>");

		html.append("<h1>");
		StringBuffer opcion = new StringBuffer();
		StringBuffer mail = new StringBuffer();
		if (relacion == null)
		{
			html.append("Agregar a mis amigos");
			opcion.append("<div class='opcion'>");
			opcion.append("<a class='boton' href='javascript:void(0);' onclick='");
			opcion.append(data.getSolicitarRelacion());
			opcion.append("'>");
			opcion.append("<img src='/portal/images/insert-object.png'/> <span>Nueva relaci—n</span>");
			opcion.append("</a>");				
			opcion.append("</div>");
		}
		else if(relacion.getEstRelUsuFk().equals(Constants.RELACION_SOLICITADA))
		{
			if (relacion.getUsu1Fk().equals(user))
			{
				html.append("Relaci—n solicitada");
				opcion.append("<div class='opcion'>");
				opcion.append("<a class='boton' href='javascript:void(0);' onclick='");
				opcion.append(data.getCancelarSolicitud());
				opcion.append("'>");
				opcion.append("<img src='/portal/images/cancel.png'/> Cancelar solicitud");
				opcion.append("</a>");
				opcion.append("</div>");
			}
			else
			{
				html.append("Relaci—n solicitada");
				opcion.append("<div class='opcion'>");
				opcion.append("<a class='boton' href='javascript:void(0);' onclick='");
				opcion.append(data.getAceptarSolicitud());
				opcion.append("'>");
				opcion.append("<img src='/portal/images/dialog-apply.png'/> Aceptar solicitud");
				opcion.append("</a>");
				opcion.append("</div>");
				
				opcion.append("<div class='opcion'>");
				opcion.append("<a class='boton' href='javascript:void(0);' onclick='");
				opcion.append(data.getRechazarSolicitud());
				opcion.append("'>");
				opcion.append("<img src='/portal/images/cancel.png'/> Rechazar solicitud");
				opcion.append("</a>");	
				opcion.append("</div>");
			}
		}		
		else if (relacion.getEstRelUsuFk().equals(Constants.RELACION_ACEPTADA))
		{
			html.append("Relaci—n establecida");
			opcion.append("<div class='opcion'>");
			opcion.append("<a class='boton' href='javascript:void(0);' onclick='");
			opcion.append(data.getCancelarRelacion());
			opcion.append("'>");
			opcion.append("<img src='/portal/images/cancel.png'/> Cancelar relaci—n");
			opcion.append("</a>");						
			opcion.append("</div>");
			mail.append("(").append(userDTO.getUsuMail()).append(")");
		}			
		html.append("</h1>");

		html.append("<p>");
		html.append(data.getNombre());
		html.append("</p>");
		html.append("<p>");
		html.append(mail);
		html.append("</p>");
		html.append("</td>");
		html.append("</tr>");		

		html.append("<tr>");		
		html.append("<td>");
		html.append(opcion);
		html.append("</td>");		
		html.append("</tr>");		
		html.append("</table>");		
		html.append("</div>");		
	}
}
