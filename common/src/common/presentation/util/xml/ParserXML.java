package common.presentation.util.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class ParserXML {

	private static Log logger = LogFactory.getLog(ParserXML.class);
	
	// Instancia del Singleton
	private static ParserXML _instance = null;
	
	// Miembros de la clase
	// Inicializador de clase
	static {
		_instance = new ParserXML();
	}
	
	/**
	 * Acceso al Singleton
	 * @return ParserXML
	 */
	public static ParserXML getInstance () {
		return _instance;
	}

//	public ParserXML()
//	{
//		super();
//	}
	
	public void Parse(String xml, ContentHandler handler) throws FileNotFoundException, IOException {
		try {
			// Creamos la factoria de parseadores por defecto
			XMLReader reader = XMLReaderFactory.createXMLReader();
			// Añadimos nuestro manejador al reader
			reader.setContentHandler(handler);         
			reader.parse(new InputSource(new FileInputStream(xml)));			
		} catch (SAXException e) {
			logger.error(e.getMessage());
		}
	}
}
