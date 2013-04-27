package util.security.sessions;

import java.util.ResourceBundle;

import util.Scheduler;


public class SessionScheduler extends Scheduler{

	// Atributos de la clase

	// Constantes

	private static ResourceBundle config;

	// Instancia del Singleton
	private static SessionScheduler _instance = null;

	// Miembros de la clase
	// Inicializador de clase
	static {
		// Accedemos al fichero de properties
		config = ResourceBundle.getBundle("system");
		_instance = new SessionScheduler();
	}
	
	//private static Timer timer;

	/**
	 * Acceso al Singleton
	 * 
	 * @return ConfigProperties
	 */
	public static SessionScheduler getInstance() {
		/*
		 * if (_instance == null) { _instance = new SessionScheduler(); }
		 */
		return _instance;
	}
	
	/**
	 * Constructor privado
	 */
	private SessionScheduler() {
		super(Long.valueOf(config.getString("SESSION_CONTROLER_TIME")), new SessionControler());
	}
}
