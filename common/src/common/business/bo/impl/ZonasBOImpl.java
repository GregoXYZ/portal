package common.business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.Zonas;
import common.business.bo.ZonasBO;
import common.business.dao.impl.ZonasDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.business.util.Convert;
import common.dto.ZonasDTO;

public class ZonasBOImpl implements ZonasBO {

	private static Log logger = LogFactory.getLog(ZonasBOImpl.class);

	@Override
	public void add(ZonasDTO dto) throws BusinessException {
		Zonas dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		ZonasDAOImpl zonasDAO =  new ZonasDAOImpl(bt);
		try 
		{
			Long pk = zonasDAO.add(dao);
			dto.setZonPk(pk);
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
	public void update(ZonasDTO dto) throws BusinessException {
		Zonas dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		ZonasDAOImpl zonasDAO =  new ZonasDAOImpl(bt);
		try 
		{
			zonasDAO.update(dao);
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
	public void delete(ZonasDTO dto) throws BusinessException {
		Zonas dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		ZonasDAOImpl zonasDAO =  new ZonasDAOImpl(bt);
		try 
		{
			zonasDAO.delete(dao);
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
	public ZonasDTO getByPrimaryKey(Long id) throws BusinessException {
		ZonasDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		ZonasDAOImpl zonasDAO =  new ZonasDAOImpl(bt);
		try 
		{
			dto = Convert.dao2dto(zonasDAO.getByPrimaryKey(id));
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
	public ZonasDTO getByCode(String uk) throws BusinessException {
		ZonasDTO dto = null;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		ZonasDAOImpl zonasDAO =  new ZonasDAOImpl(bt);
		try {
			Zonas zona = zonasDAO.getByCode(uk);
			
			if ( zona != null )
			{
				dto = Convert.dao2dto(zona);
			}
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return dto;
	}

	@Override
	public ZonasDTO[] getZonas() throws BusinessException {
		List<Zonas> list = null;
		ZonasDTO[] zonas = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		ZonasDAOImpl zonasDAO =  new ZonasDAOImpl(bt);
		try 
		{
			list = zonasDAO.getZonas();
			
			zonas = new ZonasDTO[list.size()];
			int i = 0;
			for (Iterator<Zonas> iterator = list.iterator(); iterator.hasNext();i++)
			{
				zonas[i] = Convert.dao2dto((Zonas) iterator.next());
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
		return zonas;
	}
}
