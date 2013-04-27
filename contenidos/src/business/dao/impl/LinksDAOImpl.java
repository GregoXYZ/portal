package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.Links;
import business.dao.LinksDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model clcar Links.
 * 
 * @see business.beans.Links
 * @author Grego
 */
public class LinksDAOImpl implements LinksDAO {

	private static final Log logger = LogFactory.getLog(LinksDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public LinksDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Links getByPrimaryKey(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		Links obj = (Links) se.get(Links.class, id);
		return obj;
	}

	public Long add(Links obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getLinPk();
	}

	public void update(Links obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.update(obj);
	}

	public void delete(Links obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Links> getLinks() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Links");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
}
