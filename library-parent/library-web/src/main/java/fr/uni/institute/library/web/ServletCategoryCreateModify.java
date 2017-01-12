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
import fr.uni.institute.library.service.InventoryManagementService;
import fr.uni.institute.library.service.impl.InventoryManagementServiceImpl;

public class ServletCategoryCreateModify extends ServletInventory {
	
	private static final  Logger logger = Logger.getLogger(ServletCategoryCreateModify.class);
	private Connection connection = null;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			 connection = getConnection();
			InventoryManagementService service = new InventoryManagementServiceImpl();
			service.setCategoryDao(new CategoryDaoJdbc(connection));
			Collection categories = service.getAllCategories();
			request.setAttribute("categories", categories);
			String categoryIdStr = request.getParameter("categoryId");
			if (categoryIdStr != null) {
				try {
					int categoryId = Integer.parseInt(categoryIdStr);
					Category category = service.getCategoryById(categoryId);
					request.setAttribute("category", category);
				} catch (Exception e) {
				}
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/WEB-INF/jsp/modifycategory.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			logger.error("Error in controller category modify : "+ e.getMessage());
			//e.printStackTrace();
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
