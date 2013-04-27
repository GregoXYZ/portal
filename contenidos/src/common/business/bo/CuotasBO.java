package common.business.bo;

import common.business.BusinessException;
import common.dto.CuotasDTO;

public interface CuotasBO {
	public CuotasDTO getByPrimaryKey(Long id) throws BusinessException;
	public CuotasDTO getCuotaRestante(Long user) throws BusinessException;
	public void add(CuotasDTO dto) throws BusinessException;
	public void update(CuotasDTO dto) throws BusinessException;
	public void delete(CuotasDTO dto) throws BusinessException;
	public CuotasDTO[] getCuotas() throws BusinessException;
}
