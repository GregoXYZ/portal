package common.presentation;

import javax.servlet.http.HttpServletRequest;


/** Excepción general del sistema
 * @author Grego
 *
 */
public class SystemException extends ParentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7357150079058724179L;

	public SystemException (String msg, HttpServletRequest request) {
		super(new Exception(msg), request);
	}

	public SystemException(Throwable arg0, HttpServletRequest request) {
		super(arg0, request);
	}
}
