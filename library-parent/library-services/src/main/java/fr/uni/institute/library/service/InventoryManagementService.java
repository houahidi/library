package fr.uni.institute.library.service;

import java.util.Collection;
import java.util.Date;

import fr.uni.institute.library.business.inventory.Category;
import fr.uni.institute.library.business.inventory.Title;
import fr.uni.institute.library.dao.AudioRecordDao;
import fr.uni.institute.library.dao.BookDao;
import fr.uni.institute.library.dao.CategoryDao;
import fr.uni.institute.library.dao.CategoryTitleRelationDao;
import fr.uni.institute.library.dao.TitleDao;

public interface InventoryManagementService {

	public abstract void addCategory(Category category) throws ServiceException;

	public abstract void addTitle(Title title) throws ServiceException;

	public abstract Collection getAllCategories() throws ServiceException;

	public abstract Category getCategoryById(int id) throws ServiceException;

	public abstract Title getTitleById(int id) throws ServiceException;

	public Collection getTitlesBy(String name, String author, Date date)
			throws ServiceException;

	public abstract void loadCategoriesForTitle(Title title)
			throws ServiceException;

	public abstract void modifyCategory(Category category)
			throws ServiceException;

	public abstract void modifyTitle(Title title)
			throws ServiceException;

	public abstract void removeCategory(Category category)
			throws ServiceException;

	public abstract void removeCategoryFromTitle(Category category, Title title)
			throws ServiceException;

	public void removeTitle(Title title) throws ServiceException;

	public abstract void setAudioRecordDao(AudioRecordDao audioRecordDao);

	public abstract void setBookDao(BookDao bookDao);

	public abstract void setCategoryDao(CategoryDao categoryDao);

	public abstract void setRelationDao(CategoryTitleRelationDao relationDao);

	public abstract void setTitleDao(TitleDao titleDao);

}