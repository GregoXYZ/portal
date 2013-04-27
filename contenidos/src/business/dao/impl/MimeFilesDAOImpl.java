package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.MimeFiles;
import business.dao.MimeFilesDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class MimeFiles.
 * 
 * @see business.beans.MimeFiles
 * @author Grego
 */
public class MimeFilesDAOImpl implements MimeFilesDAO {

	private static final Log logger = LogFactory.getLog(MimeFilesDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public MimeFilesDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public MimeFiles getByPrimaryKey(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		MimeFiles obj = (MimeFiles) se.get(MimeFiles.class, id);
		return obj;
	}

	public MimeFiles getByExtension(String uk) throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from MimeFiles mf where mf.mimFilExtension like '" + uk + "'");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());

		return (MimeFiles) q.uniqueResult();
	}

	public Long add(MimeFiles obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getMimFilPk();
	}

	public void update(MimeFiles obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.update(obj);
	}

	public void delete(MimeFiles obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<MimeFiles> getMimeFiles() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from MimeFiles mf order by mf.mimFilExtension");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
}
