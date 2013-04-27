/**
 * 
 */
package presentation.util.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.util.spring.SpringUtil;

/**
 * @author grego
 *
 */
public class AppStartup implements ServletContextListener {

	private final Log logger = LogFactory.getLog(this.getClass());

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// Accedemos a ServletContext
		ServletContext sc = arg0.getServletContext();
		
		// Iniciamos factoria de Spring
		//SpringUtil.init();
		SpringUtil.getInstance();
		
		// Cargamos mensajes multi-idioma
		String name = sc.getInitParameter("messages");
		logger.info("Messages=" + name);
		//ResourceBundleMessageProvider.install(name);
		
		// Cargamos entorno inicial
	}

}
