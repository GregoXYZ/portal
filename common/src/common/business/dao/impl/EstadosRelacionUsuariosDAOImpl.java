package common.business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import common.business.DaoException;
import common.business.beans.EstadosRelacionUsuarios;
import common.business.dao.EstadosRelacionUsuariosDAO;
import common.business.hibernate.BusinessTransactionBo;
import common.dto.EstadosRelacionUsuariosDTO;

/**
 * Home object for domain model class EstadosRelacionUsuarios.
 * 
 * @see EstadosRelacionUsuariosDTO.beans.EstadosRelacionUsuarios
 * @author Grego
 */
public class EstadosRelacionUsuariosDAOImpl implements EstadosRelacionUsuariosDAO {

	private static final Log logger = LogFactory.getLog(EstadosRelacionUsuariosDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public EstadosRelacionUsuariosDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public EstadosRelacionUsuarios getByPrimaryKey(Long id) throws DaoException {
		Session se = bTx.getSession();
		EstadosRelacionUsuarios obj = (EstadosRelacionUsuarios) se.get(EstadosRelacionUsuarios.class, id);
		return obj;
	}

	public Long add(EstadosRelacionUsuarios obj) throws DaoException {
		Session se = bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getEstRelUsuPk();
	}

	public void update(EstadosRelacionUsuarios obj) throws DaoException {
		Session se = bTx.getSession();
		se.update(obj);
	}

	public void delete(EstadosRelacionUsuarios obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<EstadosRelacionUsuarios> getEstadosRelacionUsuarios() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from EstadosRelacionUsuarios e order by e.estRelUsuCodigo");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
}
