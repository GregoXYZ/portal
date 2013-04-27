package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.Cuotas;
import business.dao.CuotasDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class Cuotas.
 * 
 * @see business.beans.Cuotas
 * @author Grego
 */
public class CuotasDAOImpl implements CuotasDAO {

	private static final Log logger = LogFactory.getLog(CuotasDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public CuotasDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Cuotas getByPrimaryKey(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		Cuotas obj = (Cuotas) se.get(Cuotas.class, id);
		return obj;
	}

	public void add(Cuotas obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.saveOrUpdate(obj);
	}

	public void update(Cuotas obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.update(obj);
	}

	public void delete(Cuotas obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Cuotas> getCuotas() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Cuotas cu order by cu.usuFkPk");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

}
