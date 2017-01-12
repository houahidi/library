package fr.uni.institute.library.web;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


public class ServletInventory extends HttpServlet {
	
	protected Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		if (ctx == null) throw new SQLException("Connection error : No Context");
		DataSource ds = (DataSource) ctx
				.lookup("java:comp/env/jdbc/UniLibraryDS");
		if (ds == null)
			throw new SQLException("Connection error : No datasource");
		return ds.getConnection();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			doPost(request, response);
		} catch (Exception e) {
			System.out.println("Error :" + e.getMessage());
		}
	}
	
}
