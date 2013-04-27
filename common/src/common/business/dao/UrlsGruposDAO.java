package common.business.dao;

import java.util.List;

import common.business.DaoException;

import common.business.beans.UrlsGrupos;
import common.business.beans.UrlsGruposId;

public interface UrlsGruposDAO {
	public UrlsGrupos getByPrimaryKey(UrlsGruposId id) throws DaoException;
	public List<UrlsGrupos> getByUrl(Long url) throws DaoException;
	public void add(UrlsGrupos obj) throws DaoException;
	public void update(UrlsGrupos obj) throws DaoException;
	public void delete(UrlsGrupos obj) throws DaoException;
	public int deleteByUrl(Long url) throws DaoException;
	public List<UrlsGrupos> getUrlsGrupos() throws DaoException;
}
