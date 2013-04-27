package common.business.bo;

import common.business.BusinessException;
import common.dto.AssetsDTO;

public interface AssetsBO {
	public AssetsDTO getByPrimaryKey(Long id) throws BusinessException;
	public AssetsDTO getByPrimaryKey(Long id, Long user) throws BusinessException;
	public AssetsDTO getByCode(String uk) throws BusinessException;
	public void add(AssetsDTO dto) throws BusinessException;
	public void update(AssetsDTO dto) throws BusinessException;
	public void delete(AssetsDTO dto) throws BusinessException;
	public AssetsDTO[] getAssets() throws BusinessException;
}
