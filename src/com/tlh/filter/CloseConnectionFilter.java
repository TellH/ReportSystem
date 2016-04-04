package com.tlh.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.tlh.utils.DaoUtils;

public class CloseConnectionFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(arg0, arg1);
		DaoUtils.release();
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
