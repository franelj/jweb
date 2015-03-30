package com.jweb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jweb.beans.News;
import com.jweb.exceptions.DAOException;

/**
 * The news Data Access Object : create, read, update or delete a new. Extends the DAO abstract class.
 * @author Julie
 * @see DAOException, DAO
 *
 */

public class NewsDAO extends DAO {
	public NewsDAO(DAOFactory db) {
		super(db);
	}
	
	/**
	 * Create new news into the database
	 * @param news
	 * @throws DAOException
	 */

	public void createNews(News news) throws DAOException {
		try {
			this.connect();
			this.prepare("INSERT INTO news (title, content, idauthor) VALUES (?, ?, ?)");
			this._s.setString(1, news.getTitle());
			this._s.setString(2, news.getContent());
			this._s.setInt(3, news.getAuthor());
			this.insert();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (updatenews)");
		}
		finally {
			this.close();
		}
	}
	

	
	/**
	 * Update or create news
	 * @param news
	 * @param id
	 * @return boolean
	 * @throws DAOException
	 */
	
	public void updateNews(News news) throws DAOException {
		try {
			this.connect();
			this.prepare("UPDATE news SET title = ?, content = ?, idauthor = ? WHERE idnews = ?");
			this._s.setString(1, news.getTitle());
			this._s.setString(2, news.getContent());
			this._s.setInt(3, news.getAuthor());
			this._s.setInt(4, news.getId());
			this.update();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (updatenews)");
		}
		finally {
			this.close();
		}
	}
	
	/**
	 * Retrieve all news infos from the database
	 * @return List<News>
	 * @throws DAOException
	 */
	
	public List<News> getNews(boolean limit) throws DAOException {
		List<News> news = new ArrayList<News>();
		try {
			this.connect();
			if (limit == false)
				this.prepare("SELECT * FROM news AS n LEFT JOIN users AS u ON n.idauthor = u.idusers");
			else
				this.prepare("SELECT * FROM news AS n LEFT JOIN users AS u ON n.idauthor = u.idusers ORDER BY idnews DESC LIMIT 6");
			this.select();
			try {
				if (this._r != null) {
					news.add(new News(this._r.getInt("idnews"), this._r.getString("title"), this._r.getString("content"), this._r.getTimestamp("created_at"), 
							this._r.getTimestamp("updated_at"), this._r.getInt("idauthor"), this._r.getString("login")));
					while (this._r.next()) {
						news.add(new News(this._r.getInt("idnews"), this._r.getString("title"), this._r.getString("content"), this._r.getTimestamp("created_at"), 
								this._r.getTimestamp("updated_at"), this._r.getInt("idauthor"), this._r.getString("login")));
					}
				}
			}
			catch (SQLException e) {
				throw new DAOException("Un problème est survenu, merci de réessayer. (getnews)");
			}
		}
		catch (DAOException e) {
			throw e;
		}
		finally {
			this.close();
		}
		return (news);
	}
	
	/**
	 * Retrieve a news with its id
	 * @param id
	 * @return News
	 * @throws DAOException
	 */
	
	public News getNewsById(int id) throws DAOException {
		News news = null;
		try {
			this.connect();
			this.prepare("SELECT * FROM news AS n LEFT JOIN users AS u ON n.idauthor = u.idusers WHERE idnews = ? LIMIT 1");
			this._s.setInt(1, id);
			this.select();
			if (this._r != null)
				news = new News(this._r.getInt("idnews"), this._r.getString("title"), this._r.getString("content"), this._r.getTimestamp("created_at"), 
						this._r.getTimestamp("updated_at"), this._r.getInt("idauthor"), this._r.getString("login"));
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (getauthorbyid)");
		}
		finally {
			this.close();
		}
		return (news);
	}
	
	/**
	 * Delete a news from the database
	 * @param id
	 * @return boolean
	 * @throws DAOException
	 */
	
	public void deleteNews(int id) throws DAOException {
		try {
			this.connect();
			this.prepare("DELETE FROM news WHERE idnews = ?");
			this._s.setInt(1, id);
			this.delete();
		}
		catch (DAOException e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DAOException("Un problème est survenu, merci de réessayer. (deletenews)");
		}
		finally {
			this.close();
		}
	}
}
