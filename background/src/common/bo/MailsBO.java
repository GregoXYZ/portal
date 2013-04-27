package common.bo;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import common.business.BusinessException;
import common.dto.MailsDTO;

public interface MailsBO {
	public MailsDTO[] getMailsPendientes() throws BusinessException, AddressException, MessagingException;
	public void update(MailsDTO mail) throws BusinessException;
}
