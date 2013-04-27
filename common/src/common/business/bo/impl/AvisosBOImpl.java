package common.business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.Avisos;
import common.business.bo.AvisosBO;
import common.business.dao.impl.AvisosDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.business.util.Convert;
import common.dto.AvisosDTO;

public class AvisosBOImpl implements AvisosBO {

	private static Log logger = LogFactory.getLog(ZonasBOImpl.class);

	@Override
	public void add(AvisosDTO dto) throws BusinessException {
		Avisos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		AvisosDAOImpl avisosDAO = new AvisosDAOImpl(bt);
		try 
		{
			Long pk = avisosDAO.add(dao);
			dto.setAviPk(pk);
			bt.commitTx();
		} catch (DaoException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} finally {
			transactionFactory.endTx();
		}
	}

	@Override
	public void delete(AvisosDTO dto) throws BusinessException {
		Avisos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		AvisosDAOImpl avisosDAO = new AvisosDAOImpl(bt);
		try 
		{
			avisosDAO.delete(dao);
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
	public AvisosDTO getByPrimaryKey(Long id) throws BusinessException {
		AvisosDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		AvisosDAOImpl avisosDAO = new AvisosDAOImpl(bt);
		try 
		{
			dto = Convert.dao2dto(avisosDAO.getByPrimaryKey(id));
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
	public AvisosDTO getByPrimaryKey(Long id, Long user) throws BusinessException {
		AvisosDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		AvisosDAOImpl avisosDAO = new AvisosDAOImpl(bt);
		try 
		{
			dto = Convert.dao2dto(avisosDAO.getByPrimaryKey(id, user));
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
	public AvisosDTO[] getByUser(Long user) throws BusinessException {
		List<Avisos> list = null;
		AvisosDTO[] avisos = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		AvisosDAOImpl avisosDAO = new AvisosDAOImpl(bt);
		try 
		{
			list = avisosDAO.getByUser(user);
			
			avisos = new AvisosDTO[list.size()];
			int i = 0;
			for (Iterator<Avisos> iterator = list.iterator(); iterator.hasNext();i++)
			{
				avisos[i] = Convert.dao2dto((Avisos) iterator.next());
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
		return avisos;
	}

	@Override
	public AvisosDTO[] getAvisos() throws BusinessException {
		List<Avisos> list = null;
		AvisosDTO[] avisos = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		AvisosDAOImpl avisosDAO = new AvisosDAOImpl(bt);
		try 
		{
			list = avisosDAO.getAvisos();
			
			avisos = new AvisosDTO[list.size()];
			int i = 0;
			for (Iterator<Avisos> iterator = list.iterator(); iterator.hasNext();i++)
			{
				avisos[i] = Convert.dao2dto((Avisos) iterator.next());
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
		return avisos;
	}

	@Override
	public void update(AvisosDTO dto) throws BusinessException {
		Avisos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		AvisosDAOImpl avisosDAO = new AvisosDAOImpl(bt);
		try 
		{
			avisosDAO.update(dao);
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
