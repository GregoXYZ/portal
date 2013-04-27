package common.dto;

public class BusquedasPersonalesDTO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5685244136734478452L;
	private Long busPerPk;
	private long usuFk;
	private long tagFk;
	private long busPerFechaAlta;

	public BusquedasPersonalesDTO() {
	}

	public BusquedasPersonalesDTO(long usuFk, long tagFk, long busPerFechaAlta) {
		this.usuFk = usuFk;
		this.tagFk = tagFk;
		this.setBusPerFechaAlta(busPerFechaAlta);
	}

	public Long getBusPerPk() {
		return this.busPerPk;
	}

	public void setBusPerPk(Long busPerPk) {
		this.busPerPk = busPerPk;
	}

	public long getUsuFk() {
		return this.usuFk;
	}

	public void setUsuFk(long usuFk) {
		this.usuFk = usuFk;
	}

	public long getTagFk() {
		return this.tagFk;
	}

	public void setTagFk(long tagFk) {
		this.tagFk = tagFk;
	}

	public void setBusPerFechaAlta(long busPerFechaAlta) {
		this.busPerFechaAlta = busPerFechaAlta;
	}

	public long getBusPerFechaAlta() {
		return busPerFechaAlta;
	}

}
