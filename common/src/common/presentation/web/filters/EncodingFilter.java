package common.presentation.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		//if (servletRequest instanceof XMLHttpRequest)
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		
		if(req.getHeader("X-Requested-With")!=null && req.getHeader("X-Requested-With").equals("XMLHttpRequest"))
			req.setCharacterEncoding("UTF-8");
		
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
    public void init(FilterConfig config) throws ServletException {
	}
}
