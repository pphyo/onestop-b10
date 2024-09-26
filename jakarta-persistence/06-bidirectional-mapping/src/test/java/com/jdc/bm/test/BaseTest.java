package com.jdc.bm.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class BaseTest {
	
	static EntityManagerFactory emf;
	EntityManager em;
	
	@BeforeAll
	static void setUpBeforeClass() {
		emf = Persistence.createEntityManagerFactory("bm");
	}
	
	@BeforeEach
	void setUp() {
		em = emf.createEntityManager();
	}
	
	@AfterEach
	void tearDown() {
		if(em != null && em.isOpen()) {
			em.close();
		}
	}
	
	@AfterAll
	static void tearDownAfterClass() {
		if(emf != null && emf.isOpen()) {
			emf.close();
		}
	}

}
