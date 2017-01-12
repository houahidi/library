package fr.uni.institute.library.dao.jdbc;

import java.sql.Connection;

public abstract class InventoryDaoJdbc {

	private Connection connection;

	public InventoryDaoJdbc() {
	}

	public InventoryDaoJdbc(Connection connection) {
		this.connection = connection;
	}

	protected Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
