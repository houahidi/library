package fr.uni.institute.library.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.uni.institute.library.business.inventory.AudioRecord;
import fr.uni.institute.library.business.inventory.Book;
import fr.uni.institute.library.business.inventory.DefaultInventoryVisitor;

public abstract class TitleViewDispatcher extends DefaultInventoryVisitor {

	private ServletContext context;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public TitleViewDispatcher(ServletContext context,
			HttpServletRequest request, HttpServletResponse response) {
		this.context = context;
		this.request = request;
		this.response = response;
	}

	public void visitAudioRecord(AudioRecord anAudioRecord) {
		try {
			RequestDispatcher rd = this.context
					.getRequestDispatcher("/WEB-INF/jsp/" + getJSPFileNameForAudioRecord() + ".jsp");
			rd.forward(this.request, this.response);
		} catch (Exception e) {
			System.out.println("Error forwarding to /WEB-INF/jsp/" + getJSPFileNameForAudioRecord() + ".jsp : " + e.getMessage());
		}
	}

	public void visitBook(Book aBook) {
		try {
			RequestDispatcher rd = this.context
					.getRequestDispatcher("/WEB-INF/jsp/" + getJSPFileNameForBook() + ".jsp");
			rd.forward(this.request, this.response);
		} catch (Exception e) {
			System.out.println("Error forwarding to /WEB-INF/jsp/" + getJSPFileNameForBook() + ".jsp : " + e.getMessage());
		}
	}
	
	public abstract String getJSPFileNameForBook() ;
	
	public abstract String getJSPFileNameForAudioRecord() ;

}
