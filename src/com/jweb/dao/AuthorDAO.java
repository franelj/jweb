package com.jweb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jweb.beans.Author;
import com.jweb.exceptions.DAOException;

/**
 * The author Data Access Object : create, read, update or delete an author. Extends the DAO abstract class.
 * @author Julie
 * @see DAO, DAOException
 *
 */

public class AuthorDAO extends DAO {
	public AuthorDAO(DAOFactory db) {
		super(db);
	}
	
	/**
	 * Create a new author into the database
	 * @param author
	 * @throws DAOException
	 */
	
	public void createAuthor(Author author) throws DAOException {
		try {
			this.connect();
			this.prepare("SELECT * FROM authors WHERE lastname LIKE ? AND firstname LIKE ?");
			this._s.setString(1, author.getLastname());
			this._s.setString(2, author.getFirstname());
			this.select();
			if (this._r != null)
				throw new DAOException("Un auteur nommé ainsi existe déjà dans la base de données.");
			this.prepare("INSERT INTO authors (firstname, lastname, resume) VALUES (?, ?, ?)");
			this._s.setString(1, author.getFirstname());
			this._s.setString(2, author.getLastname());
			this._s.setString(3, author.getResume());
			this.insert();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (updateauthors)");
		}
		finally {
			this.close();
		}
	}
	
	/**
	 * Update an existing author into the database
	 * @param author
	 * @throws DAOException
	 */

	public void updateAuthor(Author author) throws DAOException {
		try {
			this.connect();
			this.prepare("UPDATE authors SET firstname = ?, lastname= ?, resume = ? WHERE idauthors = ?");
			this._s.setString(1, author.getFirstname());
			this._s.setString(2, author.getLastname());
			this._s.setString(3, author.getResume());
			this._s.setInt(4, author.getId());
			this.update();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (updateauthors)");
		}
		finally {
			this.close();
		}
	}
	
	/**
	 * Get an author with his id
	 * @param id
	 * @return Author
	 * @throws DAOException
	 */
	
	public Author getAuthorById(int id) throws DAOException {
		Author author = null;
		try {
			this.connect();
			this.prepare("SELECT * FROM authors WHERE idauthors = ? LIMIT 1");
			this._s.setInt(1, id);
			this.select();
			if (this._r != null)
				author = new Author(this._r.getInt("idauthors"), this._r.getString("firstname"), this._r.getString("lastname"), this._r.getString("resume"));
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (getauthorbyid)");
		}
		finally {
			this.close();
		}
		return (author);
	}
	
	/**
	 * Retrieve all authors' infos from the database
	 * @return List<Author>
	 * @throws DAOException
	 */
	
	public List<Author> getAuthors() throws DAOException {
		List<Author> author = new ArrayList<Author>();
		
		try {
			this.connect();
			this.prepare("SELECT * FROM authors ORDER BY lastname ASC");
			this.select();
			try {
				if (this._r != null) {
					author.add(new Author(this._r.getInt("idauthors"), this._r.getString("firstname"), this._r.getString("lastname"), this._r.getString("resume")));
					while (this._r.next()) {
						author.add(new Author(this._r.getInt("idauthors"), this._r.getString("firstname"), this._r.getString("lastname"), this._r.getString("resume")));
					}
				}
			}
			catch (SQLException e) {
				throw new DAOException("Un problème est survenu, merci de réessayer. (getauthors)");
			}
		}
		catch (DAOException e) {
			throw e;
		}
		finally {
			this.close();
		}
		return (author);
	}
	
	/**
	 * Delete an author from the database
	 * @param id
	 * @throws DAOException
	 */
	
	public void deleteAuthor(int id) throws DAOException {
		try {
			this.connect();
			this.prepare("DELETE FROM authors WHERE idauthors = ?");
			this._s.setInt(1, id);
			this.delete();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (deleteauthor)");
		}
		finally {
			this.close();
		}
	}
}
