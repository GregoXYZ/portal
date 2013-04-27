package business.dao;

import java.util.List;

import business.beans.TiposAssets;

import common.business.DaoException;

public interface TiposAssetsDAO {
	public TiposAssets getByPrimaryKey(Long id) throws DaoException;
	public TiposAssets getByCode(String uk) throws DaoException;
	public Long add(TiposAssets obj) throws DaoException;
	public void update(TiposAssets obj) throws DaoException;
	public void delete(TiposAssets obj) throws DaoException;
	public List<TiposAssets> getTiposAssets() throws DaoException;
}
