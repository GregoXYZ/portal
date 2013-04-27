package common.presentation.security.listeners;

import java.util.Collections;
import java.util.Date;
import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.bo.SessionsBO;
import common.dto.SessionsDTO;
import common.presentation.security.beans.UserInfo;
import common.util.ConfigProperties;
import common.util.spring.SpringUtil;

public class SessionCreatedExpiredListener implements HttpSessionListener {
	/**
	 * Logger for this class
	 */
	private final Log logger = LogFactory.getLog(this.getClass().getName());

	private int sessionCount;

	public void SessionListen() {
		this.sessionCount = 0;
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) { 	// Llamado automaticamente
														// cada vez que se crea
														// una sesión
		String maxInactiveSession = ConfigProperties.getInstance().get("maxInactiveSession");

		HttpSession session = se.getSession();
		if ( maxInactiveSession != null )
			session.setMaxInactiveInterval(Integer.valueOf(maxInactiveSession));			

		synchronized (this) {
			sessionCount++;
		}
		String id = session.getId();
		Date now = new Date();
		String message = new StringBuffer("New Session created on ").append(
				now.toString()).append("\nID: ").append(id).append("\n")
				.append("There are now ").append("" + sessionCount).append(
						" live sessions in the application.").toString();
		logger.info(message);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) { // Llamado
														// automaticamente cada
														// vez que se cierre una
														// sesion
		HttpSession session = se.getSession();

		String id = session.getId();
		synchronized (this) {
			--sessionCount;
		}
		
		String message = new StringBuffer("Session destroyed").
				append("\nValue of destroyed session ID is ").append("" + id).
				append("\n").append("There are now ").append("" + sessionCount).
				append(" live sessions in the application.").toString();

		UserInfo user = (UserInfo) session.getAttribute("user");
		if ( user != null )
		{
    		SessionsBO boSession = (SessionsBO) SpringUtil.getInstance().getBean("SessionsBO");
    		try {
				SessionsDTO s = boSession.getByPrimaryKey(user.getSessionPk());
				s.setValidSession('N');
				s.setClosedTime(new Date());
				
				boSession.update(s);
			} catch (BusinessException e) {
				logger.error(e.getMessage());
			}
		}

    	for (Object name : Collections.list(session.getAttributeNames())) {
    	    session.removeAttribute((String)name);
    	}
		
		logger.info(message);
	}
}