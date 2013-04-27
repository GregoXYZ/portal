package business.beans;

// Generated 28-feb-2009 13:22:52 by Hibernate Tools 3.2.4.CR1

/**
 * Compartidos generated by hbm2java
 */
public class Compartidos implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6567735582471683905L;
	private Long comPk;
	private Long assFk;
	private Long usuFk_source;
	private Long usuFk;
	private Long comFechaAlta;

	public Compartidos() {
	}

	public Compartidos(Long assFk, Long usuFk) {
		this.assFk = assFk;
		this.usuFk = usuFk;
	}

	public Long getComPk() {
		return this.comPk;
	}

	public void setComPk(Long comPk) {
		this.comPk = comPk;
	}

	public Long getAssFk() {
		return this.assFk;
	}

	public void setAssFk(Long assFk) {
		this.assFk = assFk;
	}

	public Long getUsuFk() {
		return this.usuFk;
	}

	public void setUsuFk(Long usuFk) {
		this.usuFk = usuFk;
	}

	public void setUsuFk_source(Long usuFk_source) {
		this.usuFk_source = usuFk_source;
	}

	public Long getUsuFk_source() {
		return usuFk_source;
	}

	public void setComFechaAlta(Long comFechaAlta) {
		this.comFechaAlta = comFechaAlta;
	}

	public Long getComFechaAlta() {
		return comFechaAlta;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Compartidos))
			return false;
		Compartidos castOther = (Compartidos) other;

		return (this.getAssFk().equals(castOther.getAssFk()))
				&& (this.getUsuFk().equals(castOther.getUsuFk()));
	}
}
