/**
 * 
 */
package fr.uni.institute.library.business.inventory;

import java.util.Date;

/**
 * @author Stephane Lietard
 * 
 */
public class Book extends Title {

	/**
	 * specific unique number which identifies a book
	 */
	private String isbn;

	/**
	 * number of pages in this book
	 */
	private int pages;

	/**
	 * Constructs a new book with only his unique id
	 * 
	 * @param id
	 *            unique id of this book
	 */
	public Book(int id) {
		this(id, "undefined", null, "unknown", null, 0);
	}

	/**
	 * Constructs a new book
	 * 
	 * @param id
	 *            unique id of this book
	 * @param name
	 *            the name
	 * @param date
	 *            the published date
	 * @param author
	 *            the author of this book
	 * @param isbn
	 *            specific unique number which identifies a book
	 * @param pages
	 *            the number of pages in this book
	 */
	public Book(int id, String name, Date date, String author, String isbn, int pages) {
		super(id, name, date, author);
		this.isbn = isbn;
		this.pages = pages;
	}

	/**
	 * implementation of the Visitor GoF Design Pattern
	 * 
	 * @param v
	 *            the generic visitor who ask to visit this agregat
	 */
	public void accept(InventoryVisitor v) throws InventoryException {
		v.visitBook(this);
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @return the pages
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * @param isbn
	 *            the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @param pages
	 *            the pages to set
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}

	/**
	 * @return a textual representation of this Book
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Book (");
		buf.append(super.toString());
		buf.append(") : ");
		buf.append(" - isbn : ");
		buf.append(this.getIsbn());
		buf.append(" - ");
		buf.append(this.getPages());
		buf.append(" pages");
		return buf.toString();
	}
}
