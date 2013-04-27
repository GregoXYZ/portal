package business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.Compartidos;
import business.dao.impl.CompartidosDAOImpl;
import business.util.Convert;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.CompartidosBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.CompartidosDTO;

public class CompartidosBOImpl implements CompartidosBO {

	private static Log logger = LogFactory.getLog(CompartidosBOImpl.class);

	@Override
	public void add(CompartidosDTO dto) throws BusinessException {
		Compartidos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CompartidosDAOImpl compartidosDAO = new CompartidosDAOImpl(bt);
			dto.setComPk(compartidosDAO.add(dao));
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
	public void delete(CompartidosDTO dto) throws BusinessException {
		Compartidos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CompartidosDAOImpl compartidosDAO = new CompartidosDAOImpl(bt);
			compartidosDAO.delete(dao);
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
	public CompartidosDTO getByPrimaryKey(Long id) throws BusinessException {
		CompartidosDTO dto;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CompartidosDAOImpl compartidosDAO = new CompartidosDAOImpl(bt);
			dto = Convert.dao2dto(compartidosDAO.getByPrimaryKey(id));
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
	public CompartidosDTO getByUniqueKey(Long assFk, Long usuFk) throws BusinessException {
		CompartidosDTO dto;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CompartidosDAOImpl compartidosDAO = new CompartidosDAOImpl(bt);
			dto = Convert.dao2dto(compartidosDAO.getByUniqueKey(usuFk, assFk));
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
	public CompartidosDTO[] getCompartidos() throws BusinessException {
		return null;
	}

	@Override
	public void update(CompartidosDTO dto) throws BusinessException {
		Compartidos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CompartidosDAOImpl compartidosDAO = new CompartidosDAOImpl(bt);
			compartidosDAO.update(dao);
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
	public CompartidosDTO[] getByUser(Long usuFk) throws BusinessException {
		CompartidosDTO[] compartidos;
		List<Compartidos> list = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CompartidosDAOImpl compartidosDAO = new CompartidosDAOImpl(bt);
			list = compartidosDAO.getByUser(usuFk);
			
			compartidos = new CompartidosDTO[list.size()];
			int i = 0;
			for (Iterator<Compartidos> iterator = list.iterator(); iterator.hasNext();i++)
			{
				compartidos[i] = Convert.dao2dto((Compartidos) iterator.next());
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
		return compartidos;
	}
	
	@Override
	public CompartidosDTO[] getByAsset(Long assFk) throws BusinessException {
		CompartidosDTO[] compartidos;
		List<Compartidos> list = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CompartidosDAOImpl compartidosDAO = new CompartidosDAOImpl(bt);
			list = compartidosDAO.getByAsset(assFk);
			
			compartidos = new CompartidosDTO[list.size()];
			int i = 0;
			for (Iterator<Compartidos> iterator = list.iterator(); iterator.hasNext();i++)
			{
				compartidos[i] = Convert.dao2dto((Compartidos) iterator.next());
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
		return compartidos;
	}

}
