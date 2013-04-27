package business.dao;

import java.util.List;

import business.beans.Assets;
import business.beans.Compartidos;

import common.business.DaoException;

public interface CompartidosDAO {
	public Compartidos getByPrimaryKey(Long id) throws DaoException;
	public Compartidos getByUniqueKey(Long user, Long asset) throws DaoException;
	public List<Compartidos> getByUser(Long user) throws DaoException;
	public List<Compartidos> getByAsset(Long asset) throws DaoException;
	public Long add(Compartidos obj) throws DaoException;
	public void update(Compartidos obj) throws DaoException;
	public void delete(Compartidos obj) throws DaoException;
	public int delete(Long asset) throws DaoException;
	public List<Compartidos> getCompartidos() throws DaoException;
	public void comparte(Assets assDAO, Long[] usuarios) throws DaoException;
}
