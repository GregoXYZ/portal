package common.presentation.web.security.forms;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import org.apache.struts.action.ActionForm;

import common.presentation.WebException;
import common.util.BeanAnalyze;
import common.util.StringUtils;

public abstract class BaseForm extends ActionForm //ValidatorForm //ActionForm  
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3128932396982500915L;

	public BaseForm() {
		super();
	}

	public void toUTF8() throws WebException, IntrospectionException, IllegalAccessException, InvocationTargetException
	{
		BeanAnalyze ba = new BeanAnalyze(this);
		String[] properties = ba.getProperties();
		for (String name:properties)
		{
			Object property = ba.getProperty(name);
			if (property instanceof String)
			{
				ba.setProperty(name, StringUtils._utf8_decode((String) property));
			}
		}
	}
	public abstract void limpia();
}
