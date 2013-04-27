package business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.MimeTypes;
import business.dao.impl.MimeTypesDAOImpl;
import business.util.Convert;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.MimeTypesBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.MimeTypesDTO;

public class MimeTypesBOImpl implements MimeTypesBO {

	private static Log logger = LogFactory.getLog(MimeTypesBOImpl.class);

	@Override
	public void add(MimeTypesDTO dto) throws BusinessException {
		MimeTypes dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			MimeTypesDAOImpl MimeTypesDAO = new MimeTypesDAOImpl(bt);
			MimeTypesDAO.add(dao);
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
	public void delete(MimeTypesDTO dto) throws BusinessException {
		MimeTypes dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			MimeTypesDAOImpl MimeTypesDAO = new MimeTypesDAOImpl(bt);
			MimeTypesDAO.delete(dao);
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
	public MimeTypesDTO getByPrimaryKey(Long id) throws BusinessException {
		MimeTypesDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			MimeTypesDAOImpl MimeTypesDAO = new MimeTypesDAOImpl(bt);
			MimeTypes dao = MimeTypesDAO.getByPrimaryKey(id);
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
	public MimeTypesDTO[] getMimeTypes() throws BusinessException {
		List<MimeTypes> list = null;
		MimeTypesDTO[] MimeTypes = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			MimeTypesDAOImpl MimeTypesDAO = new MimeTypesDAOImpl(bt);
			list = MimeTypesDAO.getMimeTypes();
			
			MimeTypes = new MimeTypesDTO[list.size()];
			int i = 0;
			for (Iterator<MimeTypes> iterator = list.iterator(); iterator.hasNext();i++)
			{
				MimeTypes[i] = Convert.dao2dto((MimeTypes) iterator.next());
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
		return MimeTypes;
	}

	@Override
	public void update(MimeTypesDTO dto) throws BusinessException {
		MimeTypes dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			MimeTypesDAOImpl MimeTypesDAO = new MimeTypesDAOImpl(bt);
			MimeTypesDAO.update(dao);
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
