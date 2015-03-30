package com.jweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.News;
import com.jweb.dao.DAOFactory;
import com.jweb.models.NewsHandler;

/**
 * CONTROLLER : to handle news
 * @author Julie
 *
 */

@WebServlet("/news/*")
public class NewsController extends HttpServlet {
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
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = req.getPathInfo();
		if (!path.matches("/[0-9]+")) {
			resp.sendRedirect(req.getContextPath() + "/error");
		}
		else {
			// Sets the handler
			NewsHandler newsHandler = new NewsHandler(this._db);
			
			// Sets the mandatory variables
			int id = Integer.valueOf(path.substring(path.lastIndexOf("/") + 1, path.length()));
			News news = new News();
			
			// Retrieve news information
			if (id >= 0) {
				news = newsHandler.getNewsById(id);
			}
			
			// Sets attributes
			req.setAttribute("newsHandler", newsHandler);
			req.setAttribute("news", news);
			this.getServletContext().getRequestDispatcher("/WEB-INF/news.jsp").forward(req, resp);
		}
	}
    
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = req.getPathInfo();
		if (!path.matches("/[0-9]+")) {
			resp.sendRedirect(req.getContextPath() + "/error");
		}
		else {
			// Sets the handler
			NewsHandler newsHandler = new NewsHandler(this._db);
			
			// Sets the mandatory variables
			int id = Integer.valueOf(path.substring(path.lastIndexOf("/") + 1, path.length()));
			News news = new News();
			
			// Retrieve news information
			if (id >= 0) {
				news = newsHandler.getNewsById(id);
			}
			
			// Sets attributes
			req.setAttribute("newsHandler", newsHandler);
			req.setAttribute("news", news);
			this.getServletContext().getRequestDispatcher("/WEB-INF/news.jsp").forward(req, resp);
		}
	}
}
