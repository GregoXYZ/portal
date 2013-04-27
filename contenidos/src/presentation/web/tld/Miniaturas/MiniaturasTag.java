package presentation.web.tld.Miniaturas;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.util.StringUtils;

import presentation.web.tld.Miniaturas.Beans.Miniatura;

public class MiniaturasTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7331836125262143618L;
	private static Log logger = LogFactory.getLog(MiniaturasTag.class);
	private String id;
	private String type;
	private String elements;
	private Integer width;
	private Integer height;

	/**
	 * Over-ride del metodo doEndTag
	 */
	@SuppressWarnings("unchecked")
	public int doEndTag() throws JspException {
		StringBuffer html = new StringBuffer();

		ServletRequest request = this.pageContext.getRequest();	
		List<Miniatura> actions = (List<Miniatura>) request.getAttribute(elements);
		
		try {
			if ( actions != null )
			{
				if (type.toLowerCase().equals("mural"))
					html.append(mural(actions));
				else if (type.toLowerCase().equals("scroll"))
					html.append(scroll(actions));

				
				this.pageContext.getOut().print(html.toString());
			}
		} catch (IOException e) {
			logger.error(e);
		}
		// Acabamos tag y continuamos pagina
		return EVAL_PAGE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getElements() {
		return elements;
	}

	public void setElements(String elements) {
		this.elements = elements;
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
	
	private StringBuffer mural(List<Miniatura> actions)
	{
		StringBuffer html = new StringBuffer();
		for (Miniatura element : actions)
		{
			html.append("<div class='");
			html.append(element.getStyle()==null?"":element.getStyle());
			html.append("' id='");					
			html.append(element.getId()==null?"":element.getId());
			html.append("'>");
			html.append("<a href='");
			html.append(element.getHref()==null?"javascript:void(0);":element.getHref());
			html.append("' onclick='");
			html.append(element.getOnClick()==null?"javascript:void(0);":element.getOnClick());
			html.append("'>");
			html.append("<div id='");
			html.append(element.getImageId()==null?"":element.getImageId());
			html.append("'>");					
			html.append(setImage(element));
			html.append("</div>");
			html.append("<p>");
			html.append(StringUtils.escapeXML(element.getText()));
			html.append("</p>");
			html.append("</a>");
			html.append("</div>");
		}
		
		return html;
	}
	
	private StringBuffer scroll(List<Miniatura> actions)
	{
		StringBuffer html = new StringBuffer();
		html.append("<ul id='");					
		html.append(this.id);
		html.append("'>");
		int i = 0;			
		for (Iterator<Miniatura> iterator = actions.iterator(); iterator.hasNext();i++)
		{
			Miniatura element = iterator.next();
			html.append("<li id='");					
			html.append(element.getId()==null?"":element.getId());
			html.append("'>");
			html.append("<a id='");
			html.append(element.getImageId()==null?"":element.getImageId());
			html.append("' href='");
			html.append(element.getHref()==null?"javascript:void(0);":element.getHref());
			html.append("' onclick='");
			html.append(element.getOnClick()==null?"javascript:void(0);":element.getOnClick());
			html.append("'>");
			html.append(setImage(element));
			html.append("</a>");
			html.append("<p>");
			html.append(StringUtils.escapeXML(element.getText()));
			html.append("</p>");
			html.append("</li>");
		}
		html.append("</ul>");

		return html;
	}
	
	private StringBuffer setImage(Miniatura element)
	{
		StringBuffer html = new StringBuffer();
		html.append("<img src='");
		html.append(element.getImage());						
		if (this.width != null)
		{
			html.append("' width='");
			html.append(this.width);
		}
		if (this.height != null)
		{
			html.append("' height='");
			html.append(this.height);
		}
		html.append("' onmouseover='");
		html.append(element.getOnMouseOver()==null?"javascript:void(0);":element.getOnMouseOver());
		html.append("'>");						
		html.append("</img>");

		return html;
	}
}
