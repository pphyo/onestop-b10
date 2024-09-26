package com.jdc.rm.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(nullable = false)
	private String township;

	@Column(nullable = false, unique = true)
	private String state;
	
//	@ManyToOne(optional = false)
//	@JoinTable
//	private Customer customer;

	@MapsId
	@OneToOne(optional = false)
	@PrimaryKeyJoinColumn(name = "id")
	private Customer customer;
	
}

//@JoinTable(
//joinColumns = @JoinColumn(name = "addr_id"),
//inverseJoinColumns = @JoinColumn(name = "cust_id")
////foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT) // Not effect
//)
