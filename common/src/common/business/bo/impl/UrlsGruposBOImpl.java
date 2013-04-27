package common.business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.UrlsGrupos;
import common.business.bo.UrlsGruposBO;
import common.business.dao.impl.UrlsGruposDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.business.util.Convert;
import common.dto.UrlsGruposDTO;

public class UrlsGruposBOImpl implements UrlsGruposBO {

	private static Log logger = LogFactory.getLog(UrlsGruposBOImpl.class);

	@Override
	public void add(UrlsGruposDTO dto) throws BusinessException {
		UrlsGrupos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UrlsGruposDAOImpl urlsGruposDAO = new UrlsGruposDAOImpl(bt);
		try 
		{
			urlsGruposDAO.add(dao);
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
	public void delete(UrlsGruposDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UrlsGruposDTO getByPrimaryKey(Long usuFk, Long gruFk) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UrlsGruposDTO[] getUrlsGrupos() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UrlsGruposDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Long[] getByUrl(Long urlFk) throws BusinessException {
		Long[] grupos;
		List<UrlsGrupos> list = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UrlsGruposDAOImpl urlsGruposDAO = new UrlsGruposDAOImpl(bt);
		try 
		{
			list = urlsGruposDAO.getByUrl(urlFk);
			
			grupos = new Long[list.size()];
			int i = 0;
			for (Iterator<UrlsGrupos> iterator = list.iterator(); iterator.hasNext();i++)
			{
				grupos[i] = ((UrlsGrupos) iterator.next()).getId().getGruFk();
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
