package com.jdc.app.dao;

import java.util.List;

import com.jdc.app.domain.Employee;

public interface EmployeeDao {
	
	/**
	 * @throws IllegalArgumentException if employee is null
	 * @param employee object for save
	 * @return generated id
	 * @author pphyo
	 */
	int save(Employee employee);
	int update(int id, Employee employee);
	void delete(int id);
	long count();
	Employee findById(int id);
	List<Employee> find(String name, double salary, String department);

}







