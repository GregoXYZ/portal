package business.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.Assets;
import business.beans.Compartidos;
import business.dao.CompartidosDAO;

import common.business.DaoException;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model class Compartidos.
 * 
 * @see business.beans.Compartidos
 * @author Grego
 */
public class CompartidosDAOImpl implements CompartidosDAO {

	private static final Log logger = LogFactory.getLog(CompartidosDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public CompartidosDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	@Override
	public Compartidos getByPrimaryKey(Long id) throws DaoException {
		Session se = this.bTx.getSession();
		Compartidos obj = (Compartidos) se.get(Compartidos.class, id);
		return obj;
	}

	@Override
	public Compartidos getByUniqueKey(Long user, Long asset) throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Compartidos c where c.usuFk = " + user.toString() +
				" and c.assFk = " + asset.toString());
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (Compartidos) q.uniqueResult();
	}

	public Long add(Compartidos obj) throws DaoException {
		Session se = this.bTx.getSession();
		if (obj.getComFechaAlta() == null || obj.getComFechaAlta().equals(0))
			obj.setComFechaAlta(new Date().getTime());
		se.saveOrUpdate(obj);

		return obj.getComPk();
	}

	public void update(Compartidos obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.update(obj);
	}

	public void delete(Compartidos obj) throws DaoException {
		Session se = this.bTx.getSession();
		se.delete(obj);
	}

	@Override
	public int delete(Long asset) throws DaoException {
		Session se = this.bTx.getSession();
		Query query = se.createQuery("delete from Compartidos where assFk = " + asset);
	    int rows = query.executeUpdate();
	    
	    return rows;
	}

	@SuppressWarnings("unchecked")
	public List<Compartidos> getCompartidos() throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Compartidos c order by c.usuFk, c.assFk");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compartidos> getByUser(Long user) throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Compartidos c where c.usuFk = " + user.toString());
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compartidos> getByAsset(Long asset) throws DaoException {
		Session se = this.bTx.getSession();
		Query q = se.createQuery("from Compartidos c where c.assFk = " + asset.toString());
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
	
	@Override
	public void comparte(Assets assDAO, Long[] usuarios) throws DaoException
	{
		for (int ind = 0; ind < usuarios.length; ind++)
		{
			Compartidos compartido = new Compartidos(assDAO.getAssPk(), usuarios[ind]);
			compartido.setUsuFk_source(assDAO.getUsuFk());
			compartido.setComFechaAlta(assDAO.getAssFechaAlta());
			this.add(compartido);
		}				
	}
}
