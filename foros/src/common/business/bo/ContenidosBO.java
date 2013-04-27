package common.business.bo;

import common.business.BusinessException;
import common.dto.ContenidosDTO;

public interface ContenidosBO {
	public ContenidosDTO getByPrimaryKey(Long id) throws BusinessException;
	public void add(ContenidosDTO dto) throws BusinessException;
	public void update(ContenidosDTO dto) throws BusinessException;
	public void delete(ContenidosDTO dto) throws BusinessException;
	public ContenidosDTO[] getContenidos() throws BusinessException;
}
