package fr.uni.institute.library.dao;

import java.util.Collection;

import fr.uni.institute.library.business.inventory.Book;

public interface BookDao {

	public Collection researchAllBooks() throws DaoException ;

	public Book researchBookById(int id) throws DaoException ;

	public void deleteBook(Book book) throws DaoException ;

	public void updateBook(Book book) throws DaoException ;

	public void createBook(Book book) throws DaoException ;

}
