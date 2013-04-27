package common.business.dao;

import java.util.List;

import common.business.DaoException;

import common.business.beans.Bugs;

public interface BugsDAO {
	public Bugs getByPrimaryKey(Long id) throws DaoException;
	public Long add(Bugs obj) throws DaoException;
	public void update(Bugs obj) throws DaoException;
	public void delete(Bugs obj) throws DaoException;
	public List<Bugs> getBugs() throws DaoException;
	public List<?> getBugsExtended() throws DaoException;
}
