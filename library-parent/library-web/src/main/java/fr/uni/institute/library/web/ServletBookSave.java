package fr.uni.institute.library.web;

import javax.servlet.http.HttpServletRequest;

import fr.uni.institute.library.business.inventory.Book;
import fr.uni.institute.library.business.inventory.Title;

public class ServletBookSave extends ServletTitleSave {
	protected void completeTitle(HttpServletRequest request, Title title){
		Book book = (Book)title ;
		book.setIsbn(request.getParameter("titleIsbn"));
		try {
			book.setPages(Integer.parseInt(request.getParameter("titlePages")));
		} catch (Exception e) {	}
	}
	
	protected Title createNewTitle() {
		return new Book(0) ;
	}
	
}
