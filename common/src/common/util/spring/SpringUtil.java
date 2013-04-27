package common.util.spring;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 
 * @author Tang.Lei <justinlei@gmail.com> DATE:2007-3-19-12:50:47
 * @version 1.0
 */
public abstract class SpringUtil {

	// Fichero de configuracion basico
	public static final String BASE_RESOURCE_FILE = "spring/beans.xml";
	
	// Instancia
	private static XmlBeanFactory factory;
	
	// Constructor estatico
	static {
		Resource res = new ClassPathResource(BASE_RESOURCE_FILE);
		factory = new XmlBeanFactory(res);
	}
	
	// Acceso a la instancia
	public static XmlBeanFactory getInstance () {
		return factory;
	}
}
