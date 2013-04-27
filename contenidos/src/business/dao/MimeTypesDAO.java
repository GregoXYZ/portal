package business.dao;

import java.util.List;

import business.beans.MimeTypes;

import common.business.DaoException;

public interface MimeTypesDAO {
	public MimeTypes getByPrimaryKey(Long id) throws DaoException;
	public Long add(MimeTypes obj) throws DaoException;
	public void update(MimeTypes obj) throws DaoException;
	public void delete(MimeTypes obj) throws DaoException;
	public List<MimeTypes> getMimeTypes() throws DaoException;
}
