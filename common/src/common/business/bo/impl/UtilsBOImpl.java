package common.business.bo.impl;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.business.BusinessException;
import common.business.DaoException;
import common.business.beans.Grupos;
import common.business.beans.Usuarios;
import common.business.bo.UtilsBO;
import common.business.dao.impl.GruposDAOImpl;
import common.business.dao.impl.UrlsDAOImpl;
import common.business.dao.impl.UsuariosDAOImpl;
import common.business.dao.impl.UsuariosGruposDAOImpl;
import common.business.dao.impl.ZonasDAOImpl;
import common.business.hibernate.BusinessTransactionBo;
import common.business.hibernate.impl.TransactionFactory;
import common.business.util.Convert;
import common.dto.UsuariosDTO;
import common.dto.UsuariosGruposDTO;
import common.util.ConfigProperties;

public class UtilsBOImpl implements UtilsBO {

	private static Log logger = LogFactory.getLog(UtilsBOImpl.class);

	@Override
	public void registerUser(UsuariosDTO dto) throws BusinessException {
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl(bt);
		GruposDAOImpl gruposDAO = new GruposDAOImpl(bt);
		UsuariosGruposDAOImpl usuariosGruposDAO = new UsuariosGruposDAOImpl(bt);
		try 
		{
	    	Usuarios uDAO = new Usuarios(dto.getUsuUkUsuario(), dto.getUsuNombre(), dto.getUsuApellido1(), dto.getUsuApellido2(), 
	    	dto.getUsuContrasena(), null, dto.getUsuMail(), true);
	    	Long pk = usuariosDAO.add(uDAO);
	    	
	    	uDAO.setUsuPk(pk);
	    	
	    	UsuariosGruposDTO usuGruDTO = new UsuariosGruposDTO();
	    	
	    	Grupos grupo = gruposDAO.getByCode(ConfigProperties.getInstance().get("usersgroup"));	    	
	    	usuGruDTO.setGruFk(grupo.getGruPk());
	    	usuGruDTO.setUsuFk(uDAO.getUsuPk());
	
	    	usuariosGruposDAO.add(Convert.dto2dao(usuGruDTO));

	    	grupo = gruposDAO.getByCode(ConfigProperties.getInstance().get("sharedgroup"));
	    	usuGruDTO.setGruFk(grupo.getGruPk());
	    	usuGruDTO.setUsuFk(uDAO.getUsuPk());
	
	    	usuariosGruposDAO.add(Convert.dto2dao(usuGruDTO));
	    	
	    	bt.commitTx();
	    	
		} catch (DaoException e) {
			bt.rollbackTx();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		} 
		finally {
			transactionFactory.endTx();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Hashtable<Object, Object> getPerfil(Long user) throws BusinessException {
		Hashtable<Object, Object> perfil = null;
		
		TransactionFactory transactionFactory = new TransactionFactory();
		BusinessTransactionBo bt = transactionFactory.beginTx();
		ZonasDAOImpl zonasDAO = new ZonasDAOImpl(bt);
		UrlsDAOImpl urlsDAO = new UrlsDAOImpl(bt);
		try 
		{
			List lZonas = zonasDAO.getUserZones(user);
			
			if (lZonas != null && lZonas.size() > 0)
			{
				Iterator itZona = lZonas.iterator(); 
				while (itZona.hasNext())
				{
					Object[] zona = (Object[]) itZona.next();
					List lUrls = urlsDAO.getUserUrls(user, (Long) zona[0]);
					if (lUrls != null && lUrls.size() > 0)
					{
						Hashtable htUrls = new Hashtable(); 
						Iterator itUrl = lUrls.iterator(); 
						while (itUrl.hasNext())
						{
							Object[] url = (Object[]) itUrl.next();
							htUrls.put(url[1], url[0]);
						}
						if (perfil == null ) perfil = new Hashtable();
						perfil.put(zona[1], htUrls);
					}
					
				}				
			}
			
		} catch (DaoException e) {
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
