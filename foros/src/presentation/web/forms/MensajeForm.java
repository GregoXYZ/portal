package presentation.web.forms;

import java.util.Date;

import common.presentation.web.security.forms.BaseForm;

public class MensajeForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1187778693970625076L;
	
	private long entPk;
	private long conPk;
	private long conFk;
	private String entrada;
	private String contenido;
	private String from;
	private Date fechaAlta;
	private Long[] to;
	private Boolean restringida;

	public void setEntPk(long entPk) {
		this.entPk = entPk;
	}

	public long getEntPk() {
		return entPk;
	}

	public void setConPk(long conPk) {
		this.conPk = conPk;
	}

	public long getConPk() {
		return conPk;
	}

	public void setConFk(long conFk) {
		this.conFk = conFk;
	}

	public long getConFk() {
		return conFk;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getContenido() {
		return contenido;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFrom() {
		return from;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setTo(Long[] to) {
		this.to = to;
	}

	public Long[] getTo() {
		return to;
	}

	public void setRestringida(Boolean restringida) {
		this.restringida = restringida;
	}

	public Boolean getRestringida() {
		return restringida;
	}

	@Override
	public void limpia() {
		entPk = 0;
		conPk = 0;
		conFk = 0;
		entrada = null;
		contenido = null;
		from = null;
		fechaAlta = null;
		restringida = null;
	}

}
