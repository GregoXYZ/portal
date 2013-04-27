package common.business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.Bugs;
import common.business.bo.BugsBO;
import common.business.dao.impl.BugsDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.business.util.Convert;
import common.dto.BugsDTO;
import common.util.StringUtils;

public class BugsBOImpl implements BugsBO {

	private static Log logger = LogFactory.getLog(BugsBOImpl.class);

	@Override
	public void add(BugsDTO dto) throws BusinessException {
		Bugs dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		BugsDAOImpl bugsDAO = new BugsDAOImpl(bt);
		try 
		{
			Long pk = bugsDAO.add(dao);
			dto.setBugPk(pk);

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
	public void delete(BugsDTO dto) throws BusinessException {
		Bugs dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		BugsDAOImpl bugsDAO = new BugsDAOImpl(bt);
		try 
		{
			bugsDAO.delete(dao);
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
	public BugsDTO getByPrimaryKey(Long id) throws BusinessException {
		BugsDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		BugsDAOImpl bugsDAO = new BugsDAOImpl(bt);
		try 
		{
			dto = Convert.dao2dto(bugsDAO.getByPrimaryKey(id));
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
	public BugsDTO[] getBugs() throws BusinessException {
		List<Bugs> list = null;
		BugsDTO[] bugs = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		BugsDAOImpl bugsDAO = new BugsDAOImpl(bt);
		try 
		{
			list = bugsDAO.getBugs();
			
			bugs = new BugsDTO[list.size()];
			int i = 0;
			for (Iterator<Bugs> iterator = list.iterator(); iterator.hasNext();i++)
			{
				bugs[i] = Convert.dao2dto((Bugs) iterator.next());
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
		return bugs;
	}

	@Override
	public void update(BugsDTO dto) throws BusinessException {
		Bugs dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		BugsDAOImpl bugsDAO = new BugsDAOImpl(bt);
		try 
		{
			bugsDAO.update(dao);
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
	public BugsDTO[] getBugsExtended() throws BusinessException {
		List<?> list = null;
		BugsDTO[] bugs = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		BugsDAOImpl bugsDAO = new BugsDAOImpl(bt);
		try 
		{
			list = bugsDAO.getBugsExtended();
			
			bugs = new BugsDTO[list.size()];
			int i = 0;
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();i++)
			{
				bugs[i] = new BugsDTO();
				Object[] bug = (Object[]) iterator.next();
				bugs[i].setBugPk((Long) bug[0]);
				bugs[i].setBugMessage(StringUtils.formatText(bug[1]));
				bugs[i].setBugDescripcion(StringUtils.formatText(bug[2]));
				bugs[i].setBugFechaReport((Long) bug[3]);
				bugs[i].setUsuFk((Long) bug[4]);
				bugs[i].setUsuUkCodigo(StringUtils.formatText(bug[5]));
				bugs[i].setUsuNombre(StringUtils.formatText(bug[6]));
				bugs[i].setUsuApellido1(StringUtils.formatText(bug[7]));
				bugs[i].setUsuApellido2(StringUtils.formatText(bug[8]));
				bugs[i].setBugSite((Long) bug[9]);
				bugs[i].setZonUkCodigo(StringUtils.formatText(bug[10]));
				bugs[i].setZonDescripcion(StringUtils.formatText(bug[11]));
				bugs[i].setBugEstFk((Long) bug[12]);
				bugs[i].setBugEstUkCodigo(StringUtils.formatText(bug[13]));
				bugs[i].setBugPriFk((Long) bug[14]);
				bugs[i].setBugPriUkCodigo(StringUtils.formatText(bug[15]));
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
		return bugs;
	}

}
