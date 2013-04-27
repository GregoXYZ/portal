package business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.Contenidos;
import business.dao.impl.ContenidosDAOImpl;
import business.util.Convert;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.ContenidosBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.ContenidosDTO;

public class ContenidosBOImpl implements ContenidosBO {

	private static Log logger = LogFactory.getLog(ContenidosBOImpl.class);

	@Override
	public void add(ContenidosDTO dto) throws BusinessException {
		Contenidos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ContenidosDAOImpl contenidosDAO = new ContenidosDAOImpl(bt);
			contenidosDAO.add(dao);
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
	public void delete(ContenidosDTO dto) throws BusinessException {
		Contenidos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ContenidosDAOImpl contenidosDAO = new ContenidosDAOImpl(bt);
			contenidosDAO.delete(dao);
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
	public ContenidosDTO getByPrimaryKey(Long id) throws BusinessException {
		ContenidosDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ContenidosDAOImpl contenidosDAO = new ContenidosDAOImpl(bt);
			Contenidos dao = contenidosDAO.getByPrimaryKey(id);
			if ( dao!= null ) dto = Convert.dao2dto(dao);
			else dto = null;
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return dto;
	}

	@Override
	public ContenidosDTO[] getContenidos() throws BusinessException {
		List<Contenidos> list = null;
		ContenidosDTO[] contenidos = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ContenidosDAOImpl contenidosDAO = new ContenidosDAOImpl(bt);
			list = contenidosDAO.getContenidos();
			
			contenidos = new ContenidosDTO[list.size()];
			int i = 0;
			for (Iterator<Contenidos> iterator = list.iterator(); iterator.hasNext();i++)
			{
				contenidos[i] = Convert.dao2dto((Contenidos) iterator.next());
			}
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return contenidos;
	}

	@Override
	public void update(ContenidosDTO dto) throws BusinessException {
		Contenidos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ContenidosDAOImpl contenidosDAO = new ContenidosDAOImpl(bt);
			contenidosDAO.update(dao);
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
