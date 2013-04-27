package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.util.Convert;
import presentation.web.forms.UrlForm;

import common.business.bo.GruposBO;
import common.business.bo.UrlsBO;
import common.business.bo.UrlsGruposBO;
import common.business.bo.ZonasBO;
import common.dto.GruposDTO;
import common.dto.UrlsDTO;
import common.dto.ZonasDTO;
import common.presentation.util.WebInfoHelper;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class EditUrlAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	UrlForm rform = (UrlForm)form;
    	
    	if ( rform.getUrlPk() == null || rform.getUrlPk() == 0 )
    	{
           	rform.limpia();
    	} else
    	{
        	UrlsBO bo = (UrlsBO) SpringUtil.getInstance().getBean("UrlsBO");
        	UrlsDTO dto = bo.getByPrimaryKey(rform.getUrlPk());
        	Convert.getInstance().setForm(rform);
        	Convert.getInstance().dto2form(dto);        	

        	UrlsGruposBO boGrupos = (UrlsGruposBO) SpringUtil.getInstance().getBean("UrlsGruposBO");
        	rform.setGrupos(boGrupos.getByUrl(rform.getUrlPk()));
    	}
    	
    	GruposBO boGrupos = (GruposBO) SpringUtil.getInstance().getBean("GruposBO");
    	GruposDTO[] grupos = boGrupos.getGrupos();        	
    	request.removeAttribute("listGrupos");
    	request.setAttribute("listGrupos", grupos);    	
    	
    	ZonasBO boZonas = (ZonasBO) SpringUtil.getInstance().getBean("ZonasBO");
    	ZonasDTO[] zonas = boZonas.getZonas();        	
    	request.removeAttribute("listZonas");
    	request.setAttribute("listZonas", zonas);    	
    	
    	WebInfoHelper.getInstance().setWebInfo(request, "Los campos marcados con (*) son obligatorios");
    	
    	return mapping.findForward("success");    		
    }
}
