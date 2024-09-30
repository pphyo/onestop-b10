package com.jdc.leaner.test;

import org.junit.jupiter.api.Test;

import jakarta.persistence.Persistence;

public class LearnerTest {
	
	@Test
	void test() {
		try(var emf = Persistence.createEntityManagerFactory("learner")) {}
	}

}
