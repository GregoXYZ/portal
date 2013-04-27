package common.business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import common.business.DaoException;
import common.business.beans.UsuariosGrupos;
import common.business.beans.UsuariosGruposId;
import common.business.dao.UsuariosGruposDAO;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class UsuariosGrupos.
 * 
 * @see business.beans.UsuariosGrupos
 * @author Grego
 */
public class UsuariosGruposDAOImpl implements UsuariosGruposDAO {

	private static final Log logger = LogFactory.getLog(UsuariosGruposDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public UsuariosGruposDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public UsuariosGrupos getByPrimaryKey(UsuariosGruposId id) throws DaoException {
		Session se = bTx.getSession();
		UsuariosGrupos obj = (UsuariosGrupos) se.get(UsuariosGrupos.class, id);
		return obj;
	}

	public void add(UsuariosGrupos obj) throws DaoException {
		Session se = bTx.getSession();
		se.saveOrUpdate(obj);
	}

	public void update(UsuariosGrupos obj) throws DaoException {
		Session se = bTx.getSession();
		se.update(obj);
	}

	public void delete(UsuariosGrupos obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<UsuariosGrupos> getUsuariosGrupos() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from UsuariosGrupos ug order by ug.usuFK, ug.gruFK");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuariosGrupos> getByUser(Long user) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from UsuariosGrupos ug where ug.id.usuFk = " + user.toString());
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@Override
	public int deleteByUser(Long user) throws DaoException {
		Session se = bTx.getSession();
		Query query = se.createQuery("delete from UsuariosGrupos where id.usuFk = " + user);
	    int rows = query.executeUpdate();
	    
	    return rows;
	}
}
