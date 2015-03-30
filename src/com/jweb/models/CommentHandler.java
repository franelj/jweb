package com.jweb.models;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jweb.beans.Comment;
import com.jweb.beans.User;
import com.jweb.dao.CommentDAO;
import com.jweb.dao.DAOFactory;
import com.jweb.exceptions.DAOException;
import com.jweb.exceptions.FormException;

/**
 * MODEL : validate and handle data for a comment. Extends the abstract class Handler.
 * @author Julie
 * @see Handler
 *
 */

public class CommentHandler extends Handler {
	CommentDAO _dao = null;

	public CommentHandler(DAOFactory db) {
		this._dao = db.getCommentDao();
	}
	
	/**
	 * Tries to validate the comment name
	 * @param name
	 * @throws FormException
	 */
	
	private void validateName(String name) throws FormException {
		if (name == null || name.isEmpty())
			throw new FormException("Vous devez renseigner un nom ou un pseudonyme.");
	}
	
	/**
	 * Tries to validate the comment content
	 * @param content
	 * @throws FormException
	 */
	
	private void validateContent(String content) throws FormException {
		if (content == null || content.isEmpty())
			throw new FormException("Vous ne pouvez poster un commentaire vide.");
	}
	
	/**
	 * Tries to validate the book id
	 * @param book
	 * @throws FormException
	 */
	
	private void validateBook(Integer book) throws FormException {
		if (book == null || book < 0)
			throw new FormException("Vous ne pouvez commenter un livre non existant.");
	}
	
	/**
	 * Tries to validate all required fields
	 * @param comment
	 * @param req
	 * @return
	 */
	
	private Comment validateFields(Comment comment, HttpServletRequest req) {
		String name = this.getParam(req, "commentName");
		String content = this.getParam(req, "commentContent");
		Integer book = this.getIntParam(req, "commentBook");
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user != null) {
			// If the user is not connected, we have to get his id and his login by ourselves from the session attributes
			comment.setAuthor(user.getId());
			comment.setAuthorName(user.getName());
		}
		else {
			// else, we have to validate it
			try {
				this.validateName(name);
			}
			catch (FormException e) {
				this.setError("name", e.getMessage());
			}
			comment.setAuthor(0);
			comment.setAuthorName(name);
		}
		
		// Validate the comment content
		try {
			this.validateContent(content);
		}
		catch (FormException e) {
			this.setError("content", e.getMessage());
		}
		comment.setContent(content);
		
		// Validate the comment book
		try {
			this.validateBook(book);
		}
		catch (FormException e) {
			this.setError("book", e.getMessage());
		}
		comment.setIdBook(book);
		return (comment);
	}
	
	/**
	 * Tries to add the comment to the database after validating
	 * @param req
	 */
	
	public Comment addComment(HttpServletRequest req) {
		Comment comment = this.validateFields(new Comment(), req);
		
		// If all required fields are okay, tries to add the comment to the database
		if (this._errors.isEmpty()) {
			try {
				this._dao.addComment(comment);
			}
			catch (DAOException e) {
				this.setError("db", e.getMessage());
			}
		}
		
		if (this._errors.isEmpty()) {
			this.setSuccess("Votre commentaire a été posté avec succès.");
		}
		else
			 this.setFail("Votre commentaire n'a pu être posté.");
		return (comment);
	}
	
	/**
	 * Tries to retrieve the list of all comments about a book
	 * @param id
	 * @return
	 */
	
	public List<Comment> getComments(int id) {
		List<Comment> comments = null;
		try {
			comments = this._dao.getComments(id);
			if (comments.isEmpty())
				this.setError("count", "Il n'y a aucun commentaire sur ce livre.");
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
		}
		return (comments);
	}
}
