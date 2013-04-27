package business.dao;

import java.util.List;

import business.beans.Contenidos;

import common.business.DaoException;

public interface ContenidosDAO {
	public Contenidos getByPrimaryKey(Long id) throws DaoException;
	public Contenidos getByUser(Long user) throws DaoException;
	public Long add(Contenidos obj) throws DaoException;
	public void update(Contenidos obj) throws DaoException;
	public void delete(Contenidos obj) throws DaoException;
	public List<Contenidos> getContenidos() throws DaoException;
}
