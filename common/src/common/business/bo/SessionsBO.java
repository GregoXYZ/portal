package common.business.bo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.business.BusinessException;
import common.business.beans.Sessions;
import common.dto.SessionsDTO;


public interface SessionsBO {
	public SessionsDTO getByPrimaryKey(Long id) throws BusinessException;
	public SessionsDTO getByCode(String uk) throws BusinessException;
	public SessionsDTO getByAddress(String uk, String address) throws BusinessException;
	public void add(SessionsDTO obj) throws BusinessException;
	public void update(SessionsDTO obj) throws BusinessException;
	public void delete(SessionsDTO obj) throws BusinessException;
	public SessionsDTO[] getSessions() throws BusinessException;
	public SessionsDTO[] getUserSessions(Long user) throws BusinessException;
	public boolean login(String user, String password, HttpServletRequest request, HttpServletResponse response) throws BusinessException;
	public boolean logout(HttpServletRequest request, HttpServletResponse response) throws BusinessException;
	public void closeSession(HttpServletRequest request, HttpServletResponse response);
	public List<Sessions> getActiveSessions() throws BusinessException;
	public int closeIncative() throws BusinessException;
}
