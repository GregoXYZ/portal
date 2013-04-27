package common.business.bo;

import common.business.BusinessException;
import common.dto.BusquedasPersonalesDTO;

public interface BusquedasPersonalesBO {
	public BusquedasPersonalesDTO getByPrimaryKey(Long id) throws BusinessException;
	public BusquedasPersonalesDTO getByUniqueKey(Long user, Long tag) throws BusinessException;
	public void add(BusquedasPersonalesDTO dto) throws BusinessException;
	public void update(BusquedasPersonalesDTO dto) throws BusinessException;
	public void delete(BusquedasPersonalesDTO dto) throws BusinessException;
	public BusquedasPersonalesDTO[] getBusquedasPersonales() throws BusinessException;
}
