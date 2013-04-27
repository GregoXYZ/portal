package common.dto;

public class CarpetasDTO extends AssetsDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2222560422341195125L;
	private Long carPk;
	private Long carFk;
	private String icon;

	public Long getCarPk() {
		return this.carPk;
	}

	public void setCarPk(Long carPk) {
		this.carPk = carPk;
	}

	public void setCarFk(Long carFk) {
		this.carFk = carFk;
	}

	public Long getCarFk() {
		return carFk;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}

}
