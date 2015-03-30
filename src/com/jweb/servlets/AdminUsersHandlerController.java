package com.jweb.servlets;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.User;
import com.jweb.dao.DAOFactory;
import com.jweb.models.UserHandler;

/**
 * CONTROLLER : to handler registered users
 * @author Julie
 *
 */

@WebServlet("/admin/users")
public class AdminUsersHandlerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory _db;

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
		UserHandler handler = new UserHandler(this._db);
		if (req.getParameter("delete") != null) {
			Integer id = Integer.valueOf(req.getParameter("delete"));
			if (id >= 0 && id != ((User)req.getSession().getAttribute("user")).getId()) {
				handler.deleteUser(id);
			}
		}
		List<User> users = handler.getUsers();
		req.setAttribute("handler", handler);
		req.setAttribute("users", users);
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin/users/list.jsp").forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {		
		UserHandler handler = new UserHandler(this._db);
		List<User> users = handler.getUsers();
		req.setAttribute("handler", handler);
		req.setAttribute("users", users);
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin/users/list.jsp").forward(req, resp);
	}
}
