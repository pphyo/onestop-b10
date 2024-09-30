package com.jdc.iami.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class DependentId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private EmployeeId empPK;
	
}
