package com.jdc.iami.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Data
@Entity
@IdClass(EmployeeId.class)
public class Employee {
	
	@Id
	private String firstName;
	@Id
	private String lastName;

}
