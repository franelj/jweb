package com.jweb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jweb.beans.Suscriber;
import com.jweb.exceptions.DAOException;

/**
 * The newsletter Data Access Object : create, read, update or delete a newsletter. Extends the DAO abstract class.
 * @author Julie
 * @see DAO, DAOException
 *
 */

public class NewsletterDAO extends DAO {
	public NewsletterDAO(DAOFactory db) {
		super(db);
	}
	
	/**
	 * Tries to add an email to the database. Check if the address doesn't already exist into the database, otherwise
	 * it throws a DBException.
	 * @param email
	 * @param choice
	 * @throws DAOException
	 */
	
	public void addSuscriber(String email) throws DAOException {
		try {
			this.connect();
			this.prepare("SELECT * FROM newsletter WHERE email LIKE ?");
			this._s.setString(1, email);
			this.select();
			if (this._r != null)
				throw new DAOException("Cette adresse email est déjà utilisée.");
			this.prepare("INSERT INTO newsletter SET email = ?");
			this._s.setString(1, email);
			this.insert();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (handleuserfornewsletter");
		}
		finally {
			this.close();
		}
	}
	
	/**
	 * Tries to delete an email from the database. Check if the address really exists into the database, otherwise
	 * it throws a DBException
	 * @param email
	 * @throws DAOException
	 */

	public void deleteSuscriber(String email) throws DAOException {
		try {
			this.connect();
			this.prepare("SELECT * FROM newsletter WHERE email LIKE ?");
			this._s.setString(1, email);
			this.select();
			if (this._r == null)
				throw new DAOException("Cette adresse email n'est pas inscrite à la newsletter.");
			this.prepare("DELETE FROM newsletter WHERE email LIKE ?");
			this._s.setString(1, email);
			this.delete();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (handleuserfornewsletter");
		}
		finally {
			this.close();
		}
	}
	
	/**
	 * Tries to delete an email from the database by its id
	 * @param id
	 * @throws DAOException
	 */
	
	public void deleteSuscriber(int id) throws DAOException {
		try {
			this.connect();
			this.prepare("DELETE FROM newsletter WHERE id = ?");
			this._s.setInt(1, id);
			this.delete();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (deletesuscriber)");
		}
		finally {
			this.close();
		}
	}
	
	/**
	 * Get a list of all emails stored into the database for the newsletter
	 * @return List<String>
	 * @throws DAOException
	 */
	
	public List<Suscriber> getNewsletterRecipients() throws DAOException {
		List<Suscriber> suscriber = new ArrayList<Suscriber>();
		try {
			this.connect();
			this.prepare("SELECT * FROM newsletter");
			this.select();
			if (this._r != null) {
				suscriber.add(new Suscriber(this._r.getInt("id"), this._r.getString("email")));
				while (this._r.next())
					suscriber.add(new Suscriber(this._r.getInt("id"), this._r.getString("email")));
			}
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (getnewsletterrecipients)");
		}
		finally {
			this.close();
		}
		return (suscriber);
	}
	
	/**
	 * Retrieve the value corresponding to the option passed as parameter from the database
	 * @param option
	 * @return String
	 * @throws DAOException
	 */
	
	public String getConfigValue(String option) throws DAOException {
		String ret = null;
		try {
			this.connect();
			this.prepare("SELECT * FROM config WHERE opt LIKE ?");
			this._s.setString(1, option);
			this.select();
			if (this._r != null)
				ret = this._r.getString("val");
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (getconfigvalue)");
		}
		finally {
			this.close();
		}
		return (ret);
	}
}
