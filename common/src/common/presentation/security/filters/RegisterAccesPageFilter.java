package common.presentation.security.filters;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.Constants;
import common.util.PathMatcher;

public class RegisterAccesPageFilter implements Filter {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(RegisterAccesPageFilter.class);
    
    private static PathMatcher matcher;
    
    private FilterConfig filterConfig;

	@Override
	public void destroy() {
		filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {

		if (servletRequest instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpSession session = request.getSession();

			String uri = request.getRequestURI();
			int index = uri.indexOf(request.getContextPath());
			if(index >= 0) {
				uri = uri.substring(index+request.getContextPath().length());
			}
			if ( logger.isDebugEnabled() )
				logger.debug("URI to filter " + uri);

			
			if(!isExcluded(uri)) {
				String url = request.getRequestURL().toString();
				
				if ( logger.isDebugEnabled() )
					logger.debug("Register access to page " + url);
				String last = (String)session.getAttribute(Constants.PAGE_LAST_VISITED);
				if(last!=null && !last.equals("") && !last.equals(url)) {
					session.setAttribute(Constants.PAGE_BEFORE_LAST_VISITED, last);
				}
				session.setAttribute(Constants.PAGE_LAST_VISITED, url);
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);		
	}

	@Override
    public void init(FilterConfig config) throws ServletException {
    	filterConfig = config;
    	matcher = new PathMatcher();
	}

	private boolean isExcluded(String page) {
		StringTokenizer excludedUris = new StringTokenizer(filterConfig.getInitParameter("excludedPages"), ",");
		boolean isExcluded = false;
		if(page!=null && !page.equals("")) {
			while(excludedUris.hasMoreTokens()) {
				String pattern = excludedUris.nextToken();
				if(matcher.match(pattern, page)) {
					isExcluded = true;
					break;
				}
			}
		}
		return isExcluded;
	}
}
