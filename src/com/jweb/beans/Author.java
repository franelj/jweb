package com.jweb.beans;

/**
 * BEAN : storing author informations
 * @author Julie
 *
 */

public class Author {
	Integer _id = null;
	String _lastname = null;
	String _firstname = null;
	String _resume = null;
	
	public Author() {
		this.setId(-1);
	}
	
	public Author(int id, String firstname, String lastname, String resume) {
		this.setId(id);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setResume(resume);
	}
	
	/**
	 * Get author ID
	 * @return Int
	 */
	
	public int getId() {
		return (this._id);
	}
	
	/**
	 * Set author ID
	 * @param id
	 */
	
	public void setId(int id) {
		this._id = id;
	}
	
	/**
	 * Get author last name
	 * @return String
	 */
	
	public String getLastname() {
		return (this._lastname);
	}
	
	/**
	 * Set author last name
	 * @param lastname
	 */
	
	public void setLastname(String lastname) {
		this._lastname = lastname;
	}
	
	/**
	 * Get author first name
	 * @return String
	 */
	
	public String getFirstname() {
		return (this._firstname);
	}
	
	/**
	 * Set author first name
	 * @param firstname
	 */
	
	public void setFirstname(String firstname) {
		this._firstname = firstname;
	}
	
	/**
	 * Get author resume
	 * @return String
	 */
	
	public String getResume() {
		return (this._resume);
	}
	
	/**
	 * Set author resume
	 * @param resume
	 */
	
	public void setResume(String resume) {
		this._resume = resume;
	}
	
	
}
