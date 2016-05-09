package com.tlh.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.tlh.utils.DaoUtils;

public class CloseConnectionFilter implements Filter {

	private FilterConfig conf;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response=(HttpServletResponse) resp ;
        String access=conf .getInitParameter("access");
        response.addHeader("Access-Control-Allow-Origin", access);
        response.addHeader("Access-Control-Allow-Methods", "POST,GET");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		chain.doFilter(req, resp);
		DaoUtils.release();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.conf =filterConfig;
	}

}
