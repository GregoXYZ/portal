package common.presentation.web.tld.relaciones.beans;

public class UsuarioRelacionado implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6100862985387281401L;
	private String id;
	private Long usuPk;		// Usuario destino de la relación
	private String codigo;
	private String nombre;
	private String className;
	
	// Eventos
	private String solicitarRelacion;	
	private String cancelarSolicitud;	
	private String aceptarSolicitud;
	private String rechazarSolicitud;
	private String cancelarRelacion;
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassName() {
		return className;
	}
	public void setUsuPk(Long usuPk) {
		this.usuPk = usuPk;
	}
	public Long getUsuPk() {
		return usuPk;
	}	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setSolicitarRelacion(String solicitarRelacion) {
		this.solicitarRelacion = solicitarRelacion;
	}
	public String getSolicitarRelacion() {
		return solicitarRelacion;
	}
	public void setCancelarSolicitud(String cancelarSolicitud) {
		this.cancelarSolicitud = cancelarSolicitud;
	}
	public String getCancelarSolicitud() {
		return cancelarSolicitud;
	}
	public void setAceptarSolicitud(String aceptarSolicitud) {
		this.aceptarSolicitud = aceptarSolicitud;
	}
	public String getAceptarSolicitud() {
		return aceptarSolicitud;
	}
	public void setRechazarSolicitud(String rechazarSolicitud) {
		this.rechazarSolicitud = rechazarSolicitud;
	}
	public String getRechazarSolicitud() {
		return rechazarSolicitud;
	}
	public void setCancelarRelacion(String cancelarRelacion) {
		this.cancelarRelacion = cancelarRelacion;
	}
	public String getCancelarRelacion() {
		return cancelarRelacion;
	}
}
