package fr.uni.institute.library.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import fr.uni.institute.library.business.inventory.Category;
import fr.uni.institute.library.dao.CategoryDao;
import fr.uni.institute.library.dao.DaoException;

public class CategoryDaoJdbc extends InventoryDaoJdbc implements CategoryDao {

	public CategoryDaoJdbc(Connection connection) {
		super(connection);
	}

	/**
	 * this method does'nt resolve complex relations
	 */
	public void createCategory(Category category) throws DaoException {
		try {
			Connection conn = this.getConnection();

			// to get a new unique id for the new category
			// this way is not good (we should have a shared service for that)
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("select max(k_puid) from t_category");
			if (res.next()) {
				int idNewCategory = res.getInt(1) + 1;
				category.setId(idNewCategory); 
			} else {
				throw new DaoException(
				"error : unable to get an unique id for new category");
			}

			PreparedStatement pst = conn
					.prepareStatement("insert into t_category (k_puid, fk_puid_parent, name) values (?, ?, ?)");
			pst.setInt(1, category.getId());
			Category parent = category.getParent();
			if (parent != null) {
				pst.setInt(2, parent.getId());
			} else {
				pst.setNull(2, Types.INTEGER);
			}
			pst.setString(3, category.getName());
			pst.execute();
		} catch (SQLException ex) {
			System.out.println("SQL error in CategoryDaoJdbc : " + ex.getMessage()) ;
			ex.printStackTrace() ;
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	/**
	 * calling this method supposes that category's relations have been deleted
	 * 
	 */
	public void deleteCategory(Category category) throws DaoException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement pst = conn
					.prepareStatement("delete from t_category where k_puid=?");
			pst.setInt(1, category.getId());
			pst.execute();
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	/**
	 * this method does'nt resolve complex relations
	 */
	public Collection researchAllCategories() throws DaoException {
		try {
			Connection conn = this.getConnection();
			ArrayList categories = new ArrayList();

			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("select * from t_category");
			while (res.next()) {
				int puid = res.getInt("k_puid");
				String name = res.getString("name");

				// instanciate a new category
				Category category = new Category(puid, name);

				// construct an empty category parent
				int puidParent = res.getInt("fk_puid_parent");
				if (puidParent > 0) {
					Category parent = new Category(puidParent);
					category.setParent(parent);
				}

				// put the loaded category into the list
				categories.add(category);
			}
			return categories;
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	public Category researchCategoryById(int id) throws DaoException {
		try {
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt
					.executeQuery("select * from t_category where k_puid=" + id);
			if (res.next()) {
				int puid = res.getInt("k_puid");
				String name = res.getString("name");
				Category category = new Category(puid, name);
				int puidParent = res.getInt("fk_puid_parent");
				if (puidParent > 0) {
					Category parent = new Category(puidParent);
					category.setParent(parent);
				}
				return category;
			}
			return null;
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	/**
	 * this method does'nt resolve complex relations
	 */
	public void updateCategory(Category category) throws DaoException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement pst = conn
					.prepareStatement("update t_category set fk_puid_parent=?, name=? where k_puid=?");
			Category parent = category.getParent();
			if (parent != null) {
				pst.setInt(1, parent.getId());
			} else {
				pst.setInt(1, 0);
			}
			pst.setString(2, category.getName());
			pst.setInt(3, category.getId());
			pst.execute();
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

}
