package common.business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import common.business.DaoException;
import common.business.beans.TiposAvisos;
import common.business.dao.TiposAvisosDAO;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class TiposAvisos.
 * 
 * @see business.beans.TiposAvisos
 * @author Grego
 */
public class TiposAvisosDAOImpl implements TiposAvisosDAO {

	private static final Log logger = LogFactory.getLog(TiposAvisosDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public TiposAvisosDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public TiposAvisos getByPrimaryKey(Integer id) throws DaoException {
		Session se = bTx.getSession();
		TiposAvisos obj = (TiposAvisos) se.get(TiposAvisos.class, id);
		return obj;
	}

	public Integer add(TiposAvisos obj) throws DaoException {
		Session se = bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getTipAviPk();
	}

	public void update(TiposAvisos obj) throws DaoException {
		Session se = bTx.getSession();
		se.update(obj);
	}

	public void delete(TiposAvisos obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<TiposAvisos> getTiposAvisos() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from TiposAvisos zo order by zo.zonUkCodigo");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
}
