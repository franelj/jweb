package com.jweb.beans;

/**
 * BEAN : storing the address of an user
 * @author Julie
 *
 */

public class Address {
	String _firstname = null;
	String _lastname = null;
	String _address = null;
	String _details = null;
	String _zipcode = null;
	String _city = null;
	Integer _idUser = null;
	
	public Address() {
		
	}
	
	public Address(String firstname, String lastname, String address, String details, String zipcode, String city) {
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setAddress(address);
		this.setDetails(details);
		this.setZipcode(zipcode);
		this.setCity(city);
	}
	
	/**
	 * Set user first name
	 * @param firstname
	 */
	
	public void setFirstname(String firstname) {
		this._firstname = firstname;
	}
	
	/**
	 * Get user first name
	 * @return String
	 */
	
	public String getFirstname() {
		return (this._firstname);
	}
	
	/**
	 * Set user last name
	 * @param lastname
	 */
	
	public void setLastname(String lastname) {
		this._lastname = lastname;
	}
	
	/**
	 * Get user last name
	 * @return String
	 */
	
	public String getLastname() {
		return (this._lastname);
	}
	
	/**
	 * Set user address
	 * @param address
	 */
	
	public void setAddress(String address) {
		this._address = address;
	}
	
	/**
	 * Get user address
	 * @return String
	 */
	
	public String getAddress() {
		return (this._address);
	}
	
	/**
	 * Set user address details if needed
	 * @param details
	 */
	
	public void setDetails(String details) {
		this._details = details;
	}
	
	/**
	 * Get user address details if exists
	 * @return String
	 */
	
	public String getDetails() {
		return (this._details);
	}
	
	/**
	 * Set user address zipcode
	 * @param zipcode
	 */
	
	public void setZipcode(String zipcode) {
		this._zipcode = zipcode;
	}
	
	/**
	 * Get user adresse zipcode
	 * @return String
	 */
	
	public String getZipcode() {
		return (this._zipcode);
	}
	
	/**
	 * Set user address city
	 * @param city
	 */
	
	public void setCity(String city) {
		this._city = city;
	}
	
	/**
	 * Get user address city
	 * @return
	 */
	
	public String getCity() {
		return (this._city);
	}
	
	/**
	 * Set the user id
	 * @param id
	 */

	public void setIdUser(int id) {
		this._idUser = id;
	}
	
	/**
	 * Get the user id
	 * @return
	 */
	
	public Integer getIdUser() {
		return (this._idUser);
	}
}