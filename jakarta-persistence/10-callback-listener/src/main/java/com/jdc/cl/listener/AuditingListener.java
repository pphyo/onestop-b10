package com.jdc.cl.listener;

import java.time.LocalDateTime;

import com.jdc.cl.entity.AuditingEntity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class AuditingListener {
	
	@PrePersist
	void doBeforeSave(Object obj) {
		System.out.println("Pre persist of %s is called.".formatted(obj.getClass().getSimpleName()));
		if(obj instanceof AuditingEntity entity)
			entity.setCreatedAt(LocalDateTime.now());
	}
	
	@PreUpdate
	void doBeforeUpdate(Object obj) {
		System.out.println("Pre update of %s is called.".formatted(obj.getClass().getSimpleName()));
		if(obj instanceof AuditingEntity entity)
			entity.setCreatedAt(LocalDateTime.now());
	}

}
