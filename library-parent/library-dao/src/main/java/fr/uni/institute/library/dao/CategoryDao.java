package fr.uni.institute.library.dao;

import java.util.Collection;

import fr.uni.institute.library.business.inventory.Category;

public interface CategoryDao {

	public Collection researchAllCategories() throws DaoException ;

	public Category researchCategoryById(int id) throws DaoException ;

	public void deleteCategory(Category category) throws DaoException ;

	public void updateCategory(Category category) throws DaoException ;

	public void createCategory(Category category) throws DaoException ;

}
