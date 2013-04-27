package common.dto;

public class MensajesDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1351691739523308260L;
	private EntradasDTO entrada;
	private ContenidosDTO contenido;
	
	
	public MensajesDTO() {
		super();
		entrada = new EntradasDTO();
		contenido = new ContenidosDTO();
	}
	public void setEntrada(EntradasDTO entrada) {
		this.entrada = entrada;
	}
	public EntradasDTO getEntrada() {
		return entrada;
	}
	public void setContenido(ContenidosDTO contenido) {
		this.contenido = contenido;
	}
	public ContenidosDTO getContenido() {
		return contenido;
	}
}
