package com.jweb.beans;

/**
 * BEAN : storing book informations
 * @author Julie
 *
 */

public class Book {
	Integer _id = null;
	String _title = null;
	String _serie = null;
	Integer _author = null;
	String _date = null;
	String _summary = null;
	String _isbn = null;
	Float _price = null;
	String _editor = null;
	String _authorName = null;
	String _file = null;
	
	public Book() {
		this.setId(-1);
	}
	
	public Book(int id, String title, String serie, int author, String date, String summary, String isbn, Float price, String editor, String authorName, String file) {
		this.setId(id);
		this.setTitle(title);
		this.setSerie(serie);
		this.setAuthor(author);
		this.setDate(date);
		this.setSummary(summary);
		this.setIsbn(isbn);
		this.setPrice(price);
		this.setEditor(editor);
		this.setAuthorName(authorName);
		this.setFile(file);
	}

	/**
	 * Get book id
	 * @return Int
	 */
	
	public int getId() {
		return (this._id);
	}
	
	/**
	 * Set book id
	 * @param id
	 */
	
	public void setId(int id) {
		this._id = id;
	}
	
	/**
	 * Get book title
	 * @return String
	 */
	
	public String getTitle() {
		return (this._title);
	}
	
	/**
	 * Set book title
	 * @param title
	 */
	
	public void setTitle(String title) {
		this._title = title;
	}
	
	/**
	 * Get book serie
	 * @return String
	 */
	
	public String getSerie() {
		return (this._serie);
	}
	
	/**
	 * Set book serie
	 * @param serie
	 */
	
	public void setSerie(String serie) {
		this._serie = serie;
	}
	
	/**
	 * Get book author
	 * @return int
	 */
	
	public Integer getAuthor() {
		return (this._author);
	}
	
	/**
	 * Set book author
	 * @param author
	 */
	
	public void setAuthor(int author) {
		this._author = author;
	}
	
	/**
	 * Get book release date
	 * @return String
	 */
	
	public String getDate() {
		return (this._date);
	}
	
	/**
	 * Set book release date
	 * @param date
	 */
	
	public void setDate(String date) {
		this._date = date;
	}
	
	/**
	 * Get book summary
	 * @return String
	 */
	
	public String getSummary() {
		return (this._summary);
	}
	
	/**
	 * Set book summary
	 * @param summary
	 */
	
	public void setSummary(String summary) {
		this._summary = summary;
	}
	
	/**
	 * Get book ISBN
	 * @return String
	 */
	
	public String getIsbn() {
		return (this._isbn);
	}
	
	/**
	 * Set book ISBN
	 * @param isbn
	 */
	
	public void setIsbn(String isbn) {
		this._isbn = isbn;
	}
	
	/**
	 * Get book price
	 * @return Float
	 */
	
	public Float getPrice() {
		return (this._price);
	}
	
	/**
	 * Set book price
	 * @param price
	 */
	
	public void setPrice(Float price) {
		this._price = price;
	}
	
	/**
	 * Get book editor
	 * @return String
	 */
	
	public String getEditor() {
		return (this._editor);
	}
	
	/**
	 * Set book editor
	 * @param editor
	 */
	
	public void setEditor(String editor) {
		this._editor = editor;
	}
	
	/**
	 * Get book author name
	 * @return String
	 */
	
	public String getAuthorName() {
		return (this._authorName);
	}
	
	/**
	 * Set book author name
	 * @param authorName
	 */
	
	public void setAuthorName(String authorName) {
		this._authorName = authorName;
	}
	
	/**
	 * Get the book cover path
	 * @return String
	 */
	
	public String getFile() {
		return (this._file);
	}
	
	/**
	 * Set the book cover path
	 * @param file
	 */
	
	public void setFile(String file) {
		if (!file.isEmpty())
			this._file = file;
	}
}
