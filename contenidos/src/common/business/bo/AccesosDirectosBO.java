package common.business.bo;

import common.business.BusinessException;
import common.dto.AccesosDirectosDTO;

public interface AccesosDirectosBO {
	public AccesosDirectosDTO getByPrimaryKey(Long id) throws BusinessException;
	public void add(AccesosDirectosDTO dto) throws BusinessException;
	public void update(AccesosDirectosDTO dto) throws BusinessException;
	public void delete(AccesosDirectosDTO dto) throws BusinessException;
	public AccesosDirectosDTO[] getAccesosDirectos() throws BusinessException;
}
