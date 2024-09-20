package com.jdc.im.test;

import org.junit.jupiter.api.Test;

import com.jdc.im.Person;
import com.jdc.im.Sale;

import jakarta.persistence.Persistence;

public class IdMappingMySQLTest {
	
	@Test
	void test() {
		try(var emf = Persistence.createEntityManagerFactory("im-mysql");
				var em = emf.createEntityManager()) {
			
			em.getTransaction().begin();
			
			var tinWinKyine = new Person();
			tinWinKyine.setName("Tin Win Kyine");
			
			var hlaNwe = new Person();
			hlaNwe.setName("Hla Nwe");
						
			var sale = new Sale();
			sale.setTaxInPercent(1.3f);
			sale.setTotal(300000);
			
			em.persist(hlaNwe);
			em.persist(tinWinKyine);
			em.persist(sale);
			
			em.getTransaction().commit();
			
		}
	}

}










