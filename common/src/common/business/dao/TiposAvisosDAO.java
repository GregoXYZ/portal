package common.business.dao;

import java.util.List;

import common.business.DaoException;

import common.business.beans.TiposAvisos;

public interface TiposAvisosDAO {
	public TiposAvisos getByPrimaryKey(Integer id) throws DaoException;
	public Integer add(TiposAvisos obj) throws DaoException;
	public void update(TiposAvisos obj) throws DaoException;
	public void delete(TiposAvisos obj) throws DaoException;
	public List<TiposAvisos> getTiposAvisos() throws DaoException;
}
