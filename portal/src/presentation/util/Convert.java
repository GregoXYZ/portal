package presentation.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.dto.BugsDTO;
import common.dto.GruposDTO;
import common.dto.MenusDTO;
import common.dto.UrlsDTO;
import common.dto.UsuariosDTO;
import common.dto.ZonasDTO;
import common.presentation.web.security.forms.BaseForm;

import presentation.web.forms.BugForm;
import presentation.web.forms.GroupForm;
import presentation.web.forms.MenuForm;
import presentation.web.forms.UrlForm;
import presentation.web.forms.UserForm;
import presentation.web.forms.ZonaForm;

public class Convert {

	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(Convert.class);

	private BaseForm form;
	public void setForm(BaseForm form) {this.form = form;}

	// Instancia del Singleton
	private static Convert _instance = null;
	
	// Miembros de la clase
	// Inicializador de clase
	static {
		_instance = new Convert();
	}

	/**
	 * Acceso al Singleton
	 * @return ConfigProperties
	 */
	public static Convert getInstance () {
		/*if (_instance == null) {
			_instance = new ConfigProperties();
		}*/
		return _instance;
	}
	
	public ZonaForm dto2form(ZonasDTO dto) {
		ZonaForm frm;
		if ( form == null) frm = new ZonaForm();
		else frm = (ZonaForm) form;
		frm.setZonPk(dto.getZonPk());
    	frm.setZonDesc(dto.getZonDesc());
    	frm.setZonCodigo(dto.getZonCodigo());
		
		return frm;
	}

	public MenuForm dto2form(MenusDTO dto) {
		MenuForm frm;
		if ( form == null) frm = new MenuForm();
		else frm = (MenuForm) form;
		frm.setMenPk(dto.getMenPk());
		frm.setMenCodigo(dto.getMenCodigo());
		frm.setMenDescripcion(dto.getMenDescripcion());
		frm.setMenTitulo(dto.getMenTitulo());
		frm.setMenFk(dto.getMenFk());
		frm.setUrlFk(dto.getUrlFk());
		frm.setGrupos(dto.getGrupos());
		frm.setMenPopup(dto.getMenPopup()=='S');
		frm.setMenRestringido(dto.getMenRestringido()=='S');
		//frm.setMenIcon(dto.getMenIcon());
		frm.setMenOrden(dto.getMenOrden());
		
		return frm;
	}

	public UserForm dto2form(UsuariosDTO dto) {
		UserForm frm;
		if ( form == null) frm = new UserForm();
		else frm = (UserForm) form;
		frm.setUsuPk(dto.getUsuPk());
		frm.setNombre(dto.getUsuNombre());
		frm.setApellido1(dto.getUsuApellido1());
		frm.setApellido2(dto.getUsuApellido2());
		frm.setMail(dto.getUsuMail());
		frm.setUser(dto.getUsuUkUsuario());
		if ( dto.getUsuFechaBaja() == null )
			frm.setFechaBaja(null);
		else
			frm.setFechaBaja(new SimpleDateFormat("dd/MM/yyyy").format(new Date(dto.getUsuFechaBaja())));
		frm.setActivo(dto.isUsuActivo());
		frm.setPerfil(dto.getPerfil());
		frm.setPublicable(dto.isUsuPublicable());
		frm.setRecibeAbisos(dto.isUsuRecibeAviso());
		return frm;
	}

	public GroupForm dto2form(GruposDTO dto) {
		GroupForm frm;
		if ( form == null) frm = new GroupForm();
		else frm = (GroupForm) form;
		frm.setGruPk(dto.getGruPk());
		frm.setGruDescripcion(dto.getGruDescripcion());
		frm.setGruPrioridad(dto.getGruPrioridad());
		frm.setGruUkCodigo(dto.getGruUkCodigo());
		
		return frm;
	}

	public UrlForm dto2form(UrlsDTO dto) {
		UrlForm frm;
		if ( form == null) frm = new UrlForm();
		else frm = (UrlForm) form;
		frm.setUrlPk(dto.getUrlPk());
		frm.setUrlCodigo(dto.getUrlCodigo());
		frm.setUrlDescripcion(dto.getUrlDescripcion());
		frm.setUrlDireccion(dto.getUrlDireccion());
		frm.setZonFk(dto.getZonFk());
		frm.setGrupos(dto.getGrupos());
		
		return frm;
	}

	public BugForm dto2form(BugsDTO dto) {
		BugForm frm;
		if ( form == null) frm = new BugForm();
		else frm = (BugForm) form;
		frm.setBugPk(dto.getBugPk());
		frm.setBugDescripcion(dto.getBugDescripcion());
		
		if ( dto.getBugFechaReport() == null )
			frm.setBugFechaReport(null);
		else
			frm.setBugFechaReport(new SimpleDateFormat("dd/MM/yyyy").format(new Date(dto.getBugFechaReport())));

		frm.setBugMessage(dto.getBugMessage());
		frm.setBugSite(dto.getZonUkCodigo());
		frm.setBugSitePk(dto.getBugSite());
		frm.setUsuFk(dto.getUsuFk());
		frm.setBugEstado(dto.getBugEstFk());
		frm.setBugPrioridad(dto.getBugPriFk());
		
		return frm;
	}
	
	public UrlsDTO frm2dto(UrlForm frm) {
		UrlsDTO dto = new UrlsDTO(frm.getZonFk(), frm.getUrlCodigo(), frm.getUrlDescripcion(), frm.getUrlDireccion());
		dto.setUrlPk(frm.getUrlPk());
		if ( frm.getZonFk() == 0 ) frm.setZonFk(null);
		dto.setGrupos(frm.getGrupos());
		
		return dto;
	}
	
	public ZonasDTO frm2dto(ZonaForm frm) {
		ZonasDTO dto = new ZonasDTO(frm.getZonCodigo(), frm.getZonDesc());
		dto.setZonPk(frm.getZonPk());		
		return dto;
	}

	public MenusDTO frm2dto(MenuForm frm) throws FileNotFoundException, IOException {
		MenusDTO dto = new MenusDTO(frm.getMenCodigo(), frm.getMenDescripcion(), 
				frm.getMenTitulo());
		dto.setMenPk(frm.getMenPk());
		dto.setUrlFk(frm.getUrlFk());
		if (frm.getMenIcon() != null && frm.getMenIcon().getFileSize() >0 )
			dto.setMenIcon(frm.getMenIcon().getFileName());
		else
			dto.setMenIcon(null);
		dto.setMenOrden(frm.getMenOrden());
		dto.setMenPopup(frm.isMenPopup()?'S':'N');
		dto.setMenRestringido(frm.isMenRestringido()?'S':'N');
		if ( !frm.getMenPk().equals(frm.getMenFk()) ) dto.setMenFk(frm.getMenFk());
		else dto.setMenFk(null);
		dto.setGrupos(frm.getGrupos());
		
		return dto;
	}

	public UsuariosDTO frm2dto(UserForm frm) {
		UsuariosDTO dto = new UsuariosDTO(frm.getUser(), frm.getNombre(), frm.getPassword());
		if ( frm.getFechaBaja() == null )
			dto.setUsuFechaBaja(null);
		else
		{
			try {
				dto.setUsuFechaBaja(new SimpleDateFormat("dd/MM/yyyy").parse(frm.getFechaBaja()).getTime());
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		dto.setUsuApellido1(frm.getApellido1());
		dto.setUsuApellido2(frm.getApellido2());
		dto.setUsuMail(frm.getMail());
		dto.setUsuPk(frm.getUsuPk());
		dto.setUsuActivo(frm.isActivo());
		dto.setPerfil(frm.getPerfil());
		dto.setUsuPublicable(frm.isPublicable());
		dto.setUsuRecibeAviso(frm.isRecibeAbisos());
		
		return dto;
	}

	public GruposDTO frm2dto(GroupForm frm) {
		GruposDTO dto = new GruposDTO(frm.getGruUkCodigo(), frm.getGruDescripcion(), frm.getGruPrioridad());
		dto.setGruPk(frm.getGruPk());
		
		return dto;
	}

	public BugsDTO frm2dto(BugForm frm) {
		BugsDTO dto = new BugsDTO();
		dto.setBugPk(frm.getBugPk());
		dto.setBugDescripcion(frm.getBugDescripcion());

		if ( frm.getBugFechaReport() == null )
			dto.setBugFechaReport(null);
		else
		{
			try {
				dto.setBugFechaReport(new SimpleDateFormat("dd/MM/yyyy").parse(frm.getBugFechaReport()).getTime());
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		
		dto.setBugMessage(frm.getBugMessage());
		dto.setBugSite(frm.getBugSitePk());
		dto.setZonUkCodigo(frm.getBugSite());
		dto.setUsuFk(frm.getUsuFk());
		dto.setBugEstFk(frm.getBugEstado());
		dto.setBugPriFk(frm.getBugPrioridad());
		
		return dto;
	}
}
