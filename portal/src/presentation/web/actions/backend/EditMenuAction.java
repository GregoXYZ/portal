package presentation.web.actions.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import presentation.util.Convert;
import presentation.web.forms.MenuForm;

import common.business.bo.GruposBO;
import common.business.bo.GruposMenusBO;
import common.business.bo.MenusBO;
import common.business.bo.UrlsBO;
import common.dto.GruposDTO;
import common.dto.MenusDTO;
import common.dto.UrlsDTO;
import common.presentation.util.WebInfoHelper;
import common.presentation.web.security.actions.SecurityAction;
import common.presentation.web.security.actions.mapping.SecurityActionMapping;
import common.util.spring.SpringUtil;

public class EditMenuAction extends SecurityAction {
	
    public ActionForward _execute(SecurityActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	MenuForm rform = (MenuForm)form;
    	
    	if ( rform.getMenPk() == null || rform.getMenPk() == 0 )
    	{
           	rform.limpia();
    	} else
    	{
        	MenusBO bo = (MenusBO) SpringUtil.getInstance().getBean("MenusBO");
        	MenusDTO dto = bo.getByPrimaryKey(rform.getMenPk());
        	Convert.getInstance().setForm(rform);
        	Convert.getInstance().dto2form(dto);

        	GruposMenusBO boGrupos = (GruposMenusBO) SpringUtil.getInstance().getBean("GruposMenusBO");
        	rform.setGrupos(boGrupos.getByMenu(rform.getMenPk()));
    	}
    	
    	GruposBO boGrupos = (GruposBO) SpringUtil.getInstance().getBean("GruposBO");
    	GruposDTO[] grupos = boGrupos.getGrupos();        	
    	request.removeAttribute("listGrupos");
    	request.setAttribute("listGrupos", grupos);    	
    	
    	MenusBO boMenus = (MenusBO) SpringUtil.getInstance().getBean("MenusBO");
    	MenusDTO[] menus = boMenus.getMenus();        	
    	request.removeAttribute("listMenus");
    	request.setAttribute("listMenus", menus);    	

    	UrlsBO boUrls = (UrlsBO) SpringUtil.getInstance().getBean("UrlsBO");
    	UrlsDTO[] urls = boUrls.getUrls();
    	request.removeAttribute("listUrls");
    	request.setAttribute("listUrls", urls);    	
    	
    	WebInfoHelper.getInstance().setWebInfo(request, "Los campos marcados con (*) son obligatorios");
    	
    	return mapping.findForward("success");    		
    }
}
