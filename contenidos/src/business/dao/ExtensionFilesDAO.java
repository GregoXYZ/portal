package business.dao;

import java.util.List;

import business.beans.ExtensionFiles;

import common.business.DaoException;


public interface ExtensionFilesDAO {
	public ExtensionFiles getByPrimaryKey(Long id) throws DaoException;
	public ExtensionFiles getByExtension(String uk) throws DaoException;
	public Long add(ExtensionFiles obj) throws DaoException;
	public void update(ExtensionFiles obj) throws DaoException;
	public void delete(ExtensionFiles obj) throws DaoException;
	public List<ExtensionFiles> getExtensionFiles() throws DaoException;
}
