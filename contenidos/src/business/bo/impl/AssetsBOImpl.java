package business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.Assets;
import business.dao.impl.AssetsDAOImpl;
import business.util.Convert;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.AssetsBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.AssetsDTO;

public class AssetsBOImpl implements AssetsBO {

	private static Log logger = LogFactory.getLog(AssetsBOImpl.class);

	@Override
	public void add(AssetsDTO dto) throws BusinessException {
		Assets dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			AssetsDAOImpl assetsDAO = new AssetsDAOImpl(bt);
			Long pk = assetsDAO.add(dao);
			dto.setAssPk(pk);
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
	public void delete(AssetsDTO dto) throws BusinessException {
		Assets dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			AssetsDAOImpl assetsDAO = new AssetsDAOImpl(bt);
			assetsDAO.delete(dao);
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

	//@Override
	public AssetsDTO getByPrimaryKey(Long id) throws BusinessException {
		AssetsDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			AssetsDAOImpl assetsDAO = new AssetsDAOImpl(bt);
			Assets dao = assetsDAO.getByPrimaryKey(id);
			if ( dao != null ) dto = Convert.dao2dto(dao);
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		catch (Exception e) {
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
	public AssetsDTO getByPrimaryKey(Long id, Long user)
			throws BusinessException {
		AssetsDTO dto = getByPrimaryKey(id);
		
		if ( dto!=null && dto.getUsuFk().compareTo(user) == 0)
		{
			return dto;
		}
		return null;
	}

	@Override
	public AssetsDTO[] getAssets() throws BusinessException {
		List<Assets> list = null;
		AssetsDTO[] assets = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			AssetsDAOImpl assetsDAO = new AssetsDAOImpl(bt);
			list = assetsDAO.getAssets();
			
			assets = new AssetsDTO[list.size()];
			int i = 0;
			for (Iterator<Assets> iterator = list.iterator(); iterator.hasNext();i++)
			{
				assets[i] = Convert.dao2dto((Assets) iterator.next());
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
		return assets;
	}

	@Override
	public void update(AssetsDTO dto) throws BusinessException {
		Assets dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			AssetsDAOImpl assetsDAO = new AssetsDAOImpl(bt);
			assetsDAO.update(dao);
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
	public AssetsDTO getByCode(String uk) throws BusinessException {
		AssetsDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			AssetsDAOImpl assetsDAO = new AssetsDAOImpl(bt);
			dto = Convert.dao2dto(assetsDAO.getByCode(uk));
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
