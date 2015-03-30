package com.jweb.models;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jweb.beans.Author;
import com.jweb.dao.AuthorDAO;
import com.jweb.dao.DAOFactory;
import com.jweb.exceptions.DAOException;
import com.jweb.exceptions.FormException;

/**
 * MODEL : validate and handle data for an author. Extends the abstract class Handler.
 * @author Julie
 * @see Handler
 *
 */

public class AuthorHandler extends Handler {
	AuthorDAO _dao = null;
	
	public AuthorHandler(DAOFactory db) {
		this._dao = db.getAuthorDao();
	}
	
	/**
	 * Check if the firstname is not null and not empty
	 * @param firstname
	 * @throws FormException
	 */
	
	private void validateFirstname(String firstname) throws FormException {
		if (firstname == null || firstname.isEmpty())
			throw new FormException("Vous devez renseigner un prénom.");
	}
	
	/**
	 * Check if the lastname is not null and not empty
	 * @param lastname
	 * @throws FormException
	 */
	
	private void validateLastname(String lastname) throws FormException {
		if (lastname == null || lastname.isEmpty())
			throw new FormException("Vous devez renseigner un nom de famille.");
	}
	
	/**
	 * Check if the resume is not null and not empty
	 * @param resume
	 * @throws FormException
	 */
	
	private void validateResume(String resume) throws FormException {
		if (resume == null || resume.isEmpty())
			throw new FormException("Vous devez renseigner un minimum de biographie.");
	}
	
	private Author validateFields(HttpServletRequest req) {
		String firstname = this.getParam(req, "authorFirstname");
		String lastname = this.getParam(req, "authorLastname");
		String resume = this.getParam(req, "authorResume");
		Author author = new Author();
		
		// Validate firstname
		try {
			this.validateFirstname(firstname);
		}
		catch (FormException e) {
			this.setError("firstname", e.getMessage());
		}
		author.setFirstname(firstname);
		
		// Validate lastname
		try {
			this.validateLastname(lastname);
		}
		catch (FormException e) {
			this.setError("lastname", e.getMessage());
		}
		author.setLastname(lastname);
		
		// Validate resume
		try {
			this.validateResume(resume);
		}
		catch (FormException e) {
			this.setError("resume", e.getMessage());
		}
		author.setResume(resume);
		return (author);
	}
	
	/**
	 * @param req
	 * @param db
	 * @return Author
	 */
	
	public Author addAuthor(HttpServletRequest req) {
		Author author = this.validateFields(req);
		
		// Create the entry into the database if there is no error
		if (this._errors.isEmpty()) {
			try {
				this._dao.createAuthor(author);
			}
			catch (DAOException e) {
				this.setError("db", e.getMessage());
			}
		}
		
		// Set the final result
		if (this._errors.isEmpty())
			this.setSuccess("La fiche auteur a été créée avec succès.");				
		else
			this.setFail("La fiche auteur n'a pu être créée.");
		return (author);
	}
	
	public Author updateAuthor(HttpServletRequest req, int id) {
		Author author = this.validateFields(req);
		
		// Set the id
		author.setId(id);
		
		// Create the entry into the database if there is no error
		if (this._errors.isEmpty()) {
			try {
				this._dao.updateAuthor(author);
			}
			catch (DAOException e) {
				this.setError("db", e.getMessage());
			}
		}
		
		// Set the final result
		if (this._errors.isEmpty())
			this.setSuccess("La fiche auteur a été éditée avec succès.");				
		else
			this.setFail("La fiche auteur n'a pu être éditée.");
		return (author);
	}
	
	public List<Author> getAuthors() {
		List<Author> authors = null;
		try {
			authors = this._dao.getAuthors();
			if (authors.isEmpty())
				this.setFail("Il n'y a aucun auteur enregistré dans la base.");
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());			
		}
		return (authors);
	}
	
	public void deleteAuthor(int id) {
		try {
			this._dao.deleteAuthor(id);
			this.setSuccess("La fiche auteur a bien été supprimée.");
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
		}
	}
	
	public Author getAuthorById(int id) {
		Author author = null;
		try {
			author = this._dao.getAuthorById(id);
			if (author == null)
				this.setFail("Cet auteur n'existe pas dans la base.");
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
		}
		return (author);
	}
}
