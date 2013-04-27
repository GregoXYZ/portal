package common.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigProperties {

	// Atributos de la clase

	// Constantes

	// Log
	private static final Log logger = LogFactory.getLog(ConfigProperties.class);

	private HashMap<String, String> properties;

	// Instancia del Singleton
	private static ConfigProperties _instance;
	private static ResourceBundle config;

	// Miembros de la clase
	// Inicializador de clase
	static {
		_instance = new ConfigProperties();
	}

	/**
	 * Acceso al Singleton
	 * 
	 * @return ConfigProperties
	 */
	public static ConfigProperties getInstance() {
		/*
		 * if (_instance == null) { _instance = new ConfigProperties(); }
		 */
		return _instance;
	}

	/**
	 * Constructor privado
	 */
	private ConfigProperties() {
		// Accedemos al fichero de properties
		config = ResourceBundle.getBundle("config");

		properties = new HashMap<String, String>();
		
		for (Enumeration<?> keys = config.getKeys(); keys.hasMoreElements();) 
		{
			String key = (String) keys.nextElement();
			try 
			{
				properties.put(key, config.getString(key));
			} catch (StringIndexOutOfBoundsException ex) 
			{
				System.out.println(ex);
			}
		}
	}

	public String get(String value) {
		// ResourceBundle config = ResourceBundle.getBundle("config");
		if ( logger.isDebugEnabled() )
			logger.debug("ConfigProperties: get propery " + value);
		//return config.getString(value);
		return (String) properties.get(value);
	}
}
