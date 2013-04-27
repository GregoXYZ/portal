package common.business.bo;

import common.business.BusinessException;
import common.dto.MimeFilesDTO;

public interface MimeFilesBO {
	public MimeFilesDTO getByPrimaryKey(Long id) throws BusinessException;
	public void add(String path, byte[] file, MimeFilesDTO dto) throws BusinessException;
	public void update(String path, byte[] file, MimeFilesDTO dto) throws BusinessException;
	public void delete(MimeFilesDTO dto) throws BusinessException;
	public MimeFilesDTO[] getMimeFiles() throws BusinessException;
	public MimeFilesDTO getByExtension(String uk) throws BusinessException;
}
