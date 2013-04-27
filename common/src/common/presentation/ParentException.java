package common.presentation;

import javax.servlet.http.HttpServletRequest;

import common.presentation.util.WebInfoHelper;

public abstract class ParentException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1424580508106637385L;

	protected ParentException(String msg)
	{
		super(msg);		
	}

	protected ParentException(Throwable arg0)
	{
		super(arg0);		
	}
	
	protected ParentException(String msg, HttpServletRequest request) {
		super(msg);

		WebInfoHelper.getInstance().setWebError(request, msg);
	}

	protected ParentException(Throwable arg0, HttpServletRequest request) {
		super(arg0);

		if (arg0 instanceof SystemException)
			WebInfoHelper.getInstance().setSystemError(request, arg0);
		else
			WebInfoHelper.getInstance().setWebError(request, arg0);
	}
}
