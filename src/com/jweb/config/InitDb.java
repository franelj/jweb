package com.jweb.config;

import javax.servlet.ServletContext;
	
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.jweb.dao.DAOFactory;
import com.jweb.exceptions.DAOException;

/**
 * The DAOFactory initializer
 * @author Julie
 * @see DAOFactory
 *
 */

public class InitDb implements ServletContextListener {
	private DAOFactory _db = null;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		try {
			this._db = DAOFactory.getInstance();
			context.setAttribute("db", this._db);
		}
		catch (DAOException e) {
			throw e;
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		if (this._db != null) {
			this._db.shutdownPool();
			this._db = null;
		}
	}

}
