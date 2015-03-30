package com.jweb.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.Address;
import com.jweb.beans.User;
import com.jweb.dao.DAOFactory;
import com.jweb.models.AddressHandler;
import com.jweb.models.UserHandler;

/**
 * CONTROLLER : to handle user account
 * @author Julie
 *
 */

@WebServlet("/user/account")
public class UserAccountController extends HttpServlet {
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
		UserHandler userHandler = new UserHandler(this._db);
		AddressHandler addrHandler = new AddressHandler(this._db);
		User user = (User) req.getSession().getAttribute("user");
		Address addr = addrHandler.getAddress(user.getId());
		req.setAttribute("userHandler", userHandler);
		req.setAttribute("addrHandler", addrHandler);
		req.setAttribute("user", user);
		req.setAttribute("addr", addr);
		this.getServletContext().getRequestDispatcher("/WEB-INF/user/account.jsp").forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {
		UserHandler userHandler = new UserHandler(this._db);
		AddressHandler addrHandler = new AddressHandler(this._db);
		User user = (User) req.getSession().getAttribute("user");
		Address addr = addrHandler.getAddress(user.getId());;
		if (req.getParameter("edit") != null) {
			String edit = req.getParameter("edit");
			
			if (edit.equals("profile")) {
				user = userHandler.updateUser(req);
				req.getSession().setAttribute("user", user);
			}
			else if (edit.equals("address")) {
				addr = addrHandler.updateAddress(addr, req);
			}
		}
		req.setAttribute("userHandler", userHandler);
		req.setAttribute("addrHandler", addrHandler);
		req.setAttribute("user", user);
		req.setAttribute("addr", addr);
		this.getServletContext().getRequestDispatcher("/WEB-INF/user/account.jsp").forward(req, resp);
	}
}
