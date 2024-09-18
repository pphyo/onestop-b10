package com.jdc.hello.test;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HelloJpaTest {
	
	@Test
	void test() {
		try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("01-hello-jpa")) {
			
		}
	}

}
