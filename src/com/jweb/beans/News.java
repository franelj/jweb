package com.jweb.beans;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * BEAN : storing a news informations
 * @author Julie
 *
 */

public class News {
	private Integer _id = null;
	private String _title = null;
	private String _content = null;
	private String _createdAt = null;
	private String _updatedAt = null;
	private Integer _author = null;
	private String _authorName = null;
	
	public News() {
		this.setId(-1);
	}
	
	public News(int id, String title, String content, Timestamp createdAt, Timestamp updatedAt, int author, String authorName) {
		this.setId(id);
		this.setTitle(title);
		this.setContent(content);
		this.setCreatedAt(createdAt);
		this.setUpdatedAt(updatedAt);
		this.setAuthor(author);
		this.setAuthorName(authorName);
	}
	
	/**
	 * Set the news id
	 * @param id
	 */
	
	public void setId(int id) {
		this._id = id;
	}
	
	/**
	 * Get the news id
	 * @return int
	 */
	
	public int getId() {
		return (this._id);
	}
	
	/**
	 * Set the news' title
	 * @param title
	 */
	
	public void setTitle(String title) {
		this._title = title;
	}
	
	/**
	 * Get the news' title
	 * @return String
	 */
	
	public String getTitle() {
		return (this._title);
	}
	
	/**
	 * Set the news' content
	 * @param content
	 */
	
	public void setContent(String content) {
		this._content = content;
	}
	
	/**
	 * Get the news' content
	 * @return String
	 */
	
	public String getContent() {
		return (this._content);
	}
	
	/**
	 * Set the news' creation date
	 * @param createdAt
	 */
	
	public void setCreatedAt(Timestamp createdAt) {
		this._createdAt = new SimpleDateFormat("dd'/'MM'/'yyyy' à 'HH'h'mm").format(createdAt);;
	}
	
	/**
	 * Get the news' creation date
	 * @return Timestamp
	 */
	
	public String getCreatedAt() {
		return (this._createdAt);
	}
	
	/**
	 * Set the news' update date
	 * @param updatedAt
	 */
	
	public void setUpdatedAt(Timestamp updatedAt) {
		this._updatedAt = new SimpleDateFormat("dd'/'MM'/'yyyy' à 'HH'h'mm").format(updatedAt);;
	}
	
	/**
	 * Get the news' update date
	 * @return Timestamp
	 */
	
	public String getUpdatedAt() {
		return (this._updatedAt);
	}
	
	/**
	 * Set the news' author id
	 * @param author
	 */
	
	public void setAuthor(int author) {
		this._author = author;
	}
	
	/**
	 * Get the news' author id
	 * @return Integer
	 */
	
	public Integer getAuthor() {
		return (this._author);
	}
	
	/**
	 * Set the news' author name
	 * @param author
	 */
	
	public void setAuthorName(String authorName) {
		this._authorName = authorName;
	}
	
	/**
	 * Get the news' author name
	 * @return String
	 */
	
	public String getAuthorName() {
		return (this._authorName);
	}
}
