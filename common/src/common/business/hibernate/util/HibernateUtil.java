package common.business.hibernate.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import common.util.ConfigProperties;


public abstract class HibernateUtil {
	
	private static final String HIBERNATE_CONFIG = ConfigProperties.getInstance().get("hibernateCFG");
	private static final Log logger = LogFactory.getLog(HibernateUtil.class);

	private static final SessionFactory sessionFactory;
	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			sessionFactory = new Configuration().configure(HIBERNATE_CONFIG).buildSessionFactory();
			
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			logger.error("Could not locate SessionFactory in JNDI", ex);
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
