package com.jdc.bm.test;

import org.junit.jupiter.api.Test;

import com.jdc.bm.entity.Role;
import com.jdc.bm.entity.Staff;

import jakarta.persistence.Persistence;

public class BiDirectionalMappingTest {
	
	@Test
	void test() {
		try(var emf = Persistence.createEntityManagerFactory("bm");
				var em = emf.createEntityManager()) {
			
			var tx = em.getTransaction();
			
			tx.begin();
			
			Role admin = new Role();
			admin.setName("Admin");
			
			Staff christopher = new Staff();
			christopher.setName("Chrostopher Neo");
			christopher.setSalary(40_000);
			
			// set bi directional mapping
			admin.setStaff(christopher);
			christopher.setRole(admin);
			
			em.persist(christopher);
			em.persist(admin);
			
			tx.commit();
			
		}
	}

}
