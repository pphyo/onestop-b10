package com.jdc.app.dao;

import java.util.List;

import com.jdc.app.domain.Department;

public interface DepartmentDao {
	
	int create(String name);
	int update(String newName, int id);
	void delete(int id);
	Department findById(int id);
	List<Department> findAll();
	
}
