package com.jdc.app.domain;

import java.time.LocalDate;

public class Employee {

	public enum Gender {
		Male, Female, Other
	}
	
	private int id;
	private String name;
	private Gender gender;
	private double salary;
	private String phone;
	private LocalDate assignAt;
	private Department department;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getAssignAt() {
		return assignAt;
	}

	public void setAssignAt(LocalDate assignAt) {
		this.assignAt = assignAt;
	}

}
