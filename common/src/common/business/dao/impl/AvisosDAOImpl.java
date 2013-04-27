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
import common.business.beans.Mails;
import common.business.beans.Usuarios;
import common.business.dao.AvisosDAO;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class Avisos.
 * 
 * @see business.beans.Avisos
 * @author Grego
 */
public class AvisosDAOImpl implements AvisosDAO {

	private static final Log logger = LogFactory.getLog(AvisosDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public AvisosDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	@Override
	public Avisos getByPrimaryKey(Long id) throws DaoException {
		Session se = bTx.getSession();
		Avisos obj = (Avisos) se.get(Avisos.class, id);
		return obj;
	}

	@Override
	public Avisos getByPrimaryKey(Long id, Long user) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Avisos a where usuFkDestino = " + user + " and aviPk = " + id);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (Avisos) q.uniqueResult();
	}

	@Override
	public Long add(Avisos obj) throws DaoException {
		Session se = bTx.getSession();
		UsuariosDAOImpl userDAO = new UsuariosDAOImpl(bTx);
		if (obj.getUsuFkOrigen() == null)
		{
			Usuarios user = userDAO.getByCode(Constants.SYSTEM_USER);
			if (user != null)
				obj.setUsuFkOrigen(user.getUsuPk());
		}
		se.saveOrUpdate(obj);
		
		TiposAvisosDAOImpl tipoDAO =  new TiposAvisosDAOImpl(bTx);
		if (tipoDAO.getByPrimaryKey(obj.getTipAviFk()).isTipAviEnviaMail() &&
				userDAO.getByPrimaryKey(obj.getUsuFkDestino()).isUsuRecibeAviso())
		{
			MailsDAOImpl mailDAO = new MailsDAOImpl(bTx);
			mailDAO.add(new Mails(obj.getAviPk(), new Date().getTime()));
		}
		return obj.getAviPk();
	}

	@Override
	public void update(Avisos obj) throws DaoException {
		Session se = bTx.getSession();
		se.update(obj);
	}

	@Override
	public void delete(Avisos obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@Override
	public int delete(Long user, Long tipo) throws DaoException {
		Session se = bTx.getSession();
		Query query = se.createQuery("delete from Avisos where usuFkOrigen = " + user + " and tipAviFk = " + tipo);
	    int rows = query.executeUpdate();
	    
	    return rows;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Avisos> getAvisos() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Avisos a order by a.aviFEnvio");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Avisos> getByUser(Long user) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Avisos a where a.usuFkDestino = " + user + 
				"and (a.aviFLeido is null or a.aviFLeido > " + new Date().getTime() + ") order by a.aviFEnvio Desc");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
}
