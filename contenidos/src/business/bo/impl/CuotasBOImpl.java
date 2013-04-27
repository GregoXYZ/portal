package business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.Cuotas;
import business.dao.impl.CuotasDAOImpl;
import business.dao.impl.ExtraQueriesDAOImpl;
import business.util.Convert;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.CuotasBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.CuotasDTO;

public class CuotasBOImpl implements CuotasBO {

	private static Log logger = LogFactory.getLog(CuotasBOImpl.class);

	@Override
	public void add(CuotasDTO dto) throws BusinessException {
		Cuotas dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CuotasDAOImpl cuotasDAO = new CuotasDAOImpl(bt);
			cuotasDAO.add(dao);
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
	public void delete(CuotasDTO dto) throws BusinessException {
		Cuotas dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CuotasDAOImpl cuotasDAO = new CuotasDAOImpl(bt);
			cuotasDAO.delete(dao);
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
	public CuotasDTO getByPrimaryKey(Long id) throws BusinessException {
		CuotasDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CuotasDAOImpl cuotasDAO = new CuotasDAOImpl(bt);
			Cuotas dao = cuotasDAO.getByPrimaryKey(id);
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
	public CuotasDTO[] getCuotas() throws BusinessException {
		List<Cuotas> list = null;
		CuotasDTO[] cuotas = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CuotasDAOImpl cuotasDAO = new CuotasDAOImpl(bt);
			list = cuotasDAO.getCuotas();
			
			cuotas = new CuotasDTO[list.size()];
			int i = 0;
			for (Iterator<Cuotas> iterator = list.iterator(); iterator.hasNext();i++)
			{
				cuotas[i] = Convert.dao2dto((Cuotas) iterator.next());
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
		return cuotas;
	}

	@Override
	public void update(CuotasDTO dto) throws BusinessException {
		Cuotas dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CuotasDAOImpl cuotasDAO = new CuotasDAOImpl(bt);
			cuotasDAO.update(dao);
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
	public CuotasDTO getCuotaRestante(Long user) throws BusinessException {
		CuotasDTO dto = null;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try {
			CuotasDAOImpl cuotasDAO = new CuotasDAOImpl(bt);
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			Cuotas cuota = extraQueriesDAO.getCuotaRestante(user);
			if ( cuota == null ) cuota = cuotasDAO.getByPrimaryKey(user);
			if ( cuota == null ) dto = null;
			else dto = Convert.dao2dto(cuota);
		} catch (DaoException e) {
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
