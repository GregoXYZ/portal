package business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.Parametros;
import business.dao.impl.ParametrosDAOImpl;
import business.util.Convert;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.ParametrosBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.ParametrosDTO;

public class ParametrosBOImpl implements ParametrosBO {

	private static Log logger = LogFactory.getLog(ParametrosBOImpl.class);

	@Override
	public void add(ParametrosDTO dto) throws BusinessException {
		Parametros dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ParametrosDAOImpl parametrosDAO = new ParametrosDAOImpl(bt);
			parametrosDAO.add(dao);
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
	public void delete(ParametrosDTO dto) throws BusinessException {
		Parametros dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ParametrosDAOImpl parametrosDAO = new ParametrosDAOImpl(bt);
			parametrosDAO.delete(dao);
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
	public ParametrosDTO getByPrimaryKey(String id) throws BusinessException {
		ParametrosDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ParametrosDAOImpl parametrosDAO = new ParametrosDAOImpl(bt);
			Parametros dao = parametrosDAO.getByPrimaryKey(id);
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
	public ParametrosDTO[] getParametros() throws BusinessException {
		List<Parametros> list = null;
		ParametrosDTO[] parametros = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ParametrosDAOImpl parametrosDAO = new ParametrosDAOImpl(bt);
			list = parametrosDAO.getParametros();
			
			parametros = new ParametrosDTO[list.size()];
			int i = 0;
			for (Iterator<Parametros> iterator = list.iterator(); iterator.hasNext();i++)
			{
				parametros[i] = Convert.dao2dto((Parametros) iterator.next());
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
		return parametros;
	}

	@Override
	public void update(ParametrosDTO dto) throws BusinessException {
		Parametros dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ParametrosDAOImpl parametrosDAO = new ParametrosDAOImpl(bt);
			parametrosDAO.update(dao);
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
	public String getValor(String id) throws BusinessException {
		Parametros dao;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ParametrosDAOImpl parametrosDAO = new ParametrosDAOImpl(bt);
			dao = parametrosDAO.getByPrimaryKey(id);
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return dao==null?"":dao.getParValor();
	}
}
