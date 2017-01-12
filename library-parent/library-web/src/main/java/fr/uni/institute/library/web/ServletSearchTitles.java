package fr.uni.institute.library.web;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.uni.institute.library.dao.jdbc.TitleDaoJdbc;
import fr.uni.institute.library.service.InventoryManagementService;
import fr.uni.institute.library.service.impl.InventoryManagementServiceImpl;

public class ServletSearchTitles extends ServletInventory {

	private static final  Logger logger = Logger.getLogger(ServletSearchTitles.class);
	private Connection connection = null;
		
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("ServletSearchTitles - display titles");
		
		try {
			ResearchTitlesDTO researchDTO = (ResearchTitlesDTO) request.getSession().getAttribute("researchDTO");
			
			request.getSession().setAttribute("researchDTO", researchDTO) ;
			
			if (researchDTO == null) {
				researchDTO = new ResearchTitlesDTO();
			}
			String pName = request.getParameter("pName");
			String pAuthor = request.getParameter("pAuthor");
			String pDate = request.getParameter("pDate");
			if (pName != null || pAuthor != null || pDate != null) {
				researchDTO.setResearchParameterAuthor(pAuthor);
				researchDTO.setResearchParameterName(pName);
				researchDTO.setResearchParameterDate(pDate);
			} else {
				pName = researchDTO.getResearchParameterName();
				pAuthor = researchDTO.getResearchParameterAuthor();
			}
			Date researchDate = null;
			try {
				researchDate = researchDTO.getResearchParameterDateAsDate();
			} catch (Exception e) {
				request.setAttribute("invalidDateFormat", "*");
			}

			if (pName != null || pAuthor != null || researchDate != null) {
				connection = getConnection();
				InventoryManagementService service = new InventoryManagementServiceImpl();
				service.setTitleDao(new TitleDaoJdbc(connection));
				
				researchDTO.setTitles(new ArrayList(service.getTitlesBy(pName,pAuthor, researchDate)));
			}

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/displaytitles.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			//e.printStackTrace() ;
		}finally{
			logger.info("libération de la connexion");
			try {
				if (connection!= null && ! connection.isClosed())
					connection.close();
			
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}	
		}
	}
}
