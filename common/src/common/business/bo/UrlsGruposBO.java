package common.business.bo;

import common.business.BusinessException;
import common.dto.UrlsGruposDTO;

public interface UrlsGruposBO {
	public UrlsGruposDTO getByPrimaryKey(Long urlFk, Long gruFk) throws BusinessException;
	public Long[] getByUrl(Long urlFk) throws BusinessException;
	public void add(UrlsGruposDTO dto) throws BusinessException;
	public void update(UrlsGruposDTO dto) throws BusinessException;
	public void delete(UrlsGruposDTO dto) throws BusinessException;
	public UrlsGruposDTO[] getUrlsGrupos() throws BusinessException;
}
