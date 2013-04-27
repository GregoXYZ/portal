package common.business.bo;

import common.business.BusinessException;
import common.dto.CompartidosDTO;

public interface CompartidosBO {
	public CompartidosDTO getByPrimaryKey(Long id) throws BusinessException;
	public CompartidosDTO getByUniqueKey(Long assFk, Long usuFk) throws BusinessException;
	public CompartidosDTO[] getByUser(Long usuFk) throws BusinessException;
	public CompartidosDTO[] getByAsset(Long assFk) throws BusinessException;
	public void add(CompartidosDTO dto) throws BusinessException;
	public void update(CompartidosDTO dto) throws BusinessException;
	public void delete(CompartidosDTO dto) throws BusinessException;
	public CompartidosDTO[] getCompartidos() throws BusinessException;
}
