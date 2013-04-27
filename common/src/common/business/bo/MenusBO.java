package common.business.bo;

import common.business.BusinessException;
import common.dto.MenusDTO;

public interface MenusBO {
	public MenusDTO getByPrimaryKey(Long id) throws BusinessException;
	public void add(MenusDTO dto) throws BusinessException;
	public void add(String path, byte[] file, MenusDTO dto) throws BusinessException;
	public void update(MenusDTO dto) throws BusinessException;
	public void update(String path, byte[] file, MenusDTO dto) throws BusinessException;
	public void delete(MenusDTO dto) throws BusinessException;
	public MenusDTO[] getMenus() throws BusinessException;
	MenusDTO getByCode(String uk) throws BusinessException;
	public MenusDTO[] getCaps(Long user) throws BusinessException;
	public MenusDTO[] getMenu(Long user, Long parent) throws BusinessException;
}
