package common.business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.GruposMenus;
import common.business.bo.GruposMenusBO;
import common.business.dao.impl.GruposMenusDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.business.util.Convert;
import common.dto.GruposMenusDTO;

public class GruposMenusBOImpl implements GruposMenusBO {

	private static Log logger = LogFactory.getLog(GruposMenusBOImpl.class);

	@Override
	public void add(GruposMenusDTO dto) throws BusinessException {
		GruposMenus dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		GruposMenusDAOImpl gruposMenusDAO = new GruposMenusDAOImpl(bt);
		try 
		{
			gruposMenusDAO.add(dao);
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
	public void delete(GruposMenusDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GruposMenusDTO getByPrimaryKey(Long usuFk, Long gruFk) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GruposMenusDTO[] getGruposMenus() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(GruposMenusDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Long[] getByMenu(Long urlFk) throws BusinessException {
		Long[] grupos;
		List<GruposMenus> list = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		GruposMenusDAOImpl gruposMenusDAO = new GruposMenusDAOImpl(bt);
		try 
		{
			list = gruposMenusDAO.getByMenu(urlFk);
			
			grupos = new Long[list.size()];
			int i = 0;
			for (Iterator<GruposMenus> iterator = list.iterator(); iterator.hasNext();i++)
			{
				grupos[i] = ((GruposMenus) iterator.next()).getId().getGruFk();
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

}
