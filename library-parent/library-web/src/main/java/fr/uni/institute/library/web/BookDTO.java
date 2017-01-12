package fr.uni.institute.library.web;

import fr.uni.institute.library.business.inventory.Book;


public class BookDTO extends TitleDTO {

	public BookDTO(Book book){
		super(book) ;
	}
	
	public String getIsbn(){
		return ((Book) getTitle()).getIsbn() ;
	}

	public int getPages(){
		return ((Book) getTitle()).getPages() ;
	}
	
}
