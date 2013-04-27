package common.business.bo;

import common.business.BusinessException;
import common.dto.ContenidosDTO;
import common.dto.EntradasDTO;
import common.dto.MensajesDTO;

public interface QueriesForosBO {
	public MensajesDTO[] getResults(Long user) throws BusinessException;
	public MensajesDTO[] getThreats(Long user, Long entrada) throws BusinessException;
	public EntradasDTO[] getEntradas(Long user) throws BusinessException;
	public ContenidosDTO[] getContenidos(Long entrada, Long user) throws BusinessException;
	public ContenidosDTO getUltimoContenido(Long entrada) throws BusinessException;
	public MensajesDTO getContenido(Long user, Long contenido) throws BusinessException;
	public void addEntrada(Long user, String entrada, String contenido, Boolean restringida, Long[] users) throws BusinessException;
	public void addInvitaciones(Long entrada, Long user, Long[] users) throws BusinessException;
}
