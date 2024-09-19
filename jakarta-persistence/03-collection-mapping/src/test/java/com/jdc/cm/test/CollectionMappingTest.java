package com.jdc.cm.test;

import org.junit.jupiter.api.Test;

import jakarta.persistence.Persistence;

public class CollectionMappingTest {

	@Test
	void test() {
		Persistence.createEntityManagerFactory("cm").close();
	}
	
}
