package com.jweb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jweb.exceptions.DAOException;

/**
 * The Data Access Object abstract class.
 * @author Julie
 * @see DAOException
 *
 */

public abstract class DAO {
	DAOFactory _db = null;
	java.sql.Connection _c = null;
	PreparedStatement _s = null;
	ResultSet _r = null;

	public DAO(DAOFactory db) {
		this._db = db;
	}

	/**
	 * Connect to the database. Throw a DbException if any SQLException occurs and close the connection if needed.
	 * @throws DbException
	 */
	
	public void connect() throws DAOException {
		try {
			this._c = this._db._cp.getConnection();
			}
		catch (SQLException e) {
			throw new DAOException("connect");
		}
	}
	
	/**
	 * Prepare a statement
	 * @param query
	 * @throws DAOException
	 */
	
	public void prepare(String query) throws DAOException {
		try {
			this._s = this._c.prepareStatement(query);
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (prepare)");
		}
	}
	
	/**
	 * Close the connection to the database. Ignore any SQLException occurring.
	 */
	
	public void close() {
		try {
			if (this._r != null) {
				this._r.close();
			}
			if (this._s != null) {
				this._s.close();
			}
			if (this._c != null) {
				this._c.close();
			}
		}
		catch (SQLException ignore) {
			// on ignore
		}
	}
	
	/**
	 * Retrieve the first row corresponding to the query. A DbException is thrown if any error occurs.
	 * Close the connection to the database if needed.
	 * @throws DbException
	 */
	
	public void select() throws DAOException {
		try {
			this._r = this._s.executeQuery();
			if (!this._r.next()) {
				this._r.close();
				this._r = null;
			}
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (getrow)" + e.getMessage());
		}
	}
	
	/**
	 * Tries to update a row into the database according to the query passed as parameter
	 * @throws DAOException
	 */
	
	public void update() throws DAOException {
		try {
			int rows = this._s.executeUpdate();
			if (rows == 0)
				throw new DAOException("Un problème est survenu, merci de réessayer. (insertrow 1)");
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (insertrow 2) " + e.getMessage());
		}
	}
	
	/**
	 * Tries to update a row into the database according to the query passed as parameter
	 * @throws DAOException
	 */
	
	public void insert() throws DAOException {
		try {
			int rows = this._s.executeUpdate();
			if (rows == 0)
				throw new DAOException("Un problème est survenu, merci de réessayer. (insertrow 1)");
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (insertrow 2) " + e.getMessage());
		}
	}

	
	/**
	 * Tries to update a row into the database according to the query passed as parameter
	 * @throws DAOException
	 */
	
	public void delete() throws DAOException {
		try {
			int rows = this._s.executeUpdate();
			if (rows == 0)
				throw new DAOException("Un problème est survenu, merci de réessayer. (insertrow 1)");
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (insertrow 2) " + e.getMessage());
		}
	}
}
