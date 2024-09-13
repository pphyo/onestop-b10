package com.jdc.jdbc.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.jdc.app.dao.DepartmentDao;
import com.jdc.app.dao.EmployeeDao;
import com.jdc.app.dao.impl.DepartmentDaoImpl;
import com.jdc.app.dao.impl.EmployeeDaoImpl;
import com.jdc.app.domain.Department;
import com.jdc.app.domain.Employee;
import com.jdc.app.domain.Employee.Gender;
import com.jdc.app.support.DbInitializer;

@TestMethodOrder(OrderAnnotation.class)
public class EmployeeDaoTest {
	
	static EmployeeDao empDao;
	static DepartmentDao depDao;
	
	@DisplayName("Find employees with dynamic query")
	@ParameterizedTest
	@CsvSource({
		",0,,7",
		"a,0,,3",
		",0,sale,2",
		",0,marketing,2",
		",0,hr,3",
		"a,0,hr,1",
		",400000,hr,2"
	})
	@Order(3)
	void testForFind(String name, double salary, String department, int expectedCount) {
		var result = empDao.find(name, salary, department);
		assertEquals(expectedCount, result.size());
	}
	
	@DisplayName("Create employees from method source with no exception")
	@ParameterizedTest
	@MethodSource("paramsForEmployee")
	@Order(2)
	void testForCEMSNE(Employee employee) {
		var returnedId = empDao.save(employee);

		var existedEmployee = empDao.findById(returnedId);
		
		assertAll(() -> {
			assertNotNull(existedEmployee);
			assertEquals(returnedId, existedEmployee.getId());
			assertEquals(employee.getName(), existedEmployee.getName());
			assertEquals(employee.getGender(), existedEmployee.getGender());
			assertEquals(employee.getSalary(), existedEmployee.getSalary());
			assertEquals(employee.getPhone(), existedEmployee.getPhone());
			assertEquals(employee.getAssignAt(), existedEmployee.getAssignAt());
			
			assertNotNull(existedEmployee.getDepartment());
			assertEquals(employee.getDepartment().getId(), existedEmployee.getDepartment().getId());
			assertEquals(employee.getDepartment().getName(), existedEmployee.getDepartment().getName());
		});
	}
	
	static Stream<Arguments> paramsForEmployee() {
		var hr = depDao.findById(1);
		var sale = depDao.findById(2);
		var marketing = depDao.findById(3);
		
		var emp1 = new Employee();
		emp1.setName("Charli Puth");
		emp1.setGender(Gender.Male);
		emp1.setSalary(300_000);
		emp1.setAssignAt(LocalDate.of(2023, 07, 03));
		emp1.setDepartment(marketing);
		
		var emp2 = new Employee();
		emp2.setName("Ariana Grande");
		emp2.setSalary(600_000);
		emp2.setGender(Gender.Female);
		emp2.setPhone("09432123543");
		emp2.setAssignAt(LocalDate.of(2023, 04, 03));
		emp2.setDepartment(sale);
		
		var emp3 = new Employee();
		emp3.setName("Shawn Mendenz");
		emp3.setGender(Gender.Male);
		emp3.setSalary(350_000);
		emp3.setPhone("09645231234");
		emp3.setAssignAt(LocalDate.of(2024, 03, 05));
		emp3.setDepartment(hr);
		
		var emp4 = new Employee();
		emp4.setName("Fredie Mercury");
		emp4.setGender(Gender.Male);
		emp4.setSalary(450_000);
		emp4.setPhone("09123345567");
		emp4.setAssignAt(LocalDate.of(2024, 05, 30));
		emp4.setDepartment(hr);
		
		return Stream.of(
					Arguments.of(emp1),
					Arguments.of(emp2),
					Arguments.of(emp3),
					Arguments.of(emp4)
				);
	}
	
	@ParameterizedTest
	@CsvSource({
		"Alan Walker, Male, 300000, 09123123123, 2023-10-01, 2, 1",
		"Annie Marie, Female, 400000, 0911112222, 2024-02-01, 1, 2",
		"Kendric Lama, Male, 500000, 0912121212, 2024-04-01, 3, 3"
	})
	@Order(1)
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
