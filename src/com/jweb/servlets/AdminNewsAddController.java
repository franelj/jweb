package com.jweb.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.News;
import com.jweb.dao.DAOFactory;
import com.jweb.models.NewsHandler;

/**
 * CONTROLLER : to add a news
 * @author Julie
 *
 */

@WebServlet("/admin/news/add")
public class AdminNewsAddController extends HttpServlet {
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
		req.setAttribute("path", req.getContextPath() + "/admin/news/add");
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin/news/form.jsp").forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {
		NewsHandler handler = new NewsHandler(this._db);
		News news = handler.addNews(req);
		req.setAttribute("handler", handler);
		req.setAttribute("news", news);
		req.setAttribute("path", req.getContextPath() + "/admin/news/add");
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin/news/form.jsp").forward(req, resp);
	}
}
