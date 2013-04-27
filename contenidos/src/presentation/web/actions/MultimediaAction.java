package presentation.web.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.util.multimedia.Song;
import presentation.web.forms.CompartidosForm;
import presentation.web.forms.ContenidosForm;

import common.business.BusinessException;
import common.business.bo.ExtraQueriesBO;
import common.business.bo.FicherosBO;
import common.business.bo.ParametrosBO;
import common.dto.FicherosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class MultimediaAction extends SecurityAction {

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");
    	
    	if (form instanceof CompartidosForm)
    	{
    		CompartidosForm lform = (CompartidosForm) form;
    		if (user.getPk().equals(lform.getUsuPk()))
    		{
            	ExtraQueriesBO queriesBO = (ExtraQueriesBO) SpringUtil.getInstance().getBean("ExtraQueriesBO");
            	FicherosDTO[] ficheros = queriesBO.getMyFicherosCompartidos(user.getPk(), lform.getCarFk());
            	multimedia(request, ficheros);    		        		    			
    		}
    		else if (lform.getUsuPk() > 0 )
        	{
            	ExtraQueriesBO queriesBO = (ExtraQueriesBO) SpringUtil.getInstance().getBean("ExtraQueriesBO");
            	FicherosDTO[] ficheros = queriesBO.getFicherosCompartidos(user.getPk(), lform.getUsuPk(), lform.getCarFk());
            	multimedia(request, ficheros);    		        		
        	}
    	}
    	else if(form instanceof ContenidosForm)
    	{
        	ContenidosForm lform = (ContenidosForm) form;
    		FicherosBO ficherosBO = (FicherosBO) SpringUtil.getInstance().getBean("FicherosBO");
        	FicherosDTO[] ficheros = ficherosBO.getFicheros(user.getPk(), lform.getCarPk());
        	multimedia(request, ficheros);    		
    	}
		return mapping.findForward("success");
	}

	private void multimedia(HttpServletRequest request, FicherosDTO[] ficheros) throws BusinessException {
    	List<Song> listaMultimedia = new ArrayList<Song>();
    	for (int ind = 0; ind < ficheros.length; ind++)
    	{
    		Song multimedia = new Song();
    		
        	ParametrosBO parametrosBO = (ParametrosBO) SpringUtil.getInstance().getBean("ParametrosBO");
    		if ( parametrosBO.getValor("SOUND_FORMATS").contains(ficheros[ind].getMimFilExtension().toLowerCase()) )
    		{
    			multimedia.setAsset(ficheros[ind].getAssPk());
    			multimedia.setLink("play/sound." + ficheros[ind].getMimFilExtension() + "?asset=" + ficheros[ind].getAssPk());
        		/*String nombre = ficheros[ind].getAssNombre();
        		if (nombre.length() > 15)
        		{
        			nombre = nombre.substring(0, 12) + "...";
        		}*/
        		multimedia.setName(ficheros[ind].getAssNombre());

        		listaMultimedia.add(multimedia);
    		}
    	}
    	
    	request.removeAttribute("songs");
    	request.setAttribute("songs", listaMultimedia);
	}	

}
