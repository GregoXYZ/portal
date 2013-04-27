package util.mail;

import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.bo.MailsBO;
import common.business.BusinessException;
import common.dto.MailsDTO;
import common.util.spring.SpringUtil;

public class MailControler extends TimerTask {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(MailControler.class);
	
	private MailsBO mailsBO = (MailsBO) SpringUtil.getInstance().getBean("MailsBO");

	public MailControler() {
		super();
	}
	
	@Override
	public void run() {
		try {
			logger.info("Envio de mails pendientes.");
			MailsDTO[] mails = mailsBO.getMailsPendientes();
			for(MailsDTO mail:mails)
			{	
				try {
					MailClient.getInstance().send(mail);
					mailsBO.update(mail);
				} catch (AddressException e) {
					logger.error(e.getMessage());
				} catch (MessagingException e) {
					logger.error(e.getMessage());
				}
			}
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		} catch (AddressException e) {
			logger.error(e.getMessage());
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
	}
}
