package business.bo.impl;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;

import business.beans.Assets;
import business.beans.Carpetas;
import business.beans.Ficheros;
import business.beans.MimeFiles;
import business.dao.impl.AssetsDAOImpl;
import business.dao.impl.CarpetasDAOImpl;
import business.dao.impl.CompartidosDAOImpl;
import business.dao.impl.ExtraQueriesDAOImpl;
import business.dao.impl.FicherosDAOImpl;
import business.dao.impl.MimeFilesDAOImpl;
import business.util.Convert;

import common.Constants;
import common.IConstantes;
import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.Avisos;
import common.business.bo.CuotasBO;
import common.business.bo.FicherosBO;
import common.business.dao.impl.AvisosDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.AssetsDTO;
import common.dto.CuotasDTO;
import common.dto.FicherosDTO;
import common.util.FileUtils;
import common.util.spring.SpringUtil;

public class FicherosBOImpl implements FicherosBO {

	private static Log logger = LogFactory.getLog(FicherosBOImpl.class);
	
	@Override
	public void add(String path, FormFile file, AssetsDTO asset, FicherosDTO fichero, Long[] usuarios, String tags) throws BusinessException {
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{			
			logger.info("Insertando fichero " + asset.getAssNombre() + " con el nombre " + fichero.getFicSysName());
			//fichero.setFicChecksum(FileUtils.getChecksum(file.getInputStream()));
			fichero.setFicChecksum(FileUtils.writeToDisk(file, fichero.getFicSysName(), path));
			Assets assetDAO = addFile(bt, asset, fichero, usuarios);
			
			if (assetDAO != null)
			{
				ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
				extraQueriesDAO.addTags(asset.getUsuFk(), asset.getAssPk(), tags);

				if ( usuarios != null )
				{
					AvisosDAOImpl avisosDAO = new AvisosDAOImpl(bt);
					for (Long usuario:usuarios)
					{
						avisosDAO.add(new Avisos(assetDAO.getUsuFk(), usuario, new Date().getTime(), Constants.AVISO_COMPARTIDOS, assetDAO.getAssPk(), 1));
					}				
				}
			}
			bt.commitTx();
		} 
		catch (DaoException e) {
			bt.rollbackTx();
			FileUtils.delete(path + fichero.getFicSysName());
			logger.error(e);
			throw new BusinessException(e);
		} 
		catch (Exception ex) {
			bt.rollbackTx();
			FileUtils.delete(path + fichero.getFicSysName());
			logger.error(ex);
			throw new BusinessException(ex);
		}
		finally {
			transactionFactory.endTx();
		}
	}
	
	@Override
	public void addZipFile(String path, FormFile file, AssetsDTO asset, FicherosDTO fichero, Long[] usuarios, String tags) throws BusinessException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String fileName = sdf.format(new Date());
		ZipFile zipFile = null;
		Assets assetDAO = null;
		Integer cantidad = 0;

		CuotasBO cuotaBO = (CuotasBO) SpringUtil.getInstance().getBean("CuotasBO");
    	CuotasDTO cuota = cuotaBO.getCuotaRestante(asset.getUsuFk());
    	
    	if (cuota == null )
    	{
    		throw new BusinessException("No tienes cuota asignada, ponte en contacto con el administrador para solicitarla.");
    	}

		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();	
		try {
			FileUtils.writeToDisk(file, fileName + ".zip", path);

			zipFile = new ZipFile(path + fileName + ".zip");
			int ind = 0;
			Enumeration<?> e = zipFile.entries();

			long cuotaDisk = cuota.getCuoCuotaDisk();
			long cuotaFile = cuota.getCuoCuotaFile();
			long fechaAlta = new Date().getTime();
			while(e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();
				if (!entry.isDirectory())
				{
					fichero.setFicPk(null);
					String fName = fileName + "." + ind++;
					asset.setAssNombre(entry.getName());
					asset.setAssFechaAlta(fechaAlta);
					fichero.setFicSysName(fName);
					fichero.setFicSize(entry.getSize());
					cuotaDisk -= entry.getSize();
					
					if (cuotaDisk > 0 && cuotaFile > entry.getSize())
					{
						asset.setAssPk(null);
						
						//fichero.setFicChecksum(FileUtils.getChecksum(zipFile.getInputStream(entry)));
						logger.info("Insertando fichero " + entry.getName() + " con el nombre " + fName);							
						fichero.setFicChecksum(FileUtils.copyInputStream(zipFile.getInputStream(entry),
				                new BufferedOutputStream(new FileOutputStream(path + fName))));
						assetDAO = addFile(bt, asset, fichero, usuarios);
						cantidad++;
						
						if (assetDAO != null)
						{
							ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
							extraQueriesDAO.addTags(asset.getUsuFk(), asset.getAssPk(), tags);
						}
					}
					else if (cuotaDisk < 0)
					{
						FileUtils.deleteFiles(path, fileName);
						logger.error("Cuota de disco agotada no se insertarán mas archivos.");
			    		throw new BusinessException("Cuota de disco agotada no se insertarán mas archivos.");
					}
					else if (cuotaFile < entry.getSize())
					{
						FileUtils.deleteFiles(path, fileName);
						logger.error("El archivo " + entry.getName() + "(" + entry.getSize() + 
								") excede el tamaño máximo de archivo (" + cuotaFile + ").");
			    		throw new BusinessException("El archivo " + entry.getName() + "(" + entry.getSize() + 
								") excede el tamaño máximo de archivo (" + cuotaFile + ").");
					}				
				}
			}

			if ( usuarios != null && cantidad > 0)
			{
				AvisosDAOImpl avisosDAO = new AvisosDAOImpl(bt);
				for (Long usuario:usuarios)
				{
					avisosDAO.add(new Avisos(assetDAO.getUsuFk(), usuario, new Date().getTime(), Constants.AVISO_COMPARTIDOS, assetDAO.getAssPk(), cantidad));
				}				
			}
			bt.commitTx();
		} catch (Exception ex) {
			FileUtils.deleteFiles(path, fileName);
			bt.rollbackTx();
			logger.error(ex);
			throw new BusinessException(ex);
		} finally {
			try {
				zipFile.close();
			} catch (IOException e) {
				logger.error(e);
				throw new BusinessException(e);
			}
			FileUtils.delete(path + fileName + ".zip");
			transactionFactory.endTx();
		}		
	}
	
	@Override
	public void delete(FicherosDTO dto) throws BusinessException {
		// Para borrar una carpeta se borra su asset
	}

	@Override
	public FicherosDTO getByPrimaryKey(Long id) throws BusinessException {
		FicherosDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			FicherosDAOImpl ficherosDAO = new FicherosDAOImpl(bt);
			dto = Convert.dao2dto(ficherosDAO.getByPrimaryKey(id));
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return dto;
	}

	@Override
	public FicherosDTO[] getFicheros() throws BusinessException {
		List<Ficheros> list = null;
		FicherosDTO[] ficheros = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			FicherosDAOImpl ficherosDAO = new FicherosDAOImpl(bt);
			list = ficherosDAO.getFicheros();
			
			ficheros = new FicherosDTO[list.size()];
			int i = 0;
			for (Iterator<Ficheros> iterator = list.iterator(); iterator.hasNext();i++)
			{
				ficheros[i] = Convert.dao2dto((Ficheros) iterator.next());
			}
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return ficheros;
	}

	@Override
	public void update(FicherosDTO dto) throws BusinessException {
		Ficheros dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			if (dto.getAssPk() != null && dto.getAssPk() > 0)
			{
				AssetsDAOImpl assetDAO = new AssetsDAOImpl(bt);
				Assets asset = assetDAO.getByPrimaryKey(dto.getAssPk());
				dao.setAssets(asset);
			}
			
			if (dto.getCarFk() != null && dto.getCarFk() > 0)
			{
				CarpetasDAOImpl carpetaDAO = new CarpetasDAOImpl(bt);
				Carpetas carpeta = carpetaDAO.getByPrimaryKey(dto.getCarFk());
				dao.setCarpetas(carpeta);
			}

			FicherosDAOImpl ficherosDAO = new FicherosDAOImpl(bt);
			ficherosDAO.update(dao);
			bt.commitTx();
		} 
		catch (DaoException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
		} 
		finally {
			transactionFactory.endTx();
		}		
	}
	
	@Override
	public FicherosDTO[] getFicheros(Long user, Long folder) throws BusinessException {
		List<?> list = null;
		FicherosDTO[] ficheros = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			MimeFilesDAOImpl mimeFilesDAO = new MimeFilesDAOImpl(bt);
			list = extraQueriesDAO.getFicheros(user, folder);

			ficheros = new FicherosDTO[list.size()];
			int i = 0;
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();i++)
			{
				Object[] elem = (Object[]) iterator.next();

				MimeFiles mime = mimeFilesDAO.getByPrimaryKey((Long) elem[7]);
				String icon = mime.getMimFilIcon();
				if ( icon == null || icon.length() == 0 )
					icon = IConstantes.DEFAUL_ICON_DIR + IConstantes.DEFAUL_ICON_FILE;
				else
					icon = IConstantes.DEFAUL_MIME_ICON_DIR + icon;

				FicherosDTO dto = new FicherosDTO();
				dto.setAssPk((Long) elem[0]);
				dto.setAssNombre((String) elem[1]);
				dto.setIcon(icon);
				dto.setAssDescripcion((String) elem[2]);
				dto.setAssFechaAlta((Long) elem[3]);
				dto.setFicPk((Long) elem[4]);
				dto.setFicSize((Long) elem[5]);
				dto.setCarFk((Long) elem[6]);
				dto.setMimFilFk((Long) elem[7]);
				dto.setMimFilExtension((String) elem[8]);
				dto.setMimFilMime((String) elem[9]);
				dto.setFicSysName((String) elem[10]);
				dto.setUsuFk((Long) elem[11]);
				ficheros[i] = dto;
			}
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return ficheros;
	}
	@Override
	public FicherosDTO getByAsset(Long id) throws BusinessException {
		FicherosDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			FicherosDAOImpl ficherosDAO = new FicherosDAOImpl(bt);
			dto = Convert.dao2dto(ficherosDAO.getByAsset(id));
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return dto;
	}

	private Assets addFile(BusinessTransactionBo bt, AssetsDTO asset, FicherosDTO fichero, Long[] usuarios) throws BusinessException
	{
		Ficheros ficDAO = Convert.dto2dao(fichero);
		Assets assDAO = Convert.dto2dao(asset);
		
		if (ficDAO.getMimFilFk() == null || ficDAO.getMimFilFk().equals(0L))
		{
			ficDAO.setMimFilFk(getMimeFile(bt, asset.getAssNombre()));
			if (ficDAO.getMimFilFk() == null)
				return null;
		}
		
		try {
			if ( fichero.getCarFk() == null )
			{
				ficDAO.setCarpetas(null);
			}
			else
			{
				CarpetasDAOImpl carpetasDAO = new CarpetasDAOImpl(bt);
				Carpetas carpeta = carpetasDAO.getByPrimaryKey(fichero.getCarFk());
				ficDAO.setCarpetas(carpeta);
			}
			
			AssetsDAOImpl assetsDAO = new AssetsDAOImpl(bt);
			FicherosDAOImpl ficherosDAO = new FicherosDAOImpl(bt);
			assDAO.setAssPk(assetsDAO.add(assDAO));
			ficDAO.setAssets(assDAO);
			fichero.setFicPk(ficherosDAO.add(ficDAO));
			ficDAO.setFicPk(fichero.getFicPk());
			
			asset.setAssPk(assDAO.getAssPk());
			
			if ( usuarios != null )
			{
				CompartidosDAOImpl compartidosDAO = new CompartidosDAOImpl(bt);
				compartidosDAO.comparte(assDAO, usuarios);
			}			
		} catch (DaoException e) {
    		throw new BusinessException(e);
		}
		
		return assDAO;
	}

	private Long getMimeFile(BusinessTransactionBo bt, String fileName) throws BusinessException
	{
		String typeFile="";
		int pos = fileName.lastIndexOf(".");
		if (pos!=-1){
			typeFile = fileName.substring(pos+1);
		}

		MimeFiles mimeFile;
		
		try {
			MimeFilesDAOImpl mimeFilesDAO = new MimeFilesDAOImpl(bt);
			mimeFile = mimeFilesDAO.getByExtension(typeFile);
		} catch (DaoException e) {
    		throw new BusinessException(e);
		}
		
		if ( mimeFile == null )
		{
    		logger.error("El archivo '" + fileName + "' es de un tipo de archivo (" + typeFile + ") no soportado.");
    		throw new BusinessException("El archivo '" + fileName + "' tiene un tipo de archivo (" + typeFile + ") no soportado.");
			//return null;				
		}
		else
			return mimeFile.getMimFilPk();
	}
	@Override
	public void move(long ficPK, long carFk) throws BusinessException {
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			FicherosDAOImpl ficherosDAO = new FicherosDAOImpl(bt);
			ficherosDAO.move(ficPK, carFk);
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
	public FicherosDTO[] getDuplicados(Long user) throws BusinessException {
		List<Ficheros> list = null;
		FicherosDTO[] ficheros = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			MimeFilesDAOImpl mimeFilesDAO = new MimeFilesDAOImpl(bt);
			FicherosDAOImpl ficherosDAO = new FicherosDAOImpl(bt);
			list = ficherosDAO.getDuplicados(user);
			
			ficheros = new FicherosDTO[list.size()];
			int i = 0;
			for (Ficheros fichero : list)
			{
				FicherosDTO dto = Convert.dao2dto(fichero);
				MimeFiles mime = mimeFilesDAO.getByPrimaryKey(fichero.getMimFilFk());
				dto.setMimFilExtension(mime.getMimFilExtension());
				dto.setMimFilMime(mime.getMimFilMime());
				String icon = mime.getMimFilIcon();
				if ( icon == null || icon.length() == 0 )
					icon = IConstantes.DEFAUL_ICON_DIR + IConstantes.DEFAUL_ICON_FILE;
				else
					icon = IConstantes.DEFAUL_MIME_ICON_DIR + icon;
				
				dto.setIcon(icon);

				ficheros[i++] = dto;
			}
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return ficheros;
	}

}
