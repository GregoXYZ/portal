package common.business.hibernate;

import org.hibernate.Session;

import common.business.BusinessException;

public interface BusinessTransactionBo {

	public Session getSession();
	
	// Graba la transaccion
	public void commitTx() throws BusinessException;
	
	// Descarta los cambios
	public void rollbackTx() throws BusinessException;
	
}
