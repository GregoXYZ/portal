package common.business.bo;

import common.business.BusinessException;
import common.dto.UrlsDTO;

public interface UrlsBO {
	public UrlsDTO getByPrimaryKey(Long id) throws BusinessException;
	public void add(UrlsDTO dto) throws BusinessException;
	public void update(UrlsDTO dto) throws BusinessException;
	public void delete(UrlsDTO dto) throws BusinessException;
	public UrlsDTO[] getUrls() throws BusinessException;
	public UrlsDTO getByCode(String uk) throws BusinessException;
}
