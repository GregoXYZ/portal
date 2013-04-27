package business.dao;

import java.util.List;

import business.beans.Cuotas;

import common.business.DaoException;

public interface CuotasDAO {
	public Cuotas getByPrimaryKey(Long id) throws DaoException;
	public void add(Cuotas obj) throws DaoException;
	public void update(Cuotas obj) throws DaoException;
	public void delete(Cuotas obj) throws DaoException;
	public List<Cuotas> getCuotas() throws DaoException;
}
