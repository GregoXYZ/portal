package common.business.bo;

import common.business.BusinessException;
import common.dto.EstadosRelacionUsuariosDTO;

public interface EstadosRelacionUsuariosBO {
	public EstadosRelacionUsuariosDTO getByPrimaryKey(Long id) throws BusinessException;
	public void add(EstadosRelacionUsuariosDTO dto) throws BusinessException;
	public void update(EstadosRelacionUsuariosDTO dto) throws BusinessException;
	public void delete(EstadosRelacionUsuariosDTO dto) throws BusinessException;
	public EstadosRelacionUsuariosDTO[] getEstadosRelacionUsuarios() throws BusinessException;
}
