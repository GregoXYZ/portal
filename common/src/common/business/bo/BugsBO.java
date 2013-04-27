package common.business.bo;

import common.business.BusinessException;
import common.dto.BugsDTO;

public interface BugsBO {
	public BugsDTO getByPrimaryKey(Long id) throws BusinessException;
	public void add(BugsDTO dto) throws BusinessException;
	public void update(BugsDTO dto) throws BusinessException;
	public void delete(BugsDTO dto) throws BusinessException;
	public BugsDTO[] getBugs() throws BusinessException;
	public BugsDTO[] getBugsExtended() throws BusinessException;
}
