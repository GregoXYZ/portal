package util.security.sessions;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.bo.SessionsBO;
import common.util.spring.SpringUtil;

public class SessionControler extends TimerTask {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(SessionControler.class);
	
	private SessionsBO sessionsBO = (SessionsBO) SpringUtil.getInstance().getBean("SessionsBO");

	public SessionControler() {
		super();
	}
	
	@Override
	public void run() {
		try {
			logger.info("Invalidadndo sesiones inactivas.");
			logger.info("Se han cerrado " + sessionsBO.closeIncative() + " sessiones.");
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
	}
}
