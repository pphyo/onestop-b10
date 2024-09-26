package com.jdc.bm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.bm.entity.Role;
import com.jdc.bm.entity.Staff;

@TestMethodOrder(OrderAnnotation.class)
public final class BiDirectionalMappingTest extends BaseTest {

	@ParameterizedTest
	@CsvSource({ "Admin, Christopher Neo, 40_000" })
	@Order(1)
	void test_for_inserting_one_to_one_bi_mapping(String roleName, String staffName, double salary) {
		var tx = em.getTransaction();

		tx.begin();

		Role admin = new Role();
		admin.setName(roleName);

		Staff christopher = new Staff();
		christopher.setName(staffName);
		christopher.setSalary(salary);

		// set bi directional mapping
		admin.setStaff(christopher);

		em.persist(christopher);

		tx.commit();

	}

	@ParameterizedTest
	@CsvSource({ "1, Admin, Christopher Neo, 40_000" })
	@Order(2)
	void test_for_find(int id, String roleName, String staffName, double salary) {
		var role = em.find(Role.class, id);

		assertNotNull(role);
		assertEquals(roleName, role.getName());
		assertEquals(staffName, role.getStaff().getName());
		assertEquals(salary, role.getStaff().getSalary());
		assertEquals(LocalDate.now(), role.getStaff().getAssignAt());
	}

}
