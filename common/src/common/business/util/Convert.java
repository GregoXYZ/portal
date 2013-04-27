package common.business.util;


import java.util.Date;

import common.business.BusinessException;
import common.business.bo.MenusBO;
import common.business.bo.ZonasBO;


import common.business.beans.Bugs;
import common.business.beans.EstadosRelacionUsuarios;
import common.business.beans.Grupos;
import common.business.beans.GruposMenus;
import common.business.beans.GruposMenusId;
import common.business.beans.Avisos;
import common.business.beans.RelacionesUsuarios;
import common.business.beans.Sessions;
import common.business.beans.Urls;
import common.business.beans.UrlsGrupos;
import common.business.beans.UrlsGruposId;
import common.business.beans.Zonas;
import common.business.beans.Menus;
import common.business.beans.Usuarios;
import common.business.beans.UsuariosGrupos;
import common.business.beans.UsuariosGruposId;
import common.dto.BugsDTO;
import common.dto.EstadosRelacionUsuariosDTO;
import common.dto.GruposDTO;
import common.dto.GruposMenusDTO;
import common.dto.AvisosDTO;
import common.dto.MenusDTO;
import common.dto.RelacionesUsuariosDTO;
import common.dto.SessionsDTO;
import common.dto.UrlsDTO;
import common.dto.UrlsGruposDTO;
import common.dto.UsuariosDTO;
import common.dto.UsuariosGruposDTO;
import common.dto.ZonasDTO;
import common.util.spring.SpringUtil;

public class Convert {
	
	public static Zonas dto2dao(ZonasDTO dto) {
		Zonas dao = new Zonas(dto.getZonPk(), dto.getZonCodigo(), dto.getZonDesc());
		dao.setZonPk(dto.getZonPk());
		
		return dao;
	}

	public static Menus dto2dao(MenusDTO dto) throws BusinessException {
		Menus dao = new Menus(dto.getMenCodigo(), dto.getMenDescripcion(), dto.getMenTitulo());
		dao.setMenPk(dto.getMenPk());
		dao.setMenIcon(dto.getMenIcon());
		dao.setMenOrden(dto.getMenOrden());
		dao.setMenPopup(dto.getMenPopup());
		dao.setMenRestringido(dto.getMenRestringido());
		
		if ( dto.getMenFk() != null && dto.getMenFk() > 0 )
		{
	    	MenusBO boMenu = (MenusBO) SpringUtil.getInstance().getBean("MenusBO");    	
			MenusDTO menu = boMenu.getByPrimaryKey(dto.getMenFk());
			dao.setMenus(dto2dao(menu));
		}
		
		if ( dto.getUrlFk() == null || dto.getUrlFk() == 0) dao.setUrlFk(null);
		else dao.setUrlFk(dto.getUrlFk());
		
		return dao;
	}

	public static Usuarios dto2dao(UsuariosDTO dto) {
		Usuarios dao = new Usuarios(dto.getUsuUkUsuario(), dto.getUsuNombre(), dto.getUsuContrasena());
		
		dao.setUsuPk(dto.getUsuPk());
		dao.setUsuApellido1(dto.getUsuApellido1());
		dao.setUsuApellido2(dto.getUsuApellido2());
		dao.setUsuFechaBaja(dto.getUsuFechaBaja());
		dao.setUsuMail(dto.getUsuMail());
		dao.setUsuActivo(dto.isUsuActivo());
		dao.setUsuAvatar(dto.getUsuAvatar());
		dao.setUsuFechaBaja(dto.getUsuFechaBaja());
		dao.setUsuPublicable(dto.isUsuPublicable());
		dao.setUsuRecibeAviso(dto.isUsuRecibeAviso());

		return dao;
	}

	public static Grupos dto2dao(GruposDTO dto) {
		Grupos dao = new Grupos(dto.getGruUkCodigo(), dto.getGruDescripcion(), dto.getGruPrioridad());
		dao.setGruPk(dto.getGruPk());
		
		return dao;
	}

	public static UsuariosGrupos dto2dao(UsuariosGruposDTO dto) {
		//UsuariosDAOImpl usuDAO = new UsuariosDAOImpl();
		//GruposDAOImpl gruDAO = new GruposDAOImpl();
		UsuariosGrupos dao = new UsuariosGrupos();
		UsuariosGruposId id = new UsuariosGruposId();
		
		id.setGruFk(dto.getGruFk());
		id.setUsuFk(dto.getUsuFk());
		
		dao.setId(id);
		//dao.setUsuarios(usuDAO.getByPrimaryKey(id.getUsuFk()));
		//dao.setGrupos(gruDAO.getByPrimaryKey(id.getGruFk()));
		
		return dao;
	}

	public static UrlsGrupos dto2dao(UrlsGruposDTO dto) {
		UrlsGrupos dao = new UrlsGrupos();
		UrlsGruposId id = new UrlsGruposId();
		
		id.setGruFk(dto.getGruFk());
		id.setUrlFk(dto.getUrlFk());
		
		dao.setId(id);
		
		return dao;
	}

	public static GruposMenus dto2dao(GruposMenusDTO dto) {
		GruposMenus dao = new GruposMenus();
		GruposMenusId id = new GruposMenusId();
		
		id.setGruFk(dto.getGruFk());
		id.setMenFk(dto.getMenFk());
		
		dao.setId(id);
		
		return dao;
	}

	public static Bugs dto2dao(BugsDTO dto) {
		Bugs dao = new Bugs(dto.getBugSite(), dto.getBugMessage(), 
				dto.getBugDescripcion(), dto.getUsuFk(), 
				dto.getBugFechaReport());
		
		dao.setBugEstFk(dto.getBugEstFk());
		dao.setBugPriFk(dto.getBugPriFk());
		dao.setBugPk(dto.getBugPk());
		
		return dao;
	}

	public static Avisos dto2dao(AvisosDTO dto) {
		Avisos dao = new Avisos(dto.getUsuFkOrigen(), 
				dto.getUsuFkDestino(), 
				dto.getAviFEnvio(),
				dto.getTipAviFk(),
				dto.getAviReferencia(),
				dto.getAviCantidad());
		dao.setAviPk(dto.getAviPk());
		dao.setAviFLeido(dto.getAviFLeido());
		
		return dao;		
	}

	public static RelacionesUsuarios dto2dao(RelacionesUsuariosDTO dto) {
		RelacionesUsuarios dao = new RelacionesUsuarios();
		dao.setRelUsuPk(dto.getRelUsuPk());
		dao.setUsu1Fk(dto.getUsu1Fk());
		dao.setUsu2Fk(dto.getUsu2Fk());
		dao.setEstRelUsuFk(dto.getEstRelUsuFk());
		dao.setRelUsuFechaRelacion(dto.getRelUsuFechaRelacion());
		
		return dao;		
	}

	public static EstadosRelacionUsuarios dto2dao(EstadosRelacionUsuariosDTO dto) {
		EstadosRelacionUsuarios dao = new EstadosRelacionUsuarios(dto.getEstRelUsuUkCodigo());
		dao.setEstRelUsuPk(dto.getEstRelUsuPk());
		
		return dao;		
	}

	public static UsuariosGruposDTO dao2dto(UsuariosGrupos dao) {

		UsuariosGruposDTO dto = new UsuariosGruposDTO();
		
		dto.setUsuFk(dao.getUsuarios().getUsuPk());
		dto.setGruFk(dao.getGrupos().getGruPk());
		
		return dto;
	}
	
	public static Urls dto2dao(UrlsDTO dto) throws BusinessException {
		Urls dao = new Urls(null, dto.getUrlCodigo(), dto.getUrlDescripcion(), dto.getUrlDireccion());
		dao.setUrlPk(dto.getUrlPk());
		
		if ( dto.getZonFk() != null && dto.getZonFk() > 0 )
		{
	    	ZonasBO boZona = (ZonasBO) SpringUtil.getInstance().getBean("ZonasBO");    	
			ZonasDTO zona = boZona.getByPrimaryKey(dto.getZonFk());			
			dao.setZonas(dto2dao(zona));
		}		
		return dao;
	}

	public static Sessions dto2dao(SessionsDTO dto) {
		Sessions dao = new Sessions(dto.getSessionId(), dto.getShareSessionId(), 
				dto.getValidSession(), dto.getMaxInactive(), dto.getCreationTime().getTime(), 
				dto.getLastAccessedTime().getTime(), dto.getAppName(), dto.getSessionData(), 
				dto.getUserId());
		dao.setAddress(dto.getAddress());
		dao.setSessionPk(dto.getSessionPk());
		dao.setClosedTime(dto.getClosedTime() == null?null:dto.getClosedTime().getTime());
	
		return dao;
	}
	
	public static UrlsDTO dao2dto(Urls dao) {
		UrlsDTO dto = new UrlsDTO(dao.getZonas().getZonPk(), dao.getUrlUkCodigo(), dao.getUrlDescripcion(), dao.getUrlDireccion());
		dto.setUrlPk(dao.getUrlPk());
		
		return dto;
	}
	
	public static ZonasDTO dao2dto(Zonas dao) {
		ZonasDTO dto = new ZonasDTO(dao.getZonUkCodigo(), dao.getZonDescripcion());
		dto.setZonPk(dao.getZonPk());
		
		return dto;
	}

	public static MenusDTO dao2dto(Menus dao) {
		MenusDTO dto = null;
		if ( dao!= null )
		{
			dto = new MenusDTO(dao.getMenUkCodigo(), dao.getMenDescripcion(), dao.getMenTitulo());

			dto.setMenPk(dao.getMenPk());
			dto.setUrlFk(dao.getUrlFk());
			dto.setMenIcon(dao.getMenIcon());
			dto.setMenOrden(dao.getMenOrden());
			dto.setMenPopup(dao.getMenPopup());
			dto.setMenRestringido(dao.getMenRestringido());
			if ( dao.getMenus()!= null ) dto.setMenFk(dao.getMenus().getMenPk());
		}
		
		return dto;
	}

	public static UsuariosDTO dao2dto(Usuarios dao) {
		UsuariosDTO dto = null;
		if ( dao!= null )
		{
			dto = new UsuariosDTO(dao.getUsuUkCodigo(), dao.getUsuNombre(), dao.getUsuContrasena());
			
			dto.setUsuPk(dao.getUsuPk());
			dto.setUsuApellido1(dao.getUsuApellido1());
			dto.setUsuApellido2(dao.getUsuApellido2());
			dto.setUsuFechaBaja(dao.getUsuFechaBaja());
			dto.setUsuMail(dao.getUsuMail());
			dto.setUsuActivo(dao.isUsuActivo());
			dto.setUsuAvatar(dao.getUsuAvatar());
			dto.setUsuFechaBaja(dao.getUsuFechaBaja());
			dto.setUsuPublicable(dao.isUsuPublicable());
			dto.setUsuRecibeAviso(dao.isUsuRecibeAviso());
		}
		
		return dto;
	}

	public static GruposDTO dao2dto(Grupos dao) {
		GruposDTO dto = new GruposDTO(dao.getGruUkCodigo(), dao.getGruDescripcion(), dao.getGruPrioridad());
		dto.setGruPk(dao.getGruPk());
		
		return dto;
	}
	
	public static SessionsDTO dao2dto(Sessions dao) {
		
		SessionsDTO dto = new SessionsDTO();
		dto.setSessionPk(dao.getSessionPk());
		dto.setAppName(dao.getAppName());
		if ( dao.getCreationTime() != null )
			dto.setCreationTime(new Date(dao.getCreationTime()));
		if ( dao.getLastAccessedTime() != null )
			dto.setLastAccessedTime(new Date(dao.getLastAccessedTime()));
		dto.setMaxInactive(dao.getMaxInactive());
		dto.setSessionData(dao.getSessionData());
		dto.setSessionId(dao.getSessionId());
		dto.setShareSessionId(dao.getShareSessionId());
		dto.setAddress(dao.getAddress());
		dto.setUserId(dao.getUserId());
		dto.setValidSession(dao.getValidSession());
		if ( dao.getClosedTime() != null )
			dto.setClosedTime(new Date(dao.getClosedTime()));
				
		return dto;
	}

	public static BugsDTO dao2dto(Bugs dao) {
		BugsDTO dto = new BugsDTO();

		dto.setBugPk(dao.getBugPk());
		dto.setBugDescripcion(dao.getBugDescripcion());
		dto.setBugFechaReport(dao.getBugFechaReport());
		dto.setBugMessage(dao.getBugMessage());
		dto.setBugSite(dao.getBugSite());
		dto.setUsuFk(dao.getUsuFk());
		dto.setBugEstFk(dao.getBugEstFk());
		dto.setBugPriFk(dao.getBugPriFk());
		
		return dto;
	}

	public static AvisosDTO dao2dto(Avisos dao) {
		AvisosDTO dto = new AvisosDTO(dao.getUsuFkOrigen(), 
				dao.getUsuFkDestino(), 
				dao.getAviFEnvio(),
				dao.getTipAviFk(),
				dao.getAviReferencia(),
				dao.getAviCantidad());
		dto.setAviPk(dao.getAviPk());
		dto.setAviFLeido(dao.getAviFLeido());
		return dto;
	}

	public static RelacionesUsuariosDTO dao2dto(RelacionesUsuarios dao) {
		if ( dao!= null )
		{
			RelacionesUsuariosDTO dto = new RelacionesUsuariosDTO(dao.getEstRelUsuFk(), 
					dao.getUsu1Fk(), dao.getUsu2Fk());
			dto.setRelUsuPk(dao.getRelUsuPk());
			dto.setRelUsuFechaRelacion(dao.getRelUsuFechaRelacion());
			
			return dto;
		}
		return null;
	}

	public static EstadosRelacionUsuariosDTO dao2dto(EstadosRelacionUsuarios dao) {
		EstadosRelacionUsuariosDTO dto = new EstadosRelacionUsuariosDTO(dao.getEstRelUsuUkCodigo());
		dto.setEstRelUsuPk(dao.getEstRelUsuPk());
		return dto;
	}
}
