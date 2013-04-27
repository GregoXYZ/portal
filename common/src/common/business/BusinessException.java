package common.business;

/** Excepción general de las clases de negocio
 * @author Grego
 *
 */
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7286412971144129931L;

	public BusinessException (Exception e) {
		super(e);
	}

	public BusinessException (String msg) {
		super(msg);
	}	
}
