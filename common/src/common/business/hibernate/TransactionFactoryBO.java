package common.business.hibernate;

import common.business.BusinessException;

public interface TransactionFactoryBO {

	// Inicia una transaccion
	public BusinessTransactionBo beginTx() throws BusinessException;

	// Cierra una transaccion
	public void endTx() throws BusinessException;

}
