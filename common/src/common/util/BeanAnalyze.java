package common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BeanAnalyze {

	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(BeanAnalyze.class);
	private Object object;
	private BeanInfo bi;

	public BeanAnalyze(Object object) throws IntrospectionException {
		super();
		this.object = object;
		this.bi = Introspector.getBeanInfo(object.getClass());
	}

	public Object getProperty(String property) throws IllegalAccessException, InvocationTargetException {
		Object result = null;
		
		if (property != null)
		{
			try {
				PropertyDescriptor[] properties = bi.getPropertyDescriptors();

				for (int i = 0; i < properties.length; i++) {
					PropertyDescriptor prp = properties[i];
					if (prp.getName().equals(property)) {
						if (prp.getReadMethod() != null)
						{
							Method method = prp.getReadMethod();
							result = method.invoke(object, (Object[]) null);
						}
						else
							result = null;
						break;
					}
				}
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage());
				throw e;
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage());
				throw e;
			} catch (InvocationTargetException e) {
				logger.error(e.getTargetException().getMessage());
				throw e;
			}			
		}

		return result;
	}

	public void setProperty(String property, Object value) throws IllegalAccessException, InvocationTargetException {
		try {
			PropertyDescriptor[] properties = bi.getPropertyDescriptors();

			for (int i = 0; i < properties.length; i++) {
				PropertyDescriptor prp = properties[i];
				if (prp.getName().equals(property)) {
					Method method = prp.getWriteMethod();
					method.invoke(object, new  Object[]{ value });
					break;
				}
			}
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			throw e;
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			throw e;
		} catch (InvocationTargetException e) {
			logger.error(e.getTargetException().getMessage());
			throw e;
		}
	}

	public String[] getProperties()
	{
		PropertyDescriptor[] properties = bi.getPropertyDescriptors();
		String[] sProperties = new String[properties.length];
		
		for (int i = 0; i < properties.length; i++) {
			sProperties[i] = properties[i].getName();
		}

		return sProperties;
	}
}
