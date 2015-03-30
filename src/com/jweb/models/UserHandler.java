package com.jweb.models;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jweb.beans.User;
import com.jweb.dao.DAOFactory;
import com.jweb.dao.UserDAO;
import com.jweb.exceptions.DAOException;
import com.jweb.exceptions.FormException;

/**
 * MODEL : validate and handle data for an user. Extends the abstract class Handler.
 * @author Julie
 * @see Handler
 *
 */

public class UserHandler extends Handler {
	UserDAO _dao = null;
	
	public UserHandler(DAOFactory db) {
		this._dao = db.getUserDao();
	}
	
	/**
	 * Tries to validate the login
	 * @param login
	 * @throws FormException
	 */
	
	private void validateLogin(String login) throws FormException {
		if (login == null || login.isEmpty())
			throw new FormException("Vous devez renseigner un login valide.");
	}
	
	/**
	 * Tries to validate the email
	 * @param email
	 * @throws FormException
	 */
	
	private void validateEmail(String email) throws FormException {
		if (email == null || email.isEmpty() || !email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
			throw new FormException("Vous devez renseigner un email valide.");
	}
	
	/**
	 * Tries to validate the password
	 * @param passwd
	 * @throws FormException
	 */
	
	private void validatePasswd(String passwd) throws FormException {
		if (passwd == null || passwd.isEmpty())
			throw new FormException("Vous devez renseigner un mot de passe.");
	}
	
	/**
	 * Tries to validate confirmation password
	 * @param passwd
	 * @param confPasswd
	 * @throws FormException
	 */
	
	private void validateConfPasswd(String passwd, String confPasswd) throws FormException {
		if (passwd == null || passwd.isEmpty() || confPasswd == null || confPasswd.isEmpty() || !passwd.equals(confPasswd))
			throw new FormException("Les deux mots de passe ne sont pas identiques.");
	}
	
	/**
	 * Tries to validate all required fields to log in
	 * @param user
	 * @param req
	 * @return
	 */
	
	private User validateFieldsConnexion(User user, HttpServletRequest req) {
		String login = this.getParam(req, "logId");
		String passwd = this.getParam(req, "logPwd");
		
		// Tries to validate the login
		try {
			this.validateLogin(login);
		}
		catch (FormException e) {
			this.setError("logId", e.getMessage());
		}
		user.setName(login);
		
		// Tries to validate the pwd
		try {
			this.validatePasswd(passwd);
		}
		catch (FormException e) {
			this.setError("logPwd", e.getMessage());
		}
		user.setPasswd(passwd);
		return (user);
	}
	
	/**
	 * Tries to validate all required fields to create account
	 * @param user
	 * @param req
	 * @return
	 */
	
	private User validateFieldsCreation(User user, HttpServletRequest req) {
		String login = this.getParam(req, "createId");
		String email = this.getParam(req, "createEmail");
		String passwd = this.getParam(req, "createPwd");
		String confPasswd = this.getParam(req, "createConfPwd");
		
		// Tries to validate the login
		try {
			this.validateLogin(login);
		}
		catch (FormException e) {
			this.setError("createId", e.getMessage());
		}
		user.setName(login);
		
		// Tries to validate the email
		try {
			this.validateEmail(email);
		}
		catch (FormException e) {
			this.setError("createEmail", e.getMessage());
		}
		user.setEmail(email);
		
		// Tries to validate the pwd
		try {
			this.validatePasswd(passwd);
		}
		catch (FormException e) {
			this.setError("createPwd", e.getMessage());
		}
		user.setPasswd(passwd);

		// Tries to validate the conf pwd
		try {
			this.validateConfPasswd(passwd, confPasswd);
		}
		catch (FormException e) {
			this.setError("createConfPwd", e.getMessage());
		}
		return (user);
	}
	
	/**
	 * Create an user
	 * @param req
	 * @return User
	 */
	
	public User addUser(HttpServletRequest req) {
		User user = this.validateFieldsCreation(new User(), req);
		
		// Tries to create the user entry
		if (this._errors.isEmpty()) {
			try {
				this._dao.createUser(user);
			}
			catch (DAOException e) {
				this.setError("db", e.getMessage());
			}
		}
		
		// Sets the result to display
		if (this._errors.isEmpty()) {
			this.setSuccess("Votre compte a bien été créé, vous pouvez désormais vous connecter les identifiants que vous avez renseigné.");
		}
		else
			this.setFail("Votre compte n'a pu être créé.");
		return (user);
	}
	
	/**
	 * Authenticate an user
	 * @param req
	 * @return User
	 */
	
	public User authenticateUser(HttpServletRequest req) {
		User user = this.validateFieldsConnexion(new User(), req);
		
		// Tries to login
		if (this._errors.isEmpty()) {
			try {
				user = this._dao.getUser(user);
			}
			catch (DAOException e) {
				this.setError("db", e.getMessage());
			}
		}
		return (user);
	}
	
	/**
	 * Get a list of all users
	 * @return List<User>
	 */
	
	public List<User> getUsers() {
		List<User> users = null;
		try {
			users = this._dao.getUsers();
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
		}
		return (users);
	}
	
	/**
	 * Tries to delete an user
	 * @param id
	 */
	
	public void deleteUser(int id) {
		try {
			this._dao.deleteUser(id);
			this.setSuccess("L'utilisateur a été supprimé avec succès.");
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
			this.setFail("L'utilisateur n'a pu être supprimé.");
		}
	}
	
	/**
	 * Tries to validate all required fields for an update
	 * @param user
	 * @param req
	 * @return
	 */
	
	private User validateFieldsUpdate(User user, HttpServletRequest req) {
		String email = this.getParam(req, "editEmail");
		String passwd = this.getParam(req, "editPwd");
		String confPasswd = this.getParam(req, "editConfPwd");
		
		user.setName(user.getName());
		
		// Tries to validate the email
		try {
			this.validateEmail(email);
		}
		catch (FormException e) {
			this.setError("email", e.getMessage());
		}
		user.setEmail(email);
		
		// Tries to validate the pwd if filled in
		if (passwd != null || confPasswd != null) {
			try {
				this.validatePasswd(passwd);
			}
			catch (FormException e) {
				this.setError("pwd", e.getMessage());
			}
			user.setPasswd(passwd);

			// Tries to validate the conf pwd
			try {
				this.validateConfPasswd(passwd, confPasswd);
			}
			catch (FormException e) {
				this.setError("confPwd", e.getMessage());
			}
		}
		return (user);
	}
	
	/**
	 * Get an user by his id
	 * @param id
	 * @return
	 */
	
	public User getUserById(int id) {
		User user = null;
		try {
			user = this._dao.getUserById(id);
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
		}
		return (user);
	}
	
	/**
	 * Tries to update an user
	 * @param req
	 * @return User
	 */
	
	public User updateUser(HttpServletRequest req) {
		User save = (User) req.getSession().getAttribute("user");
		User user = this.getUserById(save.getId());
		user = this.validateFieldsUpdate(user, req);
		
		// Tries to create the user entry
		if (this._errors.isEmpty()) {
			try {
				this._dao.updateUser(user);
			}
			catch (DAOException e) {
				this.setError("db", e.getMessage());
			}
		}
		
		// Sets the result to display
		if (this._errors.isEmpty()) {
			this.setSuccess("Votre profil a bien été édité.");
		}
		else {
			user = save;
			this.setFail("Votre profil n'a pu être édité.");
		}
		
		return (user);
	}

}
