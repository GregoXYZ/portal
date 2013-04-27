package common.business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import common.business.DaoException;
import common.business.beans.GruposMenus;
import common.business.beans.GruposMenusId;
import common.business.dao.GruposMenusDAO;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class GruposMenus.
 * 
 * @see business.beans.GruposMenus
 * @author Grego
 */
public class GruposMenusDAOImpl implements GruposMenusDAO {

	private static final Log logger = LogFactory.getLog(GruposMenusDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public GruposMenusDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public GruposMenus getByPrimaryKey(GruposMenusId id) throws DaoException {
		Session se = bTx.getSession();
		GruposMenus obj = (GruposMenus) se.get(GruposMenus.class, id);
		return obj;
	}

	public void add(GruposMenus obj) throws DaoException {
		Session se = bTx.getSession();
		se.saveOrUpdate(obj);
	}

	public void update(GruposMenus obj) throws DaoException {
		Session se = bTx.getSession();
		se.update(obj);
	}

	public void delete(GruposMenus obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<GruposMenus> getGruposMenus() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from GruposMenus ug order by ug.usuFk, ug.gruFk");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GruposMenus> getByMenu(Long menu) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from GruposMenus gm where gm.id.menFk = " + menu.toString());
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@Override
	public int deleteByMenu(Long menu) throws DaoException {
		Session se = bTx.getSession();
		Query query = se.createQuery("delete from GruposMenus where id.menFk = " + menu);
	    int rows = query.executeUpdate();
	    
	    return rows;
	}
}
