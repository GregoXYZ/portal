package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.ContenidosForm;

import common.IConstantes;
import common.business.BusinessException;
import common.business.bo.AssetsBO;
import common.business.bo.CarpetasBO;
import common.business.bo.FicherosBO;
import common.dto.AssetsDTO;
import common.dto.CarpetasDTO;
import common.dto.FicherosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;


public class MoveOperAction extends SecurityAction {

	CarpetasBO carpetasBO = (CarpetasBO) SpringUtil.getInstance().getBean("CarpetasBO");

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");
    	
    	ContenidosForm sform = (ContenidosForm) session.getAttribute("ContenidosForm");
    	ContenidosForm lform = (ContenidosForm) form;
    	
    	AssetsBO assetsBO = (AssetsBO) SpringUtil.getInstance().getBean("AssetsBO");
    	
		AssetsDTO asset = assetsBO.getByPrimaryKey(lform.getAssPk(), user.getPk());
		if (asset.getTipAssFk().compareTo(IConstantes.TIPO_ASSET_FICHERO) == 0)
		{
	    	FicherosBO ficherosBO = (FicherosBO) SpringUtil.getInstance().getBean("FicherosBO");
			FicherosDTO fichero = ficherosBO.getByAsset(asset.getAssPk());
			
			ficherosBO.move(fichero.getFicPk(), sform.getCarPk());
			
	    	return mapping.findForward("successfile");
		}
		else if (asset.getTipAssFk().compareTo(IConstantes.TIPO_ASSET_CARPETA) == 0)
		{
	    	CarpetasDTO carpeta = carpetasBO.getByAsset(asset.getAssPk());
			if (existeRecursibidad(carpeta.getCarPk(), sform.getCarPk()))
				return null;
				//throw new WebException("Implosible mover la carpeta, ya que dependería de si misma.", request);
			else
				carpetasBO.move(carpeta.getCarPk(), sform.getCarPk());
	    	return mapping.findForward("successfolder");
		}

		return null;
	}
	
	private boolean existeRecursibidad(long carPk, long carFk) throws BusinessException
	{
		if (carFk == 0)
		{
			return false;
		}
		else if (carFk == carPk) 
		{
			return true;
		}
		else
		{
			CarpetasDTO carpeta = carpetasBO.getByPrimaryKey(carFk);
			return existeRecursibidad(carPk, carpeta.getCarFk()==null?0:carpeta.getCarFk());
		}
	}

}
