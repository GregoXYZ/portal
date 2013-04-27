package common.business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import common.business.DaoException;
import common.business.beans.Grupos;
import common.business.dao.GruposDAO;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class Grupos.
 * 
 * @see business.beans.Grupos
 * @author Grego
 */
public class GruposDAOImpl implements GruposDAO {

	private static final Log logger = LogFactory.getLog(GruposDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public GruposDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Grupos getByPrimaryKey(Long id) throws DaoException {
		Session se = bTx.getSession();
		Grupos obj = (Grupos) se.get(Grupos.class, id);
		return obj;
	}

	public Long add(Grupos obj) throws DaoException {
		Session se = bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getGruPk();
	}

	public void update(Grupos obj) throws DaoException {
		Session se = bTx.getSession();
		se.update(obj);
	}

	public void delete(Grupos obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Grupos> getGrupos() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Grupos gr order by gr.gruUkCodigo");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@Override
	public Grupos getByCode(String uk) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Grupos gr where gr.gruUkCodigo like '" + uk + "'");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (Grupos) q.uniqueResult();
	}
}
