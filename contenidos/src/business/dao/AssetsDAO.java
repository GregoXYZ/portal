package business.dao;

import java.util.List;

import business.beans.Assets;

import common.business.DaoException;

public interface AssetsDAO {
	public Assets getByPrimaryKey(Long id) throws DaoException;
	public Assets getByCode(String uk) throws DaoException;
	public Long add(Assets obj) throws DaoException;
	public void update(Assets obj) throws DaoException;
	public void delete(Assets obj) throws DaoException;
	public List<Assets> getAssets() throws DaoException;
}
