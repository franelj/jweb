package com.jweb.models;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jweb.beans.News;
import com.jweb.beans.User;
import com.jweb.dao.DAOFactory;
import com.jweb.dao.NewsDAO;
import com.jweb.exceptions.DAOException;
import com.jweb.exceptions.FormException;

/**
 * MODEL : validate and handle data for a news. Extends the abstract class Handler.
 * @author Julie
 * @see Handler
 *
 */

public class NewsHandler extends Handler {
	NewsDAO _dao = null;

	public NewsHandler(DAOFactory db) {
		this._dao = db.getNewsDao();
	}
	
	/**
	 * Check if the title is not null nor empty
	 * @param title
	 * @throws FormException
	 */
	
	private void validateTitle(String title) throws FormException {
		if (title == null || title.isEmpty())
			throw new FormException("Vous devez renseigner un titre.");
	}
	
	/**
	 * Check if the content is not null nor empty
	 * @param content
	 * @throws FormException
	 */
	
	private void validateContent(String content) throws FormException {
		if (content == null || content.isEmpty())
			throw new FormException("Vous devez renseigner un contenu.");
	}
	
	/**
	 * Check if the user id is not null nor inferior to 0
	 * @param id
	 * @throws FormException
	 */
	
	private void validateId(Integer id) throws FormException {
		if (id == null || id < 0)
			throw new FormException("Vous devez être un utilisateur valide.");
	}
	
	/**
	 * Tries to validate all the required fields
	 * @param news
	 * @param req
	 * @return News
	 */
	
	private News validateFields(News news, HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String title = this.getParam(req, "postTitle");
		String content = this.getParam(req, "postContent");
		
		// Validate the title
		try {
			this.validateTitle(title);
		}
		catch (FormException e) {
			this.setError("title", e.getMessage());
		}
		news.setTitle(title);
		
		// Validate the content
		try {
			this.validateContent(content);
		}
		catch (FormException e) {
			this.setError("content", e.getMessage());
		}
		news.setContent(content);
		
		// Validate the author id
		try {
			this.validateId(user.getId());
		}
		catch (FormException e) {
			this.setError("id", e.getMessage());
		}
		news.setAuthor(user.getId());
		return (news);
	}
	
	/**
	 * Tries to add the news after validating
	 * @param req
	 * @return
	 */
	
	public News addNews(HttpServletRequest req) {
		News news = this.validateFields(new News(), req);
		
		// Tries to create the entry into the database
		if (this._errors.isEmpty()) {
			try {
				this._dao.createNews(news);
			}
			catch (DAOException e) {
				this.setError("db", e.getMessage());
			}
		}
		
		// Set the final result
		if (this._errors.isEmpty())
			this.setSuccess("La news a été créée avec succès.");				
		else
			this.setFail("La news n'a pu être créée.");
		return (news);
	}

	/**
	 * Tries to add the news after validating
	 * @param req
	 * @return
	 */
	
	public News updateNews(News news, HttpServletRequest req) {
		news = this.validateFields(news, req);
		
		// Tries to create the entry into the database
		if (this._errors.isEmpty()) {
			try {
				this._dao.updateNews(news);
			}
			catch (DAOException e) {
				this.setError("db", e.getMessage());
			}
		}
		
		// Set the final result
		if (this._errors.isEmpty())
			this.setSuccess("La news a été éditée avec succès.");				
		else
			this.setFail("La news n'a pu être éditée.");
		return (news);
	}
	
	/**
	 * Tries to retrieve a news from the database by to its id
	 * @param id
	 * @return News
	 */
	
	public News getNewsById(int id) {
		News news = null;
		try {
			news = this._dao.getNewsById(id);
			if (news== null)
				this.setFail("Cette news n'existe pas dans la base.");
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
		}
		return (news);
	}
	
	/**
	 * Tries to retrieve a list of news. If limit == true, the limit is set to 6 news - for the frontpage. If limit == false, there is
	 * no limit - for the news edit form. 
	 * @param limit
	 * @return List<News>
	 */
	
	public List<News> getNews(boolean limit) {
		List<News> news = new ArrayList<News>();
		try {
			news = this._dao.getNews(limit);
			if (news.isEmpty())
				this.setFail("Il n'y a aucune news enregistrée dans la base.");
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
		}
		return (news);
	}
	
	/**
	 * Tries to delete a news from the database
	 * @param id
	 */
	
	public void deleteNews(int id) {
		try {
			this._dao.deleteNews(id);
			this.setSuccess("La news a bien été supprimée.");
		}
		catch (DAOException e) {
			this.setError("db", e.getMessage());
		}
	}

}
