package presentation.web.actions;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.util.Parametros;
import presentation.web.forms.ContenidosForm;

import common.IConstantes;
import common.business.BusinessException;
import common.business.bo.AssetsBO;
import common.business.bo.CarpetasBO;
import common.business.bo.FicherosBO;
import common.dto.AssetsDTO;
import common.dto.CarpetasDTO;
import common.dto.FicherosDTO;
import common.presentation.WebException;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class DeleteAssetAction extends SecurityAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(DeleteAssetAction.class);
	
	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");

		ContenidosForm lform = (ContenidosForm) form; 

    	AssetsBO boAsset = (AssetsBO) SpringUtil.getInstance().getBean("AssetsBO");
    	AssetsDTO dto =  boAsset.getByPrimaryKey(lform.getAssPk(), user.getPk());
    	// Sustituir por borrado en cascada
    	if ( dto == null || !borraAsset(dto, request) )
    	{
        	throw new WebException("Errores al borrar el asset " + lform.getAssPk() +" o alguno de sus hijos.", request);
    	}
    	
		return null;
	}

	private Boolean borraAsset(AssetsDTO asset, HttpServletRequest request) throws BusinessException
	{
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		
		if ( asset.getTipAssFk().compareTo(IConstantes.TIPO_ASSET_FICHERO) == 0 )
		{
	    	FicherosBO boFichero = (FicherosBO) SpringUtil.getInstance().getBean("FicherosBO");
	    	FicherosDTO fichero = boFichero.getByAsset(asset.getAssPk());
	    	
	    	String path = Parametros.get_instance().getValor(IConstantes.DEFAUL_USER_DIR) + user.getUser() + "/";
	    	File f = new File(path + fichero.getFicSysName());
	    	if ( !f.delete() )
	    	{
	    		logger.error("Error al borrar el archivo (" + asset.getAssNombre() + "):"+ path + fichero.getFicSysName());
	    	}
	    	AssetsBO boAsset = (AssetsBO) SpringUtil.getInstance().getBean("AssetsBO");
	    	boAsset.delete(asset);
			return true;
		}
		else if ( asset.getTipAssFk().compareTo(IConstantes.TIPO_ASSET_CARPETA) == 0 )
		{

	    	AssetsBO boAsset = (AssetsBO) SpringUtil.getInstance().getBean("AssetsBO");

	    	CarpetasBO boCarpeta = (CarpetasBO) SpringUtil.getInstance().getBean("CarpetasBO");
	    	CarpetasDTO carpeta = boCarpeta.getByAsset(asset.getAssPk());

	    	FicherosBO boFichero = (FicherosBO) SpringUtil.getInstance().getBean("FicherosBO");
	    	FicherosDTO[] listFicheros = boFichero.getFicheros(user.getPk(), carpeta.getCarPk());
	    	for ( int i=0; i < listFicheros.length; i++ )
	    	{
	    		AssetsDTO cAsset = boAsset.getByPrimaryKey( listFicheros[i].getAssPk(), user.getPk() );
	    		if ( !borraAsset(cAsset, request) )
	    		{
	    			return false;
	    		}
	    	}
	    	
	    	CarpetasDTO[] listCarpetas = boCarpeta.getCarpetas(user.getPk(), carpeta.getCarPk());
	    	for ( int i=0; i < listCarpetas.length; i++ )
	    	{
	    		AssetsDTO cAsset = boAsset.getByPrimaryKey(listCarpetas[i].getAssPk(), user.getPk()); 
	    		if ( !borraAsset(cAsset, request) )
	    		{
	    			return false;
	    		}
	    	}

	    	boAsset.delete(asset);
			return true;	    		
		}
		else if ( asset.getTipAssFk().compareTo(IConstantes.TIPO_ASSET_URL) == 0 )
		{
	    	AssetsBO boAsset = (AssetsBO) SpringUtil.getInstance().getBean("AssetsBO");
	    	asset = boAsset.getByPrimaryKey(asset.getAssPk(), user.getPk());
	    	
	    	if(asset!=null)
	    	{
	    		boAsset.delete(asset);
		    	return true;	    		
	    	}
	    	else
	    		return false;
		}
		else
		{
			return false;
		}
	}
}
