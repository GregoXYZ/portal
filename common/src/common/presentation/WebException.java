package common.presentation;

import javax.servlet.http.HttpServletRequest;

/** Excepción general de las clases de presentación
 * @author Grego
 *
 */
public class WebException extends ParentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7357150079058724179L;

	public WebException (String msg, HttpServletRequest request) {
		super(msg, request);
	}

	public WebException(Throwable arg0, HttpServletRequest request) {
		super(arg0, request);
	}
}
