package business.dao;

import java.util.List;

import business.beans.Ficheros;

import common.business.DaoException;

public interface FicherosDAO {
	public Ficheros getByPrimaryKey(Long id) throws DaoException;
	public Ficheros getByAsset(Long id) throws DaoException;
	public Long add(Ficheros obj) throws DaoException;
	public void update(Ficheros obj) throws DaoException;
	public void delete(Ficheros obj) throws DaoException;
	public List<Ficheros> getFicheros() throws DaoException;
	public List<Ficheros> getDuplicados(Long user) throws DaoException;
	public void move(long ficPK, long carFk) throws DaoException;
}
