package common.business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import common.business.DaoException;
import common.business.beans.Zonas;
import common.business.dao.ZonasDAO;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class Zonas.
 * 
 * @see business.beans.Zonas
 * @author Grego
 */
public class ZonasDAOImpl implements ZonasDAO {

	private static final Log logger = LogFactory.getLog(ZonasDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public ZonasDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Zonas getByPrimaryKey(Long id) throws DaoException {
		Session se = bTx.getSession();
		Zonas obj = (Zonas) se.get(Zonas.class, id);
		return obj;
	}

	public Zonas getByCode(String uk) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Zonas zo where zo.zonUkCodigo like '" + uk + "'");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());

		return (Zonas) q.uniqueResult();
	}

	public Long add(Zonas obj) throws DaoException {
		Session se = bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getZonPk();
	}

	public void update(Zonas obj) throws DaoException {
		Session se = bTx.getSession();
		se.update(obj);
	}

	public void delete(Zonas obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Zonas> getZonas() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Zonas zo order by zo.zonUkCodigo");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getUserZones(Long user) throws DaoException {
		Session se = bTx.getSession();

		Query q = se.getNamedQuery("ZonasDAO.userZones");
		q.setLong("user", user);
		return q.list();
	}
}
