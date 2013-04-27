package common.presentation;

import javax.servlet.http.HttpServletRequest;


public class AccesoNoPermitidoException extends ParentException    
{   
	/**
	 * 
	 */
	private static final long serialVersionUID = 5165871454891621073L;

    public AccesoNoPermitidoException(Throwable arg0, HttpServletRequest request) {
		super(arg0, request);
	}
}
