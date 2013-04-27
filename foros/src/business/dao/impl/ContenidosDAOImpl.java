package business.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.Contenidos;
import business.beans.Destinos;
import business.dao.ContenidosDAO;

import common.Constants;
import common.business.DaoException;
import common.business.beans.Avisos;
import common.business.dao.impl.AvisosDAOImpl;
import common.business.hibernate.BusinessTransactionBo;

/**
 * Home object for domain model clcar Contenidos.
 * 
 * @see business.beans.Contenidos
 * @author Grego
 */
public class ContenidosDAOImpl implements ContenidosDAO {

	private static final Log logger = LogFactory.getLog(ContenidosDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public ContenidosDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	public Contenidos getByPrimaryKey(Long id) throws DaoException {
		Session se = bTx.getSession();
		Contenidos obj = (Contenidos) se.get(Contenidos.class, id);
		return obj;
	}

	public Contenidos getByUser(Long user) throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Contenidos c where c.usuFk = " + user);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return (Contenidos) q.uniqueResult();
	}

	public Long add(Contenidos obj) throws DaoException {
		Session se = bTx.getSession();
		if (obj.getConData().trim().length() == 0)
			throw new DaoException("ERROR: No se puede insertar un contenido vacio.");
		
		se.saveOrUpdate(obj);

		DestinosDAOImpl destinosDAO =  new DestinosDAOImpl(bTx);
		AvisosDAOImpl avisosDAO = new AvisosDAOImpl(bTx);
		List<Destinos> destinos = destinosDAO.getByEntrada(obj.getEntFk());
		for (Destinos destino : destinos)
		{
			if (!obj.getUsuFk().equals(destino.getId().getUsuFk()))
			{
				Avisos mensaje = new Avisos(destino.getId().getUsuFk(), 
						new Date().getTime(), Constants.AVISO_FORO, 
						obj.getConPk(), 1); 
				mensaje.setUsuFkOrigen(obj.getUsuFk());
				avisosDAO.add(mensaje);				
			}
		}
		
		return obj.getConPk();
	}

	public void update(Contenidos obj) throws DaoException {
		Session se = bTx.getSession();
		if (obj.getConData().trim().length() == 0)
			throw new DaoException("ERROR: No se puede insertar un contenido vacio.");
		se.update(obj);
	}

	public void delete(Contenidos obj) throws DaoException {
		Session se = bTx.getSession();
		se.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Contenidos> getContenidos() throws DaoException {
		Session se = bTx.getSession();
		Query q = se.createQuery("from Contenidos");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();
	}
}
