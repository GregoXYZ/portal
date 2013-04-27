package common.business.bo;

import common.business.BusinessException;
import common.dto.TagsDTO;

public interface TagsBO {
	public TagsDTO getByPrimaryKey(Long id) throws BusinessException;
	public void add(TagsDTO dto) throws BusinessException;
	public void update(TagsDTO dto) throws BusinessException;
	public void updateContadores(String tags) throws BusinessException;
	public void delete(TagsDTO dto) throws BusinessException;
	public void deleteTagAsset(TagsDTO dto) throws BusinessException;
	public TagsDTO[] getTags() throws BusinessException;
}
