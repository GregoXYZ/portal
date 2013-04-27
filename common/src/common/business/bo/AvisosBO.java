package common.business.bo;

import common.business.BusinessException;
import common.dto.AvisosDTO;

public interface AvisosBO {
	public AvisosDTO getByPrimaryKey(Long id) throws BusinessException;
	public AvisosDTO getByPrimaryKey(Long id, Long user) throws BusinessException;
	public AvisosDTO[] getByUser(Long user) throws BusinessException;
	public void add(AvisosDTO dto) throws BusinessException;
	public void update(AvisosDTO dto) throws BusinessException;
	public void delete(AvisosDTO dto) throws BusinessException;
	public AvisosDTO[] getAvisos() throws BusinessException;
}
