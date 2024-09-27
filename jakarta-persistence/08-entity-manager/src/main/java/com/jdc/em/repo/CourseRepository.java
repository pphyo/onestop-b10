package com.jdc.em.repo;

import jakarta.persistence.EntityManager;

@SuppressWarnings("unused")
public class CourseRepository {
	
	private EntityManager em;
	private static CourseRepository INSTANCE;
	
	private CourseRepository(EntityManager em) {
		this.em = em;
	}
	
	public static CourseRepository getInstance(EntityManager em) {
		if(null == INSTANCE)
			INSTANCE = new CourseRepository(em);
		return INSTANCE;
	}

}
