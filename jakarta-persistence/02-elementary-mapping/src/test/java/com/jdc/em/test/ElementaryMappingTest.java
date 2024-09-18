package com.jdc.em.test;

import org.junit.jupiter.api.Test;

import jakarta.persistence.Persistence;

public class ElementaryMappingTest {
	
	@Test
	void test() {
		try(var emf = Persistence.createEntityManagerFactory("em")) {}
	}

}
