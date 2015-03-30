package com.jweb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jweb.beans.Book;
import com.jweb.exceptions.DAOException;

/**
 * The book Data Access Object : create, read, update or delete a book. Extends the DAO abtract class.
 * @author Julie
 * @see DAO, DAOException
 *
 */

public class BookDAO extends DAO {
	public BookDAO(DAOFactory db) {
		super(db);
	}
	
	
	/**
	 * Retrieve a book from the database according to the id passed as parameter
	 * @param id
	 * @return Book
	 * @throws DAOException
	 */

	public Book getBookById(int id) throws DAOException {
		Book book = null;
		try {
			this.connect();
			this.prepare("SELECT * FROM books AS b LEFT JOIN authors AS a ON b.idauthor = a.idauthors WHERE idbooks = ? LIMIT 1");
			this._s.setInt(1, id);
			this.select();
			if (this._r != null)
				book = new Book(this._r.getInt("idbooks"), this._r.getString("title"), this._r.getString("serie"), this._r.getInt("idauthor"), 
						this._r.getString("date"), this._r.getString("summary"), this._r.getString("isbn"), this._r.getFloat("price"), this._r.getString("editor"), 
						this._r.getString("firstname") + " " + this._r.getString("lastname"), this._r.getString("file"));
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
		return (book);
	}
	
	/**
	 * Update a book into the database
	 * @param book
	 * @param id
	 * @return boolean
	 * @throws DAOException
	 */
	
	public void createBook(Book book) throws DAOException {
		try {
			this.connect();
			this.prepare("SELECT * FROM books WHERE title LIKE ? AND idauthor = ?");
			this._s.setString(1, book.getTitle());
			this._s.setInt(2, book.getAuthor());
			this.select();
			if (this._r != null)
				throw new DAOException("Un livre ayant les mêmes titre et auteur existe déjà dans la base.");
			this.prepare("INSERT INTO books (title, serie, idauthor, date, summary, isbn, price, editor, file) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			this._s.setString(1, book.getTitle());
			this._s.setString(2, book.getSerie());
			this._s.setInt(3, book.getAuthor());
			this._s.setString(4, book.getDate());
			this._s.setString(5, book.getSummary());
			this._s.setString(6, book.getIsbn());
			this._s.setFloat(7, book.getPrice());
			this._s.setString(8, book.getEditor());
			this._s.setString(9, book.getFile());
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
	 * Update a book into the database
	 * @param book
	 * @param id
	 * @return boolean
	 * @throws DAOException
	 */
	
	public void updateBook(Book book) throws DAOException {
		try {
			this.connect();
			this.prepare("UPDATE books SET title = ?, serie = ?, idauthor = ?, date = ?, summary = ?, isbn = ?, price = ?, editor = ?, file = ? WHERE idbooks = ?");
			this._s.setString(1, book.getTitle());
			this._s.setString(2, book.getSerie());
			this._s.setInt(3, book.getAuthor());
			this._s.setString(4, book.getDate());
			this._s.setString(5, book.getSummary());
			this._s.setString(6, book.getIsbn());
			this._s.setFloat(7, book.getPrice());
			this._s.setString(8, book.getEditor());
			this._s.setString(9, book.getFile());
			this._s.setInt(10, book.getId());
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
	 * Returns a list of all book stored into the database
	 * @return List<Book>
	 * @throws DAOException
	 */
	
	public List<Book> getBooks() throws DAOException {
		List<Book> book = new ArrayList<Book>();
		try {
			this.connect();
			this.prepare("SELECT * FROM books AS b LEFT JOIN authors AS a ON b.idauthor = a.idauthors ORDER BY a.lastname ASC, b.title ASC");
			this.select();
			try {
				if (this._r != null) {
					book.add(new Book(this._r.getInt("idbooks"), this._r.getString("title"), this._r.getString("serie"), this._r.getInt("idauthor"), 
							this._r.getString("date"), this._r.getString("summary"), this._r.getString("isbn"), this._r.getFloat("price"), this._r.getString("editor"), 
							this._r.getString("firstname") + " " + this._r.getString("lastname"), this._r.getString("file")));
					while (this._r.next()) {
						book.add(new Book(this._r.getInt("idbooks"), this._r.getString("title"), this._r.getString("serie"), this._r.getInt("idauthor"), 
								this._r.getString("date"), this._r.getString("summary"), this._r.getString("isbn"), this._r.getFloat("price"), this._r.getString("editor"), 
								this._r.getString("firstname") + " " + this._r.getString("lastname"), this._r.getString("file")));
					}
				}
			}
			catch (SQLException e) {
				throw new DAOException("Un problème est survenu, merci de réessayer. (getbooks)");
			}
		}
		catch (DAOException e) {
			throw e;
		}
		finally {
			this.close();
		}
		return (book);
	}
	
	/**
	 * Delete a book from the database
	 * @param id
	 * @return boolean
	 * @throws DAOException
	 */
	
	public void deleteBook(int id) throws DAOException {
		try {
			this.connect();
			this.prepare("DELETE FROM books WHERE idbooks = ?");
			this._s.setInt(1, id);
			this.delete();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (deletebooks)");
		}
		finally {
			this.close();
		}
	}
	
}
