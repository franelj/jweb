package com.jweb.beans;

/**
 * BEAN : storing a suscriber informations
 * @author Julie
 *
 */

public class Suscriber {
	Integer _id = null;
	String _email = null;
	Boolean _choice = null;
	
	public Suscriber() {
		
	}
	
	public Suscriber(int id, String email) {
		this.setId(id);
		this.setEmail(email);
	}
	
	/**
	 * Set the suscriber id
	 * @param id
	 */
	
	public void setId(int id) {
		this._id = id;
	}
	
	/**
	 * Get the suscriber id
	 * @return Integer
	 */
	
	public Integer getId() {
		return (this._id);
	}
	
	/**
	 * Set the suscriber email
	 * @param email
	 */
	
	public void setEmail(String email) {
		this._email = email;
	}
	
	/**
	 * Get the suscriber email
	 * @return String
	 */
	
	public String getEmail() {
		return (this._email);
	}
	
	/**
	 * Set the suscriber choice : suscribe or unsuscribe
	 * @param choice
	 */
	
	public void setChoice(boolean choice) {
		this._choice = choice;
	}
	
	/**
	 * Get the suscriber choice : suscribe of unsuscribe
	 * @return Boolean
	 */
	
	public Boolean getChoice() {
		return (this._choice);
	}
}
