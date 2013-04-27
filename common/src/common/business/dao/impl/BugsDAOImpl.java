package common.business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import common.business.DaoException;
import common.business.beans.Bugs;
import common.business.dao.BugsDAO;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class Bugs.
 * 
 * @see business.beans.Bugs
 * @author Grego
 */
public class BugsDAOImpl implements BugsDAO {

	private static final Log logger = LogFactory.getLog(BugsDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public BugsDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Bugs getByPrimaryKey(Long id) throws DaoException {
		Session se = bTx.getSession();
		Bugs obj = (Bugs) se.get(Bugs.class, id);
		return obj;
	}

	public Long add(Bugs obj) throws DaoException {
		Session se = bTx.getSession();
		if (obj.getBugEstFk() == null || obj.getBugEstFk().equals(0L))
			obj.setBugEstFk(3L);	// Informado
		if (obj.getBugPriFk() == null || obj.getBugPriFk().equals(0L))
			obj.setBugPriFk(5L);	// Sin categorizar
		se.saveOrUpdate(obj);
		return obj.getBugPk();
	}

	public void update(Bugs obj) throws DaoException {
		Session se = bTx.getSession();
		se.update(obj);
	}

	public void delete(Bugs obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Bugs> getBugs() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Bugs b order by b.bugSite, b.bugDescripcion");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
	
	@Override
	public List<?> getBugsExtended() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.getNamedQuery("Bugs.listado");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
}
