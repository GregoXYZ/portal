package common.business.bo;

import common.business.BusinessException;
import common.dto.AssetsDTO;
import common.dto.CarpetasDTO;
import common.dto.FicherosDTO;
import common.dto.TagsDTO;
import common.dto.TagsNubeDTO;
import common.dto.UsuariosDTO;

public interface ExtraQueriesBO {
	public UsuariosDTO[] getUsersContenidos() throws BusinessException;
	public UsuariosDTO[] getUsersCompartir(Long user) throws BusinessException;
	public void updateFichero(AssetsDTO asset, Long[] usuarios, String tags) throws BusinessException;
	public void comparteFicheros(AssetsDTO[] asset, Long[] usuarios, boolean reemplaza, String tags) throws BusinessException;
	public UsuariosDTO[] getUsersComparten(Long user) throws BusinessException;
	public AssetsDTO[] getAssetsCompartidos(Long user, Long propietario) throws BusinessException;
	public FicherosDTO[] getFicherosCompartidos(Long user, Long propietario, Long folder) throws BusinessException;
	public FicherosDTO[] getMyFicherosCompartidos(Long user, Long folder) throws BusinessException;
	public CarpetasDTO[] getCarpetasConCompartidos(Long user, Long propietario) throws BusinessException;
	public CarpetasDTO[] getMyCarpetasCompartidas(Long user) throws BusinessException;
	public TagsDTO[] getTags(Long user, Long asset) throws BusinessException;
	public AssetsDTO[] getLinks(Long user, Long asset) throws BusinessException;
	public TagsNubeDTO[] getTagsNube(Long user, Integer tipo) throws BusinessException;
	public AssetsDTO[] getAssetsTag(Long user, Long tag) throws BusinessException;
	public FicherosDTO[] getFicherosTag(Long user, Long tag) throws BusinessException;
	public FicherosDTO[] findAssets(String criteria, String tags, Long user) throws BusinessException;
}
