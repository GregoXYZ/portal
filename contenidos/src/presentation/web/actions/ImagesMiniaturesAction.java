package presentation.web.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.CompartidosForm;
import presentation.web.forms.ContenidosForm;
import presentation.web.tld.Miniaturas.Beans.Miniatura;

import common.business.BusinessException;
import common.business.bo.ExtraQueriesBO;
import common.business.bo.FicherosBO;
import common.business.bo.ParametrosBO;
import common.dto.FicherosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class ImagesMiniaturesAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");
    	
    	if (form instanceof CompartidosForm)
    	{
    		CompartidosForm lform = (CompartidosForm) form;
        	String tipo =lform.getTag()==null?"":lform.getTag().toString();
    		request.removeAttribute("myimages");
    		request.setAttribute("sharedimages", "S");
    		if (user.getPk().equals(lform.getUsuPk()))
    		{
            	ExtraQueriesBO queriesBO = (ExtraQueriesBO) SpringUtil.getInstance().getBean("ExtraQueriesBO");
            	FicherosDTO[] ficheros;
            	ficheros = queriesBO.getMyFicherosCompartidos(user.getPk(), lform.getCarFk());
        		imagesMiniatures(request, ficheros, tipo);    		        		    			
    		}
    		else if (lform.getUsuPk() > 0 )
        	{
            	ExtraQueriesBO queriesBO = (ExtraQueriesBO) SpringUtil.getInstance().getBean("ExtraQueriesBO");
            	FicherosDTO[] ficheros;
            	ficheros = queriesBO.getFicherosCompartidos(user.getPk(), lform.getUsuPk(), lform.getCarFk());
        		imagesMiniatures(request, ficheros, tipo);    		        		
        	}
    	}
    	else if(form instanceof ContenidosForm)
    	{
        	ContenidosForm lform = (ContenidosForm) form;
        	String tipo = lform.getTag()==null?"":lform.getTag().toString();
    		request.removeAttribute("sharedimages");
    		request.setAttribute("myimages", "S");
    		FicherosBO ficherosBO = (FicherosBO) SpringUtil.getInstance().getBean("FicherosBO");
        	FicherosDTO[] ficheros = ficherosBO.getFicheros(user.getPk(), lform.getCarPk());
    		imagesMiniatures(request, ficheros, tipo);    		
    	}
		return mapping.findForward("success");
	}

	private void imagesMiniatures(HttpServletRequest request, FicherosDTO[] ficheros, String tipo) throws BusinessException {
    	List<Miniatura> listaMiniaturas = new ArrayList<Miniatura>();
    	for (int ind = 0; ind < ficheros.length; ind++)
    	{
    		Miniatura miniatura = new Miniatura();
    		
        	ParametrosBO parametrosBO = (ParametrosBO) SpringUtil.getInstance().getBean("ParametrosBO");
    		if (parametrosBO.getValor("IMAGE_FORMATS").contains(ficheros[ind].getMimFilExtension().toLowerCase()) )
    		{
    			miniatura.setImage("resizer?asset=" + ficheros[ind].getAssPk() + "&width=150&height=150");
        		miniatura.setStyle("miniatura");
    			miniatura.setImageId(ficheros[ind].getAssPk().toString());
        		miniatura.setFormname("ContenidosForm");
        		miniatura.setParam("assPk");
        		miniatura.setConfirm("False");
        		miniatura.setOnClick("sendData(\"download\",\"" + ficheros[ind].getAssPk() + "\",\"assPk\",\"none\",\"\")");
        		if (tipo!=null && tipo.equals("carrousel"))
        		{
            		miniatura.setOnMouseOver("cargaCarrouselImage("+ ficheros[ind].getAssPk().toString() + ")");        			
        		}
        		String nombre = ficheros[ind].getAssNombre();
        		if (nombre.length() > 15)
        		{
        			nombre = nombre.substring(0, 12) + "...";
        		}
        		miniatura.setText(nombre);

        		listaMiniaturas.add(miniatura);
    		}
    	}
    	
    	request.removeAttribute("listMiniaturas");
    	request.setAttribute("listMiniaturas", listaMiniaturas);
	}	

}
