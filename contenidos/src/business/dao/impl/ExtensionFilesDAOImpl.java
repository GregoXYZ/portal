package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.ExtensionFiles;
import business.dao.ExtensionFilesDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class ExtensionFiles.
 * 
 * @see business.beans.ExtensionFiles
 * @author Grego
 */
public class ExtensionFilesDAOImpl implements ExtensionFilesDAO {

	private static final Log logger = LogFactory.getLog(ExtensionFilesDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public ExtensionFilesDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	@Override
	public ExtensionFiles getByPrimaryKey(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		ExtensionFiles obj = (ExtensionFiles) se.get(ExtensionFiles.class, id);
		return obj;
	}

	@Override
	public ExtensionFiles getByExtension(String uk) throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from ExtensionFiles ef where ef.extFilCode like '" + uk + "'");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());

		return (ExtensionFiles) q.uniqueResult();
	}

	@Override
	public Long add(ExtensionFiles obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getExtFilPk();
	}

	@Override
	public void update(ExtensionFiles obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.update(obj);
	}

	@Override
	public void delete(ExtensionFiles obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExtensionFiles> getExtensionFiles() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from ExtensionFiles ef order by ef.extFilCode");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
}
