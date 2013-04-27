package business.dao;

import java.util.List;

import business.beans.Parametros;

import common.business.DaoException;

public interface ParametrosDAO {
	public Parametros getByPrimaryKey(String id) throws DaoException;
	public void add(Parametros obj) throws DaoException;
	public void update(Parametros obj) throws DaoException;
	public void delete(Parametros obj) throws DaoException;
	public List<Parametros> getParametros() throws DaoException;
}
