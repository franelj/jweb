package com.jweb.exceptions;

/**
 * EXCEPTION : Form exception
 * @author Julie
 *
 */

public class FormException extends RuntimeException {
	private static final long serialVersionUID = 1L;

		public FormException(String msg) {
	        super(msg);
	    }

	    public FormException(String msg, Throwable cause) {
	        super(msg, cause);
	    }

	    public FormException(Throwable cause) {
	        super(cause);
	    }
}
