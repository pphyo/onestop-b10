package com.jdc.iami.test;

import org.junit.jupiter.api.Test;

import jakarta.persistence.Persistence;

public class IdAndMapsIdTest {
	
	@Test
	void test() {
		try(var emf = Persistence.createEntityManagerFactory("iami")) {}
	}

}
