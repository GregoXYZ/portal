package common.business.dao;

import java.util.List;

import common.business.DaoException;
import common.business.beans.Mails;

public interface MailsDAO {
	public Mails getByPrimaryKey(Long id) throws DaoException;
	public Long add(Mails obj) throws DaoException;
	public void update(Mails obj) throws DaoException;
	public int updateGroup(Long usuOrigen, Long usuDestino, Integer tipoAviso) throws DaoException;
	public void delete(Mails obj) throws DaoException;
	public List<Mails> getMails() throws DaoException;
	public List<?> getMailsPendientes() throws DaoException;
	public List<Mails> getMailsByUser(Long user) throws DaoException;
}
