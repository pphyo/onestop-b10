package com.jdc.im.test;

import org.junit.jupiter.api.Test;

import jakarta.persistence.Persistence;

public class IdMappingHSQLTest {

	@Test
	void test() {
		Persistence.createEntityManagerFactory("im-hsql").close();
	}
}
