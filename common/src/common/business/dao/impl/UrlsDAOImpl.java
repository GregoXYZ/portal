package common.business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import common.business.DaoException;
import common.business.beans.Urls;
import common.business.dao.UrlsDAO;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class Urls.
 * 
 * @see business.beans.Urls
 * @author Grego
 */
public class UrlsDAOImpl implements UrlsDAO {

	private static final Log logger = LogFactory.getLog(UrlsDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public UrlsDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Urls getByPrimaryKey(Long id) throws DaoException {
		Session se = bTx.getSession();
		Urls obj = (Urls) se.get(Urls.class, id);
		return obj;
	}

	public Urls getByCode(String uk) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Urls ur where ur.urlUkCodigo like '" + uk + "'");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (Urls) q.uniqueResult();
	}

	public Long add(Urls obj) throws DaoException {
		Session se = bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getUrlPk();
	}

	public void update(Urls obj) throws DaoException {
		Session se = bTx.getSession();
		se.update(obj);
	}

	public void delete(Urls obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Urls> getUrls() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Urls ur order by ur.urlUkCodigo");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Urls> getUserUrls(Long user, Long zona) throws DaoException {
		Session se = bTx.getSession();

		Query q = se.getNamedQuery("UrlsDAO.userUrls");
		q.setLong("user", user);
		q.setLong("zona", zona);
		return q.list();
	}
}
