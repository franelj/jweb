package com.jweb.dao;

import java.sql.SQLException;

import com.jweb.beans.Address;
import com.jweb.exceptions.DAOException;

/**
 * The address Data Access Object : create, read, update or delete an address. Extends the DAO abstract class.
 * @author Julie
 * @see DAO, DAOException
 *
 */

public class AddressDAO extends DAO {

	public AddressDAO(DAOFactory db) {
		super(db);
	}
	

	
	/**
	 * Retrieve the address of a user from the database
	 * @param id
	 * @return Address
	 * @throws DAOException
	 */
	
	public Address getAddress(int id) throws DAOException {
		Address addr = new Address();
		try {
			this.connect();
			this.prepare("SELECT * FROM address WHERE iduser = ?");
			this._s.setInt(1, id);
			this.select();
			if (this._r != null)
				addr = new Address(this._r.getString("firstname"), this._r.getString("lastname"), this._r.getString("address"), this._r.getString("details"), 
						this._r.getString("zipcode"), this._r.getString("city"));
			addr.setIdUser(id);
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (getaddress 2)");
		}
		finally {
			this.close();
		}
		return (addr);
	}
	

	
	/**
	 * Update an address entry for a user into the database
	 * @param addr
	 * @param id
	 * @throws DAOException
	 */
	
	public void createAddress(Address addr) throws DAOException {
		try {
			this.connect();
			this.prepare("INSERT INTO address (lastname, firstname, address, details, zipcode, city, iduser) VALUES (?, ?, ?, ?, ?, ?, ?)");
			this._s.setString(1, addr.getLastname());
			this._s.setString(2, addr.getFirstname());
			this._s.setString(3, addr.getAddress());
			this._s.setString(4, addr.getDetails());
			this._s.setString(5, addr.getZipcode());
			this._s.setString(6, addr.getCity());
			this._s.setInt(7, addr.getIdUser());
			this.insert();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (updateaddress)");
		}
		finally {
			this.close();
		}
	}
	
	/**
	 * Update an address entry for a user into the database
	 * @param addr
	 * @param id
	 * @throws DAOException
	 */
	
	public void updateAddress(Address addr) throws DAOException {
		try {
			this.connect();
			this.prepare("UPDATE address SET lastname = ?, firstname = ?, address = ?, details = ?, zipcode = ?, city = ? WHERE iduser = ?");
			this._s.setString(1, addr.getLastname());
			this._s.setString(2, addr.getFirstname());
			this._s.setString(3, addr.getAddress());
			this._s.setString(4, addr.getDetails());
			this._s.setString(5, addr.getZipcode());
			this._s.setString(6, addr.getCity());
			this._s.setInt(7, addr.getIdUser());
			this.update();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (updateaddress)");
		}
		finally {
			this.close();
		}
	}
}
