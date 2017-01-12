/**
 * 
 */
package fr.uni.institute.library.business.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author S. Lietard
 * 
 */
public class Category extends BusinessObject {

	/**
	 * the titles which belongs to this category. association Category-Title
	 * with *..* cardinality
	 */
	private ArrayList titles;

	/**
	 * the parent category. Categories can be structured as a tree
	 */
	private Category parent;

	/**
	 * the name of this category
	 */
	private String name;

	/**
	 * construct with unknown name, no parent and an empty tiles list
	 * 
	 * @param id
	 *            unique id of this instance
	 */
	public Category(int id) {
		this(id, "unknown category name", null);
	}

	/**
	 * construct with no parent and an empty tiles list
	 * 
	 * @param id
	 *            unique id of this instance
	 * @param name
	 *            the name of the category
	 */
	public Category(int id, String name) {
		this(id, name, null);
	}

	/**
	 * construct with an empty titles list
	 * 
	 * @param id
	 *            unique id of this instance
	 * @param name
	 *            the name of the category
	 * @param parent
	 *            parent category (or null if any)
	 */
	public Category(int id, String name, Category parent) {
		super(id);
		this.name = name;
		this.parent = parent;
		titles = new ArrayList();
	}

	/**
	 * implementation of the Visitor GoF Design Pattern
	 * 
	 * @param v
	 *            the generic visitor who ask to visit this agregat
	 */
	public void accept(InventoryVisitor v) throws InventoryException {
		v.visitCategory(this);
	}

	/**
	 * add a title to this category resolve the reverse relation (this category
	 * will be added by the given title)
	 * 
	 * @param title
	 *            the title to add
	 */
	public void addTitle(Title title) {
		if (!titles.contains(title)) {
			this.titles.add(title);
			title.addCategory(this);
		}
	}

	/**
	 * 
	 * @return the audio records which belongs to this category
	 */
	public ArrayList getAudioRecords() {
		TitlesLister lister = new TitlesLister() {
			public void visitAudioRecord(AudioRecord anAudioRecord) {
				list.add(anAudioRecord);
			}
		};
		Iterator it = getTitles().iterator();
		while (it.hasNext()) {
			Title aTitle = (Title) it.next();
			try {
				aTitle.accept(lister);
			} catch (Exception e) {	}
		}
		return lister.getList();
	}

	/**
	 * 
	 * @return the books which belongs to this category
	 */
	public ArrayList getBooks() {
		TitlesLister lister = new TitlesLister() {
			public void visitBook(Book aBook) {
				list.add(aBook);
			}
		};
		Iterator it = getTitles().iterator();
		while (it.hasNext()) {
			Title aTitle = (Title) it.next();
			try {
				aTitle.accept(lister);
			} catch (Exception e) {	}
		}
		return lister.getList();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the parent
	 */
	public Category getParent() {
		return parent;
	}

	/**
	 * 
	 * @return the titles which belongs to this category
	 */
	public Collection getTitles() {
		return titles;
	}

	/**
	 * 
	 * @return the video records which belongs to this category
	 */
	public Collection getVideoRecords() {
		TitlesLister lister = new TitlesLister() {
			public void visitVideoRecord(VideoRecord aVideoRecord) {
				list.add(aVideoRecord);
			}
		};
		Iterator it = getTitles().iterator();
		while (it.hasNext()) {
			Title aTitle = (Title) it.next();
			try {
				aTitle.accept(lister);
			} catch (Exception e) {	}
		}
		return lister.getList();
	}

	
	public boolean hasTitle(Title title) {
		return (this.titles.contains(title));
	}

	/**
	 * remove a title from this category 
	 * resolve the reverse relation 
	 * (this category will be not referenced anymore by the given title)
	 * 
	 * @param title
	 *            the title to add
	 */
	public void removeTitle(Title title) {
		if (titles.contains(title)) {
			titles.remove(title);
			title.removeCategory(this);
		}
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Category parent) {
		this.parent = parent;
	}

	/**
	 * @return a textual representation of a Category
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Category (");
		buf.append(super.toString());
		buf.append(")");
		buf.append(" name : ");
		buf.append(this.getName());
		Category p = this.getParent();
		if (p != null) {
			buf.append(" - parent :");
			buf.append(p.getName());
		}
		return buf.toString();
	}
}
