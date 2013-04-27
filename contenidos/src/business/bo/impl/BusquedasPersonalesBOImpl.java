package business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.BusquedasPersonales;
import business.dao.impl.BusquedasPersonalesDAOImpl;
import business.util.Convert;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.BusquedasPersonalesBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.BusquedasPersonalesDTO;

public class BusquedasPersonalesBOImpl implements BusquedasPersonalesBO {

	private static Log logger = LogFactory.getLog(BusquedasPersonalesBOImpl.class);

	@Override
	public void add(BusquedasPersonalesDTO dto) throws BusinessException {
		BusquedasPersonales dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			BusquedasPersonalesDAOImpl busquedasPersonalesDAO = new BusquedasPersonalesDAOImpl(bt);
			busquedasPersonalesDAO.add(dao);
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
	public void delete(BusquedasPersonalesDTO dto) throws BusinessException {
		BusquedasPersonales dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			BusquedasPersonalesDAOImpl busquedasPersonalesDAO = new BusquedasPersonalesDAOImpl(bt);
			busquedasPersonalesDAO.delete(dao);
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
	public BusquedasPersonalesDTO getByPrimaryKey(Long id) throws BusinessException {
		BusquedasPersonalesDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			BusquedasPersonalesDAOImpl busquedasPersonalesDAO = new BusquedasPersonalesDAOImpl(bt);
			BusquedasPersonales dao = busquedasPersonalesDAO.getByPrimaryKey(id);
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
	public BusquedasPersonalesDTO[] getBusquedasPersonales() throws BusinessException {
		List<BusquedasPersonales> list = null;
		BusquedasPersonalesDTO[] busquedasPersonales = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			BusquedasPersonalesDAOImpl busquedasPersonalesDAO = new BusquedasPersonalesDAOImpl(bt);
			list = busquedasPersonalesDAO.getBusquedasPersonales();
			
			busquedasPersonales = new BusquedasPersonalesDTO[list.size()];
			int i = 0;
			for (Iterator<BusquedasPersonales> iterator = list.iterator(); iterator.hasNext();i++)
			{
				busquedasPersonales[i] = Convert.dao2dto((BusquedasPersonales) iterator.next());
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
		return busquedasPersonales;
	}

	@Override
	public void update(BusquedasPersonalesDTO dto) throws BusinessException {
		BusquedasPersonales dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			BusquedasPersonalesDAOImpl busquedasPersonalesDAO = new BusquedasPersonalesDAOImpl(bt);
			busquedasPersonalesDAO.update(dao);
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
	public BusquedasPersonalesDTO getByUniqueKey(Long user, Long tag)
			throws BusinessException {
		BusquedasPersonalesDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			BusquedasPersonalesDAOImpl busquedasPersonalesDAO = new BusquedasPersonalesDAOImpl(bt);
			BusquedasPersonales dao = busquedasPersonalesDAO.getByUniqueKey(user, tag);
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
}
