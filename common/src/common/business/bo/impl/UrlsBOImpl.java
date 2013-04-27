package common.business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.Urls;
import common.business.beans.UrlsGrupos;
import common.business.beans.UrlsGruposId;
import common.business.bo.UrlsBO;
import common.business.dao.impl.UrlsDAOImpl;
import common.business.dao.impl.UrlsGruposDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.business.util.Convert;
import common.dto.UrlsDTO;

public class UrlsBOImpl implements UrlsBO {

	private static Log logger = LogFactory.getLog(UrlsBOImpl.class);

	@Override
	public void add(UrlsDTO dto) throws BusinessException {
		Urls dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UrlsDAOImpl urlsDAO = new UrlsDAOImpl(bt);
		UrlsGruposDAOImpl urlsGruposDAO = new UrlsGruposDAOImpl(bt);
		try 
		{
			Long pk = urlsDAO.add(dao);
			dto.setUrlPk(pk);

			for (int ind = 0; ind < dto.getGrupos().length; ind++)
			{
				UrlsGruposId id = new UrlsGruposId(pk, dto.getGrupos()[ind]);
				UrlsGrupos ug = new UrlsGrupos();
				ug.setId(id);
				urlsGruposDAO.add(ug);
			}

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
	public void delete(UrlsDTO dto) throws BusinessException {
		Urls dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UrlsDAOImpl urlsDAO = new UrlsDAOImpl(bt);
		try 
		{
			urlsDAO.delete(dao);
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
	public UrlsDTO getByPrimaryKey(Long id) throws BusinessException {
		UrlsDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UrlsDAOImpl urlsDAO = new UrlsDAOImpl(bt);
		try 
		{
			dto = Convert.dao2dto(urlsDAO.getByPrimaryKey(id));
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
	public UrlsDTO getByCode(String uk) throws BusinessException {
		UrlsDTO dto = null;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UrlsDAOImpl urlsDAO = new UrlsDAOImpl(bt);
		try {
			Urls url = urlsDAO.getByCode(uk);
			
			if ( url != null )
			{
				dto = Convert.dao2dto(url);
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
	public UrlsDTO[] getUrls() throws BusinessException {
		List<Urls> list = null;
		UrlsDTO[] urls = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UrlsDAOImpl urlsDAO = new UrlsDAOImpl(bt);
		try 
		{
			list = urlsDAO.getUrls();
			
			urls = new UrlsDTO[list.size()];
			int i = 0;
			for (Iterator<Urls> iterator = list.iterator(); iterator.hasNext();i++)
			{
				urls[i] = Convert.dao2dto((Urls) iterator.next());
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
		return urls;
	}

	@Override
	public void update(UrlsDTO dto) throws BusinessException {
		Urls dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UrlsDAOImpl urlsDAO = new UrlsDAOImpl(bt);
		UrlsGruposDAOImpl urlsGruposDAO = new UrlsGruposDAOImpl(bt);
		try 
		{
			urlsDAO.update(dao);

			urlsGruposDAO.deleteByUrl(dto.getUrlPk());
			for (int ind = 0; ind < dto.getGrupos().length; ind++)
			{
				if (dto.getGrupos()[ind] > 0)
				{
					UrlsGruposId id = new UrlsGruposId(dto.getUrlPk(), dto.getGrupos()[ind]);
					UrlsGrupos ug = new UrlsGrupos();
					ug.setId(id);
					urlsGruposDAO.add(ug);
				}
			}
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
