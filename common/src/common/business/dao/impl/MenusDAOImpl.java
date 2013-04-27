package common.business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import common.business.DaoException;
import common.business.beans.Menus;
import common.business.dao.MenusDAO;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class Menus.
 * 
 * @see business.beans.Menus
 * @author Grego
 */
public class MenusDAOImpl implements MenusDAO {

	private static final Log logger = LogFactory.getLog(MenusDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public MenusDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Menus getByPrimaryKey(Long id) throws DaoException {
		Session se = bTx.getSession();
		Menus obj = (Menus) se.get(Menus.class, id);
		return obj;
	}

	public Long add(Menus obj) throws DaoException {
		Session se = bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getMenPk();
	}

	public void update(Menus obj) throws DaoException {
		Session se = bTx.getSession();
		se.update(obj);
	}

	public void delete(Menus obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Menus> getMenus() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Menus me order by me.menDescripcion");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menus> getCaps(Long user) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.getNamedQuery("MenusDAO.findCap");
		q.setLong("user", user);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menus> getMenu(Long user, Long parent) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.getNamedQuery("MenusDAO.findMenu");
		q.setLong("user", user);
		q.setLong("parent", parent);
		return q.list();
	}

	@Override
	public Menus getByCode(String uk)  throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Menus me where me.menUkCodigo like '" + uk + "'");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (Menus) q.uniqueResult();
	}
}
