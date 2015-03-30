package com.jweb.beans;

import java.sql.Timestamp;

/**
 * BEAN : storing comment informations
 * @author Julie
 *
 */

public class Comment {
	Integer _id = null;
	String _content = null;
	Timestamp _date = null;
	Integer _author = null;
	String _authorName = null;
	Integer _idBook = null;
	
	public Comment() {
		
	}
	
	public Comment(int id, String content, Timestamp date, String authorName, int book, int author) {
		this.setId(id);
		this.setContent(content);
		this.setDate(date);
		this.setAuthorName(authorName);
		this.setIdBook(book);
		this.setAuthor(author);
	}
	
	/**
	 * Set the comment id
	 * @param id
	 */
	
	public void setId(int id) {
		this._id = id;
	}
	
	/**
	 * Get the comment id
	 * @return
	 */
	
	public Integer getId() {
		return (this._id);
	}
	
	/**
	 * Set comment content
	 * @param content
	 */
	
	public void setContent(String content) {
		this._content = content;
	}
	
	/**
	 * Get comment content
	 * @return String
	 */
	
	public String getContent() {
		return (this._content);
	}
	
	/**
	 * Set comment date
	 * @param date
	 */
	
	public void setDate(Timestamp date) {
		this._date = date;
	}
	
	/**
	 * Get comment date
	 * @return Timestamp
	 */
	
	public Timestamp getDate() {
		return (this._date);
	}
	
	/**
	 * Set comment author name
	 * @param author
	 */
	
	public void setAuthorName(String authorName) {
		this._authorName = authorName;
	}
	
	/**
	 * Get comment author name
	 * @return String
	 */
	
	public String getAuthorName() {
		return (this._authorName);
	}
	
	/**
	 * Set the book id
	 * @param id
	 */
	
	public void setIdBook(int id) {
		this._idBook = id;
	}
	
	/**
	 * Get the book id
	 * @return Integer
	 */
	
	public Integer getIdBook() {
		return (this._idBook);
	}
	
	/**
	 * Set the author id
	 * @param id
	 */
	
	public void setAuthor(int id) {
		this._author = id;
	}
	
	/**
	 * Get the author id
	 * @return Integer
	 */
	
	public Integer getAuthor() {
		return (this._author);
	}
}
