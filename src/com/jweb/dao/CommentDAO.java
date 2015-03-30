package com.jweb.dao;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.jweb.beans.Comment;
import com.jweb.exceptions.DAOException;

/**
 * The comment Data Access Object : create, read, update or delete a comment. Extends the DAO abstract class.
 * @author Julie
 * @see DAO, DAOException
 *
 */

public class CommentDAO extends DAO {
	
	public CommentDAO(DAOFactory db) {
		super(db);
	}

	/**
	 * Returns a list of comments 
	 * @return
	 * @throws DAOException
	 */
	
	public List<Comment> getComments(int id) throws DAOException {
		List<Comment> book = new ArrayList<Comment>();
		
		try {
			this.connect();
			this.prepare("SELECT * FROM comments AS c LEFT JOIN users AS u ON c.idauthor = u.idusers WHERE idbook = ?");
			this._s.setInt(1, id);
			this.select();
			try {
				if (this._r != null) {
					book.add(new Comment(this._r.getInt("idcomments"), this._r.getString("content"), this._r.getTimestamp("date"), 
							this._r.getString("authorname"), this._r.getInt("idbook"), this._r.getInt("idauthor")));
					while (this._r.next()) {
						book.add(new Comment(this._r.getInt("idcomments"), this._r.getString("content"), this._r.getTimestamp("date"), 
								this._r.getString("authorname"), this._r.getInt("idbook"), this._r.getInt("idauthor")));
					}
				}
			}
			catch (SQLException e) {
				throw new DAOException("Un problème est survenu, merci de réessayer. (getcomments)");
			}
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (getcomments)");
		}
		finally {
			this.close();
		}
		return (book);
	}
	
	/**
	 * Add a new comment into the database
	 * @param user
	 * @param book
	 * @param content
	 * @throws DAOException
	 */
	
	public void addComment(Comment comment) throws DAOException {
		try {
			this.connect();
			this.prepare("INSERT INTO comments (idauthor, authorname, content, idbook) VALUES (?, ?, ?, ?)");
			this._s.setInt(1, comment.getAuthor());
			this._s.setString(2, comment.getAuthorName());
			this._s.setString(3, comment.getContent());
			this._s.setInt(4, comment.getIdBook());
			this.insert();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (addcomment)");
		}
		finally {
			this.close();
		}
	}
	
	/**
	 * Tries to delete a comment from the database
	 * @param id
	 * @throws DAOException
	 */

	public void deleteComment(int id) throws DAOException {
		try {
			this.connect();
			this.prepare("DELETE FROM comments WHERE idcomments = ?");
			this._s.setInt(1, id);
			this.delete();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (addcomment)");
		}
		finally {
			this.close();
		}
	}

}
