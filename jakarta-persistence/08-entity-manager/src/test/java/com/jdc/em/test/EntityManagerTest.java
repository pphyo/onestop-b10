package com.jdc.em.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.em.entity.Course;
import com.jdc.em.entity.Course.Level;
import com.jdc.em.entity.Outline;

@TestMethodOrder(OrderAnnotation.class)
public class EntityManagerTest extends BaseTest {
	
	@Test
	@Order(2)
	void test2() {
		var foundedCourse = em.find(Course.class, 1);
		assertTrue(em.contains(foundedCourse));
		
		em.getTransaction().begin();
		
		List<Outline> outlines = foundedCourse.getOutlines();
		if(outlines.size() >= 4)
			outlines.remove(3);
		
		em.getTransaction().commit();
		
//		em.getTransaction().begin();
//		em.remove(foundedCourse);
//		em.getTransaction().commit();
	}

	@Test
	@Order(1)
	void test1() {
		// transient or new state
		var course = new Course();
		course.setName("Angular Framework");
		course.setFees(new BigDecimal(450_000d));
		course.setLevel(Level.INTERMEDIATE);
		course.setDuration(4);
		course.setStartDate(LocalDate.of(2024, 07, 02));
		
		var intro = new Outline("Angular Introduction");
		// bi drectional mapping with setting
		course.addOutline(intro);
		
		var comp = new Outline("About Component");
		course.addOutline(comp);
		
		var db = new Outline("Databinding");
		course.addOutline(db);
		
		var form = new Outline("Angular Forms");
		course.addOutline(form);
		
		var router = new Outline("Router");
		course.addOutline(router);
		
		var di = new Outline("Dependency Injection");
		course.addOutline(di);
		
		var signal = new Outline("Angular Signal");
		course.addOutline(signal);
		
		em.getTransaction().begin();
		
		em.persist(course);
		
		em.getTransaction().commit();
		
//		em.getTransaction().begin();
//
//		assertFalse(em.contains(course));
//		em.persist(course);
//		assertTrue(em.contains(course));
//
//		course.setFees(new BigDecimal(500_000));
//
//		em.detach(course);
//		assertFalse(em.contains(course));
//
//		course.setDuration(5);
//
//		var mergedCourse = em.merge(course);
//		assertTrue(em.contains(mergedCourse));
//
//		em.getTransaction().commit();

	}

}
