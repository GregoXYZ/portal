package presentation.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.web.forms.CompartidosForm;

import common.business.bo.AssetsBO;
import common.business.bo.CompartidosBO;
import common.dto.AssetsDTO;
import common.dto.CompartidosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.util.WebInfoHelper;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class DeleteSharedAction extends SecurityAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(DeleteSharedAction.class);

	@Override
	public ActionForward _execute(SecurityActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	HttpSession session = request.getSession();
    	UserInfo user = (UserInfo) session.getAttribute("user");
    	
    	CompartidosForm lform = (CompartidosForm) form;

    	AssetsBO assetBO = (AssetsBO)SpringUtil.getInstance().getBean("AssetsBO");
		AssetsDTO assetDTO = assetBO.getByPrimaryKey(lform.getAssPk(), user.getPk());
    	
    	CompartidosBO compartidoBO =(CompartidosBO) SpringUtil.getInstance().getBean("CompartidosBO");
    	if (assetDTO == null)
    	{
    		CompartidosDTO dto = compartidoBO.getByUniqueKey(lform.getAssPk(), user.getPk());    		
        	compartidoBO.delete(dto);
        	logger.info("Compartición anulada USER:" + dto.getUsuFk() + " ASSET:" + dto.getAssFk());
    	}
    	else
    	{
    		if (lform.getUsuPk()>0)
    		{
    			CompartidosDTO dto = compartidoBO.getByUniqueKey(assetDTO.getAssPk(), lform.getUsuPk());
            	compartidoBO.delete(dto);    			
            	logger.info("Compartición anulada USER:" + dto.getUsuFk() + " ASSET:" + dto.getAssFk());
    		}
    		else
    		{
    			CompartidosDTO[] dto = compartidoBO.getByAsset(assetDTO.getAssPk());
    			for(int ind=0; ind<dto.length; ind++)
    			{
    	        	compartidoBO.delete(dto[ind]);
    	        	logger.info("Compartición anulada USER:" + dto[ind].getUsuFk() + " ASSET:" + dto[ind].getAssFk());    				
    			}
    		}
    		
    	}
    	WebInfoHelper.getInstance().setWebInfo(request, "Compartición anulada.");    	
		return null;
	}

}
