package com.jweb.servlets;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.Author;
import com.jweb.dao.DAOFactory;
import com.jweb.models.AuthorHandler;

/**
 * CONTROLLER : to edit an author
 * @author Julie
 *
 */

@WebServlet("/admin/author/edit")
public class AdminAuthorEditController extends HttpServlet {
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
		AuthorHandler handler = new AuthorHandler(this._db);
		if (req.getParameter("id") == null) {
			if (req.getParameter("delete") != null) {
				Integer id = Integer.valueOf(req.getParameter("delete"));
				if (id >= 0) {
					handler.deleteAuthor(id);
				}
			}
			List<Author> authors = handler.getAuthors();
			req.setAttribute("handler", handler);
			req.setAttribute("authors", authors);
			this.getServletContext().getRequestDispatcher("/WEB-INF/admin/authors/list.jsp").forward(req, resp);
		}
		else {
			Integer id = Integer.valueOf(req.getParameter("id"));
			Author author = new Author();
			if (id >= 0)
				 author = handler.getAuthorById(id);
			req.setAttribute("handler", handler);
			req.setAttribute("author", author);
			req.setAttribute("path", req.getContextPath() + "/admin/author/edit?id=" + req.getParameter("id"));
			this.getServletContext().getRequestDispatcher("/WEB-INF/admin/authors/form.jsp").forward(req, resp);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {		
		AuthorHandler handler = new AuthorHandler(this._db);
		if (req.getParameter("id") != null) {
			Integer id = Integer.valueOf(req.getParameter("id"));
			Author author = new Author();
			if (id >= 0)
				author = handler.updateAuthor(req, id);
			req.setAttribute("handler", handler);
			req.setAttribute("author", author);
			req.setAttribute("path", req.getContextPath() + "/admin/author/edit?id=" + req.getParameter("id"));
			this.getServletContext().getRequestDispatcher("/WEB-INF/admin/authors/form.jsp").forward(req, resp);
		}
		else {
			List<Author> authors = handler.getAuthors();
			req.setAttribute("handler", handler);
			req.setAttribute("authors", authors);
			this.getServletContext().getRequestDispatcher("/WEB-INF/admin/authors/list.jsp").forward(req, resp);
		}
	}
}
