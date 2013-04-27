package common.business.dao;

import java.util.List;

import common.business.DaoException;

import common.business.beans.RelacionesUsuarios;

public interface RelacionesUsuariosDAO {
	public RelacionesUsuarios getByPrimaryKey(Long id) throws DaoException;
	public Long add(RelacionesUsuarios obj) throws DaoException;
	public void update(RelacionesUsuarios obj) throws DaoException;
	public void delete(RelacionesUsuarios obj) throws DaoException;
	public List<RelacionesUsuarios> getRelacionesUsuarios() throws DaoException;
	public RelacionesUsuarios getRelacionUsuarios(Long user1, Long user2) throws DaoException;
	public List<RelacionesUsuarios> getRelacionByEstado(Long user, Long estado)	throws DaoException;
}
