package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.Parametros;
import business.dao.ParametrosDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class Parametros.
 * 
 * @see business.beans.Parametros
 * @author Grego
 */
public class ParametrosDAOImpl implements ParametrosDAO {

	private static final Log logger = LogFactory.getLog(ParametrosDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public ParametrosDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Parametros getByPrimaryKey(String id) throws DaoException {
		Session se = this.bTx.getSession();
		Parametros obj = (Parametros) se.get(Parametros.class, id);
		return obj;
	}

	public void add(Parametros obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.saveOrUpdate(obj);
	}

	public void update(Parametros obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.update(obj);
	}

	public void delete(Parametros obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Parametros> getParametros() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Parametros pa order by pa.parPk");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
}
