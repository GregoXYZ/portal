package common.business.dao;

import java.util.List;

import common.business.DaoException;
import common.business.beans.Usuarios;

public interface UsuariosDAO {
	public Usuarios getByPrimaryKey(Long id) throws DaoException;
	public Usuarios getByCode(String uk) throws DaoException;
	public Long add(Usuarios obj) throws DaoException;
	public void update(Usuarios obj) throws DaoException;
	public void delete(Usuarios obj) throws DaoException;
	public List<Usuarios> getUsuarios() throws DaoException;
	public Usuarios getLogin(String uk, String password) throws DaoException;
	public List<Usuarios> getUsuarios(Long user) throws DaoException;
	public List<Usuarios> getByFilter(List<String> filter) throws DaoException;
	public List<?> getEnLinea(Long user) throws DaoException;
}
