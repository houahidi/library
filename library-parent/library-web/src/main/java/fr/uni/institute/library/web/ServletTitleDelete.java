package fr.uni.institute.library.web;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.uni.institute.library.business.inventory.Title;
import fr.uni.institute.library.dao.TitleDao;
import fr.uni.institute.library.dao.jdbc.AudioRecordDaoJdbc;
import fr.uni.institute.library.dao.jdbc.BookDaoJdbc;
import fr.uni.institute.library.dao.jdbc.CategoryDaoJdbc;
import fr.uni.institute.library.dao.jdbc.CategoryTitleRelationDaoJdbc;
import fr.uni.institute.library.dao.jdbc.TitleDaoJdbc;
import fr.uni.institute.library.service.InventoryManagementService;
import fr.uni.institute.library.service.impl.InventoryManagementServiceImpl;

public class ServletTitleDelete extends ServletInventory {
	private static final  Logger logger = Logger.getLogger(ServletTitleDelete.class);
	private Connection connection = null;
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("titleId"));
			 connection = getConnection();
			InventoryManagementService service = new InventoryManagementServiceImpl();
			TitleDao titleDao = new TitleDaoJdbc(connection) ;
			service.setTitleDao(titleDao);
			service.setAudioRecordDao(new AudioRecordDaoJdbc(connection,titleDao));
			service.setBookDao(new BookDaoJdbc(connection,titleDao));
			service.setCategoryDao(new CategoryDaoJdbc(connection));
			service.setRelationDao(new CategoryTitleRelationDaoJdbc(
					connection));
			Title title = service.getTitleById(id);
			if (title!=null){
				service.removeTitle(title) ;
				request.setAttribute("message", "title (id=" + title.getId() + ") " + title.getName() + " deleted");
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
