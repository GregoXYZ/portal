package common.business.bo;

import org.apache.struts.upload.FormFile;

import common.business.BusinessException;
import common.dto.AssetsDTO;
import common.dto.FicherosDTO;

public interface FicherosBO {
	public FicherosDTO getByPrimaryKey(Long id) throws BusinessException;
	public void add(String path, FormFile file, AssetsDTO asset, FicherosDTO fichero, Long[] usuarios, String tags) throws BusinessException;
	public void addZipFile(String path, FormFile file, AssetsDTO asset, FicherosDTO fichero, Long[] usuarios, String tags) throws BusinessException;
	public void update(FicherosDTO dto) throws BusinessException;
	public void delete(FicherosDTO dto) throws BusinessException;
	public FicherosDTO[] getFicheros() throws BusinessException;
	public FicherosDTO[] getFicheros(Long user, Long folder) throws BusinessException;
	public FicherosDTO getByAsset(Long id) throws BusinessException;
	public FicherosDTO[] getDuplicados(Long user) throws BusinessException;
	public void move(long ficPK, long carFk) throws BusinessException;
}
