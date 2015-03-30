package com.jweb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jweb.beans.User;
import com.jweb.exceptions.DAOException;

/**
 * The user Data Access Object : create, read, update or delete an user. Extends the DAO abstract class.
 * @author Julie
 * @see DAO, DAOException
 *
 */

public class UserDAO extends DAO {

	public UserDAO(DAOFactory db) {
		super(db);
	}
	
	/**
	 * Retrieve a user from the database according to the username and the password passed as parameters. Throw a DBException if there
	 * is any error.
	 * @param username
	 * @param password
	 * @return User
	 * @throws DAOException
	 */
	
	public User getUser(User user) throws DAOException {
		try {
			this.connect();
			this.prepare("SELECT * FROM users WHERE login LIKE ? AND passwd LIKE ? ORDER BY login ASC");
			this._s.setString(1, user.getName());
			this._s.setString(2, user.getPasswd());
			this.select();
			if (this._r != null)
				user = new User(this._r.getInt("idusers"), this._r.getString("login"), this._r.getString("email"), this._r.getString("passwd"), 
						(this._r.getInt("isadmin") == 1 ? true : false), this._r.getTimestamp("created_at"));
			else
				throw new DAOException("Ce nom d'utilisateur n'existe pas ou le mot de passe donné ne correspond pas.");
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (getuser)");
		}
		finally {
			this.close();
		}
		return (user);
	}
	

	/**
	 * Retrieve a user from the database according to the username and the password passed as parameters. Throw a DBException if there
	 * is any error.
	 * @param username
	 * @param password
	 * @return User
	 * @throws DAOException
	 */
	
	public User getUserById(int id) throws DAOException {
		User user = null;
		try {
			this.connect();
			this.prepare("SELECT * FROM users WHERE idusers = ?");
			this._s.setInt(1, id);
			this.select();
			if (this._r != null)
				user = new User(this._r.getInt("idusers"), this._r.getString("login"), this._r.getString("email"), this._r.getString("passwd"), 
						(this._r.getInt("isadmin") == 1 ? true : false), this._r.getTimestamp("created_at"));
			else
				throw new DAOException("Ce nom d'utilisateur n'existe pas ou le mot de passe donné ne correspond pas.");
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (getuser)");
		}
		finally {
			this.close();
		}
		return (user);
	}
	
	/**
	 * Tries to create a user. Check if there is already a user having the specified username or the email address. Throws a DBException
	 * if there is any error.
	 * @param username
	 * @param email
	 * @param password
	 * @throws DAOException
	 */
	
	public void createUser(User user) throws DAOException {
		try {
			this.connect();
			
			// validate login
			this.prepare("SELECT * FROM users WHERE login LIKE ?");
			this._s.setString(1, user.getName());
			this.select();
			if (this._r != null)
				throw new DAOException("Ce nom d'utilisateur est déjà pris.");

			// validate email
			this.prepare("SELECT * FROM users WHERE email LIKE ?");
			this._s.setString(1, user.getEmail());
			this.select();
			if (this._r != null)
				throw new DAOException("Cette adresse email est déjà utilisée.");

			// create user
			this.prepare("INSERT INTO users (login, email, passwd) VALUES (?, ?, ?)");
			this._s.setString(1, user.getName());
			this._s.setString(2, user.getEmail());
			this._s.setString(3, user.getPasswd());
			this.insert();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (create user)");
		}
		finally {
			this.close();
		}
	}
	
	/**
	 * Update user
	 * @param user
	 * @throws DAOException
	 */
	
	public void updateUser(User user) throws DAOException {
		try {
			this.connect();
			this.prepare("SELECT * FROM users WHERE idusers != ? AND login NOT LIKE ? AND email LIKE ?");
			this._s.setInt(1, user.getId());
			this._s.setString(2, user.getName());
			this._s.setString(3, user.getEmail());
			this.select();
			if (this._r != null)
				throw new DAOException("Cette adresse email est déjà utilisée.");
			this.prepare("UPDATE users SET email = ?, passwd = ? WHERE idusers = ?");
			this._s.setString(1, user.getEmail());
			this._s.setString(2, user.getPasswd());
			this._s.setInt(3, user.getId());
			this.update();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (updateuser)");
		}
		finally {
			this.close();
		}
	}
	
	/**
	 * Get a list of all users
	 * @return List<User>
	 * @throws DAOException
	 */
	
	public List<User> getUsers() throws DAOException {
		List<User> users = new ArrayList<User>();
		try {
			this.connect();
			this.prepare("SELECT * FROM users ORDER BY login ASC");
			this.select();
			if (this._r != null) {
				users.add(new User(this._r.getInt("idusers"), this._r.getString("login"), this._r.getString("email"), this._r.getString("passwd"), 
						this._r.getBoolean("isadmin"), this._r.getTimestamp("created_at")));
				while (this._r.next()) {
					users.add(new User(this._r.getInt("idusers"), this._r.getString("login"), this._r.getString("email"), this._r.getString("passwd"), 
							this._r.getBoolean("isadmin"), this._r.getTimestamp("created_at")));
				}
			}
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (getusers) => " + e.getMessage());
		}
		finally {
			this.close();
		}
		return (users);
	}
	
	/**
	 * Delete an user from the database
	 * @param id
	 * @throws DAOException
	 */
	
	public void deleteUser(int id) throws DAOException {
		try {
			this.connect();
			this.prepare("DELETE FROM users WHERE idusers = ?");
			this._s.setInt(1, id);
			this.delete();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (deleteuser)");
		}
		finally {
			this.close();
		}
	}
	
	/**
	 * Check if a user is an admin
	 * @param user
	 * @return boolean
	 * @throws DAOException
	 */

	public boolean isAdmin(User user) throws DAOException {
		boolean ret = false;
		if (user == null || user.getName().isEmpty() || user.getPasswd().isEmpty())
			return (ret);
		try {
			this.connect();
			this.prepare("SELECT isadmin FROM users WHERE idusers = ? AND login LIKE ? AND passwd LIKE ?");
			this._s.setInt(1, user.getId());
			this._s.setString(2, user.getName());
			this._s.setString(3, user.getPasswd());
			this.select();
			if (this._r != null) {
				if (this._r.getInt("isadmin") == 1)
					ret = true;
			}
		}
		catch(DAOException e) {
			throw e;
		}
		catch(SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (isAdmin 2)");
		}
		finally {
			this.close();
		}
		return (ret);
	}
}
