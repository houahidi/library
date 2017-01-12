/**
 * 
 */
package fr.uni.institute.library.business.inventory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * @author Stephane Lietard
 * 
 */
public class Title extends BusinessObject {

	/**
	 * the name of the title
	 */
	private String name;

	/**
	 * the date when this title was published
	 */
	private Date date;

	/**
	 * the author of this title
	 */
	private String author;

	/**
	 * the categories into this title is referenced.
	 * association Category-Title with *..* cardinality
	 */
	private ArrayList categories;

	/**
	 * Constructs a new Title with only his unique id
	 * 
	 * @param id
	 *            unique id of this title
	 */
	public Title(int id) {
		this(id, "unknow", null, "unknown");
	}

	
	public Title(int id, String name) {
		this(id, name, null, "unknown");
	}
	/**
	 * Constructs a new Title with unknown author
	 * 
	 * @param id
	 *            unique id of this title
	 * @param name
	 *            the name
	 * @param date
	 *            the published date
	 */
	public Title(int id, String name, Date date) {
		this(id, name, date, "unknow author");
	}

	/**
	 * Constructs a new Title
	 * 
	 * @param id
	 *            unique id of this title
	 * @param name
	 *            the name
	 * @param date
	 *            the published date
	 * @param author
	 *            the author of this title
	 */
	public Title(int id, String name, Date date, String author) {
		super(id);
		this.name = name;
		this.date = date;
		this.author = author;
		categories = new ArrayList();
	}

	/**
	 * implementation of the Visitor GoF Design Pattern
	 * 
	 * @param v
	 *            the generic visitor who ask to visit this agregat
	 */
	public void accept(InventoryVisitor v) throws InventoryException {
		v.visitTitle(this);
	}

	/**
	 * add a category to this title (or includes this title into the category)
	 * resolve the reverse relation (this title will be added to the given
	 * category)
	 * 
	 * @param category
	 *            the category to add
	 */
	public void addCategory(Category category) {
		if (!categories.contains(category)) {
			categories.add(category);
			category.addTitle(this);
		}
	}

	/**
	 * remove all categories
	 *
	 */
	public void emptyCategories() {
		if (this.categories != null){
			Iterator it = this.categories.iterator() ;
			this.categories = new ArrayList() ;
			while (it.hasNext()){
				this.removeCategory((Category)it.next()) ;
			}
		}
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the categories this title belongs to
	 */
	public Collection getCategories() {
		return categories;
	}

	/**
	 * @return the date when this title was published
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param category
	 *            the category to test
	 * @return true if this title belongs to the given category
	 */
	public boolean hasCategory(Category category) {
		return (this.categories.contains(category));
	}

	/**
	 * remove a category to this title (or removes this title from the category)
	 * resolve the reverse relation (the title will be removed from the
	 * category)
	 * 
	 * @param category
	 *            the category to remove
	 */
	public void removeCategory(Category category) {
		if (categories.contains(category)) {
			categories.remove(category);
			category.removeTitle(this);
		}
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @param date
	 *            the published date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return a textual representation of a Title
	 */
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		StringBuffer buf = new StringBuffer();
		buf.append("Title");
		/*
		 * buf.append("("); buf.append(super.toString()); buf.append(")");
		 */
		buf.append(" name : ");
		buf.append(this.getName());
		buf.append(" - author : ");
		buf.append(this.getAuthor());
		buf.append(" - date : ");
		buf.append(df.format(this.getDate()));
		buf.append(" - categories : ");
		Iterator it = categories.iterator();
		while (it.hasNext()) {
			Category category = (Category) it.next();
			buf.append(category.getName());
			if (it.hasNext())
				buf.append(", ");
		}
		return buf.toString();
	}
}
