package common.business.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import common.Constants;
import common.business.DaoException;
import common.business.beans.Avisos;
import common.business.beans.RelacionesUsuarios;
import common.business.dao.RelacionesUsuariosDAO;
import common.business.hibernate.BusinessTransactionBo;
import common.dto.RelacionesUsuariosDTO;

/**
 * Home object for domain model class RelacionesUsuarios.
 * 
 * @see RelacionesUsuariosDTO.beans.RelacionesUsuarios
 * @author Grego
 */
public class RelacionesUsuariosDAOImpl implements RelacionesUsuariosDAO {

	private static final Log logger = LogFactory.getLog(RelacionesUsuariosDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public RelacionesUsuariosDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public RelacionesUsuarios getByPrimaryKey(Long id) throws DaoException {
		Session se = bTx.getSession();
		RelacionesUsuarios obj = (RelacionesUsuarios) se.get(RelacionesUsuarios.class, id);
		return obj;
	}

	public Long add(RelacionesUsuarios obj) throws DaoException {
		Session se = bTx.getSession();
		if (obj.getEstRelUsuFk().equals(Constants.RELACION_ACEPTADA))
			obj.setRelUsuFechaRelacion(new Date().getTime());
		else
			obj.setRelUsuFechaRelacion(null);
		se.saveOrUpdate(obj);
		trataAvisos(obj);
		return obj.getRelUsuPk();
	}

	public void update(RelacionesUsuarios obj) throws DaoException {
		Session se = bTx.getSession();
		if (obj.getEstRelUsuFk().equals(Constants.RELACION_ACEPTADA))
			obj.setRelUsuFechaRelacion(new Date().getTime());
		else
			obj.setRelUsuFechaRelacion(null);
		se.update(obj);
		trataAvisos(obj);
	}

	public void delete(RelacionesUsuarios obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<RelacionesUsuarios> getRelacionesUsuarios() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from RelacionesUsuarios e order by e.relUsuCodigo");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@Override
	public RelacionesUsuarios getRelacionUsuarios(Long user1, Long user2)
			throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from RelacionesUsuarios r where (" +
				"r.usu1Fk = " + user1 + " AND r.usu2Fk = " + user2 + 
				") OR (r.usu1Fk = " + user2 + " AND r.usu2Fk = " + user1 + ")");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (RelacionesUsuarios) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RelacionesUsuarios> getRelacionByEstado(Long user, Long estado)
			throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from RelacionesUsuarios r where (" +
				"r.usu1Fk = " + user + " OR r.usu2Fk = " + user + 
				") AND estRelUsuFk = " + estado);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
	
	private void trataAvisos(RelacionesUsuarios obj) throws DaoException
	{

		if (obj.getEstRelUsuFk().equals(Constants.RELACION_SOLICITADA))
		{
			AvisosDAOImpl avisosDAO = new AvisosDAOImpl(bTx);
			Avisos aviso = new Avisos(obj.getUsu1Fk(), obj.getUsu2Fk(), 
					new Date().getTime(), Constants.AVISO_AMISTAD, 
					obj.getRelUsuPk(), 1); 
			avisosDAO.add(aviso);			
		}
		else if (obj.getEstRelUsuFk().equals(Constants.RELACION_ACEPTADA))
		{
			AvisosDAOImpl avisosDAO = new AvisosDAOImpl(bTx);
			Avisos aviso = new Avisos(obj.getUsu2Fk(), obj.getUsu1Fk(), 
					new Date().getTime(), Constants.AVISO_AMISTAD_ACEPTDA, 
					obj.getRelUsuPk(), 1); 
			avisosDAO.add(aviso);						
		}	
	}
}
