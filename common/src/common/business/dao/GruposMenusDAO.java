package common.business.dao;

import java.util.List;

import common.business.DaoException;

import common.business.beans.GruposMenus;
import common.business.beans.GruposMenusId;

public interface GruposMenusDAO {
	public GruposMenus getByPrimaryKey(GruposMenusId id) throws DaoException;
	public List<GruposMenus> getByMenu(Long menu) throws DaoException;
	public void add(GruposMenus obj) throws DaoException;
	public void update(GruposMenus obj) throws DaoException;
	public void delete(GruposMenus obj) throws DaoException;
	public int deleteByMenu(Long menu) throws DaoException;
	public List<GruposMenus> getGruposMenus() throws DaoException;
}
