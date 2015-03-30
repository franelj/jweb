package com.jweb.servlets;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.Suscriber;
import com.jweb.dao.DAOFactory;
import com.jweb.models.NewsletterHandler;

/**
 * CONTROLLER : to handle newsletter suscribers
 * @author Julie
 *
 */

@WebServlet("/admin/newsletter/users")
public class AdminNewsletterHandlerController extends HttpServlet {
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
		NewsletterHandler handler = new NewsletterHandler(this._db);
		if (req.getParameter("delete") != null) {
			Integer id = Integer.valueOf(req.getParameter("delete"));
			if (id >= 0) {
				handler.deleteSuscriber(id);
			}
		}
		List<Suscriber> suscribers = handler.getSuscribers();
		req.setAttribute("handler", handler);
		req.setAttribute("suscribers", suscribers);
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin/newsletter/users.jsp").forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {		
		NewsletterHandler handler = new NewsletterHandler(this._db);
		List<Suscriber> suscribers = handler.getSuscribers();
		req.setAttribute("handler", handler);
		req.setAttribute("suscribers", suscribers);
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin/newsletter/users.jsp").forward(req, resp);
	}
}
