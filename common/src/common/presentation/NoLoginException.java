package common.presentation;

import javax.servlet.http.HttpServletRequest;


public class NoLoginException extends ParentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8606495909841264958L;

	public NoLoginException (String msg, HttpServletRequest request) {
		super(msg, request);
	}

	public NoLoginException(Throwable arg0, HttpServletRequest request) {
		super(arg0, request);
	}
}
