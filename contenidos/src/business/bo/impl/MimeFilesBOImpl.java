package business.bo.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.MimeFiles;
import business.dao.impl.MimeFilesDAOImpl;
import business.util.Convert;

import common.IConstantes;
import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.MimeFilesBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.MimeFilesDTO;
import common.util.FileUtils;

public class MimeFilesBOImpl implements MimeFilesBO {

	private static Log logger = LogFactory.getLog(MimeFilesBOImpl.class);

	@Override
	public void add(String path, byte[] file, MimeFilesDTO dto) throws BusinessException {
		MimeFiles dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			MimeFilesDAOImpl mimeFilesDAO =  new MimeFilesDAOImpl(bt);
			Long pk = mimeFilesDAO.add(dao);
			dto.setMimFilPk(pk);

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
	public void delete(MimeFilesDTO dto) throws BusinessException {
		MimeFiles dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			MimeFilesDAOImpl mimeFilesDAO =  new MimeFilesDAOImpl(bt);
			mimeFilesDAO.delete(dao);
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
	public MimeFilesDTO getByPrimaryKey(Long id) throws BusinessException {
		MimeFilesDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			MimeFilesDAOImpl mimeFilesDAO =  new MimeFilesDAOImpl(bt);
			dto = Convert.dao2dto(mimeFilesDAO.getByPrimaryKey(id));
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
	public MimeFilesDTO getByExtension(String uk) throws BusinessException {
		MimeFilesDTO dto = null;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try {
			MimeFilesDAOImpl mimeFilesDAO =  new MimeFilesDAOImpl(bt);
			MimeFiles mimefile = mimeFilesDAO.getByExtension(uk);
			
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
	public MimeFilesDTO[] getMimeFiles() throws BusinessException {
		List<MimeFiles> list = null;
		MimeFilesDTO[] mimefiles = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			MimeFilesDAOImpl mimeFilesDAO =  new MimeFilesDAOImpl(bt);
			list = mimeFilesDAO.getMimeFiles();
			
			mimefiles = new MimeFilesDTO[list.size()];
			int i = 0;
			for (Iterator<MimeFiles> iterator = list.iterator(); iterator.hasNext();i++)
			{
				mimefiles[i] = Convert.dao2dto((MimeFiles) iterator.next());
				if ( mimefiles[i].getMimFilIcon() == null )
					mimefiles[i].setMimFilIcon(IConstantes.DEFAUL_ICON_DIR + IConstantes.DEFAUL_ICON_FILE);
				else
					mimefiles[i].setMimFilIcon(IConstantes.DEFAUL_MIME_ICON_DIR + mimefiles[i].getMimFilIcon());
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
		return mimefiles;
	}

	@Override
	public void update(String path, byte[] file, MimeFilesDTO dto) throws BusinessException {
		MimeFiles dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			MimeFilesDAOImpl mimeFilesDAO =  new MimeFilesDAOImpl(bt);
			mimeFilesDAO.update(dao);

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
