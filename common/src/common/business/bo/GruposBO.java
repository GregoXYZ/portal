package common.business.bo;

import common.business.BusinessException;
import common.dto.GruposDTO;

public interface GruposBO {
	public GruposDTO getByPrimaryKey(Long id) throws BusinessException;
	public GruposDTO getByCode(String uk) throws BusinessException;
	public void add(GruposDTO dto) throws BusinessException;
	public void update(GruposDTO dto) throws BusinessException;
	public void delete(GruposDTO dto) throws BusinessException;
	public GruposDTO[] getGrupos() throws BusinessException;
}
