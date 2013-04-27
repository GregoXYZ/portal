package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.TiposAssets;
import business.dao.TiposAssetsDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class TiposAssets.
 * 
 * @see business.beans.TiposAssets
 * @author Grego
 */
public class TiposAssetsDAOImpl implements TiposAssetsDAO {

	private static final Log logger = LogFactory.getLog(TiposAssetsDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public TiposAssetsDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public TiposAssets getByPrimaryKey(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		TiposAssets obj = (TiposAssets) se.get(TiposAssets.class, id);
		return obj;
	}

	public Long add(TiposAssets obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getTipAssPk();
	}

	public void update(TiposAssets obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.update(obj);
	}

	public void delete(TiposAssets obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<TiposAssets> getTiposAssets() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from TiposAssets ti");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@Override
	public TiposAssets getByCode(String uk) throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from TiposAssets as where as.tipAssUkCodigo like '" + uk + "'");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return (TiposAssets) q.uniqueResult();
	}
}
