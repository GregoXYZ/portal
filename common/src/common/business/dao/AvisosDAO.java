package common.business.dao;

import java.util.List;

import common.business.DaoException;

import common.business.beans.Avisos;

public interface AvisosDAO {
	public Avisos getByPrimaryKey(Long id) throws DaoException;
	public Avisos getByPrimaryKey(Long id, Long user) throws DaoException;
	public List<Avisos> getByUser(Long user) throws DaoException;
	public Long add(Avisos obj) throws DaoException;
	public void update(Avisos obj) throws DaoException;
	public void delete(Avisos obj) throws DaoException;
	public int delete(Long user, Long tipo) throws DaoException;
	public List<Avisos> getAvisos() throws DaoException;
}
