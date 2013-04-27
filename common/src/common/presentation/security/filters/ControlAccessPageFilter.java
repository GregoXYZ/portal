package common.presentation.security.filters;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Constants;
import common.business.BusinessException;
import common.business.bo.SessionsBO;
import common.business.bo.UsuariosBO;
import common.business.bo.UtilsBO;
import common.dto.SessionsDTO;
import common.dto.UsuariosDTO;
import common.presentation.security.beans.UserInfo;
import common.presentation.util.ControlCookies;
import common.presentation.util.WebInfoHelper;
import common.util.spring.SpringUtil;


public class ControlAccessPageFilter implements Filter {
    
    //private FilterConfig filterConfig;

	//private boolean shareSessionId;

	@Override
	public void destroy() {
		//filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		if (servletRequest instanceof HttpServletRequest) 
		{
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpSession session = request.getSession();
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			
			Cookie cookie = ControlCookies.getCookie(request, Constants.SESSION_COOKIE);
			if ( cookie == null )
			{
	    		SessionsBO boSession = (SessionsBO) SpringUtil.getInstance().getBean("SessionsBO");
				boSession.closeSession(request, response);
			}
			else
			{
				try {
		    		SessionsBO boSession = (SessionsBO) SpringUtil.getInstance().getBean("SessionsBO");
		    		SessionsDTO dtoSession = boSession.getByCode(cookie.getValue());
		    		
	    			UserInfo user = (UserInfo) session.getAttribute("user");
		    		if ( dtoSession != null && dtoSession.getAddress().equals(request.getRemoteAddr()) &&
		    				( user == null || dtoSession.getUserId() != user.getPk() ))
		    		{
			    		UsuariosBO boUsuario = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
				    	UsuariosDTO dto = boUsuario.getByPrimaryKey(dtoSession.getUserId());
				    	
				    	if ( dto == null )
				    	{
				    		boSession.closeSession(request, response);
				    	}
				    	else
				    	{
					    	UtilsBO boUtils = (UtilsBO) SpringUtil.getInstance().getBean("UtilsBO");
					    	UserInfo userInfo = new UserInfo();
					    	userInfo.setUserInfo(dto, boUtils.getPerfil(dto.getUsuPk()));
					    	userInfo.setSessionPk(dtoSession.getSessionPk());
					    	session.setAttribute("user", userInfo);
				    		dtoSession.setLastAccessedTime(new Date());
				    		boSession.update(dtoSession);
				    	}
		    		}
		    		else
		    		{
		    			boSession.closeSession(request, response);
		    		}
				} catch (BusinessException e) {
					WebInfoHelper.getInstance().setWebError(request, e);
					throw new ServletException(e); 
				}
	    	}
		}		
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
    public void init(FilterConfig config) throws ServletException {
		/*
    	filterConfig = config;

    	String s = filterConfig.getInitParameter("shareSessionId");
		if (s != null) {
			shareSessionId = Boolean.parseBoolean(s);
		} else {
			shareSessionId = false;
		}
		*/
	}
}
