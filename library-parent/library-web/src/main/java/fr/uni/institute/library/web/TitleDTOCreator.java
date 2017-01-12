package fr.uni.institute.library.web;

import javax.servlet.http.HttpServletRequest;

import fr.uni.institute.library.business.inventory.AudioRecord;
import fr.uni.institute.library.business.inventory.Book;
import fr.uni.institute.library.business.inventory.DefaultInventoryVisitor;

public class TitleDTOCreator extends DefaultInventoryVisitor {

	private HttpServletRequest request;
	
	public TitleDTOCreator(HttpServletRequest request) {
		this.request = request;
	}

	public void visitAudioRecord(AudioRecord anAudioRecord) {
		request.setAttribute("titleDTO", new AudioRecordDTO(anAudioRecord)) ;
	}

	public void visitBook(Book aBook) {
		request.setAttribute("titleDTO", new BookDTO(aBook)) ;
	}
	
}
