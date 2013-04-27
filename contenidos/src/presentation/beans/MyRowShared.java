package presentation.beans;

import common.business.BusinessException;
import common.business.bo.CompartidosBO;
import common.business.bo.UsuariosBO;
import common.dto.CompartidosDTO;
import common.dto.FicherosDTO;
import common.dto.UsuariosDTO;
import common.util.spring.SpringUtil;

public class MyRowShared extends RowFile implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7156121409725197792L;
	private String onUpdate;
	
	public MyRowShared(FicherosDTO file) {
		super(file);
	}

	public MyRowShared(Long assPk, String assNombre, String assDescripcion,
			Long ficSize, String mimFilExtension, String icon) {
		super(assPk, assNombre, assDescripcion, ficSize, mimFilExtension, icon);
	}

	public String getUsersComparten() throws BusinessException {
		StringBuffer html = new StringBuffer();
		if (this.getAssPk()>0)
		{
			html.append("<div id='ru");
			html.append(this.getAssPk());
			html.append("'>\n");
			html.append("<ul>\n");
	    	CompartidosBO compartirBO = (CompartidosBO) SpringUtil.getInstance().getBean("CompartidosBO");
	    	CompartidosDTO[] compartidos = compartirBO.getByAsset(this.getAssPk());
	    	UsuariosBO userBO = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
	    	for (int i=0; i < compartidos.length; i++ )
	    	{
				html.append("<li>\n");
	    		UsuariosDTO usuarios = userBO.getByPrimaryKey(compartidos[i].getUsuFk());
	    		html.append("(");
	    		html.append(usuarios.getUsuUkUsuario());
	    		html.append(") ");
	    		html.append(usuarios.getUsuNombre());
	    		html.append(" "); 
	    		html.append(usuarios.getUsuApellido1());
	    		html.append(" ");
	    		html.append(usuarios.getUsuApellido2()); 
	    		html.append(" <a href='javascript:void(0);' onclick='delMyShared(");
	    		html.append(usuarios.getUsuPk());
	    		html.append(", ");
	    		html.append(this.getAssPk());
	    		html.append(")'>");
	    		html.append("<img width='12' height='12' src='/portal/images/cancel.png'/>");
	    		html.append("</a>");
				html.append("</li>\n");
	    	}
			html.append("</ul>\n");
			html.append("</div>\n");
		}
		return html.toString();
	}

	public void setOnUpdate(String onUpdate) {
		this.onUpdate = onUpdate;
	}

	public String getOnUpdate() {
		return onUpdate;
	}
}
