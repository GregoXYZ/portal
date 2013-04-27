package common.dto;

public class CompartidosDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2439605540401325212L;
	private Long comPk;
	private Long carFk;
	private Long assFk;
	private Long usuFk;
	private Long assFechaAlta;

	public void setComPk(Long comPk) {
		this.comPk = comPk;
	}

	public Long getComPk() {
		return comPk;
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

	public void setCarFk(Long carFk) {
		this.carFk = carFk;
	}

	public Long getCarFk() {
		return carFk;
	}

	public void setAssFechaAlta(Long assFechaAlta) {
		this.assFechaAlta = assFechaAlta;
	}

	public Long getAssFechaAlta() {
		return assFechaAlta;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CompartidosDTO))
			return false;
		CompartidosDTO castOther = (CompartidosDTO) other;

		return (this.getAssFk() == castOther.getAssFk())
				&& (this.getUsuFk() == castOther.getUsuFk());
	}
}
