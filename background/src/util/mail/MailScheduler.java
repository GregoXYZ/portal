package util.mail;

import java.util.ResourceBundle;

import util.Scheduler;


public class MailScheduler extends Scheduler {

	// Atributos de la clase

	// Constantes

	private static ResourceBundle config;

	// Instancia del Singleton
	private static MailScheduler _instance = null;

	// Miembros de la clase
	// Inicializador de clase
	static {
		// Accedemos al fichero de properties
		config = ResourceBundle.getBundle("mail");
		_instance = new MailScheduler();
	}
	
	//private static Timer timer;

	/**
	 * Acceso al Singleton
	 * 
	 * @return ConfigProperties
	 */
	public static MailScheduler getInstance() {
		/*
		 * if (_instance == null) { _instance = new MailScheduler(); }
		 */
		return _instance;
	}
	
	/**
	 * Constructor privado
	 */
	private MailScheduler() {
		super(Long.valueOf(config.getString("MAIL_SCHEDULER_PERIOD")), new MailControler());
	}
}
