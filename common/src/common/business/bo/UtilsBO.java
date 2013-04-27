package common.business.bo;

import java.util.Hashtable;

import common.business.BusinessException;
import common.dto.UsuariosDTO;

public interface UtilsBO {
	public void registerUser(UsuariosDTO dto) throws BusinessException;
	public Hashtable<Object, Object> getPerfil(Long pk) throws BusinessException;
}
