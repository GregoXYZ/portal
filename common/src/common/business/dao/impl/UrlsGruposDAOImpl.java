package common.business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import common.business.DaoException;
import common.business.beans.UrlsGrupos;
import common.business.beans.UrlsGruposId;
import common.business.dao.UrlsGruposDAO;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class UrlsGrupos.
 * 
 * @see business.beans.UrlsGrupos
 * @author Grego
 */
public class UrlsGruposDAOImpl implements UrlsGruposDAO {

	private static final Log logger = LogFactory.getLog(UrlsGruposDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public UrlsGruposDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public UrlsGrupos getByPrimaryKey(UrlsGruposId id) throws DaoException {
		Session se = bTx.getSession();
		UrlsGrupos obj = (UrlsGrupos) se.get(UrlsGrupos.class, id);
		return obj;
	}

	public void add(UrlsGrupos obj) throws DaoException {
		Session se = bTx.getSession();
		se.saveOrUpdate(obj);
	}

	public void update(UrlsGrupos obj) throws DaoException {
		Session se = bTx.getSession();
		se.update(obj);
	}

	public void delete(UrlsGrupos obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<UrlsGrupos> getUrlsGrupos() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from UrlsGrupos ug order by ug.usuFK, ug.gruFK");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UrlsGrupos> getByUrl(Long url) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from UrlsGrupos ug where ug.id.urlFk = " + url.toString());
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@Override
	public int deleteByUrl(Long url) throws DaoException {
		Session se = bTx.getSession();
		Query query = se.createQuery("delete from UrlsGrupos where id.urlFk = " + url);
	    int rows = query.executeUpdate();
	    
	    return rows;
	}
}
