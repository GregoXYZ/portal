package business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.Destinos;
import business.beans.DestinosId;
import business.dao.impl.DestinosDAOImpl;
import business.util.Convert;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.DestinosBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.DestinosDTO;

public class DestinosBOImpl implements DestinosBO {

	private static Log logger = LogFactory.getLog(DestinosBOImpl.class);

	@Override
	public void add(DestinosDTO dto) throws BusinessException {
		Destinos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			DestinosDAOImpl destinosDAO = new DestinosDAOImpl(bt);
			destinosDAO.add(dao);
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
	public void delete(DestinosDTO dto) throws BusinessException {
		Destinos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			DestinosDAOImpl destinosDAO = new DestinosDAOImpl(bt);
			destinosDAO.delete(dao);
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
	public DestinosDTO getByPrimaryKey(Long usuFk, Long entFk) throws BusinessException {
		DestinosDTO dto;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			DestinosDAOImpl destinosDAO = new DestinosDAOImpl(bt);
			DestinosId id = new DestinosId(entFk, usuFk);			
			dto = Convert.dao2dto(destinosDAO.getByPrimaryKey(id));
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
	public DestinosDTO[] getDestinos() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(DestinosDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Long[] getByUser(Long usuFk) throws BusinessException {
		Long[] entradas;
		List<Destinos> list = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			DestinosDAOImpl destinosDAO = new DestinosDAOImpl(bt);
			list = destinosDAO.getByUser(usuFk);
			
			entradas = new Long[list.size()];
			int i = 0;
			for (Iterator<Destinos> iterator = list.iterator(); iterator.hasNext();i++)
			{
				entradas[i] = ((Destinos) iterator.next()).getId().getEntFk();
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

}
