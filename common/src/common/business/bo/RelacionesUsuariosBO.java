package common.business.bo;

import common.business.BusinessException;
import common.dto.RelacionesUsuariosDTO;

public interface RelacionesUsuariosBO {
	public RelacionesUsuariosDTO getByPrimaryKey(Long id) throws BusinessException;
	public void add(RelacionesUsuariosDTO dto) throws BusinessException;
	public void update(RelacionesUsuariosDTO dto) throws BusinessException;
	public void delete(RelacionesUsuariosDTO dto) throws BusinessException;
	public RelacionesUsuariosDTO[] getRelacionesUsuarios() throws BusinessException;
	public RelacionesUsuariosDTO getRelacionUsuarios(Long user1, Long user2) throws BusinessException;
	public RelacionesUsuariosDTO[] getRelacionByEstado(Long user, Long estado) throws BusinessException;	
}
