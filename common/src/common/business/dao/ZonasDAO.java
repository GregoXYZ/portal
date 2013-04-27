package common.business.dao;

import java.util.List;

import common.business.DaoException;

import common.business.beans.Zonas;

public interface ZonasDAO {
	public Zonas getByPrimaryKey(Long id) throws DaoException;
	public Zonas getByCode(String uk) throws DaoException;
	public Long add(Zonas obj) throws DaoException;
	public void update(Zonas obj) throws DaoException;
	public void delete(Zonas obj) throws DaoException;
	public List<Zonas> getZonas() throws DaoException;
	@SuppressWarnings("rawtypes")
	public List getUserZones(Long user) throws DaoException;
}
