package com.jweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.Book;
import com.jweb.beans.Comment;
import com.jweb.dao.DAOFactory;
import com.jweb.models.BookHandler;
import com.jweb.models.CommentHandler;

/**
 * CONTROLLER : to display book informations
 * @author Julie
 *
 */

@WebServlet("/book/*")
public class BookController extends HttpServlet {
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
			// Sets the handlers
			BookHandler bookHandler = new BookHandler(this._db);
			CommentHandler commentHandler = new CommentHandler(this._db);
			
			// Sets the mandatory variables
			int id = Integer.valueOf(path.substring(path.lastIndexOf("/") + 1, path.length()));
			Book book = new Book();
			List<Comment> comments = null;
			
			// Retrieve book and comments informations
			if (id >= 0) {
				book = bookHandler.getBookById(id);
				comments = commentHandler.getComments(id);
			}
			
			// Sets the attributes
			req.setAttribute("bookHandler", bookHandler);
			req.setAttribute("commentHandler", commentHandler);
			req.setAttribute("book", book);
			req.setAttribute("comments", comments);
			this.getServletContext().getRequestDispatcher("/WEB-INF/book.jsp").forward(req, resp);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = req.getPathInfo();
		if (!path.matches("/[0-9]+")) {
			resp.sendRedirect(req.getContextPath() + "/error");
		}
		else {
			// Sets the handlers
			BookHandler bookHandler = new BookHandler(this._db);
			CommentHandler commentHandler = new CommentHandler(this._db);
			
			// Sets the mandatory variable 
			int id = Integer.valueOf(path.substring(path.lastIndexOf("/") + 1, path.length()));
			Book book = new Book();
			Comment comment = commentHandler.addComment(req);
			List<Comment> comments = null;
			
			// Retrieve book and comments informations
			if (id >= 0) {
				book = bookHandler.getBookById(id);
				comments = commentHandler.getComments(id);
			}
			
			// Sets the attributes
			req.setAttribute("bookHandler", bookHandler);
			req.setAttribute("commentHandler", commentHandler);
			req.setAttribute("book", book);
			if (!commentHandler.getErrors().isEmpty())
				req.setAttribute("comment", comment);
			req.setAttribute("comments", comments);
			this.getServletContext().getRequestDispatcher("/WEB-INF/book.jsp").forward(req, resp);
		}
	}
}
