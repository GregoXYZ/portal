package common.business.dao;

import java.util.List;

import common.business.DaoException;

import common.business.beans.UsuariosGrupos;
import common.business.beans.UsuariosGruposId;

public interface UsuariosGruposDAO {
	public UsuariosGrupos getByPrimaryKey(UsuariosGruposId id) throws DaoException;
	public List<UsuariosGrupos> getByUser(Long user) throws DaoException;
	public void add(UsuariosGrupos obj) throws DaoException;
	public void update(UsuariosGrupos obj) throws DaoException;
	public void delete(UsuariosGrupos obj) throws DaoException;
	public int deleteByUser(Long user) throws DaoException;
	public List<UsuariosGrupos> getUsuariosGrupos() throws DaoException;
}
