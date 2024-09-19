package com.jdc.em;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Deparment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;

}
