package fr.uni.institute.library.dao;

import java.util.Collection;
import java.util.Date;

import fr.uni.institute.library.business.inventory.Title;

public interface TitleDao {
	public void createTitle(Title title) throws DaoException;

	public void deleteTitle(Title title) throws DaoException;

	public Collection researchAllTitles() throws DaoException;

	public Title researchTitleById(int id) throws DaoException;

	public Collection researchTitlesBy(String pName, String pAuthor,
			Date date) throws DaoException;

	public void updateTitle(Title title) throws DaoException;
}
