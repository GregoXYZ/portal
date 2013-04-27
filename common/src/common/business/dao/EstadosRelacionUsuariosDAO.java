package common.business.dao;

import java.util.List;

import common.business.DaoException;

import common.business.beans.EstadosRelacionUsuarios;

public interface EstadosRelacionUsuariosDAO {
	public EstadosRelacionUsuarios getByPrimaryKey(Long id) throws DaoException;
	public Long add(EstadosRelacionUsuarios obj) throws DaoException;
	public void update(EstadosRelacionUsuarios obj) throws DaoException;
	public void delete(EstadosRelacionUsuarios obj) throws DaoException;
	public List<EstadosRelacionUsuarios> getEstadosRelacionUsuarios() throws DaoException;
}
