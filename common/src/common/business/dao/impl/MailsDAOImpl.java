package common.business.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import common.business.DaoException;
import common.business.beans.Mails;
import common.business.dao.MailsDAO;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class Mails.
 * 
 * @see business.beans.MailsDTO
 * @author Grego
 */
public class MailsDAOImpl implements MailsDAO {

	private static final Log logger = LogFactory.getLog(MailsDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public MailsDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	@Override
	public Mails getByPrimaryKey(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		Mails obj = (Mails) se.get(Mails.class, id);
		return obj;
	}

	@Override
	public Long add(Mails obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getMaiPk();
	}

	@Override
	public void update(Mails obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.update(obj);
	}

	@Override
	public int updateGroup(Long usuOrigen, Long usuDestino, Integer tipoAviso) throws DaoException {
		Session se = this.bTx.getSession();
		Query query = se.createQuery("UPDATE Mails m" +
				" SET m.maiFechaEnvio = " + new Date().getTime() +
				" WHERE	m.maiFechaEnvio IS null" +
				" AND EXISTS (SELECT '1' FROM Avisos a" +
				" WHERE	a.usuFkOrigen = " + usuOrigen + 
				" AND a.usuFkDestino = " + usuDestino +
				" AND a.tipAviFk = " + tipoAviso +
				")");
	    int rows = query.executeUpdate();
	    
	    return rows;
	}

	@Override
	public void delete(Mails obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mails> getMails() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Mails ma");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@Override
	public List<?> getMailsPendientes() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.getNamedQuery("Mails.pendientes");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@Override
	public List<Mails> getMailsByUser(Long user) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
}
