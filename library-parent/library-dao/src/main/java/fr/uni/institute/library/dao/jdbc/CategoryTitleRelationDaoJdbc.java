package fr.uni.institute.library.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.uni.institute.library.business.inventory.Category;
import fr.uni.institute.library.business.inventory.Title;
import fr.uni.institute.library.dao.CategoryTitleRelationDao;
import fr.uni.institute.library.dao.DaoException;

public class CategoryTitleRelationDaoJdbc extends InventoryDaoJdbc implements
		CategoryTitleRelationDao {

	public CategoryTitleRelationDaoJdbc(Connection connection){
		super(connection) ;
	}
	
	public Collection researchTitlesIdsForCategory(Category category)
			throws DaoException {
		try {
			ArrayList ids = new ArrayList();
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt
					.executeQuery("select * from r_category_title where k_puid_category="
							+ category.getId());
			while (res.next()) {
				int puid = res.getInt("k_puid_title");
				ids.add(new Integer(puid));
			}
			return ids;
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	public Collection researchCategoriesIdsForTitle(Title title)
			throws DaoException {
		try {
			ArrayList ids = new ArrayList();
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt
					.executeQuery("select * from r_category_title where k_puid_title="
							+ title.getId());
			while (res.next()) {
				int puid = res.getInt("k_puid_category");
				ids.add(new Integer(puid));
			}
			return ids;
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	public void addRelation(Title title, Category category) throws DaoException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement pst = conn
					.prepareStatement("insert into r_category_title (k_puid_category, k_puid_title) values (?, ?)");
			pst.setInt(1, category.getId());
			pst.setInt(2, title.getId());
			pst.execute();
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	public void removeCategoriesForTitle(Title title) throws DaoException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement pst = conn
					.prepareStatement("delete from r_category_title where k_puid_title=?");
			pst.setInt(1, title.getId());
			pst.execute();
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	public void removeRelation(Title title, Category category)
			throws DaoException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement pst = conn
					.prepareStatement("delete from r_category_title where (k_puid_category=?) and (k_puid_title=?)");
			pst.setInt(1, category.getId());
			pst.setInt(2, title.getId());
			pst.execute();
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	public void removeTitlesForCategory(Category category) throws DaoException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement pst = conn
					.prepareStatement("delete from r_category_title where k_puid_category=?");
			pst.setInt(1, category.getId());
			pst.execute();
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

}
