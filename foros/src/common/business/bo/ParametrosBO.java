package common.business.bo;

import common.business.BusinessException;
import common.dto.ParametrosDTO;

public interface ParametrosBO {
	public ParametrosDTO getByPrimaryKey(String id) throws BusinessException;
	public void add(ParametrosDTO dto) throws BusinessException;
	public void update(ParametrosDTO dto) throws BusinessException;
	public void delete(ParametrosDTO dto) throws BusinessException;
	public ParametrosDTO[] getParametros() throws BusinessException;

	public String getValor(String id) throws BusinessException;
}
