package com.jweb.exceptions;

/**
 * EXCEPTION : Database exception
 * @author Julie
 * @see DAO
 *
 */

public class DAOException extends RuntimeException {
	private static final long serialVersionUID = 1L;

		public DAOException(String msg) {
	        super(msg);
	    }

	    public DAOException(String msg, Throwable cause) {
	        super(msg, cause);
	    }

	    public DAOException(Throwable cause) {
	        super(cause);
	    }
}
