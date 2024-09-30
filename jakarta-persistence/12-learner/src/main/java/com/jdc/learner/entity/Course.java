package com.jdc.learner.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity @Table(name = "course")
public class Course implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false, precision = 9, scale = 2)
	private BigDecimal fee;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Level level;
	
	@Column(nullable = false, name = "start_date")
	private LocalDate startDate;
	
	@OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Module> modules = new ArrayList<>();
	
	public void addModule(Module module) {
		this.modules.add(module);
		module.setCourse(this);
	}

}
