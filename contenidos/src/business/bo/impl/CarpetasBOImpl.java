package business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.Assets;
import business.beans.Carpetas;
import business.dao.impl.AssetsDAOImpl;
import business.dao.impl.CarpetasDAOImpl;
import business.dao.impl.ExtraQueriesDAOImpl;
import business.util.Convert;

import common.IConstantes;
import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.CarpetasBO;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.AssetsDTO;
import common.dto.CarpetasDTO;

public class CarpetasBOImpl implements CarpetasBO {

	private static Log logger = LogFactory.getLog(CarpetasBOImpl.class);

	@Override
	public void add(Long parent, AssetsDTO dto) throws BusinessException {
		Assets asset = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			AssetsDAOImpl assetsDAO = new AssetsDAOImpl(bt);
			CarpetasDAOImpl carpetasDAO = new CarpetasDAOImpl(bt);
			assetsDAO.add(asset);
			Carpetas cParent = null;
			if ( parent != null )
				cParent = carpetasDAO.getByPrimaryKey(parent); 
			Carpetas carpeta =  new Carpetas();
			carpeta.setCarpetas(cParent);
			carpeta.setAssets(asset);
			carpetasDAO.add(carpeta);
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
	public void delete(CarpetasDTO dto) throws BusinessException {
		// Para borrar una carpeta se borra su asset
	}

	@Override
	public CarpetasDTO getByPrimaryKey(Long id) throws BusinessException {
		CarpetasDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CarpetasDAOImpl carpetasDAO = new CarpetasDAOImpl(bt);
			Carpetas carpeta = carpetasDAO.getByPrimaryKey(id);
			if ( carpeta != null )
				dto = Convert.dao2dto(carpetasDAO.getByPrimaryKey(id));
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
	public CarpetasDTO[] getCarpetas() throws BusinessException {
		List<Carpetas> list = null;
		CarpetasDTO[] carpetas = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CarpetasDAOImpl carpetasDAO = new CarpetasDAOImpl(bt);
			list = carpetasDAO.getCarpetas();
			
			carpetas = new CarpetasDTO[list.size()];
			int i = 0;
			for (Iterator<Carpetas> iterator = list.iterator(); iterator.hasNext();i++)
			{
				carpetas[i] = Convert.dao2dto((Carpetas) iterator.next());
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
		return carpetas;
	}

	@Override
	public void update(CarpetasDTO dto) throws BusinessException {
		Carpetas dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CarpetasDAOImpl carpetasDAO = new CarpetasDAOImpl(bt);
			AssetsDAOImpl assetsDAO = new AssetsDAOImpl(bt);
			assetsDAO.update(dao.getAssets());
			Carpetas carpeta =  new Carpetas();
			carpeta.setAssets(dao.getAssets());
			carpetasDAO.update(carpeta);			
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
	public CarpetasDTO[] getCarpetas(Long user, Long folder) throws BusinessException {
		List<?> list = null;
		CarpetasDTO[] carpetas = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			String tagIMG = IConstantes.DEFAUL_ICON_DIR + IConstantes.DEFAUL_ICON_FOLDER; 
		
			list = extraQueriesDAO.getCarpetas(user, folder);

			carpetas = new CarpetasDTO[list.size()];
			int i = 0;
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();i++)
			{
				Object[] elem = (Object[]) iterator.next();
				CarpetasDTO dto = new CarpetasDTO();
				dto.setAssPk((Long) elem[0]);
				dto.setAssNombre((String) elem[1]);
				dto.setIcon(tagIMG);
				dto.setAssDescripcion((String) elem[2]);
				dto.setAssFechaAlta((Long) elem[3]);
				dto.setUsuFk((Long) elem[4]);
				dto.setCarFk((Long) elem[5]);
				dto.setCarPk((Long) elem[6]);
				carpetas[i] = dto;
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
		return carpetas;
	}
	@Override
	public CarpetasDTO getByAsset(Long id) throws BusinessException {
		CarpetasDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CarpetasDAOImpl carpetasDAO = new CarpetasDAOImpl(bt);
			dto = Convert.dao2dto(carpetasDAO.getByAsset(id));
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
	public void move(long carPK, long carFk) throws BusinessException {
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			CarpetasDAOImpl carpetasDAO = new CarpetasDAOImpl(bt);
			carpetasDAO.move(carPK, carFk);
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
