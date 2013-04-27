package common.business.bo;

import common.business.BusinessException;
import common.dto.AssetsDTO;
import common.dto.LinksDTO;

public interface LinksBO {
	public LinksDTO getByPrimaryKey(Long id) throws BusinessException;
	public void add(LinksDTO link, AssetsDTO asset) throws BusinessException;
	public void update(LinksDTO dto, AssetsDTO asset) throws BusinessException;
	public void delete(LinksDTO dto) throws BusinessException;
	public LinksDTO[] getLinks() throws BusinessException;
	public LinksDTO[] getLinks(Long user, Long folder) throws BusinessException;
}
