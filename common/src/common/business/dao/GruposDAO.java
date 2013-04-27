package common.business.dao;

import java.util.List;

import common.business.DaoException;

import common.business.beans.Grupos;

public interface GruposDAO {
	public Grupos getByPrimaryKey(Long id) throws DaoException;
	public Grupos getByCode(String uk) throws DaoException;
	public Long add(Grupos obj) throws DaoException;
	public void update(Grupos obj) throws DaoException;
	public void delete(Grupos obj) throws DaoException;
	public List<Grupos> getGrupos() throws DaoException;
}
