package common.dto;

public class AccesosDirectosDTO implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7394666596297288050L;
	private Long accDirPk;
	private long assFk;
	private long carFk;

	public void setAccDirPk(Long accDirPk) {
		this.accDirPk = accDirPk;
	}

	public Long getAccDirPk() {
		return accDirPk;
	}

	public void setAssFk(long assFk) {
		this.assFk = assFk;
	}

	public long getAssFk() {
		return assFk;
	}

	public void setCarFk(long carFk) {
		this.carFk = carFk;
	}

	public long getCarFk() {
		return carFk;
	}
}
