package com.jdc.app.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.jdc.app.DbConnector;
import com.jdc.app.dao.DepartmentDao;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public int create(String name) {
		return 0;
	}

	@Override
	public int update(String newName, int id) {
		return 0;
	}

	@Override
	public void delete(int id) {
	}

	@Override
	public Object[] findById(int id) {
		final String sql = 
				"select d.id dep_id, d.name dep_name from department d where id = %d".formatted(id);
		
		try(var conn = DbConnector.getDbConnection();
				var stmt = conn.createStatement()) {
			
			var rs = stmt.executeQuery(sql);
			
			int depId = 0;
			String depName = null;
			
			while(rs.next()) {
				depId = rs.getInt("dep_id");
				depName = rs.getString("dep_name");
			}
		
			return new Object[] {depId, depName};
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Object[]> findAll() {
		return null;
	}

}
