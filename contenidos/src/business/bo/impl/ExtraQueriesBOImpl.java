package business.bo.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.beans.Assets;
import business.beans.Compartidos;
import business.beans.Ficheros;
import business.beans.MimeFiles;
import business.beans.Tags;
import business.dao.impl.AssetsDAOImpl;
import business.dao.impl.CompartidosDAOImpl;
import business.dao.impl.ExtraQueriesDAOImpl;
import business.dao.impl.MimeFilesDAOImpl;

import common.Constants;
import common.IConstantes;
import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.Avisos;
import common.business.beans.Usuarios;
import common.business.bo.ExtraQueriesBO;
import common.business.dao.impl.AvisosDAOImpl;
import common.business.dao.impl.UsuariosDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.business.util.Convert;
import common.dto.AssetsDTO;
import common.dto.CarpetasDTO;
import common.dto.FicherosDTO;
import common.dto.TagsDTO;
import common.dto.TagsNubeDTO;
import common.dto.UsuariosDTO;

public class ExtraQueriesBOImpl implements ExtraQueriesBO {

	private static Log logger = LogFactory.getLog(ExtraQueriesBOImpl.class);
	
	@Override
	public UsuariosDTO[] getUsersContenidos() throws BusinessException {
		List<Usuarios> list = null;
		UsuariosDTO[] usuarios = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			list = extraQueriesDAO.getUsersContenidos();
			usuarios = new UsuariosDTO[list.size()];
			int i = 0;
			for (Iterator<Usuarios> iterator = list.iterator(); iterator.hasNext();i++)
			{
				usuarios[i] = Convert.dao2dto(iterator.next());
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
		return usuarios;
	}
	
	@Override
	public void updateFichero(AssetsDTO asset, Long[] usuarios, String tags)
			throws BusinessException {
		Assets dao = business.util.Convert.dto2dao(asset);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			AssetsDAOImpl assetsDAO = new AssetsDAOImpl(bt);
			assetsDAO.update(dao);
			
			comparte(bt, asset, usuarios);

			if (tags != null)
			{
				ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
				extraQueriesDAO.addTags(asset.getUsuFk(), asset.getAssPk(), tags);
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
	public void comparteFicheros(AssetsDTO[] assets, Long[] usuarios, boolean reemplaza, String tags)
			throws BusinessException {
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			
			comparte(bt, assets, usuarios);

			for(AssetsDTO asset:assets)
			{
				extraQueriesDAO.addTags(asset.getUsuFk(), asset.getAssPk(), tags);
			}
			
			if (reemplaza)
			{
				for(AssetsDTO asset:assets)
				{
					CompartidosDAOImpl compartidosDAO = new CompartidosDAOImpl(bt);
					List<Compartidos> compartidos = compartidosDAO.getByAsset(asset.getAssPk());
					
					for (Long userPk:usuarios)
					{
						Compartidos elemento = new Compartidos(asset.getAssPk(), userPk);
						if (compartidos.contains(elemento))
						{
							compartidos.remove(elemento);
						}
					}
					
					for (Compartidos compartido: compartidos)
					{
						compartidosDAO.delete(compartido);
					}
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
	
	private void comparte(BusinessTransactionBo bt, AssetsDTO asset, Long[] usuarios) throws DaoException
	{
		CompartidosDAOImpl compartidosDAO = new CompartidosDAOImpl(bt);
		List<Compartidos> compartidos = compartidosDAO.getByAsset(asset.getAssPk());
		if ( usuarios != null)
		{
			// Añado los nuevos
			for (Long userPk:usuarios)
			{
				Compartidos elemento = new Compartidos(asset.getAssPk(), userPk);
				if (!compartidos.contains(elemento))
				{
					elemento.setUsuFk_source(asset.getUsuFk());
					compartidosDAO.add(elemento);

					AvisosDAOImpl avisosDAO = new AvisosDAOImpl(bt);
					avisosDAO.add(new Avisos(asset.getUsuFk(), userPk, new Date().getTime(), Constants.AVISO_COMPARTIDOS, asset.getAssPk(), 1));
				}
			}				
		}
		else
		{
			for (Compartidos compartido : compartidos)
			{
				compartidosDAO.delete(compartido);
			}
		}
	}
	
	private void comparte(BusinessTransactionBo bt, AssetsDTO[] assets, Long[] usuarios) throws DaoException
	{
		if (usuarios != null)
		{
			// Añado los nuevos
			for (Long userPk:usuarios)
			{
				int contador = 0;
				Long propietario = null;
				for (AssetsDTO asset:assets)
				{
					CompartidosDAOImpl compartidosDAO = new CompartidosDAOImpl(bt);
					List<Compartidos> compartidos = compartidosDAO.getByAsset(asset.getAssPk());
					Compartidos elemento = new Compartidos(asset.getAssPk(), userPk);
					
					if (!compartidos.contains(elemento))
					{
						elemento.setUsuFk_source(asset.getUsuFk());
						compartidosDAO.add(elemento);
						propietario = asset.getUsuFk();
						contador ++;
					}					
				}
				
				if (contador > 0)
				{
					AvisosDAOImpl avisosDAO = new AvisosDAOImpl(bt);
					avisosDAO.add(new Avisos(propietario, userPk, new Date().getTime(), Constants.AVISO_COMPARTIDOS, null, contador));					
				}
			}				
		}
		else
		{
			for (AssetsDTO asset:assets)
			{
				CompartidosDAOImpl compartidosDAO = new CompartidosDAOImpl(bt);
				List<Compartidos> compartidos = compartidosDAO.getByAsset(asset.getAssPk());
				
				for (Compartidos compartido : compartidos)
				{
					compartidosDAO.delete(compartido);
				}
			}
			
		}
	}

	@Override
	public UsuariosDTO[] getUsersCompartir(Long user) throws BusinessException {
		UsuariosDTO[] usuarios = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			List<Usuarios> list = extraQueriesDAO.getUsersContenidos(user);
			usuarios = new UsuariosDTO[list.size()];
			int i = 0;
			for (Iterator<Usuarios> iterator = list.iterator(); iterator.hasNext();)
			{
				Usuarios usuario = iterator.next();
				usuarios[i++] = Convert.dao2dto(usuario);					
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
		return usuarios;
	}
	@Override
	public AssetsDTO[] getAssetsCompartidos(Long user, Long propietario)
			throws BusinessException {
		AssetsDTO[] assets = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			List<Assets> list = extraQueriesDAO.getAssetsCompartidos(user, propietario);
			assets = new AssetsDTO[list.size()];
			int i = 0;
			for (Iterator<Assets> iterator = list.iterator(); iterator.hasNext();)
			{
				Assets usuario = iterator.next();
				assets[i++] = business.util.Convert.dao2dto(usuario);					
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
		return assets;
	}
	@Override
	public UsuariosDTO[] getUsersComparten(Long user) throws BusinessException {
		UsuariosDTO[] usuarios = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl(bt);
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			Usuarios yo = usuariosDAO.getByPrimaryKey(user);
			List<Usuarios> list = extraQueriesDAO.getUsersComparten(user);
			usuarios = new UsuariosDTO[list.size() +1];
			usuarios[0] = Convert.dao2dto(yo);
			int i = 1;
			for (Iterator<Usuarios> iterator = list.iterator(); iterator.hasNext();)
			{
				Usuarios usuario = (Usuarios) iterator.next();
				usuarios[i++] = Convert.dao2dto(usuario);					
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
		return usuarios;
	}
	
	@Override
	public FicherosDTO[] getFicherosCompartidos(Long user, Long propietario, Long folder) throws BusinessException {
		FicherosDTO[] ficheros = null;		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			List<?> list = extraQueriesDAO.getFicherosCompartidos(user, propietario, folder);

			MimeFilesDAOImpl mimeFilesDAO = new MimeFilesDAOImpl(bt);
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
	public FicherosDTO[] getMyFicherosCompartidos(Long user, Long folder)
			throws BusinessException {
		FicherosDTO[] ficheros = null;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			List<?> list = extraQueriesDAO.getMyFicherosCompartidos(user, folder);

			MimeFilesDAOImpl mimeFilesDAO = new MimeFilesDAOImpl(bt);
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
	public CarpetasDTO[] getCarpetasConCompartidos(Long user, Long propietario)
			throws BusinessException {
		CarpetasDTO[] carpetas = null;		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			List<?> list = extraQueriesDAO.getCarpetasConCompartidos(user, propietario);

			carpetas = new CarpetasDTO[list.size()];
			int i = 0;
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();i++)
			{
				Object[] elem = (Object[]) iterator.next();
				CarpetasDTO dto = new CarpetasDTO();
				dto.setCarPk((Long) elem[0]);
				dto.setAssPk((Long) elem[1]);
				dto.setAssNombre((String) elem[2]);
				dto.setAssDescripcion((String) elem[3]);
				carpetas[i] = dto;
			}
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return carpetas;
	}
	
	@Override
	public CarpetasDTO[] getMyCarpetasCompartidas(Long user) throws BusinessException {
		CarpetasDTO[] carpetas = null;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			List<?> list = extraQueriesDAO.getMyCarpetasCompartidas(user);

			carpetas = new CarpetasDTO[list.size()];
			int i = 0;
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();i++)
			{
				Object[] elem = (Object[]) iterator.next();
				CarpetasDTO dto = new CarpetasDTO();
				dto.setCarPk((Long) elem[0]);
				dto.setAssPk((Long) elem[1]);
				dto.setAssNombre((String) elem[2]);
				dto.setAssDescripcion((String) elem[3]);
				carpetas[i] = dto;
			}
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return carpetas;
	}
	@Override
	public TagsDTO[] getTags(Long user, Long asset) throws BusinessException {
		TagsDTO[] tags = null;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			List<Tags> list = extraQueriesDAO.getTags(user, asset);
			tags = new TagsDTO[list.size()];
			int i = 0;
			for (Iterator<Tags> iterator = list.iterator(); iterator.hasNext();i++)
			{
				tags[i] = business.util.Convert.dao2dto(iterator.next());
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
	public AssetsDTO[] getLinks(Long user, Long asset) throws BusinessException {
		AssetsDTO[] assets = null;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			AssetsDAOImpl assetsDAO = new AssetsDAOImpl(bt);
			List<Assets> list = null;
			Assets assetDAO = assetsDAO.getByPrimaryKey(asset);
			
			if (user.compareTo(assetDAO.getUsuFk()) == 0)
			{
				ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
				list = extraQueriesDAO.getLinks(user, asset);				
			}
			else
			{
				CompartidosDAOImpl compartidosDAO = new CompartidosDAOImpl(bt);
				Compartidos compartido = compartidosDAO.getByUniqueKey(user, asset);
				if (compartido != null)
				{
					ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
					list = extraQueriesDAO.getLinks(assetDAO.getUsuFk(), asset);
				}
			}
			assets = new AssetsDTO[list.size()];
			int i = 0;
			for (Iterator<Assets> iterator = list.iterator(); iterator.hasNext();i++)
			{
				assets[i] = business.util.Convert.dao2dto(iterator.next());
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
		return assets;
	}
	@Override
	public TagsNubeDTO[] getTagsNube(Long user, Integer tipo) throws BusinessException {
		TagsNubeDTO[] tagsNube = null;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			List<?> list = extraQueriesDAO.getTagsNube(user, tipo);

			tagsNube = new TagsNubeDTO[list.size()];
			int i = 0;
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();i++)
			{
				Object[] elem = (Object[]) iterator.next();
				TagsNubeDTO dto = new TagsNubeDTO();
				dto.setTagPk(((BigInteger) elem[0]).longValue());
				dto.setTagUkCodigo((String) elem[1]);
				dto.setContador(((BigInteger) elem[2]).longValue());
				tagsNube[i] = dto;
			}
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return tagsNube;
	}
	@Override
	public AssetsDTO[] getAssetsTag(Long user, Long tag)
			throws BusinessException {
		AssetsDTO[] assets = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			List<Assets> list = extraQueriesDAO.getAssetsTag(user, tag);
			assets = new AssetsDTO[list.size()];
			int i = 0;
			for (Assets asset:list)
			{
				assets[i++] = business.util.Convert.dao2dto(asset);					
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
		return assets;
	}
	@Override
	public FicherosDTO[] getFicherosTag(Long user, Long tag)
			throws BusinessException {
		FicherosDTO[] ficheros = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			MimeFilesDAOImpl mimeFilesDAO = new MimeFilesDAOImpl(bt);
			List<Ficheros> list = extraQueriesDAO.getFicherosTag(user, tag);
			ficheros = new FicherosDTO[list.size()];
			int i = 0;
			for (Ficheros fichero:list)
			{
				ficheros[i] = business.util.Convert.dao2dto(fichero);
				
				MimeFiles mime = mimeFilesDAO.getByPrimaryKey(fichero.getMimFilFk());
				ficheros[i].setMimFilExtension(mime.getMimFilExtension());
				ficheros[i].setMimFilMime(mime.getMimFilMime());
				String icon = mime.getMimFilIcon();
				if ( icon == null || icon.length() == 0 )
					icon = IConstantes.DEFAUL_ICON_DIR + IConstantes.DEFAUL_ICON_FILE;
				else
					icon = IConstantes.DEFAUL_MIME_ICON_DIR + icon;
				
				ficheros[i].setIcon(icon);
				++i;
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
		return ficheros;
	}
	
	@Override
	public FicherosDTO[] findAssets(String criteria, String tags, Long user) throws BusinessException {
		FicherosDTO[] ficheros = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		try 
		{
			ExtraQueriesDAOImpl extraQueriesDAO = new ExtraQueriesDAOImpl(bt);
			AssetsDAOImpl assetsDAO = new AssetsDAOImpl(bt);
			MimeFilesDAOImpl mimeFilesDAO = new MimeFilesDAOImpl(bt);
			List<Ficheros> list = extraQueriesDAO.findFiles(criteria, tags, user);
			ficheros = new FicherosDTO[list.size()];
			int i = 0;
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();i++)
			{
				Object[] elem = (Object[]) iterator.next();
				FicherosDTO dto = new FicherosDTO();
				
				dto.setFicPk(((BigInteger) elem[0]).longValue());
				dto.setFicSize(elem[1]==null?0:((BigInteger) elem[1]).longValue());
				dto.setAssPk(elem[2]==null?0:((BigInteger) elem[2]).longValue());
				dto.setCarFk(elem[3]==null?0:((BigInteger) elem[3]).longValue());
				dto.setMimFilFk(elem[4]==null?0:((Integer) elem[4]).longValue());
				dto.setFicSysName(elem[5]==null?"":(String) elem[5]);

				Assets asset = assetsDAO.getByPrimaryKey(dto.getAssPk());
				dto.setAssNombre(asset.getAssNombre());
				dto.setAssDescripcion(asset.getAssDescripcion());
				dto.setAssFechaAlta(asset.getAssFechaAlta());
				dto.setUsuFk(asset.getUsuFk());

				MimeFiles mime = mimeFilesDAO.getByPrimaryKey(dto.getMimFilFk());
				dto.setMimFilExtension(mime.getMimFilExtension());
				dto.setMimFilMime(mime.getMimFilMime());
				String icon = mime.getMimFilIcon();
				if ( icon == null || icon.length() == 0 )
					icon = IConstantes.DEFAUL_ICON_DIR + IConstantes.DEFAUL_ICON_FILE;
				else
					icon = IConstantes.DEFAUL_MIME_ICON_DIR + icon;
				
				dto.setIcon(icon);

				ficheros[i] = dto;
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
		return ficheros;
	}
}
