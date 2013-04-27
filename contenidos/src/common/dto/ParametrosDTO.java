package common.dto;

public class ParametrosDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6403813227001969469L;
	private String parPk;
	private String parValor;
	private String parDescripcion;

	public String getParPk() {
		return this.parPk;
	}

	public void setParPk(String parPk) {
		this.parPk = parPk;
	}

	public String getParValor() {
		return this.parValor;
	}

	public void setParValor(String parValor) {
		this.parValor = parValor;
	}

	public String getParDescripcion() {
		return this.parDescripcion;
	}

	public void setParDescripcion(String parDescripcion) {
		this.parDescripcion = parDescripcion;
	}

}
