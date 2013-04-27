package presentation.web.tld;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.dto.TagsNubeDTO;

public class TagsNubeTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3721540384274107335L;
	private static Log logger = LogFactory.getLog(TagsNubeTag.class);
	private String elements;
	private String style;

	/**
	 * Over-ride del metodo doEndTag
	 */
	@SuppressWarnings("unchecked")
	public int doEndTag() throws JspException {
		StringBuffer html = new StringBuffer();
		double total = 0.00;

		ServletRequest request = this.pageContext.getRequest();	
		List<TagsNubeDTO> dto = (List<TagsNubeDTO>) request.getAttribute(elements);
		
		//html.append("<div id='tagnube'>");
		for (TagsNubeDTO tagNube:dto)
		{
			total += tagNube.getContador();
		}
		
		for (TagsNubeDTO tagNube:dto)
		{
			double fontSize = ((22.00 * tagNube.getContador()) / total) + 10.00;
			html.append("<div class='").append(style==null?"":style).append("'>");
			html.append("<a id='").append(tagNube.getTagPk()).append("' style='font-size: ").append(fontSize).append("pt;'");
			html.append("href='javascript:void(0);' onclick='javascript:buscaTag(").append(tagNube.getTagPk()).append(");'>");
			html.append(tagNube.getTagUkCodigo());
			html.append("</a>");
			html.append("</div>");
		}
		//html.append("</div>");
		
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

	public void setElements(String elements) {
		this.elements = elements;
	}

	public String getElements() {
		return elements;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStyle() {
		return style;
	}
}
