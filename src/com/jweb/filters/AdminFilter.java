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

import com.jweb.beans.User;

/**
 * FILTER : Handle access to the administration
 * @author Julie
 *
 */

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		else {
			if (user.getIsAdmin() == true)
				chain.doFilter(req, resp);
			else
				resp.sendRedirect(req.getContextPath() + "/user/account");
		}
	}

	@Override
	public void destroy() {

	}
}
