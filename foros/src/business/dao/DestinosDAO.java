package business.dao;

import java.util.List;

import common.business.DaoException;

import business.beans.Destinos;
import business.beans.DestinosId;

public interface DestinosDAO {
	public Destinos getByPrimaryKey(DestinosId id) throws DaoException;
	public List<Destinos> getByUser(Long user) throws DaoException;
	public List<Destinos> getByEntrada(Long entrada) throws DaoException;
	public void add(Destinos obj) throws DaoException;
	public void update(Destinos obj) throws DaoException;
	public void delete(Destinos obj) throws DaoException;
	public int deleteByUser(Long user) throws DaoException;
	public List<Destinos> getDestinos() throws DaoException;
}
