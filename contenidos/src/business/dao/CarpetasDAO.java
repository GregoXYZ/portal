package business.dao;

import java.util.List;

import business.beans.Carpetas;

import common.business.DaoException;

public interface CarpetasDAO {
	public Carpetas getByPrimaryKey(Long id) throws DaoException;
	public Carpetas getByAsset(Long id) throws DaoException;
	public Long add(Carpetas obj) throws DaoException;
	public void update(Carpetas obj) throws DaoException;
	public void delete(Carpetas obj) throws DaoException;
	public List<Carpetas> getCarpetas() throws DaoException;
	public void move(long carPK, long carFk) throws DaoException;
}
