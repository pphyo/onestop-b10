package com.jdc.app;

import java.sql.SQLException;

public class DbInitializer {
	
	// create table department
	private static final String depSql = """
			create table department(
				id int primary key auto_increment,
				name varchar(100) not null
			);
			""";
	
	// create table employee
	private static final String empSql = """
			create table employee(
				id int primary key auto_increment,
				name varchar(50) not null,
				salary decimal(9, 2) not null,
				phone varchar(13),
				department_id int not null,
				foreign key('department_id') references department('id')
			);
			""";
	
	public static void create() {
		try(var conn = DbConnector.getDbConnection();
				var stmt = conn.createStatement()) {
			
			stmt.execute(depSql);
			stmt.execute(empSql);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void init() {
		try(var conn = DbConnector.getDbConnection();
				var stmt = conn.createStatement()) {
			
			stmt.executeUpdate("insert into department (name) values ('HR'), ('Sale'), ('Marketing')");
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void drop(String... tables) {
		try(var conn = DbConnector.getDbConnection();
				var stmt = conn.createStatement()) {
			
			for(var table : tables) {
				stmt.execute("drop table if exists %s".formatted(table));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}







