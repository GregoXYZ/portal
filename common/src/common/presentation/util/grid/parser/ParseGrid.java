package common.presentation.util.grid.parser;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import common.presentation.beans.Action;
import common.presentation.util.grid.Column;
import common.presentation.util.grid.Grid;
import common.util.BeanAnalyze;

public class ParseGrid implements ContentHandler {

	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(ParseGrid.class);	
	
	private Object currentElement;
	private String chararters;
	private Grid grid;
	Column column;
	Action action;
	
	@Override
	public void startDocument() throws SAXException {		
		grid = new Grid();
		List<Column> columns = new ArrayList<Column>();
		List<Action> rowactions = new ArrayList<Action>();
		List<Action> globalactions = new ArrayList<Action>();
		grid.setRowActions(rowactions);
		grid.setGlobalActions(globalactions);
		grid.setColumns(columns);
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		Action l_action = null;
		if ( localName.equals("grid") )
		{
			grid.setName(attributes.getValue("name"));
			String attribute = attributes.getValue("title");
			grid.setStyle(attributes.getValue("style"));
			grid.setTitle(attribute == null?"":attribute);
			attribute = attributes.getValue("showAlertNoData");
			grid.setShowAlertNoData(Boolean.valueOf(attribute == null?false:true));
			attribute = attributes.getValue("editable");
			grid.setEditable(Boolean.valueOf(attribute == null?false:true));
		}

		if ( localName.equals("column") )
		{
			column = new Column();
			loadElement(column, attributes);
			currentElement = column;
		}

		if ( localName.equals("action") || 
			localName.equals("row-action") || 
			localName.equals("global-action"))
		{
			l_action = new Action();
			loadElement(l_action, attributes);
			action = l_action;
		}
		
		if ( currentElement instanceof Column && localName.equals("action"))
		{
			/*if ( action.getFormname() == null )
				logger.error("El action " + action.getName() + " de la columna " + 
						((Column) currentElement).getId() + " no tiene valor (se ignora)");
			else*/
			((Column) currentElement).setAction(action);

		}

		if ( localName.equals("row-action") || localName.equals("global-action") )
		{
			currentElement = action;
		}		
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {		
		chararters = String.valueOf(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		
		if ( localName.equals("column") )
		{
			grid.getColumns().add(column);
			currentElement = null;
		} else if ( localName.equals("row-action") )
		{
			grid.getRowActions().add(action);
			currentElement = null;
		} else if ( localName.equals("global-action") )
		{
			grid.getGlobalActions().add(action);
			currentElement = null;
		} else if ( localName.equals("select-key") )
		{
			grid.setSelectKey(chararters);
			grid.setSelectedElements(new ArrayList<Object>());
			currentElement = null;
		} else if ( localName.equals("with-header") )
		{
			grid.setWithHeader(Boolean.valueOf(chararters));
			currentElement = null;
		} else if ( localName.equals("with-footer") )
		{
			grid.setWithFooter(Boolean.valueOf(chararters));
			currentElement = null;
		} else if ( localName.equals("showRowNums") )
		{
			grid.setShowRowNums(Boolean.valueOf(chararters));
			currentElement = null;
		}
		else if ( localName.equals("rowId") )
		{
			grid.setRowId(chararters);
			currentElement = null;
		} else
		{
			if ( currentElement != null )
			{
				if ( currentElement instanceof Action || currentElement instanceof Column )
				{
					try {
						BeanAnalyze ba = new BeanAnalyze(currentElement);
						if ( currentElement instanceof Column && localName.equals("action") )
						{
							if ( logger.isDebugEnabled() )
								logger.debug("Action ya asignado");
						}
						else
							ba.setProperty(localName, chararters);
					} catch (IntrospectionException e) {
						logger.error(e.getMessage());
					} catch (IllegalAccessException e) {
						logger.error(e.getMessage());
					} catch (InvocationTargetException e) {
						logger.error(e.getMessage());
					} 
				}
			}
		}
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public Grid getGrid() {
		return grid;
	}
	
	private void loadElement(Object element, Attributes attributes)
	{
		try {
			BeanAnalyze ba = new BeanAnalyze(element);
			for (int ind=0 ; ind < attributes.getLength(); ind++)
			{
				ba.setProperty(attributes.getQName(ind), attributes.getValue(attributes.getQName(ind)));
			}
		} catch (IntrospectionException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (InvocationTargetException e) {
			logger.error(e);
		}		
	}
}
