package com.jweb.servlets;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.News;
import com.jweb.beans.Newsletter;
import com.jweb.dao.DAOFactory;
import com.jweb.models.NewsHandler;
import com.jweb.models.NewsletterHandler;

/**
 * CONTROLLER : to edit a news
 * @author Julie
 *
 */

@WebServlet("/admin/news/edit")
public class AdminNewsEditController extends HttpServlet {
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
		NewsHandler handler = new NewsHandler(this._db);
		if (req.getParameter("id") == null) {
			if (req.getParameter("delete") != null) {
				Integer id = Integer.valueOf(req.getParameter("delete"));
				if (id >= 0) {
					handler.deleteNews(id);
				}
			}
			else if (req.getParameter("send") != null) {
				Integer id = Integer.valueOf(req.getParameter("send"));
				if (id >= 0) {
					NewsletterHandler nHandler = new NewsletterHandler(this._db);
					News news = handler.getNewsById(id);
					Newsletter tobesend = nHandler.prepare(news);
					nHandler.send(tobesend);
					req.setAttribute("nHandler", nHandler);
				}
			}
			List<News> news = handler.getNews(false);
			req.setAttribute("handler", handler);
			req.setAttribute("news", news);
			this.getServletContext().getRequestDispatcher("/WEB-INF/admin/news/list.jsp").forward(req, resp);
		}
		else {
			Integer id = Integer.valueOf(req.getParameter("id"));
			News news = new News();
			if (id >= 0) {
				news = handler.getNewsById(id);
			}
			req.setAttribute("handler", handler);
			req.setAttribute("news", news);
			req.setAttribute("path", req.getContextPath() + "/admin/news/edit?id=" + req.getParameter("id"));
			this.getServletContext().getRequestDispatcher("/WEB-INF/admin/news/form.jsp").forward(req, resp);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {
		NewsHandler handler = new NewsHandler(this._db);
		if (req.getParameter("id") != null) {
			Integer id = Integer.valueOf(req.getParameter("id"));
			News news = new News();
			if (id >= 0) {
				news = handler.getNewsById(id);
				news = handler.updateNews(news, req);
			}
			req.setAttribute("handler", handler);
			req.setAttribute("news", news);
			req.setAttribute("path", req.getContextPath() + "/admin/news/edit?id=" + req.getParameter("id"));
			this.getServletContext().getRequestDispatcher("/WEB-INF/admin/news/form.jsp").forward(req, resp);
		}
		else {
			List<News> news = handler.getNews(false);
			req.setAttribute("handler", handler);
			req.setAttribute("news", news);
			this.getServletContext().getRequestDispatcher("/WEB-INF/admin/news/list.jsp").forward(req, resp);
		}
	}
}
