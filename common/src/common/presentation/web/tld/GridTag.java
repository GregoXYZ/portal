package common.presentation.web.tld;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.presentation.beans.Action;
import common.presentation.beans.HtmlEvents;
import common.presentation.util.grid.Grid;
import common.presentation.util.grid.handler.Handler;
import common.util.BeanAnalyze;
import common.util.StringUtils;

public class GridTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5778692960747202435L;
	private StringBuffer html;

	private static Log logger = LogFactory.getLog(GridTag.class);

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GridTag() {
		super();
	}

	/**
	 * Over-ride del metodo doEndTag
	 */
	public int doEndTag() throws JspException {
		try {
			html = new StringBuffer();
			Handler handler = (Handler) this.pageContext.getRequest()
					.getAttribute(id);

			Grid grid = null;
			if (handler != null) 
				grid = (Grid) handler.getGrig();

			if (grid != null) {
				CreaGrid(grid);
				this.pageContext.getOut().print(html.toString());
			}
			else
			{
				logger.info("Grig " + id + " no encontrado.");
			}

			if ( logger.isDebugEnabled() )
				logger.debug(id + " Done!");

			// JspWriter salida = this.pageContext.getOut();
			// salida.println(getMenu());
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
		} catch (IntrospectionException e) {
			logger.error(e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return EVAL_PAGE;
	}

	private void CreaGrid(Grid grid) throws IntrospectionException, IllegalAccessException, InvocationTargetException
	{
		html.append("<script type='text/javascript'>"); 
		html.append("$(document).ready(function() {	");
		html.append("$('.rowactions .nav li').hover( function() {");
		html.append("var position = $(this).position();");
		html.append("$(this).find('ul:first:hidden').css( {");
		html.append("visibility :'visible',");
		html.append("display :'none',");
		html.append("top :position.top,");				
		html.append("left :position.left");				
		html.append("}).show(250);");
		html.append("}, function() {");
		html.append("$(this).find('ul:first').css({");
		html.append("visibility :'hidden',");
		html.append("display :'none'");
		html.append("}).hide('slow');");
		html.append("});");
		html.append("});");
		html.append("</script>"); 

		html.append("<div class='tablearea'>\n");
		
		if (grid.getTitle() != null)
		{
			html.append("<div class='title'>\n");
			html.append("<span class='tablecab'><h3>");
			html.append(StringUtils.escapeXML(grid.getTitle()));
			if (grid.getShowRowNums())
			{
				html.append(" (");
				html.append(grid.getData().size());
				html.append(")");				
			}
			html.append("</h3></span>\n");
			globalActions(grid);
			html.append("</div>\n");
		}
		else
		{
			globalActions(grid);	
		}						

		if ( grid.getColumns().size() > 0 )
		{			
			html.append("<div>\n");
			html.append("<table id='");
			html.append(grid.getName()==null?"":grid.getName());
			html.append("' class='");
			html.append(grid.getStyle()==null?"":grid.getStyle());
			html.append("'>\n");
			headFoot(grid);
			body(grid);
			html.append("</table>\n");
			html.append("</div>\n");
		}
		html.append("</div>\n");
	}
	
	private void body(Grid grid) throws IntrospectionException, IllegalAccessException, InvocationTargetException
	{
		html.append("<tbody>\n");
		for (int ib=0; ib<grid.getData().size();ib++)
		{
			try {
				BeanAnalyze ba = new BeanAnalyze(grid.getData().get(ib));
				html.append("<tr");
				if (grid.getRowId()!=null)
				{
					html.append(" id='");
					html.append(ba.getProperty(grid.getRowId()));
					html.append("'");					
				}
				html.append(">\n");
				for (int ib2=0; ib2<grid.getColumns().size();ib2++)
				{
					String style = null;
					if (grid.getColumns().get(ib2).getStyle() != null)
						style = (String) ba.getProperty(grid.getColumns().get(ib2).getStyle());
					
					html.append("<td");
					if (style!=null && style.trim().length()>0)
					{
						html.append(" class='").append(style).append("'");						
					}	
					html.append(">\n");
					
					if (ba.getProperty(grid.getColumns().get(ib2).getReference()) instanceof Date)
					{
						html.append("<input type='hidden' value='");
						html.append(((Date) ba.getProperty(grid.getColumns().get(ib2).getReference())).getTime());
						html.append("'/>");						
					}
					
					if ( grid.getSelectKey()!= null && grid.getSelectKey().trim().length() > 0 && ib2 == 0)
					{
						String checked = grid.getSelectedElements().indexOf(ba.getProperty(grid.getSelectKey())) == -1?"":"checked";
						html.append("<input type='hidden' value='");
						html.append(ba.getProperty(grid.getColumns().get(ib2).getReference()));
						html.append("'/>");

						html.append("<input type='checkbox' name='");
						html.append(ba.getProperty(grid.getSelectKey()));
						html.append("' value='");
						html.append(ba.getProperty(grid.getSelectKey()));
						html.append("' ");
						html.append(checked);
						html.append("/>");
					}

					// Mira si hay un action asociado a la columna
					Action action = grid.getColumns().get(ib2).getAction();
					if (action != null )
					{
						// Campo oculto para la ordenación
						html.append("<input type='hidden' value='");
						html.append(ba.getProperty(grid.getColumns().get(ib2).getReference()));
						html.append("'/>");
						
						html.append("<a href='");
						String code = StringUtils.formatText(ba.getProperty(action.getHref()));
						if (code.length() == 0)
						{
							html.append("javascript:void(0);");
							html.append("' ");
							
							html.append("onclick='");
							code = StringUtils.formatText(ba.getProperty(action.getOnClick()));
							if (code.length() == 0)
							{
								createSendData(action.getFormname(), ba.getProperty(action.getParam()), action.getParam(), action.getConfirm(), action.getMessage());
							}
							else
							{
								html.append(code);
								html.append("' ");
							}
						}
						else
							html.append(code);
						html.append("' ");
							
						code = StringUtils.formatText(ba.getProperty(action.getOnMouseOver()));
						if (code.length() > 0)
						{
							html.append("onmouseover='");
							html.append(code);
							html.append("' ");							
						}
						
						if (action.getAlt()!= null)
						{
							html.append(" alt='");							
							html.append(action.getAlt());
							html.append("' ");
						}
						if (action.getTitle()!= null)
						{
							html.append(" title='");							
							html.append(action.getTitle());
							html.append("' ");
						}
						html.append(">\n");
					}
					
					if (grid.getColumns().get(ib2).getIcon() != null )
					{
						html.append("<img style='margin-right: 5px;' src='" + ba.getProperty(grid.getColumns().get(ib2).getIcon()) + "'/>");						
					}
					
					if (ba.getProperty(grid.getColumns().get(ib2).getReference()) instanceof Date)
					{	
						html.append(StringUtils.escapeXML(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").
								format(ba.getProperty(grid.getColumns().get(ib2).getReference()))));						
					}
					else
					{
						html.append(StringUtils.escapeXML(ba.getProperty(grid.getColumns().get(ib2).getReference())));						
					}
					
					if (action != null )
					{
						html.append("</a>\n");
					}
					
					html.append("</td>\n");
				}
				if (grid.getRowActions().size()>0)
				{
					html.append("<td class='rowactions'><div class='actions'>");
					if (grid.getRowActions().size() == 1)
					{
						Action action = grid.getRowActions().get(0);
						trataAction(action, grid.getData().get(ib));
					}
					else
					{
						boolean hasActions = false;
						for (int ia=0; ia<grid.getRowActions().size(); ia++)
						{
							boolean isAction = true;
							Action action = grid.getRowActions().get(ia);
							
							HtmlEvents event = grid.getData().get(ib)==null?action:grid.getData().get(ib);
							BeanAnalyze baction = new BeanAnalyze(event);
							isAction = StringUtils.formatText(baction.getProperty(action.getOnClick())).length() > 0 ||
								StringUtils.formatText(baction.getProperty(action.getHref())).length() > 0 ||
								(action.getFormname()==null?0:action.getFormname().length()) > 0;
								
							if (isAction && !hasActions)
							{
								html.append("<ul class='nav'>");
								html.append("<li><a href='javascript:void(0);' title='Opciones' Alt='Opciones'>");
								html.append("<img src='/portal/images/gridOptions.png'/></a>");
								html.append("<ul class='menu'>");
								hasActions = true;
							}
							if (isAction)
							{
								html.append("<li>");
								trataAction(action, grid.getData().get(ib));
								html.append("</li>");
							}
						}
						if (hasActions)
						{
							html.append("</ul>");
							html.append("</li>");
							html.append("</ul>");
						}
					}
					html.append("</div></td>");
				}
				html.append("</tr>\n");
			} catch (IntrospectionException e) {
				logger.error(e.getMessage());
				throw e;
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage());
				throw e;
			} catch (InvocationTargetException e) {
				logger.error(e.getMessage());
				throw e;
			}
		}
		html.append("</tbody>\n");
	}
	
	private void globalActions(Grid grid) throws IntrospectionException, IllegalAccessException, InvocationTargetException
	{
		try {
			html.append("<span class='globalactions'>\n");
			for (int ig=0; ig<grid.getGlobalActions().size(); ig++)
			{
				Action action = grid.getGlobalActions().get(ig);
				trataGlobalAction(action);				
			}
			html.append("</span>\n");				
		} catch (IntrospectionException e) {
			logger.error(e.getMessage());
			throw e;			
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			throw e;
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	private void headFoot(Grid grid)
	{
		StringBuffer headfoot = new StringBuffer();

		if ( grid.getWithHeader() ) 
		{
			html.append("<thead>\n");
			html.append("<tr>\n");
			
			for (int ih=0; ih<grid.getColumns().size();ih++)
			{
				headfoot.append("<th>\n");
				headfoot.append(grid.getColumns().get(ih).getHeader());
				headfoot.append("</th>\n");
			}
			if ( grid.getRowActions().size() > 0 )
			{
				headfoot.append("<td class='rowactions'>\n");
				headfoot.append("</td>\n");				
			}
			html.append(headfoot);
			html.append("</tr>\n");
			html.append("</thead>\n");			
		}

		if ( grid.getWithFooter() ) 
		{
			html.append("<tfoot>\n");
			html.append("<tr>\n");
			html.append(headfoot);
			html.append("</tr>\n");
			html.append("</tfoot>\n");
		}
	}

	private void trataAction(Action action, HtmlEvents data) throws IntrospectionException, IllegalAccessException, InvocationTargetException
	{
		HtmlEvents events = data==null?action:data;
		BeanAnalyze ba = new BeanAnalyze(events);

		String idFormName = action.getParam() == null?"":action.getParam();
		Object idValue = idFormName == null?"":ba.getProperty(idFormName);						
		
		if (action.getImage() == null || action.getImage().trim().length() == 0) 
		{
			html.append("<input type='button' class='");
			html.append(action.getStyle()); 
			html.append("' name='");
			html.append(action.getName()); 
			html.append("' value='");
			html.append(action.getText());
			html.append("' onclick='");
			String code = StringUtils.formatText(ba.getProperty(action.getOnClick()));
			if (code.length() == 0)
			{
				createSendData(action.getFormname(), idValue, idFormName, action.getConfirm(), action.getMessage());
			}
			else
			{
				html.append(code);
			}
			html.append("' ");							

			code = StringUtils.formatText(ba.getProperty(action.getOnMouseOver()));
			if (code.length() > 0)
			{
				html.append("onmouseover='");
				html.append(code);
				html.append("' ");							
			}

			if (action.getAlt()!= null)
			{
				html.append(" alt='");							
				html.append(action.getAlt());
				html.append("'");
			}
			if (action.getTitle()!= null)
			{
				html.append(" title='");							
				html.append(action.getTitle());
				html.append("' ");
			}

			html.append("/>");
		} else
		{	
			html.append("<a href='");
			String code = StringUtils.formatText(ba.getProperty(action.getHref()));
			if (code.length() == 0)
			{
				html.append("javascript:void(0);");
				html.append("' ");
				html.append("onclick='");
				code = StringUtils.formatText(ba.getProperty(action.getOnClick()));
				if (code.length() == 0)
				{
					createSendData(action.getFormname(), idValue, idFormName, action.getConfirm(), action.getMessage());
				}
				else
				{
					html.append(code);
				}
				html.append("' ");
			}
			else
				html.append(code);
			html.append("' ");							
				
			code = StringUtils.formatText(ba.getProperty(action.getOnMouseOver()));
			if (code.length() > 0)
			{
				html.append("onmouseover='");
				html.append(code);
				html.append("' ");							
			}
			
			if (action.getAlt()!= null)
			{
				html.append(" alt='");							
				html.append(action.getAlt());
				html.append("'");
			}
			if (action.getTitle()!= null)
			{
				html.append(" title='");							
				html.append(action.getTitle());
				html.append("' ");
			}
			
			html.append("class='").append(action.getStyle()).append("' >\n");
			html.append("<img src='");
			html.append(action.getImage());
			html.append("'/>\n");
			html.append("</a>\n");
		}

	}

	private void trataGlobalAction(Action action) throws IntrospectionException, IllegalAccessException, InvocationTargetException
	{
		if (action.getImage() == null || action.getImage().trim().length() == 0) 
		{
			html.append("<input type='button' class='");
			html.append(action.getStyle()); 
			html.append("' name='");
			html.append(action.getName()); 
			html.append("' value='");
			html.append(action.getText());
			html.append("' onclick='");
			String code = action.getOnClick();
			if (code.length() == 0)
			{
				createSendData(action.getFormname(), null, null, action.getConfirm(), action.getMessage());
			}
			else
			{
				html.append(code);
			}
			html.append("' ");							

			code = action.getOnMouseOver();
			if (code.length() > 0)
			{
				html.append("onmouseover='");
				html.append(code);
				html.append("' ");							
			}

			if (action.getAlt()!= null)
			{
				html.append(" alt='");							
				html.append(action.getAlt());
				html.append("'");
			}
			if (action.getTitle()!= null)
			{
				html.append(" title='");							
				html.append(action.getTitle());
				html.append("' ");
			}

			html.append("/>");
		} else
		{	
			html.append("<a href='");
			String code = action.getHref();
			if (code == null || code.length() == 0)
			{
				html.append("javascript:void(0);");
				html.append("' ");
				html.append("onclick='");
				code = action.getOnClick();
				if (code == null || code.length() == 0)
				{
					createSendData(action.getFormname(), null, null, action.getConfirm(), action.getMessage());
				}
				else
				{
					html.append(code);
				}
				html.append("' ");
			}
			else
				html.append(code);
			html.append("' ");							
				
			code = action.getOnMouseOver();
			if (code != null && code.length() > 0)
			{
				html.append("onmouseover='");
				html.append(code);
				html.append("' ");							
			}
			
			if (action.getAlt()!= null)
			{
				html.append(" alt='");							
				html.append(action.getAlt());
				html.append("'");
			}
			if (action.getTitle()!= null)
			{
				html.append(" title='");							
				html.append(action.getTitle());
				html.append("' ");
			}
			
			html.append("class='").append(action.getStyle()).append("' >\n");
			html.append("<img src='");
			html.append(action.getImage());
			html.append("'/>\n");
			html.append("</a>\n");
		}

	}
	
	private void createSendData(String formName, Object idValue, String idFormName, String confirm, String message)
	{
		html.append("sendData(\""); 
		html.append(formName);
		html.append("\",\""); 
		html.append(idValue==null?"":idValue);
		html.append("\",\""); 
		html.append(idFormName==null?"":idFormName); 
		html.append("\",\"");
		html.append(confirm==null?"none":confirm);
		html.append("\",\""); 
		html.append(message==null?"":message);
		html.append("\")");		
	}
}
