package common.business.beans;

// Generated 28-feb-2009 20:08:00 by Hibernate Tools 3.2.4.CR1

/**
 * Mensajes generated by hbm2java
 */
public class Avisos implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3020636351808000369L;
	private Long aviPk;
	private Long aviFLeido;
	private Long usuFkOrigen;
	private Long usuFkDestino;
	private Long aviFEnvio;
	private Integer tipAviFk;
	private Long aviReferencia;
	private Integer aviCantidad;

	public Avisos() {
	}

	public Avisos(Long usuFkDestino, Long aviFEnvio, Integer tipAviFk, 
			Long aviReferencia, Integer aviCantidad) {
		this.setUsuFkDestino(usuFkDestino);
		this.setAviFEnvio(aviFEnvio);
		this.setTipAviFk(tipAviFk);
		this.setAviReferencia(aviReferencia);
		this.setAviCantidad(aviCantidad);
	}

	public Avisos(Long usuFkOrigen,	Long usuFkDestino, 
			Long aviFEnvio, Integer tipAviFk, 
			Long aviReferencia, Integer aviCantidad) {
		this.setUsuFkOrigen(usuFkOrigen);
		this.setUsuFkDestino(usuFkDestino);
		this.setAviFEnvio(aviFEnvio);
		this.setTipAviFk(tipAviFk);
		this.setAviReferencia(aviReferencia);
		this.setAviCantidad(aviCantidad);
	}

	public void setAviPk(Long aviPk) {
		this.aviPk = aviPk;
	}

	public Long getAviPk() {
		return aviPk;
	}

	public void setAviFLeido(Long aviFLeido) {
		this.aviFLeido = aviFLeido;
	}

	public Long getAviFLeido() {
		return aviFLeido;
	}

	public void setUsuFkOrigen(Long usuFkOrigen) {
		this.usuFkOrigen = usuFkOrigen;
	}

	public Long getUsuFkOrigen() {
		return usuFkOrigen;
	}

	public void setUsuFkDestino(Long usuFkDestino) {
		this.usuFkDestino = usuFkDestino;
	}

	public Long getUsuFkDestino() {
		return usuFkDestino;
	}

	public void setAviFEnvio(Long aviFEnvio) {
		this.aviFEnvio = aviFEnvio;
	}

	public Long getAviFEnvio() {
		return aviFEnvio;
	}

	public void setTipAviFk(Integer tipAviFk) {
		this.tipAviFk = tipAviFk;
	}

	public Integer getTipAviFk() {
		return tipAviFk;
	}

	public void setAviReferencia(Long aviReferencia) {
		this.aviReferencia = aviReferencia;
	}

	public Long getAviReferencia() {
		return aviReferencia;
	}

	public void setAviCantidad(Integer aviCantidad) {
		this.aviCantidad = aviCantidad;
	}

	public Integer getAviCantidad() {
		return aviCantidad;
	}

}
