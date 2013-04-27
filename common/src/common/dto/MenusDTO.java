package common.dto;

import common.presentation.beans.HtmlEvents;

public class MenusDTO extends HtmlEvents implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 801201369004644409L;
	private Long menPk;
	private String menCodigo;
	private String menDescripcion;
	private String menTitulo;
	private Long menFk;
	private Long urlFk;
	private int menOrden;
	private String menIcon;
	private char menPopup;
	private char menRestringido;
	private Long[] grupos;

	public MenusDTO() {
	}

	public MenusDTO(String menCodigo, String menDescripcion, String menTitulo) {
		this.menCodigo = menCodigo;
		this.menDescripcion = menDescripcion;
		this.menTitulo = menTitulo;
	}

	public MenusDTO(String menCodigo, String menDescripcion, String menTitulo, Long urlFk) {
		this.menCodigo = menCodigo;
		this.menDescripcion = menDescripcion;
		this.menTitulo = menTitulo;
		this.urlFk = urlFk;
	}

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

	public void setMenIcon(String menIcon) {
		this.menIcon = menIcon;
	}

	public String getMenIcon() {
		return menIcon;
	}

	public void setMenPopup(char menPopup) {
		this.menPopup = menPopup;
	}

	public char getMenPopup() {
		return menPopup;
	}

	public void setMenRestringido(char menRestringido) {
		this.menRestringido = menRestringido;
	}

	public char getMenRestringido() {
		return menRestringido;
	}

	public void setGrupos(Long[] grupos) {
		this.grupos = grupos;
	}

	public Long[] getGrupos() {
		return grupos;
	}
}
