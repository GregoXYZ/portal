package presentation.web.forms;

import org.apache.struts.upload.FormFile;

import common.presentation.web.security.forms.BaseForm;

public class MenuForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4000045538254664085L;
	private Long menPk;
	private String menCodigo;
	private String menDescripcion;
	private String menTitulo;
	private Long menFk;
	private Long urlFk;
	private int menOrden;
	private FormFile menIcon;
	private boolean menPopup;
	private boolean menRestringido;
	private Long[] grupos;

	public Long getMenPk() {
		return this.menPk;
	}

	public void setMenPk(Long menPk) {
		this.menPk = menPk;
	}

	public String getMenCodigo() {
		return this.menCodigo;
	}

	public void setMenCodigo(String menCodigo) {
		this.menCodigo = menCodigo;
	}

	public String getMenDescripcion() {
		return this.menDescripcion;
	}

	public void setMenDescripcion(String menDescripcion) {
		this.menDescripcion = menDescripcion;
	}

	public String getMenTitulo() {
		return this.menTitulo;
	}

	public void setMenTitulo(String menTitulo) {
		this.menTitulo = menTitulo;
	}

	public void setUrlFk(Long urlFk) {
		this.urlFk = urlFk;
	}

	public Long getUrlFk() {
		return urlFk;
	}

	public void setMenFk(Long menFk) {
		this.menFk = menFk;
	}

	public Long getMenFk() {
		return menFk;
	}

	public void setMenOrden(int menOrden) {
		this.menOrden = menOrden;
	}

	public int getMenOrden() {
		return menOrden;
	}

	public void setMenIcon(FormFile menIcon) {
		this.menIcon = menIcon;
	}

	public FormFile getMenIcon() {
		return menIcon;
	}

	public void setGrupos(Long[] grupos) {
		this.grupos = grupos;
	}

	public Long[] getGrupos() {
		return grupos;
	}

	public void setMenPopup(boolean menPopup) {
		this.menPopup = menPopup;
	}

	public boolean isMenPopup() {
		return menPopup;
	}

	public void setMenRestringido(boolean menRestringido) {
		this.menRestringido = menRestringido;
	}

	public boolean isMenRestringido() {
		return menRestringido;
	}

	@Override
	public void limpia() {
		menPk = null;
		menCodigo = null;
		menDescripcion = null;
		menTitulo = null;
		menFk = null;
		menOrden = 0;
		menIcon = null;
		urlFk = null;
		menPopup = false;
	}
}
