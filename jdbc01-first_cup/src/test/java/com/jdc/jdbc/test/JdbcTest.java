package com.jdc.jdbc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class JdbcTest {
	
	@Test
	void test_for_getting_connection_with_mysql() {
		try(var conn = 
				DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/jdbc_db", 
						"onestop_user", 
						"onestoppass");
				var stmt = conn.createStatement()) {
			
			var result = stmt.executeUpdate(
					"insert into category (name) values ('News')"
					);
			
			assertEquals(1, result);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}









