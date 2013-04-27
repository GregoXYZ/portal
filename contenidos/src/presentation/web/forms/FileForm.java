package presentation.web.forms;

import org.apache.struts.upload.FormFile;

import common.presentation.web.security.forms.BaseForm;

public class FileForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 434512803428683346L;
	private Long assPk;
	private Long carPk;
	private Long ficPk;
	private String nombre;
	private String referencia;
	private String descripcion;
	private FormFile file;
	private Long ficSize;
	private Long mimFilFk;
	private Long[] users;
	private boolean descomprimir;
	private String tags;

	public void setAssPk(Long assPk) {
		this.assPk = assPk;
	}
	public Long getAssPk() {
		return assPk;
	}
	public void setFicPk(Long ficPk) {
		this.ficPk = ficPk;
	}
	public Long getFicPk() {
		return ficPk;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public FormFile getFile() {
		return file;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getReferencia() {
		return referencia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setCarPk(Long carPk) {
		this.carPk = carPk;
	}
	public Long getCarPk() {
		return carPk;
	}
	public Long getFicSize() {
		return ficSize;
	}
	public void setFicSize(Long ficSize) {
		this.ficSize = ficSize;
	}
	public Long getMimFilFk() {
		return mimFilFk;
	}
	public void setMimFilFk(Long mimFilFk) {
		this.mimFilFk = mimFilFk;
	}
	public void setUsers(Long[] users) {
		this.users = users;
	}
	public Long[] getUsers() {
		return users;
	}
	public void setDescomprimir(boolean descomprimir) {
		this.descomprimir = descomprimir;
	}
	public boolean isDescomprimir() {
		return descomprimir;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getTags() {
		return tags;
	}
	@Override
	public void limpia() {
		ficPk = null;
		file = null;
		nombre = null;
		descripcion = null;
		carPk = null;
		ficSize = null;
		mimFilFk = null;
		referencia = null;
		assPk = null;
		users = null;
		descomprimir = false;
		tags = null;
	}
	
}
