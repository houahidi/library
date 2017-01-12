package fr.uni.institute.library.web;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.uni.institute.library.business.inventory.Category;
import fr.uni.institute.library.business.inventory.Title;
import fr.uni.institute.library.dao.TitleDao;
import fr.uni.institute.library.dao.jdbc.AudioRecordDaoJdbc;
import fr.uni.institute.library.dao.jdbc.BookDaoJdbc;
import fr.uni.institute.library.dao.jdbc.CategoryDaoJdbc;
import fr.uni.institute.library.dao.jdbc.CategoryTitleRelationDaoJdbc;
import fr.uni.institute.library.dao.jdbc.TitleDaoJdbc;
import fr.uni.institute.library.service.InventoryManagementService;
import fr.uni.institute.library.service.impl.InventoryManagementServiceImpl;

public abstract class ServletTitleSave extends ServletInventory {
	
	protected abstract void completeTitle(HttpServletRequest request, Title title) ;

	protected abstract Title createNewTitle() ;
	
	private static final  Logger logger = Logger.getLogger(ServletTitleSave.class);
	private Connection connection = null;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			connection = getConnection();
			InventoryManagementService service = new InventoryManagementServiceImpl();
			TitleDao titleDao = new TitleDaoJdbc(connection) ;
			service.setTitleDao(titleDao);
			service.setAudioRecordDao(new AudioRecordDaoJdbc(connection,titleDao));
			service.setBookDao(new BookDaoJdbc(connection,titleDao));
			service.setCategoryDao(new CategoryDaoJdbc(connection));
			service.setRelationDao(new CategoryTitleRelationDaoJdbc(connection));

			String strTitleId = request.getParameter("titleId");
			Title title = null;
			if (strTitleId != null) {
				title = service.getTitleById(Integer.parseInt(strTitleId));
				if (title != null)
					title.emptyCategories();
			} else {
				title = this.createNewTitle();
			}
			title.setName(request.getParameter("titleName"));
			title.setAuthor(request.getParameter("titleAuthor"));
			String pDate = request.getParameter("titleDate");
			Date date = null;
			if (pDate != null) {
				String[] formats = { "dd/MM/yyyy", "MM/yyyy", "dd/MM/yy", "MM/yy", "yyyy" };
				for (int i = 0; i < formats.length; i++) {
					try {
						SimpleDateFormat df = new SimpleDateFormat(formats[i]);
						date = df.parse(pDate);
						request.removeAttribute("invalidDateFormat");
						break;
					} catch (Exception e) {
						System.out.println("Invalid date format : " + formats[i]) ;
						request.setAttribute("invalidDateFormat", "invalid");
					}
				}
			}
			if (date != null) {
				title.setDate(date);
			}
			String[] categoriesId = request.getParameterValues("titlecategoriesid");
			for (int i = 0; i < categoriesId.length; i++) {
				Category category = service.getCategoryById(Integer
						.parseInt(categoriesId[i]));
				title.addCategory(category);
			}
			this.completeTitle(request, title) ;
			if (strTitleId != null) {
				service.modifyTitle(title);
				request.setAttribute("message", "Title "	+ title.getName() + " modified");
			} else {
				service.addTitle(title);
				request.setAttribute("message", "Title "	+ title.getName() + " created");
			}			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/titles.do");
			rd.forward(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
			//e.printStackTrace() ;
		}finally{
		
			try {
				if (connection!= null && ! connection.isClosed())
					connection.close();
			
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}	
		}
	}
}
