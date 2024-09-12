package com.jdc.jdbc.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.app.dao.DepartmentDao;
import com.jdc.app.dao.impl.DepartmentDaoImpl;
import com.jdc.app.domain.Department;
import com.jdc.app.support.DbInitializer;

@TestMethodOrder(OrderAnnotation.class)
public class DeparmentDaoTest {

	static DepartmentDao depDao;
	
	@ParameterizedTest
	@CsvSource("1, 4")
	void test_for_delete(int idForDelete, int expectedCount) {
		depDao.delete(idForDelete);
		var count = depDao.findAll().size();
		assertEquals(expectedCount, count);
	}
	
	@ParameterizedTest
	@CsvSource({"Accounting, 4", "Finance, 5"})
	@Order(4)
	void test_for_create(String name, int expectedId) {
		var returnedId = depDao.create(name);
		assertEquals(expectedId, returnedId);
		
		var department = depDao.findById(returnedId);
		assertNotNull(department);
		assertEquals(name, department.getName());
	}
	
	@ParameterizedTest
	@CsvSource({"Sales, 2", "Human Resources, 1"})
	@Order(3)
	void test_for_update(String nameForUpdate, int id) {
		
		var updatedId = depDao.update(nameForUpdate, id);
		
		if(updatedId > 0) {
			var department = depDao.findById(updatedId);
		
			assertAll(() -> {
				assertNotNull(department);
				assertEquals(id, department.getId());
				assertEquals(nameForUpdate, department.getName());
			});
		}
	}
	
	@ParameterizedTest
	@ValueSource(ints = 3)
	@Order(1)
	void test_for_findAll(int expected) {
		var list = depDao.findAll();
		assertEquals(expected, list.size());
	}

	@ParameterizedTest
	@CsvSource({ "1, HR", "2, Sale", "3, Marketing" })
	@Order(2)
	void test_for_findById(int givenId, String expectedName) {

		Department result = depDao.findById(givenId);

		assertEquals(expectedName, result.getName());
	}
	
	@ParameterizedTest
	@MethodSource(value = "getIntList")
	void test_getIntList(List<Integer> list) {
		System.out.println(list);
	}
	
	static Stream<Arguments> getIntList() {
		return Stream.of(Arguments.of(List.of(1, 2, 3, 4, 5)));
	}

	@BeforeAll
	static void setUpBeforeClass() {
		DbInitializer.drop("employee", "department");
		DbInitializer.create();
		DbInitializer.init();

		depDao = new DepartmentDaoImpl();
	}

}
