package common.business.bo;


import javax.servlet.http.HttpServletRequest;

import common.business.BusinessException;
import common.dto.UsuariosDTO;

public interface UsuariosBO {
	public UsuariosDTO getByPrimaryKey(Long id) throws BusinessException;
	public UsuariosDTO getByCode(String uk) throws BusinessException;
	public void add(UsuariosDTO dto) throws BusinessException;
	public void update(UsuariosDTO dto) throws BusinessException;
	public void updateUserData(UsuariosDTO dto, HttpServletRequest request) throws BusinessException;
	public void delete(UsuariosDTO dto) throws BusinessException;
	public void delete(UsuariosDTO dto, HttpServletRequest request) throws BusinessException;
	public UsuariosDTO[] getUsuarios() throws BusinessException;
	public UsuariosDTO[] getUsuarios(Long user) throws BusinessException;
	public UsuariosDTO[] getUsuarios(Long user, Long estado) throws BusinessException;
	public UsuariosDTO getLogin(String uk, String password) throws BusinessException;
	public UsuariosDTO[] getByFilter(String filter) throws BusinessException;
}
