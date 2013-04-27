package common.business.bo;

import common.business.BusinessException;
import common.dto.GruposMenusDTO;

public interface GruposMenusBO {
	public GruposMenusDTO getByPrimaryKey(Long menFk, Long gruFk) throws BusinessException;
	public Long[] getByMenu(Long menFk) throws BusinessException;
	public void add(GruposMenusDTO dto) throws BusinessException;
	public void update(GruposMenusDTO dto) throws BusinessException;
	public void delete(GruposMenusDTO dto) throws BusinessException;
	public GruposMenusDTO[] getGruposMenus() throws BusinessException;
}
