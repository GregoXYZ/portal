package business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.Tags;
import business.beans.TagsAsset;
import business.dao.impl.TagsAssetDAOImpl;
import business.dao.impl.TagsDAOImpl;
import business.util.Convert;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.TagsBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.TagsDTO;

public class TagsBOImpl implements TagsBO {

	private static Log logger = LogFactory.getLog(TagsBOImpl.class);

	@Override
	public void add(TagsDTO dto) throws BusinessException {
		Tags dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			TagsDAOImpl tagsDAO = new TagsDAOImpl(bt);
			Long pk = tagsDAO.add(dao);
			dto.setTagPk(pk);
			
			if (dto.getAssPk()!=null)
			{
				TagsAssetDAOImpl tagsAssetDAO = new TagsAssetDAOImpl(bt);
				TagsAsset tagAsset = new TagsAsset(dto.getTagPk(), dto.getAssPk(), dto.getUsuPk());
				tagsAssetDAO.add(tagAsset);
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
	public void delete(TagsDTO dto) throws BusinessException {
		Tags dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			TagsDAOImpl tagsDAO = new TagsDAOImpl(bt);
			tagsDAO.delete(dao);
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
	public void deleteTagAsset(TagsDTO dto) throws BusinessException {
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			TagsAssetDAOImpl tagsAssetDAO = new TagsAssetDAOImpl(bt);
			TagsAsset dao = tagsAssetDAO.getByUniqueKey(dto.getTagPk(), dto.getAssPk(), dto.getUsuPk());
			tagsAssetDAO.delete(dao);
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
	public TagsDTO getByPrimaryKey(Long id) throws BusinessException {
		TagsDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			TagsDAOImpl tagsDAO = new TagsDAOImpl(bt);
			Tags dao = tagsDAO.getByPrimaryKey(id);
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
	public TagsDTO[] getTags() throws BusinessException {
		List<Tags> list = null;
		TagsDTO[] tags = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			TagsDAOImpl tagsDAO = new TagsDAOImpl(bt);
			list = tagsDAO.getTags();
			
			tags = new TagsDTO[list.size()];
			int i = 0;
			for (Iterator<Tags> iterator = list.iterator(); iterator.hasNext();i++)
			{
				tags[i] = Convert.dao2dto((Tags) iterator.next());
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
		return tags;
	}

	@Override
	public void update(TagsDTO dto) throws BusinessException {
		Tags dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			TagsDAOImpl tagsDAO = new TagsDAOImpl(bt);
			tagsDAO.update(dao);
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
	public void updateContadores(String tags) throws BusinessException {
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			TagsDAOImpl tagsDAO = new TagsDAOImpl(bt);
			tagsDAO.updateContadores(tags);
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
