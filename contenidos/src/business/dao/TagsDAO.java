package business.dao;

import java.util.List;

import business.beans.Tags;

import common.business.DaoException;

public interface TagsDAO {
	public Tags getByPrimaryKey(Long id) throws DaoException;
	public Tags getByCode(String uk) throws DaoException;
	public Long add(Tags obj) throws DaoException;
	public void update(Tags obj) throws DaoException;
	public void updateContadores(String tags) throws DaoException;
	public void delete(Tags obj) throws DaoException;
	public List<Tags> getTags() throws DaoException;
}
