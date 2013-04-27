package common.business.hibernate.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import common.business.BusinessException;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.TransactionFactoryBO;
import common.business.hibernate.util.HibernateUtil;

public class TransactionFactory implements TransactionFactoryBO {

	private static final Log logger = LogFactory.getLog(TransactionFactory.class);
	private SessionFactory sf;
	private Session se;
	
	public TransactionFactory () {
		this.sf = HibernateUtil.getSessionFactory();
		if ( logger.isDebugEnabled() )
			logger.debug("Created...");
	}
		
	public BusinessTransactionBo beginTx() throws BusinessException {
		// Abrimos sesion e iniciamos transaccion
		try {
			//se = this.sf.getCurrentSession();
			se = this.sf.openSession();
			Transaction tx = se.beginTransaction();

			BusinessTransactionBo bt = new BusinessTransaction(se, tx);
			if ( logger.isDebugEnabled() )
				logger.debug("beginTx(): SessionId=" + se.hashCode());
			return bt;
		} catch (HibernateException he) {
			logger.error(he.getMessage());
			throw new BusinessException(he.getMessage());
		}
	}

	public void endTx() throws BusinessException {
		// Cerrar sesion
		try {
			se.flush();			
		} catch(Exception e) {
			logger.error(this, e);
		}
		se.close();
	}	
}
