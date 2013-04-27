package business.dao;

import java.util.List;

import business.beans.AccesosDirectos;

import common.business.DaoException;

public interface AccesosDirectosDAO {
	public AccesosDirectos getByPrimaryKey(Long id) throws DaoException;
	public Long add(AccesosDirectos obj) throws DaoException;
	public void update(AccesosDirectos obj) throws DaoException;
	public void delete(AccesosDirectos obj) throws DaoException;
	public List<AccesosDirectos> getAccesosDirectos() throws DaoException;
}
