package com.jdc.inhm.test;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.jdc.inhm.DailyWorker;
import com.jdc.inhm.OfficeStaff;
import com.jdc.inhm.Programmer;

import jakarta.persistence.Persistence;

public class InheritanceMappingTest {
	
	@Test
	void test() {
		try(var emf = Persistence.createEntityManagerFactory("inhm");
				var em = emf.createEntityManager()) {
			
			var tx = em.getTransaction();
			
			tx.begin();
			
			var prog = new Programmer();
			prog.setName("Ko Min Lwin");
			prog.setAssignDate(LocalDate.of(2024, 1, 1));
			prog.setFeesPerProject(new BigDecimal(50_000_000));
			
			var dailyWorker = new DailyWorker();
			dailyWorker.setName("Ko Mg Myint");
			dailyWorker.setAssignDate(LocalDate.of(2023, 10, 20));
			dailyWorker.setDailyFee(new BigDecimal(40_000));
			
			var officeStaff = new OfficeStaff();
			officeStaff.setName("Isacc Home");
			officeStaff.setAssignDate(LocalDate.of(2024, 4, 4));
			officeStaff.setSalary(new BigDecimal(2_000_000));
			
			em.persist(prog);
			em.persist(dailyWorker);
			em.persist(officeStaff);
			
			tx.commit();
			
		}
	}

}







