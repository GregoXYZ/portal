package common.business.bo;

import common.business.BusinessException;
import common.dto.ZonasDTO;

public interface ZonasBO {
	public ZonasDTO getByPrimaryKey(Long id) throws BusinessException;
	public void add(ZonasDTO dto) throws BusinessException;
	public void update(ZonasDTO dto) throws BusinessException;
	public void delete(ZonasDTO dto) throws BusinessException;
	public ZonasDTO[] getZonas() throws BusinessException;
	public ZonasDTO getByCode(String uk) throws BusinessException;
}
