package common.business.bo.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.Constants;
import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.Sessions;
import common.business.beans.Usuarios;
import common.business.bo.SessionsBO;
import common.business.bo.UtilsBO;
import common.business.dao.impl.SessionsDAOImpl;
import common.business.dao.impl.UsuariosDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.business.util.Convert;
import common.dto.SessionsDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.util.ControlCookies;
import common.util.ConfigProperties;
import common.util.Encrypt;
import common.util.FileUtils;
import common.util.spring.SpringUtil;

public class SessionsBOImpl implements SessionsBO {

	private static Log logger = LogFactory.getLog(SessionsBOImpl.class);
	
	@Override
	public void add(SessionsDTO dto) throws BusinessException {
		Sessions dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		SessionsDAOImpl sessionsDAO = new SessionsDAOImpl(bt);
		try 
		{
			Long pk = sessionsDAO.add(dao);
			// Al insertar devuelve el siguiente id de sesión
			dao.setSessionPk(pk);
			dao.setSessionId(Encrypt.encriptar(pk.toString()));
			sessionsDAO.update(dao);
			dto.setSessionPk(dao.getSessionPk());
			dto.setSessionId(dao.getSessionId());
			bt.commitTx();
		} 
		catch (DaoException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			transactionFactory.endTx();
		}
	}
	
	@Override
	public void delete(SessionsDTO dto) throws BusinessException {
		Sessions dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		SessionsDAOImpl sessionsDAO = new SessionsDAOImpl(bt);
		try 
		{
			sessionsDAO.delete(dao);
			bt.commitTx();
		} 
		catch (DaoException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			transactionFactory.endTx();
		}
	}

	@Override
	public SessionsDTO getByPrimaryKey(Long id) throws BusinessException {
		SessionsDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		SessionsDAOImpl sessionsDAO = new SessionsDAOImpl(bt);
		try 
		{
			dto = Convert.dao2dto(sessionsDAO.getByPrimaryKey(id));
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return dto;
	}

	@Override
	public SessionsDTO getByCode(String uk) throws BusinessException {
		SessionsDTO dto = null;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		SessionsDAOImpl sessionsDAO = new SessionsDAOImpl(bt);
		try {
			Sessions session = sessionsDAO.getByCode(uk);
			
			if ( session != null )
			{
				dto = Convert.dao2dto(session);
			}
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return dto;
	}

	@Override
	public SessionsDTO getByAddress(String uk, String address) throws BusinessException {
		SessionsDTO dto = null;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		SessionsDAOImpl sessionsDAO = new SessionsDAOImpl(bt);
		try {
			Sessions session = sessionsDAO.getByAddress(uk, address);
			
			if ( session != null )
			{
				dto = Convert.dao2dto(session);
			}
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return dto;
	}

	@Override
	public void update(SessionsDTO dto) throws BusinessException {
		Sessions dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		SessionsDAOImpl sessionsDAO = new SessionsDAOImpl(bt);
		try 
		{
			sessionsDAO.update(dao);
			bt.commitTx();
		} 
		catch (DaoException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			transactionFactory.endTx();
		}		
	}

	@Override
	public SessionsDTO[] getSessions() throws BusinessException {
		List<Sessions> list = null;
		SessionsDTO[] sessions = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		SessionsDAOImpl sessionsDAO = new SessionsDAOImpl(bt);
		try 
		{
			list = sessionsDAO.getSessions();
			
			sessions = new SessionsDTO[list.size()];
			int i = 0;
			for (Iterator<Sessions> iterator = list.iterator(); iterator.hasNext();i++)
			{
				sessions[i] = Convert.dao2dto((Sessions) iterator.next());
			}
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return sessions;
	}

	@Override
	public SessionsDTO[] getUserSessions(Long user) throws BusinessException {
		List<Sessions> list = null;
		SessionsDTO[] sessions = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		SessionsDAOImpl sessionsDAO = new SessionsDAOImpl(bt);
		try 
		{
			list = sessionsDAO.getUserSessions(user);
			
			sessions = new SessionsDTO[list.size()];
			int i = 0;
			for (Iterator<Sessions> iterator = list.iterator(); iterator.hasNext();i++)
			{
				sessions[i] = Convert.dao2dto((Sessions) iterator.next());
			}
			
		}
		catch (DaoException e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return sessions;
	}

	public boolean login(String user, String password, HttpServletRequest request, HttpServletResponse response) throws BusinessException
	{
    	HttpSession httpSession = request.getSession();
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		SessionsDAOImpl sessionsDAO = new SessionsDAOImpl(bt);
		UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl(bt);
		try {
			Usuarios userDAO = usuariosDAO.getLogin(user, Encrypt.encriptar(password));    	
	    	if ( userDAO == null ) {
				bt.rollbackTx();
				return false;
//				throw new BusinessException("Usuario/Contraseña incorrectos.");
	    	}
	
	    	Sessions session = new Sessions();
	    	session.setAppName(request.getContextPath());
	    	
	       	// ShareCookie
	    	Cookie shareCookie = ControlCookies.crearCookie(Constants.SHARE_SESSION_COOKIE, httpSession.getId());
	    	shareCookie.setPath("/");
			response.addCookie(shareCookie);
	    	
	    	//Cookie shareCookie = ControlCookies.getCookie(request, Constants.SHARE_SESSION_COOKIE);
	    	session.setShareSessionId(shareCookie.getValue());
	    	session.setCreationTime(new Date().getTime());
	    	session.setLastAccessedTime(new Date().getTime());
	    	session.setAddress(request.getRemoteAddr());
    		// Segundos 
	    	session.setMaxInactive(Integer.valueOf(ConfigProperties.getInstance().get("maxInactiveSession")));	    		
	    	session.setUserId(userDAO.getUsuPk());
	    	session.setValidSession('S');
	    	
	    	//SessionsBO boSessions = (SessionsBO) SpringUtil.getInstance().getBean("SessionsBO");
	    	session.setSessionPk(sessionsDAO.add(session));
	
	    	if ( session.getSessionPk() > 0 )
	    	{
		    	session.setSessionId(Encrypt.encriptar(session.getSessionPk().toString()));
		    	sessionsDAO.update(session);
		    	
		    	// SessionCookie
		    	//Cookie cookie = new Cookie(Constants.SESSION_COOKIE, session.getSessionId());
		    	Cookie cookie = ControlCookies.crearCookie(Constants.SESSION_COOKIE, session.getSessionId());
	    		cookie.setPath("/");
	    		response.addCookie(cookie);
	    		
	    		// Guarda el avatar del usuario
	    		if ( userDAO.getUsuAvatar() != null && userDAO.getUsuAvatar().length > 0 )
	    		{
					String path = request.getSession().getServletContext().getRealPath(Constants.TEMP_USER_DIR + Constants.DEFAUL_AVATAR_DIR) + "/";
	    			FileUtils.carga(path + userDAO.getUsuUkCodigo(), userDAO.getUsuAvatar());
	    		}
	    	}
	    	else
	    	{
	    		logger.error("Error al crear la sesión.");
	    		closeSession(request, response);
				bt.rollbackTx();
		    	return false;
	    	}
	    	bt.commitTx();
	    	
	    	UtilsBO boUtils = (UtilsBO) SpringUtil.getInstance().getBean("UtilsBO");
	    	UserInfo userInfo = new UserInfo();
	    	userInfo.setUserInfo(userDAO, boUtils.getPerfil(userDAO.getUsuPk()));
	    	userInfo.setSessionPk(session.getSessionPk());
	    	httpSession.setAttribute("user", userInfo);
	    	
	    	return true;
		} catch (IllegalStateException e) {
			bt.rollbackTx();
    		closeSession(request, response);
    		throw new BusinessException(e);
		} catch (DaoException e) {
			bt.rollbackTx();
    		closeSession(request, response);
    		throw new BusinessException(e);
		} catch (IOException e) {
			bt.rollbackTx();
    		closeSession(request, response);
    		throw new BusinessException(e);
		}
		finally {
			transactionFactory.endTx();
		}
	}

	@Override
	public boolean logout(HttpServletRequest request, HttpServletResponse response) throws BusinessException {
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		SessionsDAOImpl sessionsDAO = new SessionsDAOImpl(bt);
		try {
			Cookie cookie = ControlCookies.getCookie(request, Constants.SESSION_COOKIE);
			if ( cookie == null )
			{
				bt.rollbackTx();
				return false;
			}
			else
			{
				Sessions sessionDAO = sessionsDAO.getByCode(cookie.getValue());
				if ( sessionDAO != null )
				{
					sessionDAO.setValidSession('N');
					sessionDAO.setClosedTime(new Date().getTime());
					sessionsDAO.update(sessionDAO);				
				}
	
				bt.commitTx();
				return true;
			}
		} catch (DaoException e) {
			bt.rollbackTx();
			throw new BusinessException(e);
		} 
		finally {
			transactionFactory.endTx();
	    	closeSession(request, response);
		}
	}

	public void closeSession(HttpServletRequest request, HttpServletResponse response)
	{
    	HttpSession session = request.getSession();    	

    	//ControlCookies.eliminarCookie(request, response, Constants.SESSION_COOKIE);
    	//ControlCookies.eliminarCookie(request, response, Constants.SHARE_SESSION_COOKIE);

    	ControlCookies.limpiaCookies(request, response);
    	
    	for (Object name : Collections.list(session.getAttributeNames())) {
    	    session.removeAttribute((String)name);
    	}
   	
    	//session.removeAttribute("user");
    	session.invalidate();
	}
	@Override
	public List<Sessions> getActiveSessions() throws BusinessException {
		List<Sessions> list = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		SessionsDAOImpl sessionsDAO = new SessionsDAOImpl(bt);
		try 
		{
			list = sessionsDAO.getActiveSessions();
		} 
		catch (DaoException e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			bt.rollbackTx();
			transactionFactory.endTx();
		}
		return list;
	}
	@Override
	public int closeIncative() throws BusinessException {
		int rows = 0;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		SessionsDAOImpl sessionsDAO = new SessionsDAOImpl(bt);
		try 
		{
			rows = sessionsDAO.closeIncative();
			bt.commitTx();
		} 
		catch (DaoException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			transactionFactory.endTx();
		}
		return rows;
	}
}
