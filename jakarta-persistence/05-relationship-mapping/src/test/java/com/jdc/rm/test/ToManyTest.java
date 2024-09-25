package com.jdc.rm.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.jdc.rm.entity.Category;
import com.jdc.rm.entity.Product;

import jakarta.persistence.Persistence;

public class ToManyTest {
	
	@Test
	void test() {
		try(var emf = Persistence.createEntityManagerFactory("rm");
				var em = emf.createEntityManager()) {
			
			var tx = em.getTransaction();
			tx.begin();
			
			Category drinks = new Category();
			drinks.setName("Drinks");
			
			Category foods = new Category();
			foods.setName("Foods");
			
			Product pChips = new Product();
			pChips.setName("Potato Chip");
			pChips.setPrice(new BigDecimal(1500d));
			pChips.setStock(30);
			
			Product sfSeeds = new Product();
			sfSeeds.setName("Sunflower Seed");
			sfSeeds.setPrice(new BigDecimal(2000d));
			sfSeeds.setStock(50);
			
			Product vodka = new Product();
			vodka.setName("Vodka");
			vodka.setPrice(new BigDecimal(100_000d));
			vodka.setStock(5);
			
			Product jd = new Product();
			jd.setName("Jack Daniel");
			jd.setPrice(new BigDecimal(60_000));
			jd.setStock(10);
			
			drinks.setProducts(List.of(vodka, jd));
			foods.setProducts(List.of(pChips, sfSeeds));
			
			em.persist(drinks);
			em.persist(foods);
			
//			em.persist(vodka);
//			em.persist(jd);
//			em.persist(pChips);
//			em.persist(sfSeeds);
			
			tx.commit();
			
		}
	}

}









