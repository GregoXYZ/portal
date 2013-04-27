package common.business.dao;

import java.util.List;

import common.business.DaoException;
import common.business.beans.Sessions;

public interface SessionsDAO {
	public Sessions getByPrimaryKey(Long id) throws DaoException;
	public Sessions getByCode(String uk) throws DaoException;
	public Sessions getByAddress(String uk, String address) throws DaoException;
	public Long add(Sessions obj) throws DaoException;
	public void update(Sessions obj) throws DaoException;
	public void delete(Sessions obj) throws DaoException;
	public List<Sessions> getSessions() throws DaoException;
	public List<Sessions> getUserSessions(Long user) throws DaoException;
	public List<Sessions> getActiveSessions() throws DaoException;
	public int closeIncative() throws DaoException;
}
