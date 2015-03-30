package com.jweb.beans;

import java.sql.Timestamp;

/**
 * BEAN : storing user informations
 * @author Julie
 *
 */

public class User {
	private Integer _id = null;
	private String _name = null;
	private String _email = null;
	private String _passwd = null;
	private Boolean _isAdmin = null;
	private Timestamp _createdAt = null;
/*	private String _error = null;
*/
	public User() {

	}
	
	public User(int id, String name, String email, String passwd, Boolean isAdmin, Timestamp createdAt) {
		this.setId(id);
		this.setName(name);
		this.setEmail(email);
		this.setPasswd(passwd);
		this.setIsAdmin(isAdmin);
		this.setCreationDate(createdAt);
	}

	/**
	 * Set user ID
	 * @param id
	 */

	public void setId(int id) {
		this._id = id;
	}

	/**
	 * Get user ID
	 * @return int
	 */

	public int getId() {
		return (this._id);
	}

	/**
	 * Set user name
	 * @param name
	 */

	public void setName(String name) {
		this._name = name;
	}

	/**
	 * get user name
	 * @return String
	 */

	public String getName() {
		return (this._name);
	}

	/**
	 * Set user email
	 * @param email
	 */

	public void setEmail(String email) {
		this._email = email;
	}

	/**
	 * Get user email
	 * @return String
	 */

	public String getEmail() {
		return (this._email);
	}

	/**
	 * Set user creation date
	 * @param createdAt
	 */

	public void setCreationDate(Timestamp createdAt) {
		this._createdAt = createdAt;
	}

	/**
	 * Get user creation date
	 * @return Timestamp
	 */

	public Timestamp getCreationDate() {
		return (this._createdAt);
	}

	/**
	 * Set the error message if needed
	 * @param error
	 */

/*	public void setError(String error) {
		this._error = error;
	}

	*//**
	 * Get the error message if exists
	 * @return String
	 *//*

	public String getError() {
		return (this._error);
	}
*/	
	/**
	 * Set user password
	 * @param passwd
	 */
	
	public void setPasswd(String passwd) {
		this._passwd = passwd;
	}
	
	/**
	 * Get user password
	 * @return
	 */
	
	public String getPasswd() {
		return (this._passwd);
	}
	
	/**
	 * Set user role - if admin or not
	 * @param isAdmin
	 */
	
	public void setIsAdmin(boolean isAdmin) {
		this._isAdmin = isAdmin;
	}
	
	/**
	 * Get user role - if admin or not
	 * @return
	 */
	
	public Boolean getIsAdmin() {
		return (this._isAdmin);
	}
}
