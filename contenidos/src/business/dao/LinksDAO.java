package business.dao;

import java.util.List;

import business.beans.Links;

import common.business.DaoException;

public interface LinksDAO {
	public Links getByPrimaryKey(Long id) throws DaoException;
	public Long add(Links obj) throws DaoException;
	public void update(Links obj) throws DaoException;
	public void delete(Links obj) throws DaoException;
	public List<Links> getLinks() throws DaoException;
}
