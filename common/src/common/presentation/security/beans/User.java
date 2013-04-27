package common.presentation.security.beans;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class User implements HttpSessionBindingListener {

	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(User.class);

	public void valueBound(HttpSessionBindingEvent arg0) {

		logger.info("User añadido a la session");
		ServletContext contexto = arg0.getSession().getServletContext();
		synchronized (contexto) {

			Integer usuarioLogados = (Integer) contexto.getAttribute("usuariosLogados");
			if (usuarioLogados == null) {
				usuarioLogados = new Integer(0);
			}
			usuarioLogados += 1;
			contexto.setAttribute("usuariosLogados", usuarioLogados);

		}

	}

	public void valueUnbound(HttpSessionBindingEvent arg0) {

		logger.info("User eliminado de session");
		ServletContext contexto = arg0.getSession().getServletContext();
		synchronized (contexto) {

			Integer usuarioLogados = (Integer) contexto.getAttribute("usuariosLogados");
			if (usuarioLogados == null) {
				usuarioLogados = new Integer(0);
			}
			usuarioLogados -= 1;
			contexto.setAttribute("usuariosLogados", usuarioLogados);

		}

	}

}