package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.BusquedasPersonales;
import business.dao.BusquedasPersonalesDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class BusquedasPersonales.
 * 
 * @see business.beans.BusquedasPersonales
 * @author Grego
 */
public class BusquedasPersonalesDAOImpl implements BusquedasPersonalesDAO {

	private static final Log logger = LogFactory.getLog(BusquedasPersonalesDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public BusquedasPersonalesDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	@Override
	public BusquedasPersonales getByPrimaryKey(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		BusquedasPersonales obj = (BusquedasPersonales) se.get(BusquedasPersonales.class, id);
		return obj;
	}

	@Override
	public void add(BusquedasPersonales obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.saveOrUpdate(obj);
	}

	@Override
	public void update(BusquedasPersonales obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.update(obj);
	}

	@Override
	public void delete(BusquedasPersonales obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusquedasPersonales> getBusquedasPersonales() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from BusquedasPersonales bp");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@Override
	public BusquedasPersonales getByUniqueKey(Long user, Long tag)
			throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from BusquedasPersonales bp where bp.usuFk=" + user +
				" and tagFk=" + tag);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (BusquedasPersonales) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusquedasPersonales> getByUser(Long user) throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from BusquedasPersonales bp where bp.usuFk=" + user);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
}
