package fr.uni.institute.library.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import fr.uni.institute.library.business.inventory.AudioRecord;
import fr.uni.institute.library.business.inventory.Book;
import fr.uni.institute.library.business.inventory.Category;
import fr.uni.institute.library.business.inventory.InventoryException;
import fr.uni.institute.library.business.inventory.InventoryVisitor;
import fr.uni.institute.library.business.inventory.Title;
import fr.uni.institute.library.dao.AudioRecordDao;
import fr.uni.institute.library.dao.BookDao;
import fr.uni.institute.library.dao.CategoryDao;
import fr.uni.institute.library.dao.CategoryTitleRelationDao;
import fr.uni.institute.library.dao.DaoException;
import fr.uni.institute.library.dao.TitleDao;
import fr.uni.institute.library.service.InventoryManagementService;
import fr.uni.institute.library.service.ServiceException;

public class InventoryManagementServiceImpl implements
		InventoryManagementService {

	private CategoryDao categoryDao;

	private BookDao bookDao;

	private AudioRecordDao audioRecordDao;

	private CategoryTitleRelationDao relationDao;

	private TitleDao titleDao;

	private HashMap categories;

	private HashMap books;

	private HashMap audioRecords;

	
	public InventoryManagementServiceImpl() {
	}
	
	public void addCategory(Category category) throws ServiceException {

		
		try {
			categoryDao.createCategory(category);
		} catch (DaoException e) {
			throw new ServiceException("Error creating category : "
					+ e.getMessage(), e);
		}
	}

	public void addTitle(Title title) throws ServiceException {
		InventoryVisitor v = new TitleAdd(this);
		try {
			title.accept(v);
		} catch (InventoryException e) {
			ServiceException es = (ServiceException) e.getCause();
			throw (es);
		}
		try {
			Iterator it = title.getCategories().iterator();
			while (it.hasNext()) {
				Category category = (Category) it.next();
				relationDao.addRelation(title, category);
			}
		} catch (DaoException e) {
			throw new ServiceException("Error creating title : "
					+ e.getMessage(), e);
		}
	}

	void addAudioRecord(AudioRecord audioRecord) throws ServiceException {
		try {
			audioRecordDao.createAudioRecord(audioRecord) ;
		} catch (DaoException e) {
			throw new ServiceException("Error creating audio record : "
					+ e.getMessage(), e);
		}
	}

	void addBook(Book book) throws ServiceException {
		try {
			bookDao.createBook(book) ;
		} catch (DaoException e) {
			throw new ServiceException("Error creating book : "
					+ e.getMessage(), e);
		}
	}

	private Collection getAllAudioRecords() throws ServiceException {
		if (audioRecords == null) {
			audioRecords = new HashMap();
			try {
				Collection loadedRecords = audioRecordDao
						.researchAllAudioRecords();
				// loops loaded categories to store into the map
				Iterator it = loadedRecords.iterator();
				while (it.hasNext()) {
					AudioRecord aRecord = (AudioRecord) it.next();
					loadCategoriesForTitle(aRecord);
					// put the loaded category into the map, using the id as
					// key
					audioRecords.put(new Integer(aRecord.getId()), aRecord);
				}
			} catch (DaoException e) {
				throw new ServiceException("Error retrieving audio records : "
						+ e.getMessage(), e);
			}
		}
		return audioRecords.values();
	}

	private Collection getAllBooks() throws ServiceException {
		if (books == null) {
			books = new HashMap();
			try {
				Collection loadedBooks = bookDao.researchAllBooks();
				// loops loaded categories to store into the map
				Iterator it = loadedBooks.iterator();
				while (it.hasNext()) {
					Book aBook = (Book) it.next();
					loadCategoriesForTitle(aBook);
					// put the loaded category into the map, using the id as
					// key
					books.put(new Integer(aBook.getId()), aBook);
				}
			} catch (DaoException e) {
				throw new ServiceException("Error retrieving books : "
						+ e.getMessage(), e);
			}
		}
		return books.values();
	}

	/*
	 * doesn't load the titles
	 */
	public Collection getAllCategories() throws ServiceException {
		
	
		if (categories == null) {
			categories = new HashMap();

			// loading categories with dao
			try {
				Collection loadedCategories = categoryDao
						.researchAllCategories();

				// loops loaded categories to store into the map
				Iterator it = loadedCategories.iterator();
				while (it.hasNext()) {
					Category aCategory = (Category) it.next();
					// put the loaded category into the map, using the id as
					// key
					categories.put(new Integer(aCategory.getId()), aCategory);
				}

				// retrieve parent for each category with a parent
				it = categories.values().iterator();
				while (it.hasNext()) {
					Category aCategory = (Category) it.next();
					Category parentTmp = aCategory.getParent();
					if (parentTmp != null) {
						int puidParent = parentTmp.getId();
						Category parentReal = (Category) categories
								.get(new Integer(puidParent));
						aCategory.setParent(parentReal);
					}
				}
			} catch (DaoException e) {
				throw new ServiceException("Error retrieving categories : "
						+ e.getMessage(), e);
			}
		}
		return categories.values();
	}

	public Collection getAllTitles() throws ServiceException {
		ArrayList titles = new ArrayList();
		titles.addAll(getAllAudioRecords());
		titles.addAll(getAllBooks());
		return titles;
	}

	private AudioRecord getAudioRecordById(int id) throws ServiceException {
		if (audioRecords != null) {
			AudioRecord record = (AudioRecord) books.get(new Integer(id));
			if (record != null) {
				return record;
			}
		}
		try {
			AudioRecord record = audioRecordDao.researchAudioRecordById(id);
			if (record != null)
				loadCategoriesForTitle(record);
			return record;
		} catch (DaoException e) {
			throw new ServiceException("Error retrieving audio record : "
					+ e.getMessage(), e);
		}
	}

	private Book getBookById(int id) throws ServiceException {
		if (books != null) {
			Book book = (Book) books.get(new Integer(id));
			if (book != null) {
				return book;
			}
		}
		try {
			Book book = bookDao.researchBookById(id);
			if (book != null)
				loadCategoriesForTitle(book);
			return book;
		} catch (DaoException e) {
			throw new ServiceException("Error retrieving book : "
					+ e.getMessage(), e);
		}
	}

	
	public Category getCategoryById(int id) throws ServiceException {
		if (categories != null) {
			Category category = (Category) categories.get(new Integer(id));
			if (category != null) {
				return category;
			}
		}
		try {
			return categoryDao.researchCategoryById(id);
		} catch (DaoException e) {
			throw new ServiceException("Error retrieving category : "
					+ e.getMessage(), e);
		}
	}

	public Title getTitleById(int id) throws ServiceException {
		AudioRecord record = getAudioRecordById(id);
		if (record != null)
			return record;
		Book book = getBookById(id);
		if (book != null)
			return book;
		return null;
	}

	public Collection getTitlesBy(String name, String author, Date date)
			throws ServiceException {
		try {
			return titleDao.researchTitlesBy(name, author, date);
		} catch (DaoException e) {
			throw new ServiceException("Error : " + e.getMessage(), e);
		}
	}

	public void loadCategoriesForTitle(Title title) throws ServiceException {
		getAllCategories();
		try {
			Collection ids = relationDao.researchCategoriesIdsForTitle(title);
			Iterator it = ids.iterator();
			while (it.hasNext()) {
				Integer idCategory = (Integer) it.next();
				Category category = (Category) categories.get(idCategory);
				title.addCategory(category);
			}
		} catch (DaoException e) {
			throw new ServiceException(
					"Error retrieving categories for title : " + e.getMessage(),
					e);
		}
	}

	void modifyAudioRecord(AudioRecord audioRecord) throws ServiceException {
		try {
			audioRecordDao.updateAudioRecord(audioRecord);
		} catch (DaoException e) {
			throw new ServiceException("Error modifying audio record : "
					+ e.getMessage(), e);
		}
	}

	void modifyBook(Book book) throws ServiceException {
		try {
			bookDao.updateBook(book) ;
		} catch (DaoException e) {
			throw new ServiceException("Error modifying book : "
					+ e.getMessage(), e);
		}
	}

	public void modifyCategory(Category category) throws ServiceException {
		try {
			categoryDao.updateCategory(category);
		} catch (DaoException e) {
			throw new ServiceException("Error modifying category : "
					+ e.getMessage(), e);
		}
	}

	public void modifyTitle(Title title) throws ServiceException {
		InventoryVisitor v = new TitleModify(this);
		try {
			title.accept(v);
		} catch (InventoryException e) {
			ServiceException es = (ServiceException) e.getCause();
			throw (es);
		}
		try {
			relationDao.removeCategoriesForTitle(title);
			Iterator it = title.getCategories().iterator();
			while (it.hasNext()) {
				Category category = (Category) it.next();
				relationDao.addRelation(title, category);
			}
		} catch (DaoException e) {
			throw new ServiceException("Error modifying title : "
					+ e.getMessage(), e);
		}
	}

	void removeAudioRecord(AudioRecord audioRecord) throws ServiceException {
		try {
			// remove the record from local list
			if (audioRecords != null) {
				audioRecords.remove(new Integer(audioRecord.getId()));
			}
			// remove relation to categories
			relationDao.removeCategoriesForTitle(audioRecord);
			// delete book
			audioRecordDao.deleteAudioRecord(audioRecord);
		} catch (Exception e) {
			throw new ServiceException("Error removing audio record : "
					+ e.getMessage(), e);
		}
	}

	void removeBook(Book book) throws ServiceException {
		try {
			// remove the book from local list
			if (books != null) {
				books.remove(new Integer(book.getId()));
			}
			// remove relation to categories
			relationDao.removeCategoriesForTitle(book);
			// delete book
			bookDao.deleteBook(book);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("Error removing book : "
					+ e.getMessage(), e);
		}
	}

	public void removeCategory(Category category) throws ServiceException {
		/*
		 * two ways to manage this operation :
		 * 
		 * 1 - the categorie still have titles referenced and there is a
		 * business rule which specify that in this case we can't remove the
		 * category (category must be empty to be deleted). This kind of rule
		 * can be modeled by an aggregation into the UML class diagram between
		 * Category class and Title class. In this case, we have to throw a
		 * ServiceException if the category is not empty.
		 * 
		 * 2 - we don't care about this and we can delete a category. In this
		 * case we must destroy the reverse relation from all titles which
		 * appear into the given category
		 */

		Collection ids = null ;
		try {
			// loads titles relation for the category
			ids = relationDao.researchTitlesIdsForCategory(category) ;
		} catch (Exception e) {}
		
		// case 1 - category must be empty (without titles) to be deleted
		if (ids!=null && !ids.isEmpty()) {
			throw new ServiceException("Category must be empty to be deleted");
		}
		try {
			// if empty
			categoryDao.deleteCategory(category);
		} catch (Exception e) {
			throw new ServiceException("Error removing category : "
					+ e.getMessage(), e);
		}
	}

	public void removeCategoryFromTitle(Category category, Title title)
			throws ServiceException {
		try {
			relationDao.removeRelation(title, category);
		} catch (DaoException e) {
			throw new ServiceException(
					"Error removing relation between category and title : "
							+ e.getMessage(), e);
		}
	}

	public void removeTitle(Title title) throws ServiceException {
		InventoryVisitor v = new TitleRemove(this);
		try {
			title.accept(v);
		} catch (InventoryException e) {
			ServiceException es = (ServiceException) e.getCause();
			throw (es);
		}
	}

	public void setAudioRecordDao(AudioRecordDao audioRecordDao) {
		this.audioRecordDao = audioRecordDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setRelationDao(CategoryTitleRelationDao relationDao) {
		this.relationDao = relationDao;
	}

	public void setTitleDao(TitleDao titleDao) {
		this.titleDao = titleDao;
	}

}
