package com.jweb.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * FILTER : handle access to user account
 * @author Julie
 *
 */

@WebFilter("/user/*")
public class UserFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		if (session.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {
		

	}
}
