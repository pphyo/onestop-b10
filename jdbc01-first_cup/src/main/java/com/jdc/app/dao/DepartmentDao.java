package com.jdc.app.dao;

import java.util.List;

public interface DepartmentDao {
	
	int create(String name);
	int update(String newName, int id);
	void delete(int id);
	Object[] findById(int id);
	List<Object[]> findAll();
	
}
