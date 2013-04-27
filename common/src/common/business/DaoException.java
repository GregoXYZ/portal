package common.business;

public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2382494177053885434L;

	public DaoException (Exception e) {
		super(e);
	}

	public DaoException (String msg) {
		super(msg);
	}
}
