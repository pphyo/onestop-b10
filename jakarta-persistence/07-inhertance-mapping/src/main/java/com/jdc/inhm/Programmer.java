package com.jdc.inhm;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Setter;

@Setter
@Entity
@DiscriminatorValue("0")
public class Programmer extends Employee {

	private static final long serialVersionUID = 1L;

	@Column(name = "fees_per_project", precision = 12, scale = 2)
	private BigDecimal feesPerProject;
	
	public BigDecimal getFeesPerProject() {
		return feesPerProject;	
	}
	
}
