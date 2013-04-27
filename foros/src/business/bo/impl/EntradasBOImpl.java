package business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.Entradas;
import business.dao.impl.EntradasDAOImpl;
import business.util.Convert;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.EntradasBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.EntradasDTO;

public class EntradasBOImpl implements EntradasBO {

	private static Log logger = LogFactory.getLog(EntradasBOImpl.class);

	@Override
	public void add(EntradasDTO dto) throws BusinessException {
		Entradas dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			EntradasDAOImpl entradasDAO = new EntradasDAOImpl(bt);
			entradasDAO.add(dao);
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
	public void delete(EntradasDTO dto) throws BusinessException {
		Entradas dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			EntradasDAOImpl entradasDAO = new EntradasDAOImpl(bt);
			entradasDAO.delete(dao);
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
	public EntradasDTO getByPrimaryKey(Long id) throws BusinessException {
		EntradasDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			EntradasDAOImpl entradasDAO = new EntradasDAOImpl(bt);
			Entradas dao = entradasDAO.getByPrimaryKey(id);
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
	public EntradasDTO[] getEntradas() throws BusinessException {
		List<Entradas> list = null;
		EntradasDTO[] entradas = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			EntradasDAOImpl entradasDAO = new EntradasDAOImpl(bt);
			list = entradasDAO.getEntradas();
			
			entradas = new EntradasDTO[list.size()];
			int i = 0;
			for (Iterator<Entradas> iterator = list.iterator(); iterator.hasNext();i++)
			{
				entradas[i] = Convert.dao2dto((Entradas) iterator.next());
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
		return entradas;
	}

	@Override
	public void update(EntradasDTO dto) throws BusinessException {
		Entradas dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			EntradasDAOImpl entradasDAO = new EntradasDAOImpl(bt);
			entradasDAO.update(dao);
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
