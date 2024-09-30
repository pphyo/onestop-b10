package com.jdc.cl.test;

import java.util.Objects;

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
		emf = Persistence.createEntityManagerFactory("cl");
	}

	@BeforeEach
	void setUp() {
		em = emf.createEntityManager();
	}

	@AfterEach
	void tearDown() {
		if (Objects.nonNull(em) && em.isOpen())
			em.close();
	}

	@AfterAll
	static void tearDownAfterClass() {
		if (Objects.nonNull(emf) && emf.isOpen())
			emf.close();
	}

}
