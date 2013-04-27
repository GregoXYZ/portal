package common.dto;

import common.business.BusinessException;
import common.business.bo.ParametrosBO;
import common.util.spring.SpringUtil;

public class FicherosDTO extends AssetsDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4349583121676320299L;
	private String ficSysName;
	private Long ficPk;
	private Long carFk;
	private Long ficSize;
	private Long mimFilFk;
	private String mimFilExtension;
	private String mimFilMime;
	private String icon;
	private String ficChecksum;

	public void setFicSysName(String ficSysName) {
		this.ficSysName = ficSysName;
	}

	public String getFicSysName() {
		return ficSysName;
	}

	public Long getFicPk() {
		return this.ficPk;
	}

	public void setFicPk(Long ficPk) {
		this.ficPk = ficPk;
	}
	public void setCarFk(Long carFk) {
		this.carFk = carFk;
	}

	public Long getCarFk() {
		return carFk;
	}

	public void setFicSize(Long ficSize) {
		this.ficSize = ficSize;
	}

	public Long getFicSize() {
		return ficSize;
	}

	public void setMimFilFk(Long mimFilFk) {
		this.mimFilFk = mimFilFk;
	}

	public Long getMimFilFk() {
		return mimFilFk;
	}
	public void setMimFilExtension(String mimFilExtension) {
		this.mimFilExtension = mimFilExtension;
	}
	public String getMimFilExtension() {
		return mimFilExtension;
	}
	public void setMimFilMime(String mimFilMime) {
		this.mimFilMime = mimFilMime;
	}
	public String getMimFilMime() {
		return mimFilMime;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIcon() {
		return icon;
	}
	public void setFicChecksum(String ficChecksum) {
		this.ficChecksum = ficChecksum;
	}

	public String getFicChecksum() {
		return ficChecksum;
	}

	public Boolean Preview() throws BusinessException
	{		
    	ParametrosBO parametrosBO = (ParametrosBO) SpringUtil.getInstance().getBean("ParametrosBO");
    	
    	if (mimFilExtension !=null && (
    			parametrosBO.getValor("IMAGE_FORMATS").contains(mimFilExtension.toLowerCase()) || 
    			parametrosBO.getValor("TEXT_FORMATS").contains(mimFilExtension.toLowerCase()) ||
    			parametrosBO.getValor("VIDEO_FORMATS").contains(mimFilExtension.toLowerCase()))) 
			return true;
		else
			return false;
		
	}
}
