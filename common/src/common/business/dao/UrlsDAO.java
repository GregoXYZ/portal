package common.business.dao;

import java.util.List;

import common.business.DaoException;

import common.business.beans.Urls;

public interface UrlsDAO {
	public Urls getByPrimaryKey(Long id) throws DaoException;
	public Urls getByCode(String uk) throws DaoException;
	public Long add(Urls obj) throws DaoException;
	public void update(Urls obj) throws DaoException;
	public void delete(Urls obj) throws DaoException;
	public List<Urls> getUrls() throws DaoException;
	public List<Urls> getUserUrls(Long user, Long zona) throws DaoException;
}
