package business.bo.impl;

import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.bo.MailsBO;
import common.business.BusinessException;
import common.business.DaoException;
import common.business.bo.impl.MenusBOImpl;
import common.business.dao.impl.MailsDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.dto.MailsDTO;

public class MailsBOImpl implements MailsBO {

	private static Log logger = LogFactory.getLog(MenusBOImpl.class);

	@Override
	public MailsDTO[] getMailsPendientes() throws BusinessException, AddressException, MessagingException {
		List<?> list = null;
		MailsDTO[] mails = null;
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		MailsDAOImpl mailsDAO = new MailsDAOImpl(bt);
		try 
		{
			list = mailsDAO.getMailsPendientes();
			if (list != null )
			{
				mails = new MailsDTO[list.size()];
				int i = 0;
				for (Iterator<?> iterator = list.iterator(); iterator.hasNext();i++)
				{
					Object[] elem = (Object[]) iterator.next();
					MailsDTO dto = new MailsDTO();
					dto.setUsuFkOrigen((Long) elem[0]);
					dto.setUsuFrom((String) elem[1]);
					dto.setUsuFkDestino((Long) elem[2]);
					dto.setUsuTo((String) elem[3]);
					dto.setTipAviFk((Integer) elem[4]);
					dto.setCantidad((Long) elem[5]);
					
					mails[i] = dto;
				}				
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
		return mails;
	}
	@Override
	public void update(MailsDTO mail) throws BusinessException {
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		MailsDAOImpl mailsDAO = new MailsDAOImpl(bt);
		try 
		{
			mailsDAO.updateGroup(mail.getUsuFkOrigen(), mail.getUsuFkDestino(), mail.getTipAviFk());
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
}
