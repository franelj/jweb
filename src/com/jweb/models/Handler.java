package com.jweb.models;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 * MODEL : the abstract class Handler.
 * @author Julie
 *
 */

public abstract class Handler {
	String _success = null;
	String _fail = null;
	Map<String, String> _errors = new HashMap<String, String>();
	
	/**
	 * Get the success string
	 * @return String
	 */
	
	public String getSuccess() {
		return (this._success);
	}
	
	/**
	 * Set the success String
	 * @param success
	 */
	
	protected void setFail(String fail) {
		this._fail = fail;
	}
	
	/**
	 * Get the success string
	 * @return String
	 */
	
	public String getFail() {
		return (this._fail);
	}
	
	/**
	 * Set the success String
	 * @param success
	 */
	
	protected void setSuccess(String success) {
		this._success = success;
	}
	
	/**
	 * Get the error map
	 * @return Map<String, String>
	 */
	
	public Map<String, String> getErrors() {
		return (this._errors);
	}
	
	/**
	 * Set an error
	 * @param key
	 * @param value
	 */
	
	protected void setError(String key, String value) {
		this._errors.put(key, value);
	}
	
	/**
	 * Tries to get the parameter corresponding to the key passed as parameter. Returns it if not null and not empty, else returns null
	 * @param req
	 * @param key
	 * @return String
	 */
	
	protected String getParam(HttpServletRequest req, String key) {
		String param = req.getParameter(key);
		if (param == null || param.isEmpty() || param.trim().length() == 0)
			return (null);
		return (param.trim());
	}
	
	protected Integer getIntParam(HttpServletRequest req, String key) {
		String param = req.getParameter(key);
		if (param == null || param.isEmpty() || param.trim().length() == 0)
			return (null);
		return (Integer.valueOf(param.trim()));
	}
	
	protected Float getFloatParam(HttpServletRequest req, String key) {
		String param = req.getParameter(key);
		if (param == null || param.isEmpty() || param.trim().length() == 0)
			return (null);
		return (Float.valueOf(param.trim().replaceAll(",", ".")));
	}
	
	protected Part getPartParam(HttpServletRequest req, String key) {
		Part param = null;
		try {
			param = req.getPart(key);
			if (param.getSubmittedFileName().isEmpty())
				param = null;
		}
		catch (IllegalStateException e) {
			this.setError("file", "Le fichier est trop volumineux.");
		}
		catch (IOException e) {
			this.setError("file", "Une erreur est survenue, merci de réessayer.");				
		}
		catch (ServletException e) {
			this.setError("file", "Une erreur est survenue, merci de réessayer.");				
		}
		return (param);
	}
}
