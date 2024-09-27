package com.jdc.em.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.em.entity.Course;

import jakarta.persistence.EntityNotFoundException;

@TestMethodOrder(OrderAnnotation.class)
public class FetchModeTest extends BaseTest {
	
	@Order(1)
	@ParameterizedTest
	@ValueSource(ints = 5)
	@DisplayName("Test for find")
	void test1(int id) {
		var course = em.find(Course.class, id);
		assertNull(course);
		assertFalse(em.contains(course));
		
//		em.detach(course);
//		
//		assertAll(() -> {
//			assertEquals(id, course.getId());
//			assertEquals("Java SE", course.getName());
////			assertEquals(new BigDecimal(400_000), course.getFees());
//			assertEquals(LocalDate.of(2024, 8, 4), course.getStartDate());
//			assertEquals(4, course.getDuration());
//			assertEquals(Level.ENTRY, course.getLevel());
//		});
	}
	
	@Order(2)
	@ParameterizedTest
	@ValueSource(ints = 5)
	@DisplayName("Test for getReference")
	void test2(int id) {
		var course = em.getReference(Course.class, id);
		assertEquals(id, course.getId());
		assertThrows(EntityNotFoundException.class, () -> course.getName());
		
//		var course = em.getReference(Course.class, id);
//		assertNotNull(course);
//		assertTrue(em.contains(course));
		
//		em.detach(course);
//		
//		assertAll(() -> {
//			assertEquals(id, course.getId());
//			assertThrows(LazyInitializationException.class, () -> course.getName());
////			assertEquals(new BigDecimal(400000.00), course.getFees());
////			assertEquals(LocalDate.of(2024, 8, 4), course.getStartDate());
////			assertEquals(4, course.getDuration());
////			assertEquals(Level.ENTRY, course.getLevel());
//		});
	}
	

}









