package com.jdc.im;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "person_tbl")
@SequenceGenerator(name = "PERSON_SEQ", initialValue = 50, allocationSize = 10)
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ")
	private int id;

	@Column(nullable = false, length = 100)
	private String name;
	private LocalDate dob;

	@AttributeOverride(column = @Column(name = "primary_township"), name = "township")
	@AttributeOverride(column = @Column(name = "primary_state"), name = "state")
	private Address primaryAddress;

	@AttributeOverride(column = @Column(name = "secondary_township"), name = "township")
	@AttributeOverride(column = @Column(name = "secondary_state"), name = "state")
	private Address secondaryAddress;
}
