package com.jdc.hello;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Person {

	private int id;
	private String name;
	private int age;
	@Id
	private String phone;

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
