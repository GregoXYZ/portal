package business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.Assets;
import business.beans.Links;
import business.dao.impl.AssetsDAOImpl;
import business.dao.impl.ExtraQueriesDAOImpl;
import business.dao.impl.LinksDAOImpl;
import business.util.Convert;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.LinksBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.AssetsDTO;
import common.dto.LinksDTO;

public class LinksBOImpl implements LinksBO {

	private static Log logger = LogFactory.getLog(LinksBOImpl.class);

	@Override
	public void add(LinksDTO link, AssetsDTO asset) throws BusinessException {
		Assets assetDAO = Convert.dto2dao(asset);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			LinksDAOImpl linksDAO = new LinksDAOImpl(bt);
			AssetsDAOImpl assetsDAO = new AssetsDAOImpl(bt);
			link.setAssPk(assetsDAO.add(assetDAO));
			linksDAO.add(Convert.dto2dao(link));			
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
	public void delete(LinksDTO dto) throws BusinessException {
		// Para borrar una link se borra su asset
	}

	@Override
	public LinksDTO getByPrimaryKey(Long id) throws BusinessException {
		LinksDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			LinksDAOImpl linksDAO = new LinksDAOImpl(bt);
			Links link = linksDAO.getByPrimaryKey(id);
			if ( link != null )
				dto = Convert.dao2dto(linksDAO.getByPrimaryKey(id));
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
	public LinksDTO[] getLinks() throws BusinessException {
		List<Links> list = null;
		LinksDTO[] links = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			LinksDAOImpl linksDAO = new LinksDAOImpl(bt);
			list = linksDAO.getLinks();
			
			links = new LinksDTO[list.size()];
			int i = 0;
			for (Iterator<Links> iterator = list.iterator(); iterator.hasNext();i++)
			{
				links[i] = Convert.dao2dto((Links) iterator.next());
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
		return links;
	}

	@Override
	public void update(LinksDTO dto, AssetsDTO asset) throws BusinessException {
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			LinksDAOImpl linksDAO = new LinksDAOImpl(bt);
			AssetsDAOImpl assetsDAO = new AssetsDAOImpl(bt);
			assetsDAO.update(Convert.dto2dao(asset));
			linksDAO.update(Convert.dto2dao(dto));			
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
	public LinksDTO[] getLinks(Long user, Long asset) throws BusinessException {
		List<?> list = null;
		LinksDTO[] links = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			//String tagIMG = IConstantes.DEFAUL_ICON_DIR + IConstantes.DEFAUL_ICON_LINK; 
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			list = extraQueriesDAO.getLinks(user, asset);

			links = new LinksDTO[list.size()];
			int i = 0;
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();i++)
			{
				LinksDTO elem = (LinksDTO) iterator.next();
				links[i] = elem;
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
		return links;
	}
}
