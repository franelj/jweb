package com.jweb.models;

import javax.servlet.http.HttpServletRequest;

import com.jweb.beans.Address;
import com.jweb.dao.AddressDAO;
import com.jweb.dao.DAOFactory;
import com.jweb.exceptions.DAOException;
import com.jweb.exceptions.FormException;

/**
 * MODEL : validate and handle data for an address. Extends the abstract class Handler.
 * @author Julie
 * @see Handler
 *
 */

public class AddressHandler extends Handler {
	AddressDAO _dao = null;
	
	public AddressHandler(DAOFactory db) {
		this._dao = db.getAdressDao();
	}
	
	/**
	 * Tries to validate the firstname
	 * @param firstname
	 * @throws FormException
	 */
	
	private void validateFirstname(String firstname) throws FormException {
		if (firstname == null || firstname.isEmpty()) {
			throw new FormException("Vous devez renseigner un nom de famille.");
		}
	}
	
	/**
	 * Tries to validate the lastname
	 * @param lastname
	 * @throws FormException
	 */
	
	private void validateLastname(String lastname) throws FormException {
		if (lastname == null || lastname.isEmpty()) {
			throw new FormException("Vous devez renseigner un prénom.");
		}
	}
	
	/**
	 * Tries to validate the address
	 * @param address
	 * @throws FormException
	 */
	
	private void validateAddress(String address) throws FormException {
		if (address == null || address.isEmpty()) {
			throw new FormException("Vous devez renseigner une adresse.");
		}
	}
	
	/**
	 * Tries to validate the zipcode
	 * @param zipcode
	 * @throws FormException
	 */
	
	private void validateZipcode(String zipcode) throws FormException {
		if (zipcode == null || zipcode.isEmpty() || !zipcode.matches("^[0-9]{5}$")) {
			throw new FormException("Vous devez renseigner un code postal valide.");
		}
	}
	
	/**
	 * Tries to validate the city
	 * @param city
	 * @throws FormException
	 */
	
	private void validateCity(String city) throws FormException {
		if (city == null || city.isEmpty()) {
			throw new FormException("Vous devez renseigner un nom de ville.");
		}
	}
	
	/**
	 * Tries to validate all required fields
	 * @param addr
	 * @param req
	 * @return Address
	 */
	
	private Address validateFields(Address addr, HttpServletRequest req) {
		String firstname = this.getParam(req, "addrFirstname");
		String lastname = this.getParam(req, "addrLastname");
		String address = this.getParam(req, "addrAddress");
		String details = this.getParam(req, "addrDetails");
		String zipcode = this.getParam(req, "addrZipcode");
		String city = this.getParam(req, "addrCity");

		// Validate the firstname
		try {
			this.validateFirstname(firstname);
		}
		catch (FormException e) {
			this.setError("firstname", e.getMessage());
		}
		addr.setFirstname(firstname);

		// Validate the lastname
		try {
			this.validateLastname(lastname);
		}
		catch (FormException e) {
			this.setError("lastname", e.getMessage());
		}
		addr.setLastname(lastname);

		// Validate the address
		try {
			this.validateAddress(address);
		}
		catch (FormException e) {
			this.setError("address", e.getMessage());
		}
		addr.setAddress(address);

		// Validate the details - not mandatory
		if (details != null)
			addr.setDetails(details);
		else
			addr.setDetails("");

		// Validate the zipcode
		try {
			this.validateZipcode(zipcode);
		}
		catch (FormException e) {
			this.setError("zipcode", e.getMessage());
		}
		addr.setZipcode(zipcode);

		// Validate the city
		try {
			this.validateCity(city);
		}
		catch (FormException e) {
			this.setError("city", e.getMessage());
		}
		addr.setCity(city);
		return (addr);
	}
	
	/**
	 * Tries to update an address entry after validating all required fields
	 * @param addr
	 * @param req
	 * @return
	 */
	
	public Address updateAddress(Address addr, HttpServletRequest req) {
		boolean create = false;
		if (addr.getLastname() == null || addr.getLastname().isEmpty()) {
			create = true;
		}
		this._errors.clear();
		addr = this.validateFields(addr, req);
		
		// Tries to create the entry
		if (this._errors.isEmpty()) {
			try {
				if (create == false)
					this._dao.updateAddress(addr);
				else
					this._dao.createAddress(addr);
			}
			catch (DAOException e) {
				this.setError("db", e.getMessage());
			}
		}
		
		// Sets final result
		if (this._errors.isEmpty())
			this.setSuccess("Votre adresse a été enregistrée avec succès.");
		else
			this.setFail("Votre adresse n'a pu être enregistrée.");
		return (addr);
	}
	
	public Address getAddress(int id) {
		Address addr = new Address();
		try {
			addr = this._dao.getAddress(id);
			if (addr == null || addr.getLastname() == null || addr.getLastname().isEmpty())
				this.setError("db", "Vous n'avez pas renseigné d'adresse.");
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
		}
		return (addr);
	}
}
