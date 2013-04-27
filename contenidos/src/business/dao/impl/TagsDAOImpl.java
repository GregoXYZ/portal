package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.Tags;
import business.dao.TagsDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class Tags.
 * 
 * @see business.beans.Tags
 * @author Grego
 */
public class TagsDAOImpl implements TagsDAO {

	private static final Log logger = LogFactory.getLog(TagsDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public TagsDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Tags getByPrimaryKey(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		Tags obj = (Tags) se.get(Tags.class, id);
		return obj;
	}

	public Long add(Tags obj) throws DaoException {
		Session se = this.bTx.getSession();
		
		obj.setTagUkCodigo(obj.getTagUkCodigo().trim().toLowerCase());
		if (obj.getTagCount() == null)
				obj.setTagCount(0L);
		se.saveOrUpdate(obj);
		return obj.getTagPk();
	}

	public void update(Tags obj) throws DaoException {
		Session se = this.bTx.getSession();
		obj.setTagUkCodigo(obj.getTagUkCodigo().trim().toLowerCase());
		se.update(obj);
	}

	public void delete(Tags obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Tags> getTags() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Tags tag order by tag.tagUkCodigo");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@Override
	public Tags getByCode(String uk) throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Tags tag where Upper(tag.tagUkCodigo) like '" + uk.toUpperCase() + "'");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (Tags) q.uniqueResult();
	}

	@Override
	public void updateContadores(String tags) throws DaoException {
		Session se = this.bTx.getSession();
		Query query = se.createQuery("update Tags set tagCount = tagCount + 1 where tagPk in (" + tags + ")");
	    query.executeUpdate();
	}
}
