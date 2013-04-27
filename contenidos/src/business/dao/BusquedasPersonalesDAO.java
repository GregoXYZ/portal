package business.dao;

import java.util.List;

import business.beans.BusquedasPersonales;

import common.business.DaoException;

public interface BusquedasPersonalesDAO {
	public BusquedasPersonales getByPrimaryKey(Long id) throws DaoException;
	public BusquedasPersonales getByUniqueKey(Long user, Long tag) throws DaoException;
	public void add(BusquedasPersonales obj) throws DaoException;
	public void update(BusquedasPersonales obj) throws DaoException;
	public void delete(BusquedasPersonales obj) throws DaoException;
	public List<BusquedasPersonales> getBusquedasPersonales() throws DaoException;
	public List<BusquedasPersonales> getByUser(Long user) throws DaoException;
}
