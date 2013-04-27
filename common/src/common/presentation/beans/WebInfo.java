package common.presentation.beans;

public final class WebInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2506591061734506629L;

	public static enum TypeInfo {
		MESSAGE,
		WARNING,
		ERROR,
		SYSTEM
	}
	
	private TypeInfo tipo;
	private StringBuffer value;

	public WebInfo() {
		super();
		tipo = TypeInfo.MESSAGE;
		value = new StringBuffer();
	}

	public WebInfo(StringBuffer value, TypeInfo tipo) {
		super();
		this.tipo = tipo;
		this.value = value;
		this.value.append("\n");
	}

	public WebInfo(String value, TypeInfo tipo) {
		super();
		this.tipo = tipo;
		this.value = new StringBuffer(value + "\n");
	}

	public TypeInfo getTipo() {
		return tipo;
	}
	
	public void setTipo(TypeInfo tipo) {
		this.tipo = tipo;
	}
	
	public StringBuffer getValue() {
		return value;
	}
	
	public void append(String data, TypeInfo tipo)
	{
		setTipo(tipo);
		value.append(data + "\n");
	}
	
	public void append(String data)
	{
		value.append(data + "\n");
	}
}
