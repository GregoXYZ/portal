package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.Entradas;
import business.dao.EntradasDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model clcar Entradas.
 * 
 * @see business.beans.Entradas
 * @author Grego
 */
public class EntradasDAOImpl implements EntradasDAO {

	private static final Log logger = LogFactory.getLog(EntradasDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public EntradasDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Entradas getByPrimaryKey(Long id) throws DaoException {
		Session se = bTx.getSession();
		Entradas obj = (Entradas) se.get(Entradas.class, id);
		return obj;
	}

	public Entradas getByUser(Long user) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Entradas e where e.usuFk = " + user);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (Entradas) q.uniqueResult();
	}

	public Long add(Entradas obj) throws DaoException {
		Session se = bTx.getSession();
		if (obj.getEntSubject().trim().length() == 0)
			throw new DaoException("ERROR: No se puede insertar una entrada vacia.");
		se.saveOrUpdate(obj);
		return obj.getEntPk();
	}

	public void update(Entradas obj) throws DaoException {
		Session se = bTx.getSession();
		if (obj.getEntSubject().trim().length() == 0)
			throw new DaoException("ERROR: No se puede insertar una entrada vacia.");
		se.update(obj);
	}

	public void delete(Entradas obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Entradas> getEntradas() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Entradas");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
}
