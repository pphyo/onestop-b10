package com.jdc.inhm;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@DiscriminatorValue("1")
public class OfficeStaff extends Employee {

	private static final long serialVersionUID = 1L;
	
	@Column(precision = 9, scale = 2)
	private BigDecimal salary;

}
