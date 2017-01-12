package fr.uni.institute.library.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import fr.uni.institute.library.business.inventory.Book;
import fr.uni.institute.library.dao.BookDao;
import fr.uni.institute.library.dao.DaoException;
import fr.uni.institute.library.dao.TitleDao;

public class BookDaoJdbc extends InventoryDaoJdbc implements BookDao {

	private TitleDao titleDao;

	public BookDaoJdbc(Connection connection, TitleDao titleDao) {
		super(connection);
		this.titleDao = titleDao;
	}

	public void createBook(Book book) throws DaoException {
		try {
			titleDao.createTitle(book);
			Connection conn = this.getConnection();
			PreparedStatement pst = conn
					.prepareStatement("insert into t_book (k_puid, isbn, pages) values (?, ?, ?)");
			pst.setInt(1, book.getId());
			pst.setString(2, book.getIsbn());
			pst.setInt(3, book.getPages());
			pst.execute();
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	/**
	 * calling this method supposes that book's relations have been deleted
	 */
	public void deleteBook(Book book) throws DaoException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement pst = conn
					.prepareStatement("delete from t_book where k_puid=?");
			pst.setInt(1, book.getId());
			pst.execute();
			titleDao.deleteTitle(book);
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	public Collection researchAllBooks() throws DaoException {
		try {
			Connection conn = this.getConnection();
			ArrayList books = new ArrayList();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt
					.executeQuery("select distinct * from t_title as t1, t_book as t2 where (t1.k_puid=t2.k_puid)");
			while (res.next()) {
				int puid = res.getInt("k_puid");
				String name = res.getString("name");
				Date publishedAt = res.getDate("published_at");
				String author = res.getString("author");
				String isbn = res.getString("isbn");
				int pages = res.getInt("pages");
				Book aBook = new Book(puid, name, publishedAt, author, isbn,
						pages);
				books.add(aBook);
			}
			return books;
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	public Book researchBookById(int id) throws DaoException {
		try {
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt
					.executeQuery("select distinct * from t_title as t1, t_book as t2 where (t1.k_puid=t2.k_puid) and t1.k_puid="
							+ id);
			if (res.next()) {
				int puid = res.getInt("k_puid");
				String name = res.getString("name");
				Date publishedAt = res.getDate("published_at");
				String author = res.getString("author");
				String isbn = res.getString("isbn");
				int pages = res.getInt("pages");
				Book book = new Book(puid, name, publishedAt, author, isbn,
						pages);
				return book;
			}
			return null;
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	public void updateBook(Book book) throws DaoException {
		try {
			titleDao.updateTitle(book);
			Connection conn = this.getConnection();
			PreparedStatement pst = conn
					.prepareStatement("update t_book set isbn=?, pages=? where k_puid=?");
			pst.setString(1, book.getIsbn());
			pst.setInt(2, book.getPages());
			pst.setInt(3, book.getId());
			pst.execute();
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}
}
