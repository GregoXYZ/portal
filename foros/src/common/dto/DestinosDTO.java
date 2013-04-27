package common.dto;

public class DestinosDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6303734703501420271L;
	private Long entFk;
	private Long usuFk;

	public Long getEntFk() {
		return this.entFk;
	}

	public void setEntFk(Long entFk) {
		this.entFk = entFk;
	}

	public Long getUsuFk() {
		return this.usuFk;
	}

	public void setUsuFk(Long usuFk) {
		this.usuFk = usuFk;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DestinosDTO))
			return false;
		DestinosDTO castOther = (DestinosDTO) other;

		return (this.getEntFk() == castOther.getEntFk())
				&& (this.getUsuFk() == castOther.getUsuFk());
	}

	public int hashCode() {
		int result = 17;

		result = (int) (37 * result + this.getEntFk());
		result = (int) (37 * result + this.getUsuFk());
		return result;
	}

}
