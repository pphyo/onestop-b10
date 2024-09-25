package com.jdc.rm.test;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

import com.jdc.rm.entity.Address;
import com.jdc.rm.entity.Customer;

import jakarta.persistence.Persistence;

public class ToOneTest {
	
	@Test
	void test() {
		try(var emf = Persistence.createEntityManagerFactory("rm");
			var em = emf.createEntityManager()) {
			
//			 insert => persist
//			 update => merge
//			 delete => remove
//			 select => find
			
			var customer = new Customer();
			customer.setName("Thidar Htwe");
			customer.setDob(LocalDate.of(1999, Month.JANUARY, 12));
			
			var address = new Address();
			address.setCustomer(customer);
			address.setTownship("Nant Thar Myaing");
			address.setState("Pegu");
			
			
			var tx = em.getTransaction();			
			tx.begin();

			em.persist(customer);
			em.persist(address);
			
			tx.commit();
			
		}
	}

}











