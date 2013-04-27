package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.MimeTypes;
import business.dao.MimeTypesDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model clcar MimeTypes.
 * 
 * @see business.beans.MimeTypes
 * @author Grego
 */
public class MimeTypesDAOImpl implements MimeTypesDAO {

	private static final Log logger = LogFactory.getLog(MimeTypesDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public MimeTypesDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public MimeTypes getByPrimaryKey(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		MimeTypes obj = (MimeTypes) se.get(MimeTypes.class, id);
		return obj;
	}

	public Long add(MimeTypes obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getMimTypPk();
	}

	public void update(MimeTypes obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.update(obj);
	}

	public void delete(MimeTypes obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<MimeTypes> getMimeTypes() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from MimeTypes");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
}
