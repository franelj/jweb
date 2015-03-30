package com.jweb.models;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.jweb.beans.News;
import com.jweb.beans.Newsletter;
import com.jweb.beans.Suscriber;
import com.jweb.dao.DAOFactory;
import com.jweb.dao.NewsletterDAO;
import com.jweb.exceptions.DAOException;
import com.jweb.exceptions.FormException;

/**
 * MODEL : validate and handle data for a newsletter. Extends the abstract class Handler.
 * @author Julie
 * @see Handler
 *
 */

public class NewsletterHandler extends Handler {
	NewsletterDAO _dao = null;

	public NewsletterHandler(DAOFactory db) {
		this._dao = db.getNewsletterDao();
	}
	
	/**
	 * Prepare the newsletter to send
	 * @param news
	 * @return Newsletter
	 */
	
	public Newsletter prepare(News news) {
		Newsletter newsletter = new Newsletter();
		
		try {
			List<Suscriber> suscribers = this._dao.getNewsletterRecipients();
			newsletter.setTo(suscribers);
			newsletter.setFrom("franeljulie@gmail.com");
			newsletter.setSubject(news.getTitle());
			newsletter.setContent(news.getContent());
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
		}		
		return (newsletter);
	}
	
	/**
	 * Tries to send the newsletter
	 * @param newsletter
	 * @param needed
	 */
	
	public void send(Newsletter newsletter) {
		try {
			String needed = this._dao.getConfigValue("gpwd");
			
	        // Server configuration
	        Properties props = System.getProperties();
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			
			// Message configuration
	        Session session = Session.getDefaultInstance(props, null);
	        try {
	            Message msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress("franeljulie@gmail.com", "Julie Frnl - Ju\'Lire"));
	            msg.addRecipient(Message.RecipientType.TO, new InternetAddress("franeljulie@gmail.com"));
	            for (int x = 0; x < newsletter.getTo().size(); x++) {
	            	msg.addRecipient(Message.RecipientType.CC, new InternetAddress(newsletter.getTo().get(x).getEmail()));
	            }
	            msg.setSubject(newsletter.getSubject());
	            msg.setContent(newsletter.getContent(), "text/html");
	            
	            // Sending configuration and send
	            Transport transport = session.getTransport("smtp");
	            transport.connect("smtp.gmail.com", "franeljulie@gmail.com", needed);
	            transport.sendMessage(msg, msg.getAllRecipients());
	            transport.close();
	            this.setSuccess("La newsletter a été envoyée avec succès.");
	        }
	        catch (AddressException e) {
	        	this.setError("error", "Un problème est survenu, merci de réessayer.");
				this.setFail("La newsletter n'a pu être envoyée.");
	        }
	        catch (MessagingException e) {
	        	this.setError("error", "Un problème est survenu, merci de réessayer.");
				this.setFail("La newsletter n'a pu être envoyée.");
	        }
	        catch (UnsupportedEncodingException e) {
	        	this.setError("error", "Un problème est survenu, merci de réessayer.");
				this.setFail("La newsletter n'a pu être envoyée.");
	        }
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
			this.setFail("La newsletter n'a pu être envoyée.");
		}
	}
	
	/**
	 * Tries to retrieve the list of all suscribers to the newsletter from the database
	 * @return List<Suscriber>
	 */
	
	public List<Suscriber> getSuscribers() {
		List<Suscriber> suscribers = null;
		try {
			suscribers = this._dao.getNewsletterRecipients();
			if (suscribers.isEmpty())
				this.setFail("Il n'y a aucun inscrit à la newsletter.");
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());			
		}
		return (suscribers);
	}
	
	/**
	 * Tries to delete a suscriber from the database by its id
	 * @param id
	 */
	
	public void deleteSuscriber(int id) {
		try {
			this._dao.deleteSuscriber(id);
			this.setSuccess("L'inscrit a été supprimé avec succès.");
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
			this.setFail("L'inscrit n'a pu être supprimé.");
		}
	}
	
	private void validateEmail(String email) throws FormException {
		if (email == null || email.isEmpty() || !email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
			throw new FormException("Vous devez renseigner un email valide.");
	}
	
	private void validateChoice(String choice) throws FormException {
		if (choice == null || choice.isEmpty() || !(choice.equals("suscribe") || choice.equals("unsuscribe")))
			throw new FormException("Vous devez choisir entre vous inscrire et vous désinscrire.");
	}
	
	private Suscriber validateFields(Suscriber suscriber, HttpServletRequest req) {
		String email = this.getParam(req, "newsletterEmail");
		String choice = this.getParam(req, "newsletterChoice");
		
		try {
			this.validateEmail(email);
			suscriber.setEmail(email);
		}
		catch (FormException e) {
			this.setError("email", e.getMessage());
		}
		
		try {
			this.validateChoice(choice);
			suscriber.setChoice(choice.equals("suscribe") ? true : false);
		}
		catch (FormException e) {
			this.setError("choice", e.getMessage());
		}
		return (suscriber);
	}
	
	public Suscriber handleSuscriber(HttpServletRequest req) {
		Suscriber suscriber = this.validateFields(new Suscriber(), req);
		
		if (this._errors.isEmpty()) {
			try {
				if (suscriber.getChoice() == true) {
					this._dao.addSuscriber(suscriber.getEmail());
					this.setSuccess("Vous avez été inscrit(e) à la newsletter avec succès.");
					this.setFail("Vous n'avez pas pu être inscrit(e) à la newsletter.");
				}
				else {
					this._dao.deleteSuscriber(suscriber.getEmail());
					this.setSuccess("Vous avez été désinscrit(e) de la newsletter avec succès.");
					this.setFail("Vous n'avez pas pu être désinscrit(e) de la newsletter.");
				}
			}
			catch (DAOException e) {
				this.setError("db", e.getMessage());
			}
		}
		
		if (!this._errors.isEmpty()) {
			this._success = null;
		}
		else
			this._fail = null;
		return (suscriber);
	}
}
