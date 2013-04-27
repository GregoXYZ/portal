package common.business.bo;

import common.business.BusinessException;
import common.dto.AssetsDTO;
import common.dto.CarpetasDTO;

public interface CarpetasBO {
	public CarpetasDTO getByPrimaryKey(Long id) throws BusinessException;
	public CarpetasDTO getByAsset(Long id) throws BusinessException;
	public void add(Long parent, AssetsDTO dto) throws BusinessException;
	public void update(CarpetasDTO dto) throws BusinessException;
	public void delete(CarpetasDTO dto) throws BusinessException;
	public CarpetasDTO[] getCarpetas() throws BusinessException;
	public CarpetasDTO[] getCarpetas(Long user, Long folder) throws BusinessException;
	public void move(long carPK, long carFk) throws BusinessException;
}
