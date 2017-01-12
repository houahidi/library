package fr.uni.institute.library.web;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.uni.institute.library.business.inventory.Category;
import fr.uni.institute.library.dao.jdbc.CategoryDaoJdbc;
import fr.uni.institute.library.service.InventoryManagementService;
import fr.uni.institute.library.service.impl.InventoryManagementServiceImpl;

public class ServletCategorySave extends ServletInventory {
	private static final Logger logger = Logger.getLogger(ServletCategorySave.class);
	Connection connection  = null;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			connection = getConnection();
			InventoryManagementService service = new InventoryManagementServiceImpl();
			service.setCategoryDao(new CategoryDaoJdbc(connection));
			Category category;
			String categoryIdStr = request.getParameter("categoryId");
			if (categoryIdStr != null) {
				int categoryId = Integer.parseInt(categoryIdStr);
				category = service.getCategoryById(categoryId);
			} else {
				category = new Category(0);
			}
			String categoryName = request.getParameter("categoryName");
			if (categoryName != null)
				category.setName(categoryName);
			String categoryParentIdStr = request
					.getParameter("categoryParentId");
			if (categoryParentIdStr != null && !"".equals(categoryParentIdStr)) {
				Category parent = service.getCategoryById(Integer
						.parseInt(categoryParentIdStr));
				category.setParent(parent);
			} else {
				category.setParent(null);
			}
			if (category.getId() > 0) {
				service.modifyCategory(category);
				request.setAttribute("message", "Category "	+ category.getName() + " modified");
			} else {
				service.addCategory(category);
				request.setAttribute("message", "Category "	+ category.getName() + " created");
			}

			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/categories.do");
			rd.forward(request, response);
		}catch (Exception e) {
			logger.error("Error in controller ServletCategorySave : " + e.getMessage());
			//e.printStackTrace() ;
		}finally{
		
			try {
				if (connection!= null && ! connection.isClosed())
					connection.close();
			
			} catch (SQLException e) {
				logger.error("Error in controller ServletCategorySave : "+ e.getMessage());
			}	
		}
	}
}
