package common.business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.Grupos;
import common.business.bo.GruposBO;
import common.business.dao.impl.GruposDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.business.util.Convert;
import common.dto.GruposDTO;

public class GruposBOImpl implements GruposBO {

	private static Log logger = LogFactory.getLog(GruposBOImpl.class);

	@Override
	public void add(GruposDTO dto) throws BusinessException {
		Grupos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		GruposDAOImpl gruposDAO = new GruposDAOImpl(bt);
		try 
		{
			Long pk = gruposDAO.add(dao);
			dto.setGruPk(pk);
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
	public void delete(GruposDTO dto) throws BusinessException {
		Grupos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		GruposDAOImpl gruposDAO = new GruposDAOImpl(bt);
		try 
		{
			gruposDAO.delete(dao);
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
	public GruposDTO getByPrimaryKey(Long id) throws BusinessException {
		GruposDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		GruposDAOImpl gruposDAO = new GruposDAOImpl(bt);
		try 
		{
			dto = Convert.dao2dto(gruposDAO.getByPrimaryKey(id));
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
	public GruposDTO[] getGrupos() throws BusinessException {
		List<Grupos> list = null;
		GruposDTO[] grupos = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		GruposDAOImpl gruposDAO = new GruposDAOImpl(bt);
		try 
		{
			list = gruposDAO.getGrupos();
			
			grupos = new GruposDTO[list.size()];
			int i = 0;
			for (Iterator<Grupos> iterator = list.iterator(); iterator.hasNext();i++)
			{
				grupos[i] = Convert.dao2dto((Grupos) iterator.next());
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
		return grupos;
	}

	@Override
	public void update(GruposDTO dto) throws BusinessException {
		Grupos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		GruposDAOImpl gruposDAO = new GruposDAOImpl(bt);
		try 
		{
			gruposDAO.update(dao);
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
	public GruposDTO getByCode(String uk) throws BusinessException {
		GruposDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		GruposDAOImpl gruposDAO = new GruposDAOImpl(bt);
		try 
		{
			dto = Convert.dao2dto(gruposDAO.getByCode(uk));
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
