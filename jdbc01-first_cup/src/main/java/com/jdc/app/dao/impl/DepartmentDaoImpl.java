package com.jdc.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdc.app.dao.DepartmentDao;
import com.jdc.app.domain.Department;
import com.jdc.app.support.DbConnector;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public int create(String name) {
		final String sql = "insert into department (name) values (?)";
		
		try(var conn = DbConnector.getDbConnection();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, name);
			
			stmt.executeUpdate();
			
			var rs = stmt.getGeneratedKeys(); // select id from table where id = ?
			while(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int saveAll(List<Department> departments) {
		int resultCount = 0;
		final String sql = "insert into department (name) values (?)";
		try(var conn = DbConnector.getDbConnection();
				var stmt = conn.prepareStatement(sql)) {
			
			for(var department : departments) {
				
				stmt.setString(1, department.getName());
				
				stmt.addBatch();
				
			}
			
			resultCount = stmt.executeBatch().length;
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return resultCount;
	}

	@Override
	public int update(String newName, int id) {
		final String sql = "update department set name = ? where id = ?";
		
		int updatedCount = 0;
		
		try(var conn = DbConnector.getDbConnection();
				var stmt = conn.prepareStatement(sql)) {
			
			// set parameters
			stmt.setString(1, newName);
			stmt.setInt(2, id);
			
			updatedCount = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updatedCount > 0 ? id : 0;
	}

	@Override
	public void delete(int id) {
		final String sql = "delete from department where id = ?";
		try(var conn = DbConnector.getDbConnection();
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Department findById(int id) {
		final String sql = 
				"select d.id dep_id, d.name dep_name from department d where id = %d".formatted(id);
		
		try(var conn = DbConnector.getDbConnection();
				var stmt = conn.createStatement()) {
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				var department = new Department();
				department.setId(rs.getInt("dep_id"));
				department.setName(rs.getString("dep_name"));
				return department;
			}
		
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Department> findAll() {
		
		var result = new ArrayList<Department>();
		final String sql = "select * from department";
		
		try(var conn = DbConnector.getDbConnection();
				var stmt = conn.createStatement()) {
			
			var rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				var department = new Department();
				department.setId(rs.getInt("id"));
				department.setName(rs.getString("name"));
				result.add(department);
			}
			
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}

}







