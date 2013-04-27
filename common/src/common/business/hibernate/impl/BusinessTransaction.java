package common.business.hibernate.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import common.business.BusinessException;
import common.business.hibernate.BusinessTransactionBo;

public class BusinessTransaction implements BusinessTransactionBo {

	private static final Log logger = LogFactory.getLog(BusinessTransaction.class);
	
	private Transaction tx;
	private Session se;
	public Session getSession(){return se;}
	
	protected BusinessTransaction (Session se, Transaction tx) {
		this.se = se;
		this.tx = tx;
	}
	
	public void commitTx() throws BusinessException {
		// Valida transaccion en curso y cierra sesion
		try {
			tx.commit();
			if ( logger.isDebugEnabled() )
				logger.debug("commitTx OK");
		} catch (HibernateException he) {
			String errMsg = he.getMessage(); 
			logger.error(errMsg);
			throw new BusinessException(errMsg);
		}
	}

	public void rollbackTx() throws BusinessException {
		// Anula transacion en curso y cierra sesion
		try {
			tx.rollback();
			if ( logger.isDebugEnabled() )
				logger.debug("rollbackTx OK");
		} catch (HibernateException he) {
			String errMsg = he.getMessage(); 
			logger.error(errMsg);
			throw new BusinessException(errMsg);
		} catch (Exception e) {
			String errMsg = e.getMessage(); 
			logger.error(errMsg);
			throw new BusinessException(errMsg);
		}
	}

}
