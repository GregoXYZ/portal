package business.util;

import business.beans.Contenidos;
import business.beans.Destinos;
import business.beans.DestinosId;
import business.beans.Entradas;
import business.beans.Parametros;

import common.business.BusinessException;
import common.dto.ContenidosDTO;
import common.dto.DestinosDTO;
import common.dto.EntradasDTO;
import common.dto.ParametrosDTO;

public class Convert {
	public static Parametros dto2dao(ParametrosDTO dto) {
		Parametros dao = new Parametros(dto.getParPk(), dto.getParValor(), dto.getParDescripcion());
		
		return dao;
	}

	public static Destinos dto2dao(DestinosDTO dto) {
		Destinos dao = new Destinos();
		DestinosId id = new DestinosId();
		
		id.setEntFk(dto.getEntFk());
		id.setUsuFk(dto.getUsuFk());
		
		dao.setId(id);
		
		return dao;
	}
	
	public static Entradas dto2dao(EntradasDTO dto) throws BusinessException {
		Entradas dao = new Entradas(dto.getEntFechaAlta(), dto.getEntSubject(), dto.getUsuFk());
		dao.setEntRestringida(dto.getEntRestringida());
		dao.setEntPk(dto.getEntPk());
		
		return dao;
	}

	public static Contenidos dto2dao(ContenidosDTO dto) {
		Contenidos dao = new Contenidos(dto.getConData(), dto.getConFechaAlta(), dto.getEntFk(), dto.getUsuFk(), dto.getConFk());
		dao.setConPk(dto.getConPk());
		
		return dao;
	}

	public static DestinosDTO dao2dto(Destinos dao)
	{
		if (dao == null)
			return null;
		DestinosDTO dto = new DestinosDTO();
		dto.setEntFk(dao.getId().getEntFk());
		dto.setUsuFk(dao.getId().getUsuFk());
		return dto;
	}
	
	public static ParametrosDTO dao2dto(Parametros dao)
	{
		ParametrosDTO dto = new ParametrosDTO();
		dto.setParPk(dao.getParPk());
		dto.setParValor(dao.getParValor());
		dto.setParDescripcion(dao.getParDescripcion());
		return dto;
	}
	
	public static EntradasDTO dao2dto(Entradas dao) {
		EntradasDTO dto = new EntradasDTO(dao.getEntFechaAlta(), dao.getEntSubject(), dao.getUsuFk());
		dto.setEntRestringida(dao.getEntRestringida());
		dto.setEntPk(dao.getEntPk());
		
		return dto;
	}
	
	public static ContenidosDTO dao2dto(Contenidos dao) {
		ContenidosDTO dto = new ContenidosDTO(dao.getConData(), dao.getConFechaAlta(), dao.getEntFk(), dao.getUsuFk(), dao.getConFk());
		dto.setConPk(dao.getConPk());
		
		return dto;
	}
}
