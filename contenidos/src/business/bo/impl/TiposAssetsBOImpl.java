package business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.TiposAssets;
import business.dao.impl.TiposAssetsDAOImpl;
import business.util.Convert;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.TiposAssetsBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.TiposAssetsDTO;

public class TiposAssetsBOImpl implements TiposAssetsBO {

	private static Log logger = LogFactory.getLog(TiposAssetsBOImpl.class);

	@Override
	public void add(TiposAssetsDTO dto) throws BusinessException {
		TiposAssets dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			TiposAssetsDAOImpl tiposAssetsDAO = new TiposAssetsDAOImpl(bt);
			Long pk = tiposAssetsDAO.add(dao);
			dto.setTipAssPk(pk);
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
	public void delete(TiposAssetsDTO dto) throws BusinessException {
		TiposAssets dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			TiposAssetsDAOImpl tiposAssetsDAO = new TiposAssetsDAOImpl(bt);
			tiposAssetsDAO.delete(dao);
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
	public TiposAssetsDTO getByPrimaryKey(Long id) throws BusinessException {
		TiposAssetsDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			TiposAssetsDAOImpl tiposAssetsDAO = new TiposAssetsDAOImpl(bt);
			dto = Convert.dao2dto(tiposAssetsDAO.getByPrimaryKey(id));
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
	public TiposAssetsDTO[] getTiposAssets() throws BusinessException {
		List<TiposAssets> list = null;
		TiposAssetsDTO[] tiposAssets = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			TiposAssetsDAOImpl tiposAssetsDAO = new TiposAssetsDAOImpl(bt);
			list = tiposAssetsDAO.getTiposAssets();
			
			tiposAssets = new TiposAssetsDTO[list.size()];
			int i = 0;
			for (Iterator<TiposAssets> iterator = list.iterator(); iterator.hasNext();i++)
			{
				tiposAssets[i] = Convert.dao2dto((TiposAssets) iterator.next());
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
		return tiposAssets;
	}

	@Override
	public void update(TiposAssetsDTO dto) throws BusinessException {
		TiposAssets dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			TiposAssetsDAOImpl tiposAssetsDAO = new TiposAssetsDAOImpl(bt);
			tiposAssetsDAO.update(dao);
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
	public TiposAssetsDTO getByCode(String uk) throws BusinessException {
		TiposAssetsDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			TiposAssetsDAOImpl tiposAssetsDAO = new TiposAssetsDAOImpl(bt);
			dto = Convert.dao2dto(tiposAssetsDAO.getByCode(uk));
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
