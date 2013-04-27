package business.dao;

import java.util.List;

import business.beans.MimeFiles;

import common.business.DaoException;


public interface MimeFilesDAO {
	public MimeFiles getByPrimaryKey(Long id) throws DaoException;
	public MimeFiles getByExtension(String uk) throws DaoException;
	public Long add(MimeFiles obj) throws DaoException;
	public void update(MimeFiles obj) throws DaoException;
	public void delete(MimeFiles obj) throws DaoException;
	public List<MimeFiles> getMimeFiles() throws DaoException;
}
