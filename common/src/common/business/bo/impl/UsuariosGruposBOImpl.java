package common.business.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.UsuariosGrupos;
import common.business.bo.UsuariosGruposBO;
import common.business.dao.impl.UsuariosGruposDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.business.util.Convert;
import common.dto.UsuariosGruposDTO;

public class UsuariosGruposBOImpl implements UsuariosGruposBO {

	private static Log logger = LogFactory.getLog(UsuariosGruposBOImpl.class);

	@Override
	public void add(UsuariosGruposDTO dto) throws BusinessException {
		UsuariosGrupos dao = Convert.dto2dao(dto);
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UsuariosGruposDAOImpl usuariosGruposDAO = new UsuariosGruposDAOImpl(bt);
		try 
		{
			usuariosGruposDAO.add(dao);
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
	public void delete(UsuariosGruposDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UsuariosGruposDTO getByPrimaryKey(Long usuFk, Long gruFk) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuariosGruposDTO[] getUsuariosGrupos() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UsuariosGruposDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Long[] getByUser(Long usuFk) throws BusinessException {
		Long[] perfil;
		List<UsuariosGrupos> list = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UsuariosGruposDAOImpl usuariosGruposDAO = new UsuariosGruposDAOImpl(bt);
		try 
		{
			list = usuariosGruposDAO.getByUser(usuFk);
			
			perfil = new Long[list.size()];
			int i = 0;
			for (Iterator<UsuariosGrupos> iterator = list.iterator(); iterator.hasNext();i++)
			{
				perfil[i] = ((UsuariosGrupos) iterator.next()).getId().getGruFk();
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
		return perfil;
	}

}
