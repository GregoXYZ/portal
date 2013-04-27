package presentation.util;

import presentation.web.forms.MimeFileForm;

import common.dto.MimeFilesDTO;
import common.presentation.web.security.forms.BaseForm;

public class Convert {
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
			_instance = new Convert();
		}*/
		return _instance;
	}

	public MimeFileForm dto2form(MimeFilesDTO dto)
	{
		MimeFileForm frm;
		if ( form == null) frm = new MimeFileForm();
		else frm = (MimeFileForm) form;
		frm.setMimFilPk(dto.getMimFilPk());
		frm.setMimFilExtension(dto.getMimFilExtension());
		frm.setMimFilMime(dto.getMimFilMime());
		frm.setMimFilIcon(dto.getMimFilIcon());
		
		return frm;
	}
	
	public MimeFilesDTO frm2dto(MimeFileForm frm)
	{
		MimeFilesDTO dto = new MimeFilesDTO(frm.getMimFilExtension(), frm.getMimFilMime());
		dto.setMimFilPk(frm.getMimFilPk());
		dto.setMimFilIcon(frm.getIconFile().getFileName());
		
		return dto;
	}

}
