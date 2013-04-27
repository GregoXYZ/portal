package common.business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.EstadosRelacionUsuarios;
import common.business.bo.EstadosRelacionUsuariosBO;
import common.business.dao.impl.EstadosRelacionUsuariosDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.business.util.Convert;
import common.dto.EstadosRelacionUsuariosDTO;

public class EstadosRelacionUsuariosBOImpl implements EstadosRelacionUsuariosBO {

	private static Log logger = LogFactory.getLog(EstadosRelacionUsuariosBOImpl.class);

	@Override
	public void add(EstadosRelacionUsuariosDTO dto) throws BusinessException {
		EstadosRelacionUsuarios dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		EstadosRelacionUsuariosDAOImpl estadosRelacionUsuariosDAO = new EstadosRelacionUsuariosDAOImpl(bt);
		try 
		{
			Long pk = estadosRelacionUsuariosDAO.add(dao);
			dto.setEstRelUsuPk(pk);

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
	public void delete(EstadosRelacionUsuariosDTO dto) throws BusinessException {
		EstadosRelacionUsuarios dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		EstadosRelacionUsuariosDAOImpl estadosRelacionUsuariosDAO = new EstadosRelacionUsuariosDAOImpl(bt);
		try 
		{
			estadosRelacionUsuariosDAO.delete(dao);
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
	public EstadosRelacionUsuariosDTO getByPrimaryKey(Long id) throws BusinessException {
		EstadosRelacionUsuariosDTO dto = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		EstadosRelacionUsuariosDAOImpl estadosRelacionUsuariosDAO = new EstadosRelacionUsuariosDAOImpl(bt);
		try 
		{
			dto = Convert.dao2dto(estadosRelacionUsuariosDAO.getByPrimaryKey(id));
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
	public EstadosRelacionUsuariosDTO[] getEstadosRelacionUsuarios() throws BusinessException {
		List<EstadosRelacionUsuarios> list = null;
		EstadosRelacionUsuariosDTO[] relacionesUsuarios = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		EstadosRelacionUsuariosDAOImpl estadosRelacionUsuariosDAO = new EstadosRelacionUsuariosDAOImpl(bt);
		try 
		{
			list = estadosRelacionUsuariosDAO.getEstadosRelacionUsuarios();
			
			relacionesUsuarios = new EstadosRelacionUsuariosDTO[list.size()];
			int i = 0;
			for (Iterator<EstadosRelacionUsuarios> iterator = list.iterator(); iterator.hasNext();i++)
			{
				relacionesUsuarios[i] = Convert.dao2dto((EstadosRelacionUsuarios) iterator.next());
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
	public void update(EstadosRelacionUsuariosDTO dto) throws BusinessException {
		EstadosRelacionUsuarios dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		EstadosRelacionUsuariosDAOImpl estadosRelacionUsuariosDAO = new EstadosRelacionUsuariosDAOImpl(bt);
		try 
		{
			estadosRelacionUsuariosDAO.update(dao);
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
