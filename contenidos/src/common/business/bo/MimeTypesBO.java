package common.business.bo;

import common.business.BusinessException;
import common.dto.MimeTypesDTO;

public interface MimeTypesBO {
	public MimeTypesDTO getByPrimaryKey(Long id) throws BusinessException;
	public void add(MimeTypesDTO dto) throws BusinessException;
	public void update(MimeTypesDTO dto) throws BusinessException;
	public void delete(MimeTypesDTO dto) throws BusinessException;
	public MimeTypesDTO[] getMimeTypes() throws BusinessException;
}
