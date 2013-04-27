package common.business.bo;

import common.business.BusinessException;
import common.dto.ExtensionFilesDTO;

public interface ExtensionFilesBO {
	public ExtensionFilesDTO getByPrimaryKey(Long id) throws BusinessException;
	public void add(String path, byte[] file, ExtensionFilesDTO dto) throws BusinessException;
	public void update(String path, byte[] file, ExtensionFilesDTO dto) throws BusinessException;
	public void delete(ExtensionFilesDTO dto) throws BusinessException;
	public ExtensionFilesDTO[] getExtensionFiles() throws BusinessException;
	public ExtensionFilesDTO getByExtension(String uk) throws BusinessException;
}
