package common.business.bo.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.Constants;
import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.RelacionesUsuarios;
import common.business.beans.Usuarios;
import common.business.beans.UsuariosGrupos;
import common.business.beans.UsuariosGruposId;
import common.business.bo.UsuariosBO;
import common.business.dao.impl.RelacionesUsuariosDAOImpl;
import common.business.dao.impl.UsuariosDAOImpl;
import common.business.dao.impl.UsuariosGruposDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.business.util.Convert;
import common.dto.UsuariosDTO;
import common.util.FileUtils;

public class UsuariosBOImpl implements UsuariosBO {

	private static Log logger = LogFactory.getLog(UsuariosBOImpl.class);

	@Override
	public void add(UsuariosDTO dto) throws BusinessException {
		Usuarios dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl(bt);
		UsuariosGruposDAOImpl usuariosGruposDAO = new UsuariosGruposDAOImpl(bt);
		try 
		{
			Long pk = usuariosDAO.add(dao);
			dto.setUsuPk(pk);
			
			if (dto.getPerfil() != null)
			{
				for (int ind = 0; ind < dto.getPerfil().length; ind++)
				{
					UsuariosGruposId id = new UsuariosGruposId(pk, dto.getPerfil()[ind]);
					UsuariosGrupos ug = new UsuariosGrupos();
					ug.setId(id);
					usuariosGruposDAO.add(ug);
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

	@Override
	public void delete(UsuariosDTO dto) throws BusinessException {
		Usuarios dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl(bt);
		try 
		{
			usuariosDAO.delete(dao);
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
	public void delete(UsuariosDTO dto, HttpServletRequest request) throws BusinessException {
		Usuarios dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl(bt);
		try 
		{
			usuariosDAO.delete(dao);

			String path = request.getSession().getServletContext().getRealPath(Constants.TEMP_USER_DIR + Constants.DEFAUL_AVATAR_DIR) + "/";
			FileUtils.delete(path + dao.getUsuUkCodigo());
			
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
	public UsuariosDTO getByPrimaryKey(Long id) throws BusinessException {
		UsuariosDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl(bt);
		try 
		{
			Usuarios dao = usuariosDAO.getByPrimaryKey(id);
			if ( dao != null) dto = Convert.dao2dto(dao);
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
	public UsuariosDTO[] getUsuarios() throws BusinessException {
		List<Usuarios> list = null;
		UsuariosDTO[] usuarios = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl(bt);
		try 
		{
			list = usuariosDAO.getUsuarios();
			
			usuarios = new UsuariosDTO[list.size()];
			int i = 0;
			for (Iterator<Usuarios> iterator = list.iterator(); iterator.hasNext();i++)
			{
				usuarios[i] = Convert.dao2dto((Usuarios) iterator.next());
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
	public UsuariosDTO[] getUsuarios(Long user) throws BusinessException {
		List<Usuarios> list = null;
		UsuariosDTO[] usuarios = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl(bt);
		try 
		{
			list = usuariosDAO.getUsuarios(user);
			
			usuarios = new UsuariosDTO[list.size()];
			int i = 0;
			for (Usuarios usuario : list)
			{
				if (usuario.isUsuActivo() && (usuario.getUsuFechaBaja() == null || usuario.getUsuFechaBaja() < new Date().getTime()))
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
	public UsuariosDTO[] getUsuarios(Long user, Long estado) throws BusinessException {
		List<RelacionesUsuarios> relaciones = null;
		UsuariosDTO[] usuarios = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl(bt);
		RelacionesUsuariosDAOImpl relacionesUsuariosDAO = new RelacionesUsuariosDAOImpl(bt);
		try 
		{
			
			relaciones = relacionesUsuariosDAO.getRelacionByEstado(user, estado);
			
			usuarios = new UsuariosDTO[relaciones.size()];
			int i = 0;
			for (Iterator<RelacionesUsuarios> iterator = relaciones.iterator(); iterator.hasNext();i++)
			{
				RelacionesUsuarios relacion = (RelacionesUsuarios) iterator.next();
				Usuarios usuario = usuariosDAO.getByPrimaryKey(user.equals(relacion.getUsu1Fk())?relacion.getUsu2Fk():relacion.getUsu1Fk());
				usuarios[i] = Convert.dao2dto(usuario);
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
	public void update(UsuariosDTO dto) throws BusinessException {
		Usuarios dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl(bt);
		UsuariosGruposDAOImpl usuariosGruposDAO = new UsuariosGruposDAOImpl(bt);
		try 
		{
			usuariosDAO.update(dao);

			usuariosGruposDAO.deleteByUser(dto.getUsuPk());
			if (dto.getPerfil()!=null)
			{
				for (int ind = 0; ind < dto.getPerfil().length; ind++)
				{
					if (dto.getPerfil()[ind] > 0)
					{
						UsuariosGruposId id = new UsuariosGruposId(dto.getUsuPk(), dto.getPerfil()[ind]);
						UsuariosGrupos ug = new UsuariosGrupos();
						ug.setId(id);
						usuariosGruposDAO.add(ug);
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

	@Override
	public void updateUserData(UsuariosDTO dto, HttpServletRequest request) throws BusinessException {
		Usuarios dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl(bt);
		try 
		{
			usuariosDAO.update(dao);

			// Controlamos las condiciones para subirlo
			// se comprueba que la ruta exista
			if ( dto.getUsuAvatar() != null )
			{
				String path = request.getSession().getServletContext().getRealPath(Constants.TEMP_USER_DIR + Constants.DEFAUL_AVATAR_DIR) + "/";
				FileUtils.carga(path + dto.getUsuUkUsuario(), dto.getUsuAvatar());
			}			
			
			bt.commitTx();
		} 
		catch (DaoException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		catch (FileNotFoundException e) {
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
	public UsuariosDTO getByCode(String uk) throws BusinessException {
		UsuariosDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl(bt);
		try 
		{
			dto = Convert.dao2dto(usuariosDAO.getByCode(uk));
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
	public UsuariosDTO getLogin(String uk, String password)	throws BusinessException {
		UsuariosDTO dto = null;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl(bt);
		try 
		{
			Usuarios dao = usuariosDAO.getLogin(uk, password);
			if (dao != null)
			{
				dto = Convert.dao2dto(dao);
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
		return dto;
	}
	@Override
	public UsuariosDTO[] getByFilter(String filter) throws BusinessException {
		String[] sFilter = (filter == null || filter.trim().length() == 0)?null:filter.trim().split(" ");

		List<Usuarios> list = null;
		UsuariosDTO[] usuarios = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl(bt);
		try 
		{
			list = usuariosDAO.getByFilter(sFilter == null?null:Arrays.asList(sFilter));
			if (list != null )
			{
				usuarios = new UsuariosDTO[list.size()];
				int i = 0;
				for (Iterator<Usuarios> iterator = list.iterator(); iterator.hasNext();i++)
				{
					usuarios[i] = Convert.dao2dto((Usuarios) iterator.next());
				}				
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
}
