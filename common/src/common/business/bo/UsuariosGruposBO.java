package common.business.bo;

import common.business.BusinessException;
import common.dto.UsuariosGruposDTO;

public interface UsuariosGruposBO {
	public UsuariosGruposDTO getByPrimaryKey(Long usuFk, Long gruFk) throws BusinessException;
	public Long[] getByUser(Long usuFk) throws BusinessException;
	public void add(UsuariosGruposDTO dto) throws BusinessException;
	public void update(UsuariosGruposDTO dto) throws BusinessException;
	public void delete(UsuariosGruposDTO dto) throws BusinessException;
	public UsuariosGruposDTO[] getUsuariosGrupos() throws BusinessException;
}
