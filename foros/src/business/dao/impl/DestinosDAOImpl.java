package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.Destinos;
import business.beans.DestinosId;
import business.dao.DestinosDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class Destinos.
 * 
 * @see business.beans.Destinos
 * @author Grego
 */
public class DestinosDAOImpl implements DestinosDAO {

	private static final Log logger = LogFactory.getLog(DestinosDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public DestinosDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Destinos getByPrimaryKey(DestinosId id) throws DaoException {
		Session se = bTx.getSession();
		Destinos obj = (Destinos) se.get(Destinos.class, id);
		return obj;
	}

	public void add(Destinos obj) throws DaoException {
		Session se = bTx.getSession();
		se.saveOrUpdate(obj);
	}

	public void update(Destinos obj) throws DaoException {
		Session se = bTx.getSession();
		se.update(obj);
	}

	public void delete(Destinos obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Destinos> getDestinos() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Destinos d order by d.usuFK, d.entFK");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Destinos> getByUser(Long user) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Destinos d where d.id.usuFk = " + user.toString());
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Destinos> getByEntrada(Long entrada) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Destinos d where d.id.entFk = " + entrada.toString());
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@Override
	public int deleteByUser(Long user) throws DaoException {
		Session se = bTx.getSession();
		Query query = se.createQuery("delete from Destinos where id.usuFk = " + user);
	    int rows = query.executeUpdate();
	    
	    return rows;
	}
}
