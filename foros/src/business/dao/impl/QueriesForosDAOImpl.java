package business.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.Contenidos;
import business.beans.Entradas;
import business.dao.QueriesForosDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * @see business.beans.ExtraQueries
 * @author Grego
 */
public class QueriesForosDAOImpl implements QueriesForosDAO {

	private static final Log logger = LogFactory.getLog(QueriesForosDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public QueriesForosDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	@Override
	public List<?> getResults(Long user) throws DaoException {
		Query q;
		Session se = bTx.getSession();

		q = se.getNamedQuery("Foros.results");
		q.setLong("user", user);			
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());

		return q.list();
	}

	@Override
	public List<?> getThreats(Long user, Long entrada) throws DaoException {
		Query q;
		Session se = bTx.getSession();

		q = se.getNamedQuery("Foros.threats");
		q.setLong("user", user);			
		q.setLong("entrada", entrada);			
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());

		return q.list();
	}

	@Override
	public Object[] getContenido(Long user, Long contenido) throws DaoException {
		Session se = bTx.getSession();

		Query q = se.getNamedQuery("Foros.contenido");
		q.setLong("user", user);			
		q.setLong("contenido", contenido);			
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());

		return (Object[]) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entradas> getEntradas(Long user) throws DaoException {
		Session se = bTx.getSession();

		Query q = se.getNamedQuery("Foros.entradas");
		q.setLong("user", user);			
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());

		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contenidos> getContenidos(Long entrada, Long user)
			throws DaoException {
		Session se = bTx.getSession();

		Query q = se.getNamedQuery("Foros.contenidos");
		q.setLong("entrada", entrada);			
		q.setLong("user", user);			
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());

		return q.list();
	}

	@Override
	public Contenidos getUltimoContenido(Long entrada) throws DaoException {
		Session se = bTx.getSession();

		Query q = se.getNamedQuery("Foros.ultimoContenido");
		q.setLong("entrada", entrada);			
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());

		return (Contenidos) q.uniqueResult();
	}
}
