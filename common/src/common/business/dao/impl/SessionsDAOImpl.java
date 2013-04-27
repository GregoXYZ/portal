package common.business.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import common.business.DaoException;
import common.business.beans.Sessions;
import common.business.dao.SessionsDAO;
import common.business.hibernate.BusinessTransactionBo;

public class SessionsDAOImpl implements SessionsDAO {
	private static final Log logger = LogFactory.getLog(SessionsDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public SessionsDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Sessions getByPrimaryKey(Long id) throws DaoException {
		Session se = bTx.getSession();
		Sessions obj = (Sessions) se.get(Sessions.class, id);
		return obj;
	}

	public Sessions getByCode(String uk) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Sessions se where se.sessionId like '" + uk + "' and se.validSession ='S'");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (Sessions) q.uniqueResult();
	}

	@Override
	public Sessions getByAddress(String uk, String address) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Sessions se where se.sessionId like '" + uk + 
				"' and se.address like '" + address +"'");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());

		return (Sessions) q.uniqueResult();
	}

	public Long add(Sessions obj) throws DaoException {
		Session se = bTx.getSession();
		se.saveOrUpdate(obj);
		return obj.getSessionPk();
	}

	public void update(Sessions obj) throws DaoException {
		Session se = bTx.getSession();
		se.update(obj);
	}

	public void delete(Sessions obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Sessions> getSessions() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Sessions se");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sessions> getUserSessions(Long user) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Sessions se where se.userId = " + user);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sessions> getActiveSessions() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Sessions se where se.validSession = 'S'");
		return q.list();
	}

	@Override
	public int closeIncative() throws DaoException {
		Session se = bTx.getSession();
		long now = new Date().getTime();
		Query query = se.createQuery("update " +
				"Sessions s set " +
				"s.validSession = 'N', " +
				"s.closedTime = " + now +
				" where s.validSession = 'S' " +
				"and (s.lastAccessedTime + (s.maxInactive * 1000)) < " + now);
	    int rows = query.executeUpdate();
	    
	    return rows;
	}
}
