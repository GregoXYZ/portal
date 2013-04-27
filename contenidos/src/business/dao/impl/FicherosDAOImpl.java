package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.Ficheros;
import business.dao.FicherosDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model clfic Ficheros.
 * 
 * @see business.beans.Ficheros
 * @author Grego
 */
public class FicherosDAOImpl implements FicherosDAO {

	private static final Log logger = LogFactory.getLog(FicherosDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public FicherosDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Ficheros getByPrimaryKey(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		Ficheros obj = (Ficheros) se.get(Ficheros.class, id);
		return obj;
	}

	public Long add(Ficheros obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getFicPk();
	}

	public void update(Ficheros obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.update(obj);
	}

	public void delete(Ficheros obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Ficheros> getFicheros() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Ficheros fi");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@Override
	public Ficheros getByAsset(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Ficheros f where f.assets.assPk = " + id);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());

		return (Ficheros) q.uniqueResult();
	}

	@Override
	public void move(long ficPK, long carFk) throws DaoException {
		Session se = this.bTx.getSession();
		Query query;
		if (carFk == 0)
			query = se.createQuery("update Ficheros set carpetas.carPk = null where ficPk = " + ficPK);
		else
			query = se.createQuery("update Ficheros set carpetas.carPk = " + carFk + " where ficPk = " + ficPK);
	    query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ficheros> getDuplicados(Long user) throws DaoException {
		Session se = this.bTx.getSession();

		Query q = se.getNamedQuery("Contenidos.ficheros_dupicados");
		q.setLong("user", user);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return q.list();
	}
}
