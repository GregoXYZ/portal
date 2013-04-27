package business.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.Assets;
import business.dao.AssetsDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class Assets.
 * 
 * @see business.beans.Assets
 * @author Grego
 */
public class AssetsDAOImpl implements AssetsDAO {

	private static final Log logger = LogFactory.getLog(AssetsDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public AssetsDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Assets getByPrimaryKey(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		Assets obj = (Assets) se.get(Assets.class, id);
		return obj;
	}

	public Long add(Assets obj) throws DaoException {
		Session se = this.bTx.getSession();
		if (obj.getAssFechaAlta() == null || obj.getAssFechaAlta().equals(0))
			obj.setAssFechaAlta(new Date().getTime());
		se.saveOrUpdate(obj);
		return obj.getAssPk();
	}

	public void update(Assets obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.update(obj);
	}

	public void delete(Assets obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Assets> getAssets() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Assets order by assNombre");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@Override
	public Assets getByCode(String uk) throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Assets where assNombre like '" + uk + "'");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (Assets) q.uniqueResult();
	}
}
