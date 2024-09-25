package com.jdc.rm.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int addressId;

	@Column(nullable = false)
	private String township;

	@Column(nullable = false, unique = true)
	private String state;
	
	@ManyToOne(optional = false)
	@JoinTable
	private Customer customer;

//	@MapsId
//	@OneToOne(optional = false)
//	@JoinColumn(name = "customer_id")
//	private Customer customer;
	
}

//@JoinTable(
//joinColumns = @JoinColumn(name = "addr_id"),
//inverseJoinColumns = @JoinColumn(name = "cust_id")
////foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT) // Not effect
//)
