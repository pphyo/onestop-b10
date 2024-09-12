package com.jdc.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdc.app.dao.EmployeeDao;
import com.jdc.app.domain.Department;
import com.jdc.app.domain.Employee;
import com.jdc.app.support.DbConnector;

public class EmployeeDaoImpl implements EmployeeDao {

	private static final String SELECT = """
			select d.id dep_id, d.name dep_name, e.id emp_id,
			e.name emp_name, e.salary,
			e.phone from department d join employee e
			on d.id = e.department_id""";

	@Override
	public int save(Employee employee) {
		return 0;
	}

	@Override
	public int update(int id, Employee employee) {
		return 0;
	}

	@Override
	public void delete(int id) {
	}

	@Override
	public Employee findById(int id) {
		try (var conn = DbConnector.getDbConnection();
				var stmt = conn.prepareStatement(SELECT.concat(" where e.id = ?"))) {

			stmt.setInt(1, id);

			var rs = stmt.executeQuery();

			while (rs.next()) {
				return domain(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Employee> findAll() {

		List<Employee> result = new ArrayList<>();

		try (var conn = DbConnector.getDbConnection(); 
				var stmt = conn.prepareStatement(SELECT)) {

			var rs = stmt.executeQuery();

			while (rs.next()) {
				result.add(domain(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Employee> find(String name, double salary, String department) {
		List<Employee> result = new ArrayList<>();

		try (var conn = DbConnector.getDbConnection(); 
				var stmt = conn.prepareStatement(SELECT.concat(" where e.name = ? and e.salary = ? and d.name = ?"))) {

			stmt.setString(1, name);
			stmt.setDouble(2, salary);
			stmt.setString(3, department);
			
			var rs = stmt.executeQuery();

			while (rs.next()) {
				result.add(domain(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	private Employee domain(ResultSet rs) throws SQLException {

		Employee emp = new Employee();
		emp.setId(rs.getInt("emp_id"));
		emp.setName(rs.getString("emp_name"));
		emp.setSalary(rs.getDouble("salary"));
		emp.setPhone(rs.getString("phone"));

		Department dep = new Department();
		dep.setId(rs.getInt("dep_id"));
		dep.setName(rs.getString("dep_name"));

		emp.setDepartment(dep);

		return emp;
	}

}
