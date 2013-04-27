package business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.AccesosDirectos;
import business.dao.impl.AccesosDirectosDAOImpl;
import business.util.Convert;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.AccesosDirectosBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.AccesosDirectosDTO;

public class AccesosDirectosBOImpl implements AccesosDirectosBO {

	private static Log logger = LogFactory.getLog(AccesosDirectosBOImpl.class);

	@Override
	public void add(AccesosDirectosDTO dto) throws BusinessException {
		AccesosDirectos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			AccesosDirectosDAOImpl AccesosDirectosDAO = new AccesosDirectosDAOImpl(bt);
			Long pk = AccesosDirectosDAO.add(dao);
			dto.setAccDirPk(pk);
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
	public void delete(AccesosDirectosDTO dto) throws BusinessException {
		AccesosDirectos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			AccesosDirectosDAOImpl AccesosDirectosDAO = new AccesosDirectosDAOImpl(bt);
			AccesosDirectosDAO.delete(dao);
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
	public AccesosDirectosDTO getByPrimaryKey(Long id) throws BusinessException {
		AccesosDirectosDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			AccesosDirectosDAOImpl AccesosDirectosDAO = new AccesosDirectosDAOImpl(bt);
			dto = Convert.dao2dto(AccesosDirectosDAO.getByPrimaryKey(id));
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
	public AccesosDirectosDTO[] getAccesosDirectos() throws BusinessException {
		List<AccesosDirectos> list = null;
		AccesosDirectosDTO[] AccesosDirectos = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			AccesosDirectosDAOImpl AccesosDirectosDAO = new AccesosDirectosDAOImpl(bt);
			list = AccesosDirectosDAO.getAccesosDirectos();
			
			AccesosDirectos = new AccesosDirectosDTO[list.size()];
			int i = 0;
			for (Iterator<AccesosDirectos> iterator = list.iterator(); iterator.hasNext();i++)
			{
				AccesosDirectos[i] = Convert.dao2dto((AccesosDirectos) iterator.next());
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
		return AccesosDirectos;
	}

	@Override
	public void update(AccesosDirectosDTO dto) throws BusinessException {
		AccesosDirectos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			AccesosDirectosDAOImpl AccesosDirectosDAO = new AccesosDirectosDAOImpl(bt);
			AccesosDirectosDAO.update(dao);
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
