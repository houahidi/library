package fr.uni.institute.library.web;

import java.util.Collection;
import java.util.Date;

import fr.uni.institute.library.business.inventory.Category;
import fr.uni.institute.library.business.inventory.Title;

public class TitleDTO {
	private Title title;

	public TitleDTO(Title title) {
		this.title = title;
	}

	/**
	 * @return the author of the title
	 */
	public String getAuthor() {
		return title.getAuthor();
	}

	/**
	 * @return the categories of the title
	 */
	public Collection getCategories() {
		return title.getCategories();
	}

	/**
	 * @return the date when the title was published
	 */
	public Date getDate() {
		return title.getDate();
	}

	/**
	 * 
	 * @return the unique id of the title
	 */
	public int getId() {
		return title.getId();
	}

	/**
	 * @return the name of the title
	 */
	public String getName() {
		return title.getName();
	}

	Title getTitle(){
		return this.title ;
	}

	/**
	 * @param category
	 *            the category to test
	 * @return true if the title belongs to the given category
	 */
	public boolean hasCategory(Category category) {
		return (title.getCategories().contains(category));
	}
	
}
