package business.beans;

/**
 * AccesosDirectos
 */
public class AccesosDirectos implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8369754503157264402L;
	private Long accDirPk;
	private long assFk;
	private long carFk;

	public AccesosDirectos() {
	}

	public AccesosDirectos(long assFk, long carFk) {
		this.setAssFk(assFk);
		this.setCarFk(carFk);
	}

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
