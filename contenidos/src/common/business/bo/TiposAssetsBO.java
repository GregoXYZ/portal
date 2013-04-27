package common.business.bo;

import common.business.BusinessException;
import common.dto.TiposAssetsDTO;

public interface TiposAssetsBO {
	public TiposAssetsDTO getByPrimaryKey(Long id) throws BusinessException;
	public TiposAssetsDTO getByCode(String uk) throws BusinessException;
	public void add(TiposAssetsDTO dto) throws BusinessException;
	public void update(TiposAssetsDTO dto) throws BusinessException;
	public void delete(TiposAssetsDTO dto) throws BusinessException;
	public TiposAssetsDTO[] getTiposAssets() throws BusinessException;
}
