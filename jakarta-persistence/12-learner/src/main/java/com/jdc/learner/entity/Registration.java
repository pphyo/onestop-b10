package com.jdc.learner.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity @Table(name = "registration")
public class Registration implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private RegistrationPK id;
	
	@Column(name = "registration_fee", nullable = false, precision = 9, scale = 2)
	private BigDecimal registrationFee;
	
	@ManyToOne
	@MapsId("courseId")
	private Course course;

	@ManyToOne
	@MapsId("learnerId")
	private Learner learner;

}
