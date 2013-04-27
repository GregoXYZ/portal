/**
 * 
 */
package util.listener;

import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.util.spring.SpringUtil;

import util.mail.MailScheduler;
import util.security.sessions.SessionScheduler;


/**
 * @author grego
 *
 */
public class AppStartup implements ServletContextListener {

	private final Log logger = LogFactory.getLog(this.getClass());
	
	Timer mailControler;
	Timer sessionControler;

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		mailControler.cancel();
		mailControler.purge();

		sessionControler.cancel();
		sessionControler.purge();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// Accedemos a ServletContext
		ServletContext sc = arg0.getServletContext();
		//sc.setAttribute(org.apache.struts.Globals.LOCALE_KEY, LocaleConstants.DEFAULT_LOCALE);
		
		// Iniciamos factoria de Spring
		//SpringUtil.init();
		SpringUtil.getInstance();
		
		// Cargamos mensajes multi-idioma
		String name = sc.getInitParameter("messages");
		logger.info("Messages=" + name);
		//ResourceBundleMessageProvider.install(name);
		
		// Cargamos entorno inicial
		// Arranco el scheduler de mails
		mailControler = MailScheduler.getInstance().getTimer();
		//  TODO Arrancar el scheduler de control de sessiones
		// Arranco el scheduler de control de sessiones
		sessionControler = SessionScheduler.getInstance().getTimer();
	}

}
