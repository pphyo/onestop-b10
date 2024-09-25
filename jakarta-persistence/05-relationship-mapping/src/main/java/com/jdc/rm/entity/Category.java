package com.jdc.rm.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity @Table(name = "CATEGORY")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category_id")
//	@JoinTable(
//			joinColumns = @JoinColumn(name = "category_id"),
//			inverseJoinColumns = @JoinColumn(name = "product_id")
//	)
	private List<Product> products;

}
