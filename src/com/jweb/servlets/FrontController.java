package com.jweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.Book;
import com.jweb.beans.News;
import com.jweb.beans.Suscriber;
import com.jweb.dao.DAOFactory;
import com.jweb.models.BookHandler;
import com.jweb.models.NewsHandler;
import com.jweb.models.NewsletterHandler;

/**
 * CONTROLLER : to display news, books and newsletter on the homepage
 * @author Julie
 *
 */

@WebServlet("/home")
public class FrontController extends HttpServlet {
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
		NewsHandler newsHandler = new NewsHandler(this._db);
		BookHandler bookHandler = new BookHandler(this._db);
		List<News> news = newsHandler.getNews(true);
		List<Book> books = bookHandler.getBooks();
		req.setAttribute("news", news);
		req.setAttribute("books", books);
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		NewsletterHandler newsletterHandler = new NewsletterHandler(this._db);
		NewsHandler newsHandler = new NewsHandler(this._db);
		BookHandler bookHandler = new BookHandler(this._db);
		Suscriber suscriber = newsletterHandler.handleSuscriber(req);
		List<News> news = newsHandler.getNews(true);
		List<Book> books = bookHandler.getBooks();
		req.setAttribute("nHandler", newsletterHandler);
		req.setAttribute("suscriber", suscriber);
		req.setAttribute("newsHandler", newsHandler);
		req.setAttribute("bookHandler", bookHandler);
		req.setAttribute("news", news);
		req.setAttribute("books", books);
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
	}
}
