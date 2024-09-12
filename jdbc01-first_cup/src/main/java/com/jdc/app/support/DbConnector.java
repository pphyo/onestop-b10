package com.jdc.app.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
	
	private static final String URL = "jdbc:mysql://localhost:3306/jdbc_db";
	private static final String USR = "onestop_user";
	private static final String PWD = "onestoppass";
	
	private DbConnector() {}

	public static Connection getDbConnection() throws SQLException {
		return DriverManager.getConnection(URL, USR, PWD);
	}
	
}
