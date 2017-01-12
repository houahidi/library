package fr.uni.institute.library.web;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.uni.institute.library.business.inventory.Category;
import fr.uni.institute.library.dao.jdbc.CategoryDaoJdbc;
import fr.uni.institute.library.dao.jdbc.CategoryTitleRelationDaoJdbc;
import fr.uni.institute.library.service.InventoryManagementService;
import fr.uni.institute.library.service.ServiceException;
import fr.uni.institute.library.service.impl.InventoryManagementServiceImpl;

public class ServletCategoryDelete extends ServletInventory {
	private static final  Logger logger = Logger.getLogger(ServletSearchTitles.class);
	private Connection connection = null;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
	
		try {
			connection = getConnection();
			InventoryManagementService service = new InventoryManagementServiceImpl();
			service.setCategoryDao(new CategoryDaoJdbc(connection));
			service.setRelationDao(new CategoryTitleRelationDaoJdbc(connection)) ;
			Collection categories = service.getAllCategories() ;
			request.setAttribute("categories", categories);
			try {
				int categoryId = Integer.parseInt(request.getParameter("categoryId")) ;
				Category category = service.getCategoryById(categoryId) ;
				service.removeCategory(category) ;
				request.setAttribute("message", "Category " + category.getName() + " deleted") ;
			}
			catch (ServiceException se){
				request.setAttribute("message", "Service error : " + se.getMessage()) ;
			}
			catch (Exception e) {
				request.setAttribute("message", "Error deleting category : " + e.getMessage()) ;
			}

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/categories.do");
			rd.forward(request, response);
		} catch (Exception e) {
			logger.error("Error in controller category delete : " + e.getMessage());
			//e.printStackTrace() ;
		}finally{
			try {
				if (connection!= null && ! connection.isClosed())
					connection.close();
			
			} catch (SQLException e) {
				logger.error("Error in controller category modify : "+ e.getMessage());
			}	
		}
	}
}
