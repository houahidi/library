package fr.uni.institute.library.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import fr.uni.institute.library.business.inventory.Title;
import fr.uni.institute.library.dao.DaoException;
import fr.uni.institute.library.dao.TitleDao;

public class TitleDaoJdbc extends InventoryDaoJdbc implements TitleDao {

	public TitleDaoJdbc(Connection connection) {
		super(connection);
	}

	public void createTitle(Title title) throws DaoException {
		try {
			Connection conn = this.getConnection();

			// to get a new unique id for the new title
			// this way is not good (we should have a shared service for that)
			Statement stmt = conn.createStatement();
			ResultSet res = stmt
					.executeQuery("select max(k_puid) from t_title");
			if (res.next()) {
				int idNewTitle = res.getInt(1) + 1;
				title.setId(idNewTitle);
			} else {
				throw new DaoException(
						"error : unable to get an unique id for new title");
			}

			PreparedStatement pst = conn
					.prepareStatement("insert into t_title (k_puid, name, author, published_at) values (?, ?, ?, ?)");
			pst.setInt(1, title.getId());
			pst.setString(2, title.getName());
			pst.setString(3, title.getAuthor());
			Date publishedAt = title.getDate();
			if (publishedAt != null) {
				pst.setDate(4, new java.sql.Date(publishedAt.getTime()));
			} else {
				pst.setNull(4, Types.DATE);
			}
			pst.execute();

		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	/**
	 * calling this method supposes that title's relations have been deleted
	 */
	public void deleteTitle(Title title) throws DaoException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement pst = conn
					.prepareStatement("delete from t_title where k_puid=?");
			pst.setInt(1, title.getId());
			pst.execute();
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}

	}

	public Collection researchAllTitles() throws DaoException {
		try {
			Connection conn = this.getConnection();
			ArrayList titles = new ArrayList();

			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("select * from t_title");
			while (res.next()) {
				int puid = res.getInt("k_puid");
				String name = res.getString("name");
				Date publishedAt = res.getDate("published_at");
				String author = res.getString("author");
				Title title = new Title(puid, name, publishedAt, author);
				titles.add(title);
			}
			return titles;
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	public Title researchTitleById(int id) throws DaoException {
		try {
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt
					.executeQuery("select * from t_title where k_puid=" + id);
			if (res.next()) {
				int puid = res.getInt("k_puid");
				String name = res.getString("name");
				Date publishedAt = res.getDate("published_at");
				String author = res.getString("author");
				Title title = new Title(puid, name, publishedAt, author);
				return title;
			}
			return null;
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	public Collection researchTitlesBy(String pName, String pAuthor, Date date)
			throws DaoException {
		try {
			Connection conn = this.getConnection();
			ArrayList titles = new ArrayList();
			Statement stmt = conn.createStatement();
			String request = "select * from t_title ";
			boolean wassert = false;
			if (pName != null && !"".equals(pName)) {
				request += " where (name like \"" + pName + "%\")";
				wassert = true;
			}
			if (pAuthor != null && !"".equals(pAuthor)) {
				if (!wassert) {
					request += " where ";
					wassert = true;
				} else {
					request += " and ";
				}
				request += " (author like \"" + pAuthor + "%\")";
			}
			if (date != null) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
				if (!wassert) {
					request += " where ";
				} else {
					request += " and ";
					wassert = true;
				}
				request += " (published_at like \"" + df.format(date) + "%\")";
			}
			// System.out.println(request);
			ResultSet res = stmt.executeQuery(request);
			while (res.next()) {
				int puid = res.getInt("k_puid");
				String name = res.getString("name");
				Date publishedAt = res.getDate("published_at");
				String author = res.getString("author");
				Title title = new Title(puid, name, publishedAt, author);
				titles.add(title);
			}
			return titles;
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	public void updateTitle(Title title) throws DaoException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement pst = conn
					.prepareStatement("update t_title set name=?, author=?, published_at=? where k_puid=?");
			pst.setString(1, title.getName());
			pst.setString(2, title.getAuthor());
			Date publishedAt = title.getDate();
			if (publishedAt != null) {
				pst.setDate(3, new java.sql.Date(publishedAt.getTime()));
			} else {
				pst.setNull(3, Types.DATE);
			}
			pst.setInt(4, title.getId());
			pst.execute();
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

}
