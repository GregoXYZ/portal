package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.AccesosDirectos;
import business.dao.AccesosDirectosDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class AccesosDirectos.
 * 
 * @see business.beans.AccesosDirectos
 * @author Grego
 */
public class AccesosDirectosDAOImpl implements AccesosDirectosDAO {

	private static final Log logger = LogFactory.getLog(AccesosDirectosDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public AccesosDirectosDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public AccesosDirectos getByPrimaryKey(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		AccesosDirectos obj = (AccesosDirectos) se.get(AccesosDirectos.class, id);
		return obj;
	}

	public Long add(AccesosDirectos obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getAccDirPk();
	}

	public void update(AccesosDirectos obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.update(obj);
	}

	public void delete(AccesosDirectos obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<AccesosDirectos> getAccesosDirectos() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from AccesosDirectos ad");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
}
