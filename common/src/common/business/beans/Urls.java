package common.business.beans;

// Generated 18-dic-2008 19:43:53 by Hibernate Tools 3.2.4.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Urls generated by hbm2java
 */
public class Urls implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1486303192851393945L;
	private Long urlPk;
	private Zonas zonas;
	private String urlUkCodigo;
	private String urlDescripcion;
	private String urlDireccion;
	private Set<UrlsGrupos> urlsGruposes = new HashSet<UrlsGrupos>(0);
	private Set<Menus> menuses = new HashSet<Menus>(0);

	public Urls() {
	}

	public Urls(Zonas zonas, String urlUkCodigo, String urlDescripcion,
			String urlDireccion) {
		this.zonas = zonas;
		this.urlUkCodigo = urlUkCodigo;
		this.urlDescripcion = urlDescripcion;
		this.urlDireccion = urlDireccion;
	}

	public Urls(Zonas zonas, String urlUkCodigo, String urlDescripcion,
			String urlDireccion, Set<UrlsGrupos> urlsGruposes, Set<Menus> menuses) {
		this.zonas = zonas;
		this.urlUkCodigo = urlUkCodigo;
		this.urlDescripcion = urlDescripcion;
		this.urlDireccion = urlDireccion;
		this.urlsGruposes = urlsGruposes;
		this.menuses = menuses;
	}

	public Long getUrlPk() {
		return this.urlPk;
	}

	public void setUrlPk(Long urlPk) {
		this.urlPk = urlPk;
	}

	public Zonas getZonas() {
		return this.zonas;
	}

	public void setZonas(Zonas zonas) {
		this.zonas = zonas;
	}

	public String getUrlUkCodigo() {
		return this.urlUkCodigo;
	}

	public void setUrlUkCodigo(String urlUkCodigo) {
		this.urlUkCodigo = urlUkCodigo;
	}

	public String getUrlDescripcion() {
		return this.urlDescripcion;
	}

	public void setUrlDescripcion(String urlDescripcion) {
		this.urlDescripcion = urlDescripcion;
	}

	public String getUrlDireccion() {
		return this.urlDireccion;
	}

	public void setUrlDireccion(String urlDireccion) {
		this.urlDireccion = urlDireccion;
	}

	public Set<UrlsGrupos> getUrlsGruposes() {
		return this.urlsGruposes;
	}

	public void setUrlsGruposes(Set<UrlsGrupos> urlsGruposes) {
		this.urlsGruposes = urlsGruposes;
	}

	public Set<Menus> getMenuses() {
		return this.menuses;
	}

	public void setMenuses(Set<Menus> menuses) {
		this.menuses = menuses;
	}

}
