package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.TagsAsset;
import business.dao.TagsAssetDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class TagsAsset.
 * 
 * @see business.beans.TagsAsset
 * @author Grego
 */
public class TagsAssetDAOImpl implements TagsAssetDAO {

	private static final Log logger = LogFactory.getLog(TagsAssetDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public TagsAssetDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	@Override
	public Long add(TagsAsset obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getTagAssPk();
	}

	@Override
	public void update(TagsAsset obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.update(obj);
	}

	@Override
	public void delete(TagsAsset obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@Override
	public TagsAsset getByPrimaryKey(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		TagsAsset obj = (TagsAsset) se.get(TagsAsset.class, id);
		return obj;
	}

	@Override
	public TagsAsset getByUniqueKey(Long tag, Long asset, Long user) throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from TagsAsset ta where ta.assFk = " + asset + 
				" and ta.tagFk = " + tag + " and ta.usuFk = " + user);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (TagsAsset) q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TagsAsset> getByAsset(Long asset) throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from TagsAsset c where c.assFk = " + asset.toString());
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TagsAsset> getByUser(Long user) throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from TagsAsset c where c.usuFk = " + user.toString());
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TagsAsset> getTagsAsset() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from TagsAsset");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
}
