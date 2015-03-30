package com.jweb.beans;

import java.util.List;

/**
 * BEAN : storing a newsletter informations
 * @author Julie
 *
 */

public class Newsletter {
	String _from = null;
	List<Suscriber> _to = null;
	String _subject = null;
	String _content = null;
	
	public Newsletter() {
		
	}
	
	public Newsletter(String from, List<Suscriber> to, String subject, String content) {
		this.setFrom(from);
		this.setTo(to);
		this.setSubject(subject);
		this.setContent(content);
	}
	
	/**
	 * Get the sender email
	 * @return String
	 */
	
	public String getFrom() {
		return (this._from);
	}
	
	/**
	 * Set the sender email
	 * @param from
	 */
	
	public void setFrom(String from) {
		this._from = from;
	}
	
	/**
	 * Get the list of recipients
	 * @return List<String>
	 */
	
	public List<Suscriber> getTo() {
		return (this._to);
	}
	
	/**
	 * Set the list of recipient
	 * @param to
	 */
	
	public void setTo(List<Suscriber> to) {
		this._to = to;
	}
	
	/**
	 * Get the subject of the newsletter
	 * @return String
	 */
	
	public String getSubject() {
		return (this._subject);
	}
	
	/**
	 * Set the subject of the newsletter
	 * @param subject
	 */
	
	public void setSubject(String subject) {
		this._subject = subject;
	}
	
	/**
	 * Get the content of the newsletter
	 * @return String
	 */
	
	public String getContent() {
		return (this._content);
	}
	
	/**
	 * Set the content of the newsletter
	 * @param content
	 */
	
	public void setContent(String content) {
		this._content = content;
	}	
}
