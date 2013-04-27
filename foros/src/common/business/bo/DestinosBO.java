package common.business.bo;

import common.business.BusinessException;
import common.dto.DestinosDTO;

public interface DestinosBO {
	public DestinosDTO getByPrimaryKey(Long usuFk, Long entFk) throws BusinessException;
	public Long[] getByUser(Long usuFk) throws BusinessException;
	public void add(DestinosDTO dto) throws BusinessException;
	public void update(DestinosDTO dto) throws BusinessException;
	public void delete(DestinosDTO dto) throws BusinessException;
	public DestinosDTO[] getDestinos() throws BusinessException;
}
