package common.presentation;

import javax.servlet.http.HttpServletRequest;


public class ZoneException extends ParentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5178138593076153103L;

	public ZoneException (String msg, HttpServletRequest request) {
		super(msg, request);
	}

	public ZoneException(Throwable arg0, HttpServletRequest request) {
		super(arg0, request);
	}
}
