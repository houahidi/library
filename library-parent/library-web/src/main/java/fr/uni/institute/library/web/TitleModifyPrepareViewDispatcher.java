package fr.uni.institute.library.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.uni.institute.library.business.inventory.AudioRecord;
import fr.uni.institute.library.business.inventory.Book;
import fr.uni.institute.library.business.inventory.DefaultInventoryVisitor;

public class TitleModifyPrepareViewDispatcher extends DefaultInventoryVisitor {

	private ServletContext context;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public TitleModifyPrepareViewDispatcher(ServletContext context,
			HttpServletRequest request, HttpServletResponse response) {
		this.context = context;
		this.request = request;
		this.response = response;
	}

	public void visitAudioRecord(AudioRecord anAudioRecord) {
		try {
			RequestDispatcher rd = this.context
					.getRequestDispatcher("/WEB-INF/jsp/audio_create_modify.jsp");
			rd.forward(this.request, this.response);
		} catch (Exception e) {
			System.out.println("Error forwarding to /WEB-INF/jsp/audio_create_modify.jsp : " + e.getMessage());
		}
	}

	public void visitBook(Book aBook) {
		try {
			RequestDispatcher rd = this.context
					.getRequestDispatcher("/WEB-INF/jsp/book_create_modify.jsp");
			rd.forward(this.request, this.response);
		} catch (Exception e) {
			System.out.println("Error forwarding to /WEB-INF/jsp/book_create_modify.jsp : " + e.getMessage());
		}
	}

}
