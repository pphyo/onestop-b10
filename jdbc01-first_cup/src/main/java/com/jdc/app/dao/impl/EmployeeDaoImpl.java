package com.jdc.app.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdc.app.dao.EmployeeDao;
import com.jdc.app.domain.Department;
import com.jdc.app.domain.Employee;
import com.jdc.app.domain.Employee.Gender;
import com.jdc.app.support.AppConstant;
import com.jdc.app.support.DbConnector;
import com.jdc.app.support.JdbcException;
import com.jdc.app.support.StringUtils;

public class EmployeeDaoImpl implements EmployeeDao {

	private static final String SELECT = """
			select d.id dep_id, d.name dep_name, e.id emp_id,
			e.name emp_name, e.gender, e.salary,
			e.phone, e.assign_at from department d join employee e
			on d.id = e.department_id""";

	@Override
	public int save(Employee employee) {
		
		validate(employee);
		
		final String sql = """
				insert into employee (name, gender, salary, 
				phone, assign_at, department_id) 
				values (?, ?, ?, ?, ?, ?)
				""";
		
		try(var conn = DbConnector.getDbConnection();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getGender().name());
			stmt.setDouble(3, employee.getSalary());
			stmt.setString(4, employee.getPhone());
			stmt.setDate(5, Date.valueOf(employee.getAssignAt()));
			stmt.setInt(6, employee.getDepartment().getId());
			
			stmt.executeUpdate();
			
			var rs = stmt.getGeneratedKeys();
			
			while(rs.next())
				return rs.getInt(1);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
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
	public long count() {
		final String sql = "select count(id) from employee";
		try(var conn = DbConnector.getDbConnection();
				var stmt = conn.createStatement()) {
			
			var rs = stmt.executeQuery(sql);
			while(rs.next()) {
				return rs.getLong(1);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
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
	
	private void validate(Employee employee) {
		if(null == employee) {
			throw new JdbcException("Employee cannot be null!");
		}
		
		if(StringUtils.isEmpty(employee.getName())) {
			throw new JdbcException("Name cannot be empty!");
		}
		
		if(null == employee.getGender()) {
			throw new JdbcException("Gender cannot be null!");
		}
		
		if(employee.getSalary() < AppConstant.BASIC_SALARY) {
			throw new JdbcException("Salary must be at least %.2f!".formatted(AppConstant.BASIC_SALARY));
		}
		
		if(null == employee.getDepartment() || employee.getDepartment().getId() <= 0) {
			throw new JdbcException("Please set department for employee!");
		}
	}

	private Employee domain(ResultSet rs) throws SQLException {

		Employee emp = new Employee();
		emp.setId(rs.getInt("emp_id"));
		emp.setName(rs.getString("emp_name"));
		emp.setGender(Gender.valueOf(rs.getString("gender")));
		emp.setSalary(rs.getDouble("salary"));
		emp.setPhone(rs.getString("phone"));
		emp.setAssignAt(rs.getDate("assign_at").toLocalDate());

		Department dep = new Department();
		dep.setId(rs.getInt("dep_id"));
		dep.setName(rs.getString("dep_name"));

		emp.setDepartment(dep);

		return emp;
	}

}
