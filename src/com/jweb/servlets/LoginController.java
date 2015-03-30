package com.jweb.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jweb.beans.User;
import com.jweb.dao.DAOFactory;
import com.jweb.models.UserHandler;

/**
 * CONTROLLER : to handle user login / account creation
 * @author Julie
 * @see Db, DbException
 *
 */

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory _db;
	public String _s = null;

	/**
	 * Servlet initialization, getting a connection to the database
	 */

	public void init() throws ServletException {
		this._db = (DAOFactory)getServletContext().getAttribute("db");
		if (this._db == null)
			throw new ServletException("Un problème est survenu, merci de réessayer.");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("user") == null)
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
		else
			resp.sendRedirect(req.getContextPath() + "/user/account");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {
		HttpSession session = req.getSession();
		
		if (session.getAttribute("user") == null) {
			UserHandler handler = new UserHandler(this._db);
			User user = null;
			User cr = null;
			if (req.getParameter("action") != null) {
				String action = req.getParameter("action");
				
				if (action.equals("create")) {
					cr = handler.addUser(req); 
				}
				else if (action.equals("login")) {
					user = handler.authenticateUser(req);
					if (handler.getErrors().isEmpty()) {
						session.setAttribute("user", user);
					}
				}
			}
			req.setAttribute("handler", handler);
			req.setAttribute("user", user);
			req.setAttribute("cr", cr);
			if (session.getAttribute("user") != null)
				resp.sendRedirect(req.getContextPath() + "/user/account");
			else
				this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
		}
		else
			resp.sendRedirect(req.getContextPath() + "/user/account");
	}
}
