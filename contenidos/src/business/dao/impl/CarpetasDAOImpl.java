package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.Carpetas;
import business.dao.CarpetasDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model clcar Carpetas.
 * 
 * @see business.beans.Carpetas
 * @author Grego
 */
public class CarpetasDAOImpl implements CarpetasDAO {

	private static final Log logger = LogFactory.getLog(CarpetasDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public CarpetasDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Carpetas getByPrimaryKey(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		Carpetas obj = (Carpetas) se.get(Carpetas.class, id);
		return obj;
	}

	public Carpetas getByAsset(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Carpetas c where c.assets.assPk = " + id);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (Carpetas) q.uniqueResult();
	}

	public Long add(Carpetas obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getCarPk();
	}

	public void update(Carpetas obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.update(obj);
	}

	public void delete(Carpetas obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Carpetas> getCarpetas() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Carpetas");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@Override
	public void move(long carPK, long carFk) throws DaoException {
		Session se = this.bTx.getSession();
		Query query;
		if (carFk == 0)
			query = se.createQuery("update Carpetas set carpetas.carPk = null where carPk = " + carPK);
		else
			query = se.createQuery("update Carpetas set carpetas.carPk = " + carFk + " where carPk = " + carPK);
	    query.executeUpdate();
	}
}
