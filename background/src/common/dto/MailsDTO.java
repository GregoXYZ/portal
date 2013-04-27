package common.dto;

public class MailsDTO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3668859633589128890L;
	
	private Long usuFkOrigen;
	private String usuFrom;
	private Long usuFkDestino;
	private String usuTo;
	private Integer tipAviFk;
	private Long cantidad;
	
	public void setUsuFkOrigen(Long usuFkOrigen) {
		this.usuFkOrigen = usuFkOrigen;
	}
	
	public Long getUsuFkOrigen() {
		return usuFkOrigen;
	}

	public void setUsuFrom(String usuFrom) {
		this.usuFrom = usuFrom;
	}

	public String getUsuFrom() {
		return usuFrom;
	}

	public void setUsuFkDestino(Long usuFkDestino) {
		this.usuFkDestino = usuFkDestino;
	}

	public Long getUsuFkDestino() {
		return usuFkDestino;
	}

	public void setUsuTo(String usuTo) {
		this.usuTo = usuTo;
	}

	public String getUsuTo() {
		return usuTo;
	}

	public void setTipAviFk(Integer tipAviFk) {
		this.tipAviFk = tipAviFk;
	}

	public Integer getTipAviFk() {
		return tipAviFk;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Long getCantidad() {
		return cantidad;
	}
}
