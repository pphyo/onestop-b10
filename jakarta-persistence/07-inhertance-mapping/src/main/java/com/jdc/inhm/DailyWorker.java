package com.jdc.inhm;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@DiscriminatorValue("D_WORK")
public class DailyWorker extends Employee {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "daily_fee", precision = 8, scale = 2)
	private BigDecimal dailyFee;

}
