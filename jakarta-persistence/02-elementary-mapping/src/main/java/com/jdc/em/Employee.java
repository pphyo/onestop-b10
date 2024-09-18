package com.jdc.em;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private byte byteCol;
	private short shortCol;
	private int intCol;
	private long longCol;
	private char charCol;
	private boolean boolCol;
	private float floatCol;
	private double doubleCol;
	private BigInteger bigIntgerCol;
	private BigDecimal bigDecimalCol;
	
	private String[] arrayCol;
	
	private String stringCol;

}















