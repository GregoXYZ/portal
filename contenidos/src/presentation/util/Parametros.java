package presentation.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.bo.impl.AccesosDirectosBOImpl;

import common.business.BusinessException;
import common.business.bo.ParametrosBO;
import common.dto.ParametrosDTO;
import common.util.spring.SpringUtil;

public class Parametros {

	private static Log logger = LogFactory.getLog(AccesosDirectosBOImpl.class);

	private static Parametros _instance = null;
	private Map<String, String> parametros;
	
	// Miembros de la clase
	// Inicializador de clase
	static {
		_instance = new Parametros();
	}

	private Parametros()
	{
		super();
		parametros = new HashMap<String, String>();
		try {
			ParametrosBO parametrosBO = (ParametrosBO) SpringUtil.getInstance().getBean("ParametrosBO");
			ParametrosDTO[] pts = parametrosBO.getParametros();
			for (ParametrosDTO parametro : pts)
			{
				parametros.put(parametro.getParPk(), parametro.getParValor());
			}
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Acceso al Singleton
	 * 
	 * @return Parametros
	 */
	public static Parametros get_instance() {
		return _instance;
	}
	
	public String getValor(String key)
	{
		return parametros.get(key);
	}
}
