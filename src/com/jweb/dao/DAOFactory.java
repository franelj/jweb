package com.jweb.dao;

import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.jweb.exceptions.DAOException;


/**
 * The DAO Factory : returns a specific DAO for each table
 * @author Julie
 * @see DAOException, DAO
 *
 */

public class DAOFactory {
	public BoneCP _cp = null;
	
	public DAOFactory(BoneCP connexionPool) {
		this._cp = connexionPool;
	}
	
	
	/**
	 * Static function returning an instance of a DB object. Configuring the driver and the connection pool.
	 * @return DAOFactory
	 * @throws DAOException
	 */
	
	public static DAOFactory getInstance() throws DAOException {
		String _url = "jdbc:mysql://localhost:3306/jweb";
		String _login = "julie";
		String _pwd = "root";
		BoneCP connectionPool = null;
		
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new DAOException("Cannot find JDBC Driver");
		}

		try {
			BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(_url);
            config.setUsername(_login);
            config.setPassword(_pwd);
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(2);
            connectionPool = new BoneCP(config);
        } catch (SQLException e) {
			throw new DAOException("Cannot configure BoneCP Pool : " + e.toString());
        }
        DAOFactory instance = new DAOFactory(connectionPool);
        return (instance);
	}
	
	/**
	 * Shutdown the connection pool
	 */
	
	public void shutdownPool() {
		this._cp.shutdown();
	}
	
	/**
	 * Get the author DAO
	 * @return AuthorDAO
	 */
	
	public AuthorDAO getAuthorDao() {
		return (new AuthorDAO(this));
	}
	
	/**
	 * Get the book DAO
	 * @return BookDAO
	 */
	
	public BookDAO getBookDao() {
		return (new BookDAO(this));
	}
	
	/**
	 * Get the news DAO
	 * @return NewsDAO
	 */
	
	public NewsDAO getNewsDao() {
		return (new NewsDAO(this));
	}
	
	/**
	 * Get the newsletter DAO
	 * @return NewsletterDAO
	 */
	
	public NewsletterDAO getNewsletterDao() {
		return (new NewsletterDAO(this));
	}
	
	/**
	 * Get the comment DAO
	 * @return CommentDAO
	 */
	
	public CommentDAO getCommentDao() {
		return (new CommentDAO(this));
	}
	
	/**
	 * Get the user DAO
	 * @return UserDAO
	 */
	
	public UserDAO getUserDao() {
		return (new UserDAO(this));
	}
	
	/**
	 * Get the address DAO
	 * @return AddressDAO
	 */
	
	public AddressDAO getAdressDao() {
		return (new AddressDAO(this))
;	}
}