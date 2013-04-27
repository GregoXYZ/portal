package common.business.dao;

import java.util.List;

import common.business.DaoException;

import common.business.beans.Menus;

public interface MenusDAO {
	public Menus getByPrimaryKey(Long id) throws DaoException;
	public Long add(Menus obj) throws DaoException;
	public void update(Menus obj) throws DaoException;
	public void delete(Menus obj) throws DaoException;
	public List<Menus> getMenus() throws DaoException;
	public List<Menus> getCaps(Long user) throws DaoException;
	public List<Menus> getMenu(Long user, Long parent) throws DaoException;
	public Menus getByCode(String uk) throws DaoException;
}
