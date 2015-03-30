package com.jweb.servlets;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.jweb.beans.Author;
import com.jweb.beans.Book;
import com.jweb.dao.DAOFactory;
import com.jweb.models.AuthorHandler;
import com.jweb.models.BookHandler;

/**
 * CONTROLLER : to edit a book
 * @author Julie
 *
 */

@WebServlet("/admin/book/add")
@MultipartConfig(
		fileSizeThreshold = 1048576, //1mo
		maxFileSize = 1048576 * 3, //3Mo
		maxRequestSize = 1048576 * 6, //6Mo
		location = "C:\\Users\\Julie\\workspace\\JWeb\\WebContent\\img\\tmp\\"
		)
public class AdminBooksAddController extends HttpServlet {
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
		AuthorHandler authorHandler = new AuthorHandler(this._db);
		List<Author> authors = authorHandler.getAuthors();
		req.setAttribute("authorHandler", authorHandler);
		req.setAttribute("authors", authors);
		req.setAttribute("path", req.getContextPath() + "/admin/book/add");
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin/books/form.jsp").forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {
		// Displays the authors' list
		AuthorHandler authorHandler = new AuthorHandler(this._db);
		List<Author> authors = authorHandler.getAuthors();
		req.setAttribute("authorHandler", authorHandler);
		req.setAttribute("authors", authors);
		
		// Handle the book post data
		BookHandler bookHandler = new BookHandler(this._db);
		Book book = bookHandler.addBook(req);
		req.setAttribute("bookHandler", bookHandler);	
		req.setAttribute("book", book);
		req.setAttribute("path", req.getContextPath() + "/admin/book/add");
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin/books/form.jsp").forward(req, resp);
	}
}
