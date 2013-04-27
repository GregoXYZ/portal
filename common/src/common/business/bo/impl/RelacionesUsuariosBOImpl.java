package common.business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.RelacionesUsuarios;
import common.business.bo.RelacionesUsuariosBO;
import common.business.dao.impl.RelacionesUsuariosDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.business.util.Convert;
import common.dto.RelacionesUsuariosDTO;

public class RelacionesUsuariosBOImpl implements RelacionesUsuariosBO {

	private static Log logger = LogFactory.getLog(RelacionesUsuariosBOImpl.class);

	@Override
	public void add(RelacionesUsuariosDTO dto) throws BusinessException {
		RelacionesUsuarios dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		RelacionesUsuariosDAOImpl relacionesUsuariosDAO = new RelacionesUsuariosDAOImpl(bt);
		try 
		{
			Long pk = relacionesUsuariosDAO.add(dao);
			dto.setRelUsuPk(pk);

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
	public void delete(RelacionesUsuariosDTO dto) throws BusinessException {
		RelacionesUsuarios dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		RelacionesUsuariosDAOImpl relacionesUsuariosDAO = new RelacionesUsuariosDAOImpl(bt);
		try 
		{
			relacionesUsuariosDAO.delete(dao);
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
	public RelacionesUsuariosDTO getByPrimaryKey(Long id) throws BusinessException {
		RelacionesUsuariosDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		RelacionesUsuariosDAOImpl relacionesUsuariosDAO = new RelacionesUsuariosDAOImpl(bt);
		try 
		{
			dto = Convert.dao2dto(relacionesUsuariosDAO.getByPrimaryKey(id));
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
	public RelacionesUsuariosDTO[] getRelacionesUsuarios() throws BusinessException {
		List<RelacionesUsuarios> list = null;
		RelacionesUsuariosDTO[] relacionesUsuarios = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		RelacionesUsuariosDAOImpl relacionesUsuariosDAO = new RelacionesUsuariosDAOImpl(bt);
		try 
		{
			list = relacionesUsuariosDAO.getRelacionesUsuarios();
			
			relacionesUsuarios = new RelacionesUsuariosDTO[list.size()];
			int i = 0;
			for (Iterator<RelacionesUsuarios> iterator = list.iterator(); iterator.hasNext();i++)
			{
				relacionesUsuarios[i] = Convert.dao2dto((RelacionesUsuarios) iterator.next());
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
		return relacionesUsuarios;
	}

	@Override
	public void update(RelacionesUsuariosDTO dto) throws BusinessException {
		RelacionesUsuarios dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		RelacionesUsuariosDAOImpl relacionesUsuariosDAO = new RelacionesUsuariosDAOImpl(bt);
		try 
		{
			relacionesUsuariosDAO.update(dao);
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
	public RelacionesUsuariosDTO getRelacionUsuarios(Long user1, Long user2)
			throws BusinessException {
		RelacionesUsuariosDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		RelacionesUsuariosDAOImpl relacionesUsuariosDAO = new RelacionesUsuariosDAOImpl(bt);
		try 
		{
			dto = Convert.dao2dto(relacionesUsuariosDAO.getRelacionUsuarios(user1, user2));
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
	public RelacionesUsuariosDTO[] getRelacionByEstado(Long user, Long estado)
			throws BusinessException {
		List<RelacionesUsuarios> list = null;
		RelacionesUsuariosDTO[] relacionesUsuarios = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		RelacionesUsuariosDAOImpl relacionesUsuariosDAO = new RelacionesUsuariosDAOImpl(bt);
		try 
		{
			list = relacionesUsuariosDAO.getRelacionByEstado(user, estado);
			
			relacionesUsuarios = new RelacionesUsuariosDTO[list.size()];
			int i = 0;
			for (Iterator<RelacionesUsuarios> iterator = list.iterator(); iterator.hasNext();i++)
			{
				relacionesUsuarios[i] = Convert.dao2dto((RelacionesUsuarios) iterator.next());
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
		return relacionesUsuarios;
	}
}
