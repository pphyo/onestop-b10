package com.jdc.learner.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class RegistrationPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "course_id")
	private long courseId;
	@Column(name = "learner_id")
	private long learnerId;
	@Column(name = "registration_date")
	private LocalDateTime registrationDate;

}
