package common.business.bo.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.GruposMenus;
import common.business.beans.GruposMenusId;
import common.business.beans.Menus;
import common.business.bo.MenusBO;
import common.business.dao.impl.GruposMenusDAOImpl;
import common.business.dao.impl.MenusDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.business.util.Convert;
import common.dto.MenusDTO;
import common.util.FileUtils;

public class MenusBOImpl implements MenusBO {

	private static Log logger = LogFactory.getLog(MenusBOImpl.class);

	@Override
	public void add(MenusDTO dto) throws BusinessException {
		Menus dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		MenusDAOImpl menusDAO = new MenusDAOImpl(bt);
		GruposMenusDAOImpl gruposMenusDAO = new GruposMenusDAOImpl(bt);
		try 
		{
			Long pk = menusDAO.add(dao);
			dto.setMenPk(pk);

			for (int ind = 0; ind < dto.getGrupos().length; ind++)
			{
				GruposMenusId id = new GruposMenusId(dto.getGrupos()[ind], pk);
				GruposMenus gm = new GruposMenus();
				gm.setId(id);
				gruposMenusDAO.add(gm);
			}
			bt.commitTx();
		} 
		catch (DaoException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		catch (Exception e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			transactionFactory.endTx();
		}
	}

	@Override
	public void add(String path, byte[] file, MenusDTO dto)
			throws BusinessException {
		Menus dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		MenusDAOImpl menusDAO = new MenusDAOImpl(bt);
		GruposMenusDAOImpl gruposMenusDAO = new GruposMenusDAOImpl(bt);
		try 
		{
			Long pk = menusDAO.add(dao);
			dto.setMenPk(pk);

			for (int ind = 0; ind < dto.getGrupos().length; ind++)
			{
				GruposMenusId id = new GruposMenusId(dto.getGrupos()[ind], pk);
				GruposMenus gm = new GruposMenus();
				gm.setId(id);
				gruposMenusDAO.add(gm);
			}

			// Controlamos las condiciones para subirlo
			// se comprueba que la ruta exista
			FileUtils.carga(path + dto.getMenIcon(), file);
			bt.commitTx();
		} 
		catch (DaoException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
		} 
		catch (Exception ex) {
			bt.rollbackTx();
			logger.error(ex.getMessage());
		}
		finally {
			transactionFactory.endTx();
		}
	}

	@Override
	public void delete(MenusDTO dto) throws BusinessException {
		Menus dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		MenusDAOImpl menusDAO = new MenusDAOImpl(bt);
		try 
		{
			menusDAO.delete(dao);
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
	public MenusDTO getByPrimaryKey(Long id) throws BusinessException {
		MenusDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		MenusDAOImpl menusDAO = new MenusDAOImpl(bt);
		try 
		{
			dto = Convert.dao2dto(menusDAO.getByPrimaryKey(id));
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
	public MenusDTO getByCode(String uk) throws BusinessException {
		MenusDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		MenusDAOImpl menusDAO = new MenusDAOImpl(bt);
		try 
		{
			dto = Convert.dao2dto(menusDAO.getByCode(uk));
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
	public MenusDTO[] getMenus() throws BusinessException {
		List<Menus> list = null;
		MenusDTO[] menus = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		MenusDAOImpl menusDAO = new MenusDAOImpl(bt);
		try 
		{
			list = menusDAO.getMenus();
			
			menus = new MenusDTO[list.size()];
			int i = 0;
			for (Iterator<Menus> iterator = list.iterator(); iterator.hasNext();i++)
			{
				menus[i] = Convert.dao2dto(iterator.next());
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
		return menus;
	}

	@Override
	public void update(MenusDTO dto) throws BusinessException {
		Menus dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		MenusDAOImpl menusDAO = new MenusDAOImpl(bt);
		GruposMenusDAOImpl gruposMenusDAO = new GruposMenusDAOImpl(bt);
		try 
		{
			menusDAO.update(dao);

			gruposMenusDAO.deleteByMenu(dto.getMenPk());
			for (int ind = 0; ind < dto.getGrupos().length; ind++)
			{
				if (dto.getGrupos()[ind] > 0)
				{
					GruposMenusId id = new GruposMenusId(dto.getGrupos()[ind], dto.getMenPk());
					GruposMenus gm = new GruposMenus();
					gm.setId(id);
					gruposMenusDAO.add(gm);
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
	public void update(String path, byte[] file, MenusDTO dto)
			throws BusinessException {
		Menus dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		MenusDAOImpl menusDAO = new MenusDAOImpl(bt);
		GruposMenusDAOImpl gruposMenusDAO = new GruposMenusDAOImpl(bt);
		try 
		{
			menusDAO.update(dao);

			gruposMenusDAO.deleteByMenu(dto.getMenPk());
			for (int ind = 0; ind < dto.getGrupos().length; ind++)
			{
				if (dto.getGrupos()[ind] > 0)
				{
					GruposMenusId id = new GruposMenusId(dto.getGrupos()[ind], dto.getMenPk());
					GruposMenus gm = new GruposMenus();
					gm.setId(id);
					gruposMenusDAO.add(gm);
				}
			}

			// Controlamos las condiciones para subirlo
			// se comprueba que la ruta exista
			FileUtils.carga(path + dto.getMenIcon(), file);

			bt.commitTx();
		} 
		catch (DaoException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} catch (IOException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			transactionFactory.endTx();
		}				
	}
	
	@Override
	public MenusDTO[] getCaps(Long user) throws BusinessException {
		List<Menus> list = null;
		MenusDTO[] menus = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		MenusDAOImpl menusDAO = new MenusDAOImpl(bt);
		try 
		{
			list = menusDAO.getCaps(user);

			menus = new MenusDTO[list.size()];
			int i = 0;
			for (Iterator<Menus> iterator = list.iterator(); iterator.hasNext();i++)
			{
				menus[i] = Convert.dao2dto(iterator.next());
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
		return menus;
	}
	
	@Override
	public MenusDTO[] getMenu(Long user, Long parent) throws BusinessException {
		List<Menus> list = null;
		MenusDTO[] menus = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		MenusDAOImpl menusDAO = new MenusDAOImpl(bt);
		try 
		{
			list = menusDAO.getMenu(user, parent);

			menus = new MenusDTO[list.size()];
			int i = 0;
			for (Iterator<Menus> iterator = list.iterator(); iterator.hasNext();i++)
			{
				menus[i] = Convert.dao2dto(iterator.next());
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
		return menus;
	}
}
