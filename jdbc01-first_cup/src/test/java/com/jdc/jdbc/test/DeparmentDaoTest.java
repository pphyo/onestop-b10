package com.jdc.jdbc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.app.DbInitializer;
import com.jdc.app.dao.DepartmentDao;
import com.jdc.app.dao.impl.DepartmentDaoImpl;

public class DeparmentDaoTest {
	
	@ParameterizedTest
	@CsvSource(value = {
		"1, HR",
		"2, Sale",
		"3, Marketing"
	})
	void test_for_findById(int givenId, String expectedName) {
		DbInitializer.drop("employee", "department");
		DbInitializer.create();
		DbInitializer.init();
		
		DepartmentDao depDao = new DepartmentDaoImpl();
		Object[] result = depDao.findById(givenId);
		assertEquals(expectedName, result[1]);
	}

}






