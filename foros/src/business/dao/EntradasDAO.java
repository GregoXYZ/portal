package business.dao;

import java.util.List;

import business.beans.Entradas;

import common.business.DaoException;

public interface EntradasDAO {
	public Entradas getByPrimaryKey(Long id) throws DaoException;
	public Entradas getByUser(Long user) throws DaoException;
	public Long add(Entradas obj) throws DaoException;
	public void update(Entradas obj) throws DaoException;
	public void delete(Entradas obj) throws DaoException;
	public List<Entradas> getEntradas() throws DaoException;
}
