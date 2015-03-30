package com.jweb.models;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.jweb.beans.Book;
import com.jweb.dao.BookDAO;
import com.jweb.dao.DAOFactory;
import com.jweb.exceptions.DAOException;
import com.jweb.exceptions.FormException;

/**
 * MODEL : validate and handle data for a book. Extends the abstract class Handler.
 * @author Julie
 * @see Handler
 *
 */

public class BookHandler extends Handler {
	BookDAO _dao = null;
	String _path = "/Users/Julie/workspace/JWeb/WebContent/img/uploads/";
	
	public BookHandler(DAOFactory db) {
		this._dao = db.getBookDao();
	}
	
	/**
	 * Check if the title is not null nor empty
	 * @param title
	 * @throws FormException
	 */
	
	private void validateTitle(String title) throws FormException {
		if (title == null || title.isEmpty())
			throw new FormException("Vous devez renseigner un titre.");
	}
	
	/**
	 * Check if the author id is not null nor empty
	 * @param author
	 * @throws FormException
	 */
	
	private void validateAuthor(Integer author) throws FormException {
		if (author == null || author < 0)
			throw new FormException("Vous devez sélectionner un auteur existant.");
	}
	
	/**
	 * Check if the date is not null nor empty
	 * @param date
	 * @throws FormException
	 */
	
	private void validateDate(String date) throws FormException {
		if (date == null || date.isEmpty())
			throw new FormException("Vous devez renseigner une date.");
	}
	
	/**
	 * Check if the summary is not null nor empty
	 * @param summary
	 * @throws FormException
	 */
	
	private void validateSummary(String summary) throws FormException {
		if (summary == null || summary.isEmpty())
			throw new FormException("Vous devez renseigner un résumé.");
	}
	
	/**
	 * Check if the isbn is not null nor empty
	 * @param isbn
	 * @throws FormException
	 */
	
	private void validateIsbn(String isbn) throws FormException {
		if (isbn == null || isbn.isEmpty() || !isbn.matches("[0-9]{13}"))
			throw new FormException("Vous devez renseigner un ISBN valide.");
	}
	
	/**
	 * Check if the price is not null nor empty
	 * @param price
	 * @throws FormException
	 */
	
	private void validatePrice(Float price) throws FormException {
		if (price == null || price < 0)
			throw new FormException("Vous devez renseigner un prix valide.");
	}
	
	/**
	 * Check if the editor is not null nor empty
	 * @param editor
	 * @throws FormException
	 */
	
	private void validateEditor(String editor) throws FormException {
		if (editor == null || editor.isEmpty())
			throw new FormException("Vous devez renseigner un éditeur.");
	}
	
	/**
	 * Check if the filename is not null nor empty and if the extension is the good one
	 * @param filename
	 * @throws FormException
	 */
	
	private void validateFile(String filename) throws FormException {
		if (filename == null || filename.isEmpty())
			throw new FormException("Vous devez joindre une image valide pour la couverture.");
		String extension = filename.substring(filename.lastIndexOf('.') + 1, filename.length());
		if (extension == null || extension.isEmpty() || !extension.matches("(?i)(jpg|jpeg|png|gif)"))
			throw new FormException("Votre image doit être au format .jpg, .jpeg, .png ou .gif.");
	}
	
	/**
	 * Tries to validate all the required fields
	 * @param req
	 * @param add
	 * @return Book
	 */
	
	private Book validateFields(Book book, HttpServletRequest req, boolean add) {
		String title = this.getParam(req, "postTitle");
		String serie = this.getParam(req, "postSerie");
		Integer author = this.getIntParam(req, "postAuthor");
		String date = this.getParam(req, "postDate");
		String summary = this.getParam(req, "postSummary");
		String isbn = this.getParam(req, "postIsbn");
		Float price = this.getFloatParam(req, "postPrice");
		String editor = this.getParam(req, "postEditor");
		
		// Validate the title
		try {
			this.validateTitle(title);
		}
		catch (FormException e) {
			this.setError("title", e.getMessage());
		}
		book.setTitle(title);
		
		// Serie is not mandatory, so it does not matter if it is null or empty
		book.setSerie(serie);
		
		// Validate the author
		try {
			this.validateAuthor(author);
		}
		catch (FormException e) {
			this.setError("author", e.getMessage());
		}
		book.setAuthor(author);
		
		// Validate the date
		try {
			this.validateDate(date);
		}
		catch (FormException e) {
			this.setError("date", e.getMessage());
		}
		book.setDate(date);
		
		// Validate the summary
		try {
			this.validateSummary(summary);
		}
		catch (FormException e) {
			this.setError("summary", e.getMessage());
		}
		book.setSummary(summary);
		
		// Validate the isbn
		try {
			this.validateIsbn(isbn);
		}
		catch (FormException e) {
			this.setError("isbn", e.getMessage());
		}
		book.setIsbn(isbn);
		
		// Validate the price
		try {
			this.validatePrice(price);
		}
		catch (FormException e) {
			this.setError("price", e.getMessage());
		}
		book.setPrice(price);
		
		// Validate the editor
		try {
			this.validateEditor(editor);
		}
		catch (FormException e) {
			this.setError("editor", e.getMessage());
		}
		book.setEditor(editor);
		
		// If this is a new book, there must be a file. Else, we need to know if there was a file uploaded
		if (add == true || (add == false && this.getPartParam(req, "postFile") != null)) {
			Part file = this.getPartParam(req, "postFile");

			if (file != null) {
				String filename = file.getSubmittedFileName();
				try {
					this.validateFile(filename);
					this.uploadFile(file, filename);
					book.setFile(filename);
				}
				catch (FormException e) {
					this.setError("file", e.getMessage());
				}
			}
			else
				this.setError("file", "Vous devez joindre une image valide pour la couverture.");
		}
		return (book);
	}
	
	/**
	 * Tries to upload the file
	 * @param file
	 * @param filename
	 * @return
	 */
	
	private void uploadFile(Part file, String filename) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		int buffsize = 4096;
        byte[] buffer = new byte[buffsize];
		int rsize = 0;
		
		// Tries to load input and output streams
		try {
			in = new BufferedInputStream(file.getInputStream(), buffsize);
			out = new BufferedOutputStream(new FileOutputStream(new File(this._path + filename)), buffsize);
			
			// While there is something to read from the input, write on the output
	        while ((rsize = in.read(buffer)) > 0) {
	        	out.write(buffer, 0, rsize);
	        }
		}
		catch (IOException e) {
			throw new FormException("Un problème est survenu lors de l'upload de l'image, merci de réessayer.");
		}
		finally {
			// If not null, close
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			}
			catch (IOException ignore) {
				// on ignore
			}
		}
	}
	
	/**
	 * Create a new book into the database if all fields were validated
	 * @param req
	 * @return Book
	 */
	
	public Book addBook(HttpServletRequest req) {
		Book book = this.validateFields(new Book(), req, true);
		
		// Create the entry into the database if there is no error
		if (this._errors.isEmpty()) {
			try {
				this._dao.createBook(book);
			}
			catch (DAOException e) {
				this.setError("db", e.getMessage());
			}
		}
		
		// Set the final result
		if (this._errors.isEmpty())
			this.setSuccess("Le livre a été créé avec succès.");				
		else
			this.setFail("Le livre n'a pu être créé.");
		return (book);
	}
	
	/**
	 * Update an existing book into the database if all fields were validated
	 * @param req
	 * @param id
	 * @return
	 */
	
	public Book updateBook(Book book, HttpServletRequest req, int id) {
		book = this.validateFields(book, req, false);
		
		// Create the entry into the database if there is no error
		if (this._errors.isEmpty()) {
			try {
				this._dao.updateBook(book);
			}
			catch (DAOException e) {
				this.setError("db", e.getMessage());
			}
		}
		
		// Set the final result
		if (this._errors.isEmpty())
			this.setSuccess("Le livre a été édité avec succès.");				
		else
			this.setFail("Le livre n'a pu être édité.");
		return (book);
	}
	
	/**
	 * Tries to retrieve a book from the database by its id
	 * @param id
	 * @return
	 */
	
	public Book getBookById(int id) {
		Book book = null;
		try {
			book = this._dao.getBookById(id);
			if (book == null)
				this.setFail("Ce livre n'existe pas dans la base.");
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
		}
		return (book);
	}
	
	/**
	 * Tries to retrieve the list of all books from the database
	 * @return
	 */
	
	public List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		try {
			books = this._dao.getBooks();
			if (books.isEmpty())
				this.setFail("Il n'y a aucun livre enregistré dans la base.");
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
		}
		return (books);
	}
	
	/**
	 * Tries to delete a book from the database
	 * @param id
	 */
	
	public void deleteBook(int id) {
		try {
			this._dao.deleteBook(id);
			this.setSuccess("Le livre a bien été supprimé.");
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
		}
	}
}
