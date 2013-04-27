package business.bo.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.Contenidos;
import business.beans.Destinos;
import business.beans.DestinosId;
import business.beans.Entradas;
import business.dao.impl.ContenidosDAOImpl;
import business.dao.impl.DestinosDAOImpl;
import business.dao.impl.EntradasDAOImpl;
import business.dao.impl.QueriesForosDAOImpl;
import business.util.Convert;

import common.Constants;
import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.Avisos;
import common.business.bo.QueriesForosBO;
import common.business.dao.impl.AvisosDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.ContenidosDTO;
import common.dto.EntradasDTO;
import common.dto.MensajesDTO;

public class QueriesForosBOImpl implements QueriesForosBO {

	private static Log logger = LogFactory.getLog(QueriesForosBOImpl.class);
	
	@Override
	public MensajesDTO[] getResults(Long user) throws BusinessException {
		List<?> list = null;
		MensajesDTO[] mensajes = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			QueriesForosDAOImpl queriesForosDAO = new QueriesForosDAOImpl(bt);
			list = queriesForosDAO.getResults(user);

			mensajes = new MensajesDTO[list.size()];
			int i = 0;
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();i++)
			{
				Object[] elem = (Object[]) iterator.next();

				MensajesDTO dto = new MensajesDTO();
				dto.getEntrada().setEntPk((Long) elem[0]);
				dto.getContenido().setConPk((Long) elem[1]);
				dto.getEntrada().setEntSubject((String) elem[2]);
				dto.getEntrada().setEntRestringida((Boolean) elem[3]);
				dto.getContenido().setConData((String) elem[4]);
				dto.getContenido().setUsuFk((Long) elem[5]);
				dto.getContenido().setConFechaAlta((Long) elem[6]);				
				
				mensajes[i] = dto;
			}
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return mensajes;
	}

	@Override
	public MensajesDTO[] getThreats(Long user, Long entrada)
			throws BusinessException {
		List<?> list = null;
		MensajesDTO[] mensajes = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			QueriesForosDAOImpl queriesForosDAO = new QueriesForosDAOImpl(bt);
			list = queriesForosDAO.getThreats(user, entrada);

			mensajes = new MensajesDTO[list.size()];
			int i = 0;
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();i++)
			{
				Object[] elem = (Object[]) iterator.next();

				MensajesDTO dto = new MensajesDTO();
				dto.getEntrada().setEntPk((Long) elem[0]);
				dto.getContenido().setConPk((Long) elem[1]);
				dto.getEntrada().setEntSubject((String) elem[2]);
				dto.getEntrada().setEntRestringida((Boolean) elem[3]);
				dto.getContenido().setConData((String) elem[4]);
				dto.getContenido().setUsuFk((Long) elem[5]);
				dto.getContenido().setConFechaAlta((Long) elem[6]);
				mensajes[i] = dto;
			}
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return mensajes;
	}
	
	@Override
	public MensajesDTO getContenido(Long user, Long contenido)
			throws BusinessException {
		MensajesDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			QueriesForosDAOImpl queriesForosDAO = new QueriesForosDAOImpl(bt);
			Object[] elem = queriesForosDAO.getContenido(user, contenido);

			if (elem != null) {
				dto = new MensajesDTO();
				dto.getEntrada().setEntPk((Long) elem[0]);
				dto.getContenido().setConPk((Long) elem[1]);
				dto.getEntrada().setEntSubject((String) elem[2]);
				dto.getEntrada().setEntRestringida((Boolean) elem[3]);
				dto.getContenido().setConData((String) elem[4]);
				dto.getContenido().setUsuFk((Long) elem[5]);
				dto.getContenido().setConFechaAlta((Long) elem[6]);				
			}
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return dto;
	}

	@Override
	public void addEntrada(Long user, String entrada, String contenido, Boolean restringida, Long[] users) throws BusinessException
	{
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			EntradasDAOImpl entradasDAO = new EntradasDAOImpl(bt);
			DestinosDAOImpl destinosDAO = new DestinosDAOImpl(bt);
			ContenidosDAOImpl contenidosDAO = new ContenidosDAOImpl(bt);
			Date fAlta = new Date();
			Long time = fAlta.getTime();
			Entradas entradaDAO = new Entradas(time, entrada, user);
			entradaDAO.setEntRestringida(restringida==null?false:restringida);
			Long entPk = entradasDAO.add(entradaDAO);

			for (Long userId: users)
			{
				DestinosId id = new DestinosId(entPk, userId);
				Destinos destino = new Destinos(id, entradaDAO);
				destinosDAO.add(destino);
			}

			Contenidos contenidoDAO = new Contenidos(contenido, time, entPk, user);
			contenidosDAO.add(contenidoDAO);

			bt.commitTx();
		} 
		catch (DaoException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			transactionFactory.endTx();
		}
	}
	
	@Override
	public ContenidosDTO[] getContenidos(Long entrada, Long user)
			throws BusinessException {
		List<Contenidos> list = null;
		ContenidosDTO[] contenidos = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			QueriesForosDAOImpl queriesForosDAO = new QueriesForosDAOImpl(bt);
			list = queriesForosDAO.getContenidos(entrada, user);

			contenidos = new ContenidosDTO[list.size()];
			int i = 0;
			for (Iterator<Contenidos> iterator = list.iterator(); iterator.hasNext();i++)
			{
				contenidos[i] = Convert.dao2dto(iterator.next());
			}
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return contenidos;
	}
	
	@Override
	public EntradasDTO[] getEntradas(Long user) throws BusinessException {
		List<Entradas> list = null;
		EntradasDTO[] entradas = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			QueriesForosDAOImpl queriesForosDAO = new QueriesForosDAOImpl(bt);
			list = queriesForosDAO.getEntradas(user);

			entradas = new EntradasDTO[list.size()];
			int i = 0;
			for (Iterator<Entradas> iterator = list.iterator(); iterator.hasNext();i++)
			{
				entradas[i] = Convert.dao2dto(iterator.next());
			}
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return entradas;
	}
	@Override
	public ContenidosDTO getUltimoContenido(Long entrada)
			throws BusinessException {
		ContenidosDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			QueriesForosDAOImpl queriesForosDAO = new QueriesForosDAOImpl(bt);
			Contenidos dao = queriesForosDAO.getUltimoContenido(entrada);

			if (dao != null) {
				dto = Convert.dao2dto(dao);
			}
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return dto;
	}
	
	@Override
	public void addInvitaciones(Long entrada, Long user, Long[] users)
			throws BusinessException {
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			EntradasDAOImpl entradasDAO = new EntradasDAOImpl(bt);
			DestinosDAOImpl destinosDAO = new DestinosDAOImpl(bt);
			AvisosDAOImpl avisosDAO = new AvisosDAOImpl(bt);
			Entradas entradaDAO = entradasDAO.getByPrimaryKey(entrada);

			for (Long userId: users)
			{
				DestinosId id = new DestinosId(entrada, userId);
				Destinos destino = new Destinos(id, entradaDAO);
				destinosDAO.add(destino);

				Avisos aviso = new Avisos(userId, new Date().getTime(), Constants.AVISO_FORO, entrada, 1);
				aviso.setUsuFkOrigen(user);
				avisosDAO.add(aviso);				
			}

			bt.commitTx();
		} 
		catch (DaoException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			transactionFactory.endTx();
		}
	}
}
