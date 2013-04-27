package common.business.dao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import common.business.DaoException;
import common.business.beans.Usuarios;
import common.business.dao.UsuariosDAO;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class Usuarios.
 * 
 * @see business.beans.Usuarios
 * @author Grego
 */
public class UsuariosDAOImpl implements UsuariosDAO {

	private static final Log logger = LogFactory.getLog(UsuariosDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public UsuariosDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Usuarios getByPrimaryKey(Long id) throws DaoException {
		Session se = bTx.getSession();
		Usuarios obj = (Usuarios) se.get(Usuarios.class, id);
		return obj;
	}

	public Usuarios getByCode(String uk) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Usuarios us where us.usuUkCodigo like '" + uk + "'");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (Usuarios) q.uniqueResult();
	}

	public Long add(Usuarios obj) throws DaoException {
		Session se = bTx.getSession();
		obj.setUsuUkCodigo(obj.getUsuUkCodigo().toUpperCase());
		se.saveOrUpdate(obj);
		return obj.getUsuPk();
	}

	public void update(Usuarios obj) throws DaoException {
		Session se = bTx.getSession();
		obj.setUsuUkCodigo(obj.getUsuUkCodigo().toUpperCase());
		se.update(obj);
	}

	public void delete(Usuarios obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Usuarios> getUsuarios() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Usuarios us order by us.usuUkCodigo");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuarios> getUsuarios(Long user) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.getNamedQuery("Usuarios.relacionesUsuarios");
		q.setLong("user", user);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@Override
	public Usuarios getLogin(String uk, String password) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Usuarios us where Upper(us.usuUkCodigo) like '" + uk.toUpperCase() + 
				"' and usuContrasena like '" + password + 
				"' and (usuFechaBaja is null or usuFechaBaja > " + new Date().getTime() +
				") and usuActivo = true");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (Usuarios) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuarios> getByFilter(List<String> filter) throws DaoException {
		StringBuffer filtro =  new StringBuffer();

		if (filter!= null )
		{
			int ind = 0;
			filtro.append("r.usuPublicable = true AND r.usuActivo = 1 AND (r.usuFechaBaja is null OR r.usuFechaBaja < ")
			.append(new Date().getTime())
			.append(") AND (");
			for (Iterator<String> iterator = filter.iterator(); iterator.hasNext();)
			{
				String elemento = iterator.next();
				if (ind++ > 0)
					filtro.append(" OR ");
				filtro.append("Upper(r.usuNombre) like Upper('%").append(elemento).append("%') OR ");
				filtro.append("Upper(r.usuApellido1) like Upper('%").append(elemento).append("%') OR ");
				filtro.append("Upper(r.usuApellido2) like Upper('%").append(elemento).append("%')");
			}
			filtro.append(")");
			
			Session se = bTx.getSession();
			Query q = se.createQuery("from Usuarios r where " + filtro.toString() + 
					" order by r.usuNombre, r.usuApellido1, r.usuApellido2");
			if ( logger.isDebugEnabled() )
				logger.debug(q.getQueryString());
			return q.list();			
		}
		return null;
	}

	@Override
	public List<?> getEnLinea(Long user) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.getNamedQuery("Usuarios.enLinea");
		q.setLong("user", user);
		q.setLong("time", new Date().getTime());
		return q.list();
	}

}
