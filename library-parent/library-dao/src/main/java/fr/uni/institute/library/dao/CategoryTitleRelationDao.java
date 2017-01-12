package fr.uni.institute.library.dao;

import java.util.Collection;

import fr.uni.institute.library.business.inventory.Category;
import fr.uni.institute.library.business.inventory.Title;

public interface CategoryTitleRelationDao {

	public void addRelation(Title title, Category category) throws DaoException;
	
	public void removeCategoriesForTitle(Title title) throws DaoException;
	
	public void removeRelation(Title title, Category category)
			throws DaoException;

	public void removeTitlesForCategory(Category category) throws DaoException;

	public Collection researchCategoriesIdsForTitle(Title title) throws DaoException;

	public Collection researchTitlesIdsForCategory(Category category) throws DaoException;

}
