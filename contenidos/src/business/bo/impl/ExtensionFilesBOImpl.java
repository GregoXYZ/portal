package business.bo.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.ExtensionFiles;
import business.dao.impl.ExtensionFilesDAOImpl;
import business.util.Convert;

import common.IConstantes;
import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.ExtensionFilesBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.ExtensionFilesDTO;
import common.util.FileUtils;

public class ExtensionFilesBOImpl implements ExtensionFilesBO {

	private static Log logger = LogFactory.getLog(ExtensionFilesBOImpl.class);

	@Override
	public void add(String path, byte[] file, ExtensionFilesDTO dto) throws BusinessException {
		ExtensionFiles dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtensionFilesDAOImpl ExtensionFilesDAO =  new ExtensionFilesDAOImpl(bt);
			Long pk = ExtensionFilesDAO.add(dao);
			dto.setExtFilPk(pk);

			if ( file != null )
			{
				FileUtils.carga(path, file);
			}
			bt.commitTx();
		} 
		catch (DaoException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		catch (IOException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());			
			throw new BusinessException(e);
		}
		finally {
			transactionFactory.endTx();
		}
	}

	@Override
	public void delete(ExtensionFilesDTO dto) throws BusinessException {
		ExtensionFiles dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtensionFilesDAOImpl ExtensionFilesDAO =  new ExtensionFilesDAOImpl(bt);
			ExtensionFilesDAO.delete(dao);
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
	public ExtensionFilesDTO getByPrimaryKey(Long id) throws BusinessException {
		ExtensionFilesDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtensionFilesDAOImpl ExtensionFilesDAO =  new ExtensionFilesDAOImpl(bt);
			dto = Convert.dao2dto(ExtensionFilesDAO.getByPrimaryKey(id));
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
	public ExtensionFilesDTO getByExtension(String uk) throws BusinessException {
		ExtensionFilesDTO dto = null;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try {
			ExtensionFilesDAOImpl ExtensionFilesDAO =  new ExtensionFilesDAOImpl(bt);
			ExtensionFiles mimefile = ExtensionFilesDAO.getByExtension(uk);
			
			if ( mimefile != null )
			{
				dto = Convert.dao2dto(mimefile);
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
	public ExtensionFilesDTO[] getExtensionFiles() throws BusinessException {
		List<ExtensionFiles> list = null;
		ExtensionFilesDTO[] ExtensionFiles = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtensionFilesDAOImpl ExtensionFilesDAO =  new ExtensionFilesDAOImpl(bt);
			list = ExtensionFilesDAO.getExtensionFiles();
			
			ExtensionFiles = new ExtensionFilesDTO[list.size()];
			int i = 0;
			for (Iterator<ExtensionFiles> iterator = list.iterator(); iterator.hasNext();i++)
			{
				ExtensionFiles[i] = Convert.dao2dto((ExtensionFiles) iterator.next());
				if ( ExtensionFiles[i].getExtFilIcon() == null )
					ExtensionFiles[i].setExtFilIcon(IConstantes.DEFAUL_ICON_DIR + IConstantes.DEFAUL_ICON_FILE);
				else
					ExtensionFiles[i].setExtFilIcon(IConstantes.DEFAUL_MIME_ICON_DIR + ExtensionFiles[i].getExtFilIcon());
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
		return ExtensionFiles;
	}

	@Override
	public void update(String path, byte[] file, ExtensionFilesDTO dto) throws BusinessException {
		ExtensionFiles dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtensionFilesDAOImpl ExtensionFilesDAO =  new ExtensionFilesDAOImpl(bt);
			ExtensionFilesDAO.update(dao);

			if ( file != null )
			{
				FileUtils.carga(path, file);
			}
			bt.commitTx();
		} 
		catch (DaoException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		catch (IOException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			transactionFactory.endTx();
		}		
	}

}
