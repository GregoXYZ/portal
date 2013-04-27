package common.business.bo;

import common.business.BusinessException;
import common.dto.EntradasDTO;

public interface EntradasBO {
	public EntradasDTO getByPrimaryKey(Long id) throws BusinessException;
	public void add(EntradasDTO dto) throws BusinessException;
	public void update(EntradasDTO dto) throws BusinessException;
	public void delete(EntradasDTO dto) throws BusinessException;
	public EntradasDTO[] getEntradas() throws BusinessException;
}
