package common.presentation;

import javax.servlet.http.HttpServletRequest;


public class NoBrowserException extends ParentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 121662285342920653L;

	public NoBrowserException (String msg, HttpServletRequest request) {
		super(msg, request);
	}

	public NoBrowserException(Throwable arg0, HttpServletRequest request) {
		super(arg0, request);
	}
}
