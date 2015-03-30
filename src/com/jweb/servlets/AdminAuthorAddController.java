package com.jweb.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.Author;
import com.jweb.dao.DAOFactory;
import com.jweb.models.AuthorHandler;

/**
 * CONTROLLER : to add an author
 * @author Julie
 *
 */

@WebServlet("/admin/author/add")
public class AdminAuthorAddController extends HttpServlet {
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
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin/authors/form.jsp").forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {
		AuthorHandler handler = new AuthorHandler(this._db);
		Author author = handler.addAuthor(req);
		req.setAttribute("handler", handler);
		req.setAttribute("author", author);
		req.setAttribute("path", req.getContextPath() + "/admin/author/add");
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin/authors/form.jsp").forward(req, resp);
	}
}
