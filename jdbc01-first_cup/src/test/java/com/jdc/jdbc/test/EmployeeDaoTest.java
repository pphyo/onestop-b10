package com.jdc.jdbc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.app.dao.DepartmentDao;
import com.jdc.app.dao.EmployeeDao;
import com.jdc.app.dao.impl.DepartmentDaoImpl;
import com.jdc.app.dao.impl.EmployeeDaoImpl;
import com.jdc.app.domain.Department;
import com.jdc.app.domain.Employee;
import com.jdc.app.domain.Employee.Gender;
import com.jdc.app.support.DbInitializer;

public class EmployeeDaoTest {
	
	static EmployeeDao empDao;
	static DepartmentDao depDao;
	
	@ParameterizedTest
	@CsvSource({
		"Alan Walker, Male, 300000, 09123123123, 2023-10-01, 2, 1",
		"Annie Marie, Female, 400000, 0911112222, 2024-02-01, 1, 2",
		"Kendric Lama, Male, 500000, 0912121212, 2024-04-01, 3, 3"
	})
	void test_for_create_employees_with_no_exception(String name, 
			Gender gender, double salary, String phone,
			LocalDate assignAt, int departmentId, int expectedCount) {
		
		Employee emp = new Employee();
		emp.setName(name);
		emp.setGender(gender);
		emp.setSalary(salary);
		emp.setPhone(phone);
		emp.setAssignAt(assignAt);
		
		Department department = depDao.findById(departmentId);
		emp.setDepartment(department);
		
		empDao.save(emp);
		
		assertEquals(expectedCount, empDao.count());
		
	}
	
	@BeforeAll
	static void setUpBeforeClass() {
		DbInitializer.drop("department", "employee");
		DbInitializer.create();
		DbInitializer.init();
		empDao = new EmployeeDaoImpl();
		depDao = new DepartmentDaoImpl();
	}

}
